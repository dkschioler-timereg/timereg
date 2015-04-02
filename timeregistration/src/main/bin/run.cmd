@echo off
cls
CALL set-env.cmd

set CMD=java  -classpath %CLASSPATH%
set CMD=%CMD% -Doutput.event-file=events/timeregistration-events.log
set CMD=%CMD% -Dconfig.freya-export=/freya-excel-dump.csv
set CMD=%CMD% -Dconfig.user-projects=/userconfigured-projects.xml
set CMD=%CMD% dk.schioler.tools.timeregistration.main.MainInput 

%CMD%



