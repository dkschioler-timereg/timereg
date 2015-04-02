@echo off
set CONFIG_DIR=
set DEPENDENCY_HOME=
set LOCAL_BASE_DIR=
set CLASSPATH=

IF DEFINED BASE_DIR (
	set LOCAL_BASE_DIR=%BASE_DIR%
    set DEPENDENCY_HOME=%LOCAL_BASE_DIR%/Lib
    set CONFIG_DIR=%LOCAL_BASE_DIR%/config		
) else (
	rem @echo "basedir not set using empty"
	set DEPENDENCY_HOME=Lib
	set CONFIG_DIR=config
)

set CLASSPATH=%CONFIG_DIR%
set ARTIFACT_ID=${project.artifactId}
set CLASSPATH=%CLASSPATH%;Lib/%ARTIFACT_ID%-${project.version}.jar
rem $CLASSPATH will be replaced by plugin.
set CLASSPATH=%CLASSPATH%;Lib/spring-retry-1.1.1.RELEASE.jar
set CLASSPATH=%CLASSPATH%;Lib/junit-4.11.jar
set CLASSPATH=%CLASSPATH%;Lib/log4j-1.2.17.jar
set CLASSPATH=%CLASSPATH%;Lib/spring-beans-4.0.7.RELEASE.jar
set CLASSPATH=%CLASSPATH%;Lib/spring-integration-file-4.0.4.RELEASE.jar
set CLASSPATH=%CLASSPATH%;Lib/spring-core-4.0.7.RELEASE.jar
set CLASSPATH=%CLASSPATH%;Lib/commons-io-2.4.jar
set CLASSPATH=%CLASSPATH%;Lib/slf4j-log4j12-1.7.7.jar
set CLASSPATH=%CLASSPATH%;Lib/spring-context-4.0.7.RELEASE.jar
set CLASSPATH=%CLASSPATH%;Lib/commons-logging-1.1.3.jar
set CLASSPATH=%CLASSPATH%;Lib/joda-time-2.5.jar
set CLASSPATH=%CLASSPATH%;Lib/spring-expression-4.0.7.RELEASE.jar
set CLASSPATH=%CLASSPATH%;Lib/commons-lang3-3.3.2.jar
set CLASSPATH=%CLASSPATH%;Lib/hamcrest-core-1.3.jar
set CLASSPATH=%CLASSPATH%;Lib/spring-aop-4.0.7.RELEASE.jar
set CLASSPATH=%CLASSPATH%;Lib/spring-messaging-4.0.7.RELEASE.jar
set CLASSPATH=%CLASSPATH%;Lib/aopalliance-1.0.jar
set CLASSPATH=%CLASSPATH%;Lib/jaxb2-basics-runtime-0.7.0.jar
set CLASSPATH=%CLASSPATH%;Lib/spring-tx-4.0.7.RELEASE.jar
set CLASSPATH=%CLASSPATH%;Lib/jline-2.12.jar
set CLASSPATH=%CLASSPATH%;Lib/slf4j-api-1.7.7.jar
set CLASSPATH=%CLASSPATH%;Lib/spring-integration-core-4.0.4.RELEASE.jar


set CLASSPATH=%CONFIG_DIR%;%CLASSPATH%
	
