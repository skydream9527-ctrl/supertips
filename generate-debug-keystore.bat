@echo off
REM 生成Debug签名密钥的脚本 (Windows)

set KEYSTORE_PATH=app\debug.keystore
set STOREPASS=android
set KEYPASS=android
set ALIAS=androiddebugkey

echo 正在生成Android Debug签名密钥...

if exist "%KEYSTORE_PATH%" (
    echo 警告: %KEYSTORE_PATH% 已存在
    set /p OVERWRITE="是否覆盖? (y/n): "
    if /i not "%OVERWRITE%"=="y" (
        echo 操作取消
        exit /b 1
    )
    del /f "%KEYSTORE_PATH%"
)

keytool -genkey -v ^
    -keystore "%KEYSTORE_PATH%" ^
    -alias "%ALIAS%" ^
    -storepass "%STOREPASS%" ^
    -keypass "%KEYPASS%" ^
    -keyalg RSA ^
    -keysize 2048 ^
    -validity 10000 ^
    -dname "CN=Android Debug,O=Android,C=US"

if %errorlevel% equ 0 (
    echo.
    echo ✅ Debug签名密钥生成成功: %KEYSTORE_PATH%
    echo.
    echo 签名信息:
    echo   密钥库文件: %KEYSTORE_PATH%
    echo   密钥别名: %ALIAS%
    echo   密钥库密码: %STOREPASS%
    echo   密钥密码: %KEYPASS%
    echo.
    echo ⚠️  警告: 此密钥仅用于调试目的，请勿用于生产环境！
) else (
    echo.
    echo ❌ Debug签名密钥生成失败
    exit /b 1
)