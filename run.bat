@echo off
java -cp ./*;libs/* com.azias.openaw.Launcher -m awbe
echo Exit Code: %errorlevel%
pause