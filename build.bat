@echo off

if exist build\ (
  echo Cleaning build folder...
  REM del /S /Q "build\*.*"
  REM Temporary workaround.
  rmdir "build\" /S /Q
  mkdir build
) else if exist build (
  echo ERROR: There is already a file named build.
  pause
  exit
) else (
  echo Creating build folder...
  mkdir build
)

cd bin
jar -cvf ../build/game.jar *
pause
