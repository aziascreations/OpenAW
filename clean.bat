@echo off

REM Add the output file from compile.bat
REM Use a "conf.bat" for that.

echo == Cleaning Project ==

if exist bin\ (
  rmdir "bin\" /S /Q
  mkdir bin
  echo ^> Cleaned bin
) else if exist bin (
  del bin
  echo ^> Deleted bin
)

if exist build\ (
  rmdir "build\" /S /Q
  mkdir build
  echo ^> Cleaned build
) else if exist build (
  del build
  echo ^> Deleted build
)

echo - Done

pause
