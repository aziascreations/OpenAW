@echo off
java -cp .;libs/*;build/game.jar com.azias.awbe.launcher.Launcher

if %errorlevel% neq 0 (
   echo Error code returned: %errorlevel%
   pause
   exit /b %errorlevel%
)

pause
