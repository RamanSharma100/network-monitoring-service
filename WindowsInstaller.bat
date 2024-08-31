@echo off
SETLOCAL

SET JAR_FILE=network-monitoring-tool-1.0-SNAPSHOT.jar
SET INSTALL_DIR=%ProgramFiles%\NetworkMonitoring

IF NOT DEFINED JAVA_HOME (
    echo Java is not installed. Please install Java and try again.
    exit /b 1
)

mkdir "%INSTALL_DIR%"
copy "target\%JAR_FILE%" "%INSTALL_DIR%"

IF NOT EXIST "%ProgramFiles%\nssm\nssm.exe" (
    echo NSSM is not installed. Downloading NSSM...
    powershell -command "Invoke-WebRequest -Uri https://nssm.cc/release/nssm-2.24.zip -OutFile nssm.zip"
    powershell -command "Expand-Archive nssm.zip -DestinationPath %ProgramFiles%"
)

"%ProgramFiles%\nssm\nssm.exe" install "NetworkMonitoring" "%JAVA_HOME%\bin\java.exe" "-jar" "%INSTALL_DIR%\%JAR_FILE%"

"%ProgramFiles%\nssm\nssm.exe" start "NetworkMonitoring"

echo Installation completed. Network monitoring service is running.
