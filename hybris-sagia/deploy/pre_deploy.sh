#!/bin/bash

function handle_error() {
  echo $1 && exit 1
}

if [ "$#" -lt "3" ]
then
  handle_error "pre_deploy.sh stag|prod|dev version_number user ssh_key"
fi

ENVIR=$1
VERSION=$2
USER=$3
SSH_KEY=$4
REMOTEORLOCAL="local"
SUFFIX=""

LOCAL_ARTIFACTS_FOLDER="../hybris/temp/hybris/hybrisServer"

BASE_ARTIFACT_FOLDER=/NFS

REMOTE_ARTIFACTS_FOLDER="$BASE_ARTIFACT_FOLDER/ams_builds/distfiles/${VERSION}"
SHARED_CONFIG_FOLDER="$BASE_ARTIFACT_FOLDER/hybris_config/config_${VERSION}"
BACKUP_FOLDER="$BASE_ARTIFACT_FOLDER/backup"

BASE_DEPLOYMENT_FOLDER=""
DEPLOY_FOLDER="$BASE_DEPLOYMENT_FOLDER"
CONFIG_FOLDER="$BASE_DEPLOYMENT_FOLDER/hybris/config"
HOST_NAME=`hostname`


#Check to which env it will be deployed
if [ "$ENVIR" == "prod" ]
then
  ADMINSERVER=VHSAGHCPAP01
elif [ "$ENVIR" == "stag" ]
  then
    ADMINSERVER=saghcqaps02
  elif [ "$ENVIR" == "dev" ]
    then
      ADMINSERVER=saghcdhws03.hec.sagia.gov.sa
    elif [ "$ENVIR" == "local" ]
      then
        ADMINSERVER=`hostname`
else
 handle_error "Environment should be one of dev stag prod local" 
fi



cat << EOF > deploy.sh
#!/bin/bash
#This script is generated by the predeploy script!!!!!!!!!!!!!!!
#execute this script as hybris

function handle_error() {
  echo \$1 && exit 1
}


if [ -f ${DEPLOY_FOLDER}/hybris/bin/platform/hybrisserver.sh ]
then
  cd ${DEPLOY_FOLDER}/hybris/bin/platform || handle_error "Can't change directory to hybris, aborting"
  ./hybrisserver.sh stop || handle_error "Can't stop hybris, aborting"
fi

