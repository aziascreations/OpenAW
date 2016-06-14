@echo off

set outputFile=src_list.txt
set tempFile=temp.txt
set startTime=%TIME%

echo == Compiling Project ==

if exist bin\ (
  echo ^> Cleaning bin
  rmdir "bin\" /S /Q
  mkdir bin
) else if exist bin (
  echo ^> ERROR: There is a file named bin
  pause
  exit
) else (
  echo ^> Creating bin
  mkdir bin
)

echo ^> Listing files

if exist "%outputfile%\" (
  echo ^> ERROR: A folder named %outputfile% already exist
  pause
  exit
) else if exist %outputfile% (
  del %outputfile%
)

dir /s /B *.java > %outputfile%
call jrepl.bat "%cd%\" "" /L /f "%outputfile%" /o "%tempFile%"
del "%outputfile%"
rename "%tempFile%" "%outputfile%"

echo ^> Compiling
javac -d bin -sourcepath src -cp ".;libs/*" @%outputfile%

echo ^> Cleaning leftovers
del "%outputfile%"

set endTime=%TIME%
set /A startTime=(1%startTime:~0,2%-100)*360000 + (1%startTime:~3,2%-100)*6000 + (1%startTime:~6,2%-100)*100 + (1%startTime:~9,2%-100)
set /A endTime=(1%endTime:~0,2%-100)*360000 + (1%endTime:~3,2%-100)*6000 + (1%endTime:~6,2%-100)*100 + (1%endTime:~9,2%-100)
set /A duration=%endTime%-%startTime%
if %endTime% LSS %startTime% set set /A DURATION=%startTime%-%endTime%
set /A durationF=%duration% / 100

echo - Done in %durationF% second(s)

pause
