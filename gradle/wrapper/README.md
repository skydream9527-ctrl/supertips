# Gradle Wrapper JAR 文件说明

本项目缺少 `gradle-wrapper.jar` 文件，因为该文件较大且可以从官方源自动下载。

## 自动生成（推荐）

GitHub Actions 会自动处理缺失的 wrapper JAR 文件。

## 本地开发

### 方式1: 使用已安装的Gradle

如果你已安装Gradle，运行：
```bash
gradle wrapper
```

### 方式2: 手动下载

从Gradle官方仓库下载：
```bash
# Gradle 8.2
curl -L -o gradle/wrapper/gradle-wrapper.jar \
  https://services.gradle.org/distributions/gradle-8.2-bin.zip

# 或从GitHub镜像下载
curl -L -o gradle/wrapper/gradle-wrapper.jar \
  https://github.com/gradle/gradle/raw/v8.2.0/gradle/wrapper/gradle-wrapper.jar
```

### 方式3: 使用Android Studio

1. 用Android Studio打开项目
2. Android Studio会自动检测并提示下载Gradle Wrapper
3. 点击"OK"自动下载

## 为什么不包含在Git中？

`gradle-wrapper.jar` 是一个二进制文件，通常不提交到版本控制系统，因为：
1. 文件较大（约60KB）
2. 可以从官方源可靠地下载
3. 避免版本控制仓库膨胀

## CI/CD环境

在GitHub Actions等CI环境中，Gradle会自动下载所需的wrapper文件，无需手动配置。