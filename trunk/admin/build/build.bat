@echo off

if "%OS%" == "Windows_NT" setlocal
rem ---------------------------------------------------------------------------
rem Start script for the cmcpse build
rem
rem $Id: build.bat 895392 2011-11-29 17:45:31Z kevin $
rem ---------------------------------------------------------------------------

rem Guess Project_HOME if not defined
set "CURRENT_DIR=%cd%"
echo %CURRENT_DIR%

if not "%CATALINA_HOME%" == "" goto gotHome
set "BUILD_HOME=%CURRENT_DIR%"
if not "%ANT_HOME%" == "" goto okJavaHome
echo Using ANT_HOME:        "%ANT_HOME%"
echo This ANT_HOME must be set
echo This environment variable is needed to run this program 
goto end
:okJavaHome
echo Using ANT_HOME:        "%ANT_HOME%"

if not "%JAVA_HOME%" == "" goto okBuild
echo Using JAVA_HOME:       "%JAVA_HOME%"
echo This JAVA_HOME must be set
echo This environment variable is needed to run this program 
goto end
:okBuild
echo Using JAVA_HOME:       "%JAVA_HOME%"

if exist "%CURRENT_DIR%\build.xml" goto okHome
echo The CATALINA_HOME environment variable is not defined correctly
echo This environment variable is needed to run this program
goto end
:okHome

set "BUILDCUTABLE=%BUILD_HOME%\build.xml"

rem Check that target BUILDCUTABLE exists
if exist "%BUILDCUTABLE%" goto okBuild
echo Cannot find "%BUILDCUTABLE%"
echo This file is needed to run this program
goto end
:okBuild

rem Get remaining unshifted command line arguments and save them in the
set CMD_LINE_ARGS=package
:setArgs
if ""%1""=="""" goto doneSetArgs
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto setArgs
:doneSetArgs

echo "ant %CMD_LINE_ARGS%"
call ant %CMD_LINE_ARGS%

:end
cmd /k
rem call pause
