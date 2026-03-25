# 快速开始指南

## 选项1: 使用GitHub Actions（推荐）

### 步骤：

1. **创建GitHub仓库**
   ```bash
   git init
   git add .
   git commit -m "Initial commit: TodoWidget Android app"
   git branch -M main
   git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git
   git push -u origin main
   ```

2. **触发自动构建**
   - GitHub Actions会自动运行
   - 进入仓库的"Actions"标签页查看构建进度

3. **下载APK**
   - 构建完成后，在Actions页面下载Artifacts
   - 包含Debug和Release两个版本的APK

## 选项2: 本地构建

### 前置要求：
- JDK 17+
- Android SDK (API 24-34)

### Windows:

```cmd
REM 1. 生成Gradle Wrapper
generate-gradle-wrapper.bat

REM 2. 生成Debug签名
generate-debug-keystore.bat

REM 3. 构建APK
gradlew.bat assembleDebug
```

### Linux/Mac:

```bash
# 1. 生成Gradle Wrapper
chmod +x generate-gradle-wrapper.sh
./generate-gradle-wrapper.sh

# 2. 生成Debug签名
chmod +x generate-debug-keystore.sh
./generate-debug-keystore.sh

# 3. 构建APK
./gradlew assembleDebug
```

## 选项3: 使用Android Studio

1. 打开Android Studio
2. 选择"Open an existing Android Studio project"
3. 选择项目目录
4. 等待Gradle同步完成
5. 点击"Run"按钮安装到设备

## 验证构建

构建成功后，APK位于：
```
app/build/outputs/apk/debug/app-debug.apk
```

安装测试：
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

## 故障排除

### 问题1: Gradle下载失败
**解决方案**: 使用提供的脚本自动生成wrapper
```bash
./generate-gradle-wrapper.sh  # Linux/Mac
generate-gradle-wrapper.bat   # Windows
```

### 问题2: 找不到Android SDK
**解决方案**: 设置环境变量
```bash
# Linux/Mac
export ANDROID_HOME=$HOME/Android/Sdk

# Windows
set ANDROID_HOME=C:\Users\YourName\AppData\Local\Android\Sdk
```

### 问题3: Java版本错误
**解决方案**: 确保使用JDK 17+
```bash
java -version
```

### 问题4: 签名错误
**解决方案**: 重新生成签名密钥
```bash
./generate-debug-keystore.sh  # Linux/Mac
generate-debug-keystore.bat   # Windows
```

## 下一步

- 添加更多待办事项功能
- 自定义UI主题
- 添加提醒功能
- 实现数据同步