@echo off

cd target

java -cp .;libs/*;awbe-1.0-SNAPSHOT.jar %1

if %errorlevel% neq 0 (
   echo Error code returned: %errorlevel%
   pause
   exit /b %errorlevel%
)

pause
