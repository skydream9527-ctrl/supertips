# TodoWidget - 待办事项小部件应用

一款简洁的Android待办事项应用，支持桌面小部件和滑动操作。

[![Android CI](https://github.com/YOUR_USERNAME/YOUR_REPO/workflows/Android%20CI/badge.svg)](https://github.com/YOUR_USERNAME/YOUR_REPO/actions)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)](https://android-arsenal.com/api?level=24)
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.0-orange.svg)](https://kotlinlang.org)

## 功能特性

- 添加、删除、完成待办事项
- 滑动操作：向右滑动完成，向左滑动删除
- 桌面小部件：在主屏幕显示未完成的待办事项
- 本地SQLite数据库存储
- 实时数据同步

## 技术栈

- Kotlin
- Android SDK 34
- SQLite数据库
- RecyclerView + ItemTouchHelper
- AppWidgetProvider
- ViewModel + LiveData

## 项目结构

```
app/
├── src/main/
│   ├── java/com/example/todowidget/
│   │   ├── MainActivity.kt              # 主界面
│   │   ├── Todo.kt                      # 数据模型
│   │   ├── TodoViewModel.kt             # 视图模型
│   │   ├── TodoDatabaseHelper.kt        # 数据库帮助类
│   │   ├── TodoAdapter.kt               # 列表适配器
│   │   ├── SwipeCallback.kt             # 滑动回调
│   │   ├── TodoWidgetProvider.kt        # 小部件提供者
│   │   └── TodoWidgetService.kt         # 小部件服务
│   ├── res/
│   │   ├── layout/                      # 布局文件
│   │   ├── values/                      # 资源值
│   │   └── xml/                         # XML配置
│   └── AndroidManifest.xml
└── build.gradle
```

## 快速开始

### GitHub自动构建（推荐）

1. 推送代码到GitHub仓库
2. GitHub Actions自动构建APK
3. 在Actions页面下载构建产物

详见：[QUICKSTART.md](QUICKSTART.md)

### 本地构建

```bash
# Linux/Mac
./generate-gradle-wrapper.sh
./generate-debug-keystore.sh
./gradlew assembleDebug

# Windows
generate-gradle-wrapper.bat
generate-debug-keystore.bat
gradlew.bat assembleDebug
```

详细说明：[BUILD.md](BUILD.md)

## 使用方法

### 添加待办事项
点击右下角的浮动按钮，在弹出的对话框中输入待办事项内容。

### 完成待办事项
在列表项上向右滑动，待办事项会被标记为已完成。

### 删除待办事项
在列表项上向左滑动，待办事项会被删除。

### 添加桌面小部件
1. 长按手机主屏幕空白处
2. 选择"小部件"或"Widgets"
3. 找到"待办事项"小部件
4. 拖动到主屏幕上

## 构建项目

### 前置要求
- JDK 17 或更高版本
- Android SDK API 34
- Gradle 8.2

### 构建命令
```bash
# Windows
gradlew.bat assembleDebug

# Linux/Mac
./gradlew assembleDebug
```

### 安装到设备
```bash
# Windows
gradlew.bat installDebug

# Linux/Mac
./gradlew installDebug
```

## 数据库结构

### todos表
| 字段 | 类型 | 说明 |
|------|------|------|
| id | INTEGER | 主键，自增 |
| title | TEXT | 待办事项标题 |
| is_completed | INTEGER | 是否完成（0=未完成，1=已完成） |
| created_at | INTEGER | 创建时间戳 |

## 权限

应用不需要任何特殊权限。

## 贡献

欢迎提交Issue和Pull Request！

## 许可证

MIT License

## 联系方式

如有问题，请在GitHub上提交Issue。