if [ \$(hostname) == "${ADMINSERVER}" ]
then  
  [ -d ${BACKUP_FOLDER} ] || mkdir -p ${BACKUP_FOLDER} || handle_error "Can't create backup folder ${BACKUP_FOLDER}, aborting"
  rm -r $BACKUP_FOLDER/* 
  if [ -d ${DEPLOY_FOLDER}/hybris/bin ]
  then 
    mv ${DEPLOY_FOLDER}/hybris/bin ${BACKUP_FOLDER}/ || handle_error "Can't backup to ${BACKUP_FOLDER}, aborting"
  fi
  if [ -d ${DEPLOY_FOLDER}/hybris/config ]
  then
    mv ${DEPLOY_FOLDER}/hybris/config ${BACKUP_FOLDER}/
  fi
  #unzip new config  
  cd $BASE_ARTIFACT_FOLDER
  mkdir -p ${SHARED_CONFIG_FOLDER} || handle_error "The ${SHARED_CONFIG_FOLDER}  already, exists, please clean up and rerun if you rerun after a failed deployment"
  cd  ${SHARED_CONFIG_FOLDER} || handle_error "Could not change directories to ${SHARED_CONFIG_FOLDER}, aborting"
  echo "unzip ${REMOTE_ARTIFACTS_FOLDER}/config-${ENVIR}.zip"
  unzip -qo ${REMOTE_ARTIFACTS_FOLDER}/config-${ENVIR}.zip || handle_error "Could not unzip ${SHARED_CONFIG_FOLDER}, aborting"
  cd ${DEPLOY_FOLDER}/hybris/config
  mkdir licence
  cd licence
  cp -Rf /NFS/licence/* .

else 
  rm -r ${DEPLOY_FOLDER}/hybris/bin || handle_error "Could not remove hybris bin folder, possibly a file ownership issue, aborting" 
  if [ -d ${DEPLOY_FOLDER}/hybris/config ]
  then
    rm -r ${DEPLOY_FOLDER}/hybris/config || handle_error "Could not remove hybris config folder, possibly a file ownership issue, aborting"
  fi
fi

if [ ! "$DEPLOY_FOLDER" ];
then
   cd / || handle_error "Could not change directories to /, aborting"
else
   cd ${DEPLOY_FOLDER} || handle_error "Could not change directories to ${DEPLOY_FOLDER}, aborting"
fi

echo "unzip ${REMOTE_ARTIFACTS_FOLDER}/hybrisServer-AllExtensions.zip"
unzip -q ${REMOTE_ARTIFACTS_FOLDER}/hybrisServer-AllExtensions.zip || handle_error "Could not unzip extensions, aborting"
echo "unzip ${REMOTE_ARTIFACTS_FOLDER}/hybrisServer-Platform.zip"
unzip -q ${REMOTE_ARTIFACTS_FOLDER}/hybrisServer-Platform.zip || handle_error "Could not unzip platform, aborting" 

# set up config on the share as it easier to maintain, use a soft link to point to correct config version
if [ -L ${DEPLOY_FOLDER}/hybris/config ]
then
  rm -r ${DEPLOY_FOLDER}/hybris/config
else
  cd ${DEPLOY_FOLDER}/hybris/  || handle_error "Could not change dirs to {DEPLOY_FOLDER}/hybris/ , aborting" 
  
  cp -r  ${SHARED_CONFIG_FOLDER} ./config/ || handle_error "Could not copy  ${SHARED_CONFIG_FOLDER}, aborting " 

  mkdir  ./config/opt
  cp  ./config/opt_config/nodes/\$(hostname).properties ./config/opt/10-local.properties
  
fi



cd ${DEPLOY_FOLDER}/hybris/bin/platform 
source setantenv.sh || handle_error "Can't set the ant environment, aborting"

ant server || handle_error  "Ant server failed, aborting"

if [ "$ENVIR" == "local" ]
then
  ./hybrisserver.sh debug || handle_error "Hybris failed to start"
else
   
   ant addoninstall -Daddonnames=commerceorgaddon -DaddonStorefront.yacceleratorstorefront=investsaudistorefront
   ant addoninstall -Daddonnames=smarteditaddon -DaddonStorefront.yacceleratorstorefront=investsaudistorefront,sagiastorefront 
   ant addoninstall -Daddonnames=captchaaddon -DaddonStorefront.yacceleratorstorefront=sagiastorefront
   ant addoninstall -Daddonnames=investsaudicaptchaddon -DaddonStorefront.yacceleratorstorefront=investsaudistorefront
   ant addoninstall -Daddonnames=customerticketingaddon -DaddonStorefront.yacceleratorstorefront=investsaudistorefront
   ant addoninstall -Daddonnames=investsaudisecureportal -DaddonStorefront.yacceleratorstorefront=investsaudistorefront 

   chmod 777 /hybris/bin/custom/sagia/sagiastorefront/node_modules/.bin/gulp
   ant sagiastorefront_compileuisrc
  ./hybrisserver.sh start || handle_error "Hybris failed to start"
fi

EOF

if [ "$REMOTEORLOCAL" == "local" ]
then
  cp ./deploy.sh ${REMOTE_ARTIFACTS_FOLDER} || handle_error "Can't copy the generated deploy script" 
  chmod 775 ${REMOTE_ARTIFACTS_FOLDER}/deploy.sh  || handle_error "Can't change permissions on the deploy script" 
else
  scp -i ${SSH_KEY} ./deploy.sh ${USER}@${ADMINSERVER}${SUFFIX}:${REMOTE_ARTIFACTS_FOLDER}/ || handle_error "Can't copy the generated deploy script" 
  ssh -i ${SSH_KEY} ${USER}@${ADMINSERVER}${SUFFIX} "chmod 775 ${REMOTE_ARTIFACTS_FOLDER}/deploy.sh"  || handle_error "Can't change permissions on the deploy script" 
fi

