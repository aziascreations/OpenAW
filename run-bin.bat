@echo off
java -cp .;bin;libs/* com.azias.awbe.Launcher

if %errorlevel% neq 0 (
   echo Error code returned: %errorlevel%
   pause
   exit /b %errorlevel%
)

pause