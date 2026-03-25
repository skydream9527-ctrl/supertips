# 构建说明

## 前置要求

1. **JDK 17或更高版本**
   - 下载地址：https://adoptium.net/

2. **Android SDK**
   - 最低API级别：24 (Android 7.0)
   - 目标API级别：34 (Android 14)
   - 下载地址：https://developer.android.com/studio

## 本地构建步骤

### 1. 生成Gradle Wrapper（首次构建）

如果项目中缺少 `gradle/wrapper/gradle-wrapper.jar`，需要先生成：

```bash
# 方式1: 使用已安装的Gradle
gradle wrapper

# 方式2: 下载Gradle后执行
./gradlew.bat wrapper  # Windows
./gradlew wrapper      # Linux/Mac
```

或者手动下载：
```bash
# 下载gradle-wrapper.jar
curl -L -o gradle/wrapper/gradle-wrapper.jar \
  https://raw.githubusercontent.com/gradle/gradle/master/gradle/wrapper/gradle-wrapper.jar
```

### 2. 生成Debug签名密钥（首次构建）

```bash
# Windows
keytool -genkey -v -keystore app/debug.keystore -alias androiddebugkey -storepass android -keypass android -keyalg RSA -keysize 2048 -validity 10000 -dname "CN=Android Debug,O=Android,C=US"

# Linux/Mac
keytool -genkey -v -keystore app/debug.keystore -alias androiddebugkey -storepass android -keypass android -keyalg RSA -keysize 2048 -validity 10000 -dname "CN=Android Debug,O=Android,C=US"
```

### 3. 构建APK

```bash
# Windows
gradlew.bat assembleDebug

# Linux/Mac
./gradlew assembleDebug
```

构建完成后，APK文件位于：
- Debug版本：`app/build/outputs/apk/debug/app-debug.apk`
- Release版本：`app/build/outputs/apk/release/app-release-unsigned.apk`

## GitHub Actions自动构建

项目已配置GitHub Actions自动构建。推送代码到GitHub后，会自动：

1. 在`main`、`master`或`develop`分支推送时触发构建
2. 自动运行测试
3. 生成Debug APK和Release APK
4. 上传APK为构建产物

### 下载构建产物

1. 进入GitHub仓库的"Actions"标签页
2. 选择对应的构建记录
3. 在"Artifacts"部分下载APK文件

## 项目配置

### 本地配置

创建 `local.properties` 文件（不要提交到Git）：

```properties
sdk.dir=/path/to/Android/sdk
```

### 签名配置

Debug签名已配置在 `app/build.gradle` 中：

```gradle
signingConfigs {
    debug {
        storeFile file('debug.keystore')
        storePassword 'android'
        keyAlias 'androiddebugkey'
        keyPassword 'android'
    }
}
```

如需配置Release签名，在 `app/build.gradle` 中添加：

```gradle
android {
    signingConfigs {
        release {
            storeFile file('release.keystore')
            storePassword 'your_store_password'
            keyAlias 'your_key_alias'
            keyPassword 'your_key_password'
        }
    }
    
    buildTypes {
        release {
            signingConfig signingConfigs.release
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
```

## 常见问题

### 1. Java版本错误

确保使用JDK 17或更高版本：
```bash
java -version
```

### 2. Android SDK未找到

设置环境变量：
```bash
# Windows (PowerShell)
$env:ANDROID_HOME = "C:\Users\YourName\AppData\Local\Android\Sdk"

# Linux/Mac
export ANDROID_HOME=$HOME/Android/Sdk
```

### 3. Gradle构建失败

清理并重新构建：
```bash
./gradlew clean build
```

### 4. 签名密钥错误

重新生成debug.keystore：
```bash
rm app/debug.keystore
keytool -genkey -v -keystore app/debug.keystore -alias androiddebugkey -storepass android -keypass android -keyalg RSA -keysize 2048 -validity 10000 -dname "CN=Android Debug,O=Android,C=US"
```

## 构建产物

- **app-debug.apk**: Debug版本，已签名，可直接安装测试
- **app-release-unsigned.apk**: Release版本，未签名，需要手动签名后才能安装

## 安装APK

### 通过ADB安装

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### 手动安装

将APK传输到Android设备，点击安装即可。