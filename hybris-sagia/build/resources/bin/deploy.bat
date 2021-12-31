@echo off

setlocal

rem # detect ant argument
SET DELIVERY_DIR=%1
SET DEPLOY_DIR=%2
SET SERVICE_NAME=%3
SET ANT_TARGET=%4

echo DELIVERY_DIR=%DELIVERY_DIR%
echo DEPLOY_DIR=%DEPLOY_DIR%
echo SERVICE_NAME=%SERVICE_NAME%
echo ANT_TARGET=%ANT_TARGET%
if "%SERVICE_NAME%"=="" (
	echo Usage: deploy.bat [DELIVERY_DIR] [DEPLOY_DIR] [SERVICE_NAME] [ANT_TARGET]
	echo  where [DELIVERY_DIR] is the directory that contains hybris\temp\hybrisServer\*.zip files
	echo    and [DEPLOY_DIR] is the directory that will contain the target hybris folder and environment
	echo    and [SERVICE_NAME] is the name of the windows service that runs hybris and that will be stopped and started
	echo    and [ANT_TARGET] is the optional ant target that will run after the 'ant customize all' command
	pause
	exit /B 1
)

rem #Variables
SET HYBRIS_SERVER_ZIP_PATH=%DEPLOY_DIR%\hybris\temp\hybrisServer
SET HYBRIS_HOME=%DEPLOY_DIR%\hybris
SET PLATFORM_HOME=%HYBRIS_HOME%\bin\platform

mkdir %DEPLOY_DIR%

rem #stop hybris server
IF EXIST %PLATFORM_HOME% (
	net stop %SERVICE_NAME%
)
echo hybrisServer is stopped

 
rem #remove old version
echo Removing existing bin directory
rd /S /Q %HYBRIS_HOME%\bin

rem #unzip extensions
echo Unzipping hybrisServer-*.zip
7za x -y %DELIVERY_DIR%\hybrisServer-AllExtensions.zip -o%DEPLOY_DIR%
7za x -y %DELIVERY_DIR%\hybrisServer-Config.zip -o%DEPLOY_DIR%
7za x -y %DELIVERY_DIR%\hybrisServer-Platform.zip -o%DEPLOY_DIR%

xcopy  %PLATFORM_HOME%\resources\configtemplates\develop\licence %HYBRIS_HOME%\config /sy

rem #rebuild and update
cd %PLATFORM_HOME%
call setantenv.bat
call ant customize all %ANT_TARGET%
echo Completed: ant customize all %ANT_TARGET%

rem #start hybris server
net start %SERVICE_NAME%

endlocal