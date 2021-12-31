#! /bin/bash
LINE=$(cat local.properties | grep sagia.staticresource.buildversion)

if [ -z "$LINE" ] 
then
	echo 'sagia.staticresource.buildversion=1' >> local.properties
 
else
	VAL=$(echo $LINE | cut -f 2 -d "=")
	VAL=$(($VAL + 1))
	NEW_LINE=$(echo sagia.staticresource.buildversion=$VAL)
	echo $NEW_LINE
	sed -e "s,$LINE,$NEW_LINE,g" -i local.properties
fi




