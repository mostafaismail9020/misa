#!/bin/bash
#This script will stop hybris, copy the updated extensions, update the system, restart hybris

# fail on first error
set -e

function show_man {
	echo "Usage: deploy-v2.sh <deliveryDir> <deployDir> [OPTIONS]"
	echo ""
	echo "Where:"
	echo  "<deliveryDir>  is the directory that contains hybris\temp\hybris\hybrisServer\*.zip files"
	echo  "<deployDir>    is the directory that will contain the target hybris folder and environment"
	echo  "[OPTIONS]      are optional arguments as follow:"
	echo ""
	echo 		"--clusterId=<clusterId>      is the value that will replace the ${CLUSTER_ID} in the local.properties (defaults to 0)"
    echo 		"--additionalAntTargets=<additionalAntTargets> is a list of ant targets that will run after the 'ant customize all' command (defaults to an empty list). If there are multiple targets to run, use the space as separator and put them into quotes, for example -additionalAntTargets:\"initialize junitinit\""
	echo  		"--licenceDir=<licenceDir>    is the path to the licence directory which contains the licences to copy to the config folder, relative to the hybris root. Defaults to \"bin\platform\resources\configtemplates\develop\licence". Example: -licenceDir="config/production-licence\""
	exit 0
}

# delivery dir (first argument of the script)
if [ -z $1 ] || [[ $1 == -* ]]; then
	show_man
	exit 1
else
	DELIVERY_DIR=$1
fi

# deploy dir (second argument of the script)
if [ -z $2 ] || [[ $2 == -* ]]; then
	echo "condition 2"
	show_man
	exit 1
else
	DEPLOY_DIR=$2
fi

# read the options
TEMP=`getopt -l clusterId:,additionalAntTargets:,licenceDir: -- "$@"`

eval set -- "$TEMP"

# Default values
clusterId="0"
additionalAntTargets=""
licenceDir="bin/platform/resources/configtemplates/develop/licence"

# extract options and their arguments into variables.
while true ; do
	case "$1" in

		--clusterId)
            case "$2" in
                "") shift 2 ;;
                *) clusterId=$2 ; shift 2 ;;
            esac ;;
		
        --additionalAntTargets)
            case "$2" in
                "") shift 2 ;;
                *) additionalAntTargets=$2 ; shift 2 ;;
            esac ;;

        --licenceDir)
            case "$2" in
                "") shift 2 ;;
                *) licenceDir=$2 ; shift 2 ;;
            esac ;;
			
		--) shift ; break ;;
        *) echo "Internal error!" ; exit 1 ;;
    esac
done

# display parameters and options
echo "DELIVERY_DIR=$DELIVERY_DIR"
echo "DEPLOY_DIR=$DEPLOY_DIR"
echo "clusterId = $clusterId"
echo "additionalAntTargets = $additionalAntTargets"
echo "licenceDir = $licenceDir"

#Variables
HYBRIS_HOME=$DEPLOY_DIR/hybris
PLATFORM_HOME=$HYBRIS_HOME/bin/platform

# Variables are set - process the deployment

#stop hybris server
if [ -x "$PLATFORM_HOME/hybrisserver.sh" ]; then
       echo "STOP CURRENT HYBRIS INSTANCE"
       pushd .
       cd $PLATFORM_HOME
       pwd
       ./hybrisserver.sh stop
       popd
else
	NEW_INSTALLATION=1
	echo hybrisServer service is not installed
fi

if [ ! -d $DEPLOY_DIR ]; then
	echo Creating the deploy directory $DEPLOY_DIR
	mkdir -p $DEPLOY_DIR
fi

#remove old version
if [ -d "$HYBRIS_HOME/bin" ]; then
	echo "Removing existing $HYBRIS_HOME/bin directory"
	rm -rf $HYBRIS_HOME/bin
fi

#remove old version
if [ -d "$HYBRIS_HOME/config" ]; then
	echo "Removing existing $HYBRIS_HOME/config directory"
	rm -rf $HYBRIS_HOME/config
fi

#check for zips
if [ `find $DELIVERY_DIR -name '*.zip' -type f |wc |awk {'print $1'}` -lt 2 ]; then
        echo "Hybris platform files missing"
        exit 1
fi

#unzip extensions
echo "Unzipping hybrisServer-*.zip"
unzip -qo -d $DEPLOY_DIR $DELIVERY_DIR/hybrisServer-\*.zip

#Update sh file to executables
if [ -d "$PLATFORM_HOME" ]; then
        chmod +x $PLATFORM_HOME/hybrisserver.sh
		find $PLATFORM_HOME -name "catalina.sh" -exec chmod a+x {} \;
		find $PLATFORM_HOME -name "wrapper.sh" -exec chmod a+x {} \;
fi

#Copy the licence if licenDir is a valid directory
if [ ! -d $HYBRIS_HOME/$licenceDir ]; then
	echo $HYBRIS_HOME/$licenceDir is not a valid directory
else
	cp -Rf $HYBRIS_HOME/$licenceDir $HYBRIS_HOME/config/licence
fi

#Replace cluster ID value in local.properties
sed -i 's/${CLUSTER_ID}/'$clusterId'/g' $HYBRIS_HOME/config/local.properties

#rebuild and update
cd $PLATFORM_HOME
. ./setantenv.sh
ant customize all $additionalAntTargets -Dmaven.update.dbdrivers=false

#restart hybris
$PLATFORM_HOME/hybrisserver.sh start
