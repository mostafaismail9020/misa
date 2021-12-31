@echo off
setlocal enableDelayedExpansion

:: Define the option names along with default values, using a <space>
:: delimiter between options.
::
:: Each option has the format -name:[default]
::
:: The option names are NOT case sensitive.
::
:: Options that have a default value expect the subsequent command line
:: argument to contain the value. If the option is not provided then the
:: option is set to the default. If the default contains spaces, contains
:: special characters, or starts with a colon, then it should be enclosed
:: within double quotes. The default can be undefined by specifying the
:: default as empty quotes "".
:: NOTE - defaults cannot contain * or ? with this solution.
::
:: Options that are specified without any default value are simply flags
:: that are either defined or undefined. All flags start out undefined by
:: default and become defined if the option is supplied.
::
:: The order of the definitions is not important.
::
set "options=-serviceName:"hybrisServer" -clusterId:"0" -additionalAntTargets:"" -licenceDir:"bin\platform\resources\configtemplates\develop\licence" -flag1:"

:: Set the default option values
for %%O in (%options%) do for /f "tokens=1,* delims=:" %%A in ("%%O") do set "%%A=%%~B"

:loop
:: Validate and store the options, one at a time, using a loop.
:: Options start at arg 3 here, arg1 and arg2 being the deliveryDir and the deployDir. Each SHIFT is done starting at the first option so required args are preserved.
::
if not "%~3"=="" (
  set "test=!options:*%~3:=! "
  if "!test!"=="!options! " (
    rem No substitution was made so this is an invalid option.
    rem Error handling goes here.
    rem I will simply echo an error message.
    echo Error: Invalid option %~3
  ) else if "!test:~0,1!"==" " (
    rem Set the flag option using the option name.
    rem The value doesn't matter, it just needs to be defined.
    set "%~3=1"
  ) else (
    rem Set the option value using the option as the name.
    rem and the next arg as the value
    set "%~3=%~4"
    shift /3
  )
  shift /3
  goto :loop
)

:: Now all supplied options are stored in variables whose names are the
:: option names. Missing options have the default value, or are undefined if
:: there is no default.
:: The required args are still available in %1 and %2 (and %0 is also preserved)
:: To echo all the option values, assuming any variable starting with - is an option:
::
:: set -

set "deliveryDir=%1"
set "deployDir=%2"

if "!deliveryDir!"=="" GOTO man
if "!deployDir!"=="" GOTO man

:: To get the value of a single parameter, just remember to include the `-`
echo    deliveryDir          = !deliveryDir!
echo    deployDir            = !deployDir!
echo    serviceName          = !-serviceName!
echo    clusterId            = !-clusterId!
echo    additionalAntTargets = !-additionalAntTargets!
echo    licenceDir           = !-licenceDir!

SET HYBRIS_HOME=!deployDir!\hybris
SET PLATFORM_HOME=!HYBRIS_HOME!\bin\platform

rem #stop hybris server
IF EXIST !PLATFORM_HOME!\hybrisserver.bat (
	echo Stopping !-serviceName!
	net stop !-serviceName!
	echo hybrisServer is stopped
) ELSE (
	SET NEW_INSTALLATION=1
	echo hybrisServer service is not installed
)

IF NOT EXIST !deployDir! (
	echo Creating the deploy dir: !deployDir!
	mkdir !deployDir!
)

IF EXIST !HYBRIS_HOME!\bin (
	rem #remove old version
	echo Removing existing hybris\bin directory
	rd /S /Q !HYBRIS_HOME!\bin
)
IF EXIST !HYBRIS_HOME!\config (
	echo Removing existing hybris\config directory
	rd /S /Q !HYBRIS_HOME!\config
)

rem #unzip extensions
echo Unzipping hybrisServer-*.zip
7za x -y !deliveryDir!\hybrisServer-AllExtensions.zip -o!deployDir! 1> nul
7za x -y !deliveryDir!\hybrisServer-Config.zip -o!deployDir! 1> nul
7za x -y !deliveryDir!\hybrisServer-Platform.zip -o!deployDir! 1> nul

mkdir !HYBRIS_HOME!\config\licence
echo xcopy  !HYBRIS_HOME!\!-licenceDir!\* !HYBRIS_HOME!\config\licence /sy
xcopy  !HYBRIS_HOME!\!-licenceDir!\* !HYBRIS_HOME!\config\licence /sy

fnr.exe --cl --dir "!HYBRIS_HOME!\config" --fileMask "local.properties" --find "${CLUSTER_ID}" --replace "!-clusterId!"

rem #rebuild and update
cd !PLATFORM_HOME!
echo Running: ant customize all !-additionalAntTargets!
call setantenv.bat
call ant customize all !-additionalAntTargets!
echo Completed: ant customize all !-additionalAntTargets!

IF "!NEW_INSTALLATION!"=="1" (
    echo This is a new installation, you will need to install the Windows service manually by running !PLATFORM_HOME!\tomcat\bin\InstallTomcatService.bat with administrator permissions
) ELSE (
	echo Starting !-serviceName!
	net start !-serviceName!
)

GOTO end

:man

	echo Usage: deploy-v2.bat ^<deliveryDir^> ^<deployDir^> [OPTIONS]
	echo.
	echo  where ^<deliveryDir^>  is the directory that contains hybris\temp\hybris\hybrisServer\*.zip files
	echo    and ^<deployDir^>    is the directory that will contain the target hybris folder and environment
	echo    and [OPTIONS]      are optional arguments as follow:
	echo.
	echo         -serviceName=^<serviceName^>  is the name of the windows service that runs hybris and that will be stopped and started (defaults to 'hybrisServer')
	echo         -clusterId=^<clusterId^>      is the value that will replace the ${CLUSTER_ID} in the local.properties (defaults to 0)
    echo         -additionalAntTargets=^<additionalAntTargets^> is a list of ant targets that will run after the 'ant customize all' command (defaults to an empty list). If there are multiple targets to run, use the space as separator and put them into quotes, for example -additionalAntTargets:"initialize junitinit"
	echo         -licenceDir=^<licenceDir^>    is the path to the licence directory which contains the licences to copy to the config folder, relative to the hybris root. Defaults to "bin\platform\resources\configtemplates\develop\licence". Example: -licenceDir="config/production-licence"
	pause
	exit /B 1

:end
endlocal


