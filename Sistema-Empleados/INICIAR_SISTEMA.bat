@echo off
title Sistema de Empleados
cd /d "%~dp0"
echo.
echo Iniciando el Sistema de Empleados...
echo Cuando aparezca "Started SistemaEmpleadosApplication", abre:
echo http://localhost:8080
echo.
call mvnw.cmd spring-boot:run
pause
