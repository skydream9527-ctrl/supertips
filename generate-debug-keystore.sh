#!/bin/bash

# 生成Debug签名密钥的脚本

KEYSTORE_PATH="app/debug.keystore"
STOREPASS="android"
KEYPASS="android"
ALIAS="androiddebugkey"

echo "正在生成Android Debug签名密钥..."

if [ -f "$KEYSTORE_PATH" ]; then
    echo "警告: $KEYSTORE_PATH 已存在"
    read -p "是否覆盖? (y/n): " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        echo "操作取消"
        exit 1
    fi
    rm -f "$KEYSTORE_PATH"
fi

keytool -genkey -v \
    -keystore "$KEYSTORE_PATH" \
    -alias "$ALIAS" \
    -storepass "$STOREPASS" \
    -keypass "$KEYPASS" \
    -keyalg RSA \
    -keysize 2048 \
    -validity 10000 \
    -dname "CN=Android Debug,O=Android,C=US"

if [ $? -eq 0 ]; then
    echo "✅ Debug签名密钥生成成功: $KEYSTORE_PATH"
    echo ""
    echo "签名信息:"
    echo "  密钥库文件: $KEYSTORE_PATH"
    echo "  密钥别名: $ALIAS"
    echo "  密钥库密码: $STOREPASS"
    echo "  密钥密码: $KEYPASS"
    echo ""
    echo "⚠️  警告: 此密钥仅用于调试目的，请勿用于生产环境！"
else
    echo "❌ Debug签名密钥生成失败"
    exit 1
fi