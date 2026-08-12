@echo off
set "MAVEN_LOCAL=%~dp0.mvn\apache-maven-3.9.9"

if not exist "%MAVEN_LOCAL%\bin\mvn.cmd" (
    echo No se encontro Maven dentro de la carpeta .mvn.
    exit /b 1
)

call "%MAVEN_LOCAL%\bin\mvn.cmd" %*
