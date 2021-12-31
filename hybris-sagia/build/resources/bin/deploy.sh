#!/bin/bash
#This script will stop hybris, copy the updated extenions, update the system, restart hybris

# fail on first error
set -e

function show_man {
        echo "Usage: deploy.bat <DELIVERY_DIR> <DEPLOY_DIR> [ANT_TARGET]"
        echo " where DELIVERY_DIR   is the directory that contains hybris\temp\hybrisServer\*.zip files"
        echo "   and DEPLOY_DIR     is the directory that will contain the target hybris folder and environment"
        echo "   and ANT_TARGET     is the optional ant target that will run after the 'ant customize all' command"
}

# delivery dir (first argument of the script)
if [ ! -z $1 ]; then
        DELIVERY_DIR=$1
else
        show_man
        exit 1
fi
# deploy dir (second argument of the script)
if [ ! -z $2 ]; then
        DEPLOY_DIR=$2
else
        show_man
        exit 1
fi
# detect ant argument
if [ ! -z $3 ]; then
        ANT_TARGET=$3
else
        ANT_TARGET=
fi

echo "DELIVERY_DIR=$DELIVERY_DIR"
echo "DEPLOY_DIR=$DEPLOY_DIR"
echo "ANT_TARGET=$ANT_TARGET"
echo ""

#Variables
HYBRIS_HOME=$DEPLOY_DIR/hybris
PLATFORM_HOME=$HYBRIS_HOME/bin/platform

mkdir -p $DEPLOY_DIR

#stop hybris server
if [ -x "$PLATFORM_HOME/hybrisserver.sh" ]; then
       echo "STOP CURRENT HYBRIS INSTANCE"
       pushd .
       cd $PLATFORM_HOME
       pwd
       ./hybrisserver.sh stop
       popd
fi

#remove old version
echo "Removing existing bin directory"
rm -rf $HYBRIS_HOME/bin

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
        chmod +x $PLATFORM_HOME/tomcat/bin/catalina.sh
        chmod +x $PLATFORM_HOME/tomcat/bin/wrapper.sh
fi

cp -Rf $PLATFORM_HOME/resources/configtemplates/develop/licence $HYBRIS_HOME/config

#rebuild and update
cd $PLATFORM_HOME
. ./setantenv.sh
ant customize all $ANT_TARGET -Dmaven.update.dbdrivers=false

#restart hybris
$PLATFORM_HOME/hybrisserver.sh start
