@echo off
REM 生成Gradle Wrapper的脚本 (Windows)

echo 正在生成Gradle Wrapper...

REM 检查是否已经安装了Gradle
where gradle >nul 2>&1
if %errorlevel% equ 0 (
    echo 使用已安装的Gradle生成wrapper...
    gradle wrapper
    goto :success
)

if exist "gradlew.bat" (
    echo 使用现有的gradlew生成wrapper...
    call gradlew.bat wrapper
    goto :success
)

echo 下载Gradle Wrapper JAR...

set WRAPPER_DIR=gradle\wrapper
set WRAPPER_JAR=%WRAPPER_DIR%\gradle-wrapper.jar

if not exist "%WRAPPER_DIR%" mkdir "%WRAPPER_DIR%"

REM 尝试多个镜像源
set MIRRORS[0]=https://raw.githubusercontent.com/gradle/gradle/v8.2.0/gradle/wrapper/gradle-wrapper.jar
set MIRRORS[1]=https://services.gradle.org/distributions/gradle-8.2-bin.zip
set MIRRORS[2]=https://downloads.gradle-dn.com/distributions/gradle-8.2-bin.zip

set DOWNLOADED=0
for /L %%i in (0,1,2) do (
    if !DOWNLOADED! equ 0 (
        call set MIRROR=%%MIRRORS[%%i]%%
        echo 尝试从 !MIRROR! 下载...
        curl -L -o "%WRAPPER_JAR%" "!MIRROR!"
        if exist "%WRAPPER_JAR%" (
            set DOWNLOADED=1
            echo ✅ 下载成功
        )
    )
)

if %DOWNLOADED% equ 0 (
    echo ❌ 无法下载gradle-wrapper.jar
    echo 请手动安装Gradle或使用Android Studio打开项目
    exit /b 1
)

:success
echo.
echo ✅ Gradle Wrapper生成完成
echo.
echo 现在可以运行以下命令构建项目:
echo   gradlew.bat build
echo   gradlew.bat assembleDebug