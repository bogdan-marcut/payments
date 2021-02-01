@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  ratpack-payments startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and RATPACK_PAYMENTS_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%/app;%APP_HOME%\lib\ratpack-payments.jar;%APP_HOME%\lib\ratpack-hikari-1.8.2.jar;%APP_HOME%\lib\ratpack-guice-1.8.2.jar;%APP_HOME%\lib\vavr-jackson-0.9.0.jar;%APP_HOME%\lib\vavr-0.9.0.jar;%APP_HOME%\lib\jooq-meta-3.14.6.jar;%APP_HOME%\lib\jooq-3.14.6.jar;%APP_HOME%\lib\mysql-connector-java-8.0.23.jar;%APP_HOME%\lib\ratpack-core-1.8.2.jar;%APP_HOME%\lib\slf4j-log4j12-1.7.28.jar;%APP_HOME%\lib\guice-4.1.0.jar;%APP_HOME%\lib\guice-multibindings-4.1.0.jar;%APP_HOME%\lib\HikariCP-2.7.8.jar;%APP_HOME%\lib\vavr-match-0.9.0.jar;%APP_HOME%\lib\jackson-datatype-guava-2.10.3.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.10.3.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.10.3.jar;%APP_HOME%\lib\jackson-databind-2.10.3.jar;%APP_HOME%\lib\ratpack-exec-1.8.2.jar;%APP_HOME%\lib\reactive-streams-1.0.2.jar;%APP_HOME%\lib\jaxb-api-2.3.1.jar;%APP_HOME%\lib\protobuf-java-3.11.4.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.48.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.48.Final.jar;%APP_HOME%\lib\netty-handler-4.1.48.Final.jar;%APP_HOME%\lib\javax.activation-1.2.0.jar;%APP_HOME%\lib\caffeine-2.8.8.jar;%APP_HOME%\lib\javassist-3.22.0-GA.jar;%APP_HOME%\lib\jackson-dataformat-yaml-2.10.3.jar;%APP_HOME%\lib\snakeyaml-1.27.jar;%APP_HOME%\lib\ratpack-base-1.8.2.jar;%APP_HOME%\lib\slf4j-api-1.7.30.jar;%APP_HOME%\lib\log4j-1.2.17.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\javax.activation-api-1.2.0.jar;%APP_HOME%\lib\jackson-annotations-2.10.3.jar;%APP_HOME%\lib\jackson-core-2.10.3.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.48.Final-linux-x86_64.jar;%APP_HOME%\lib\netty-codec-socks-4.1.48.Final.jar;%APP_HOME%\lib\netty-codec-4.1.48.Final.jar;%APP_HOME%\lib\netty-transport-native-unix-common-4.1.48.Final.jar;%APP_HOME%\lib\netty-transport-4.1.48.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.48.Final.jar;%APP_HOME%\lib\netty-tcnative-2.0.30.Final-linux-x86_64.jar;%APP_HOME%\lib\netty-resolver-4.1.48.Final.jar;%APP_HOME%\lib\netty-common-4.1.48.Final.jar;%APP_HOME%\lib\checker-qual-3.8.0.jar;%APP_HOME%\lib\guava-28.2-jre.jar;%APP_HOME%\lib\error_prone_annotations-2.4.0.jar;%APP_HOME%\lib\failureaccess-1.0.1.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\j2objc-annotations-1.3.jar
cd "%APP_HOME%/app"


@rem Execute ratpack-payments
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %RATPACK_PAYMENTS_OPTS%  -classpath "%CLASSPATH%" com.bogdan.Application %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable RATPACK_PAYMENTS_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%RATPACK_PAYMENTS_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
