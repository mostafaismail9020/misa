@echo off

setlocal

rem # detect ant argument
SET DELIVERY_DIR=%1
SET DEPLOY_DIR=%2
SET SERVICE_NAME=%3
SET CLUSTER_ID=%4

echo DELIVERY_DIR=%DELIVERY_DIR%
echo DEPLOY_DIR=%DEPLOY_DIR%
echo SERVICE_NAME=%SERVICE_NAME%
echo CLUSTER_ID=%CLUSTER_ID%
if "%CLUSTER_ID%"=="" (
	echo Usage: deploy.bat [DELIVERY_DIR] [DEPLOY_DIR] [SERVICE_NAME] [CLUSTER_ID]
	echo  where [DELIVERY_DIR] is the directory that contains hybris\temp\hybrisServer\*.zip files
	echo    and [DEPLOY_DIR] is the directory that will contain the target hybris folder and environment
	echo    and [SERVICE_NAME] is the name of the windows service that runs hybris and that will be stopped and started
	echo    and [CLUSTER_ID] is the value that will replace the ${CLUSTER_ID} in the local.properties
	pause
	exit /B 1
)

rem #Variables
SET HYBRIS_HOME=%DEPLOY_DIR%\hybris
SET PLATFORM_HOME=%HYBRIS_HOME%\bin\platform

IF EXIST %PLATFORM_HOME% (
	net stop %SERVICE_NAME%
)
echo hybrisServer is stopped

echo Removing existing bin\custom directory
rd /S /Q %HYBRIS_HOME%\bin\custom

echo Unzipping hybrisServer-*.zip
7za x -y %DELIVERY_DIR%\hybrisServer-Config.zip -o%DEPLOY_DIR% > unzip.log
7za x -y %DELIVERY_DIR%\hybrisServer-AllExtensions.zip -o%DEPLOY_DIR% > unzip.log
rem 7za x -y %DELIVERY_DIR%\hybrisServer-Platform.zip -o%DEPLOY_DIR% > unzip.log

IF EXIST %HYBRIS_HOME%\config\production-license\hybrislicence.jar (
	xcopy %HYBRIS_HOME%\config\production-license %HYBRIS_HOME%\config\licence /sy
)
IF NOT EXIST %HYBRIS_HOME%\config\production-license\hybrislicence.jar (
	xcopy  %PLATFORM_HOME%\resources\configtemplates\develop\licence %HYBRIS_HOME%\config /sy
)

fnr.exe --cl --dir "%HYBRIS_HOME%\config" --fileMask "local.properties" --find "${CLUSTER_ID}" --replace "%CLUSTER_ID%"

call %PLATFORM_HOME%\setantenv.bat

echo Rebuild and update
cd %PLATFORM_HOME%
call ant customize all
echo Completed: ant customize all

net start %SERVICE_NAME%

endlocal