#!/bin/bash

# 生成Gradle Wrapper的脚本

echo "正在生成Gradle Wrapper..."

# 检查是否已经安装了Gradle
if command -v gradle &> /dev/null; then
    echo "使用已安装的Gradle生成wrapper..."
    gradle wrapper
elif [ -f "./gradlew" ]; then
    echo "使用现有的gradlew生成wrapper..."
    ./gradlew wrapper
else
    echo "下载Gradle Wrapper JAR..."
    
    WRAPPER_DIR="gradle/wrapper"
    WRAPPER_JAR="$WRAPPER_DIR/gradle-wrapper.jar"
    
    mkdir -p "$WRAPPER_DIR"
    
    # 尝试多个镜像源
    MIRRORS=(
        "https://raw.githubusercontent.com/gradle/gradle/v8.2.0/gradle/wrapper/gradle-wrapper.jar"
        "https://services.gradle.org/distributions/gradle-8.2-bin.zip"
        "https://downloads.gradle-dn.com/distributions/gradle-8.2-bin.zip"
    )
    
    DOWNLOADED=false
    for MIRROR in "${MIRRORS[@]}"; do
        echo "尝试从 $MIRROR 下载..."
        if curl -L -o "$WRAPPER_JAR" "$MIRROR" && [ -f "$WRAPPER_JAR" ]; then
            DOWNLOADED=true
            echo "✅ 下载成功"
            break
        fi
    done
    
    if [ "$DOWNLOADED" = false ]; then
        echo "❌ 无法下载gradle-wrapper.jar"
        echo "请手动安装Gradle或使用Android Studio打开项目"
        exit 1
    fi
fi

# 设置执行权限
chmod +x gradlew

echo ""
echo "✅ Gradle Wrapper生成完成"
echo ""
echo "现在可以运行以下命令构建项目:"
echo "  ./gradlew build"
echo "  ./gradlew assembleDebug"