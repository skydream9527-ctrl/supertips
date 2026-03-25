# 贡献指南

感谢你对TodoWidget项目的关注！欢迎任何形式的贡献。

## 如何贡献

### 报告问题

如果你发现了bug或有新功能建议：

1. 在GitHub Issues中搜索是否已有相关问题
2. 如果没有，创建新Issue并详细描述：
   - Bug：描述问题、复现步骤、期望行为
   - 功能建议：描述功能需求和使用场景

### 提交代码

#### 1. Fork仓库

```bash
# 在GitHub上Fork仓库后
git clone https://github.com/YOUR_USERNAME/TodoWidget.git
cd TodoWidget
```

#### 2. 创建分支

```bash
git checkout -b feature/your-feature-name
# 或
git checkout -b fix/your-bug-fix
```

#### 3. 开发环境设置

```bash
# 生成Gradle Wrapper
./generate-gradle-wrapper.sh  # Linux/Mac
generate-gradle-wrapper.bat   # Windows

# 生成Debug签名
./generate-debug-keystore.sh  # Linux/Mac
generate-debug-keystore.bat   # Windows
```

#### 4. 编写代码

- 遵循Kotlin编码规范
- 添加必要的注释
- 保持代码简洁清晰
- 遵循MVVM架构模式

#### 5. 测试

```bash
# 运行单元测试
./gradlew test

# 构建Debug APK
./gradlew assembleDebug

# 安装到设备测试
./gradlew installDebug
```

#### 6. 提交更改

```bash
git add .
git commit -m "描述你的更改"
git push origin feature/your-feature-name
```

#### 7. 创建Pull Request

- 在GitHub上创建Pull Request
- 清晰描述更改内容和原因
- 关联相关的Issue

## 代码规范

### Kotlin代码风格

- 使用4空格缩进
- 遵循[官方Kotlin编码规范](https://kotlinlang.org/docs/coding-conventions.html)
- 类和方法使用有意义的命名

### Git提交信息

- 使用清晰、描述性的提交信息
- 使用现在时态："Add feature"而不是"Added feature"
- 首行简短（50字符以内），详细描述放在第二行

示例：
```
Add swipe-to-delete functionality

Implement ItemTouchHelper for RecyclerView to allow
users to delete items by swiping left.
```

## 项目结构

```
app/src/main/java/com/example/todowidget/
├── MainActivity.kt          # 主界面Activity
├── Todo.kt                  # 数据模型
├── TodoViewModel.kt         # ViewModel
├── TodoDatabaseHelper.kt    # 数据库操作
├── TodoAdapter.kt           # RecyclerView适配器
├── SwipeCallback.kt         # 滑动处理
├── TodoWidgetProvider.kt    # 桌面小部件
└── TodoWidgetService.kt     # 小部件数据服务
```

## 架构设计

- **MVVM架构**: ViewModel + LiveData
- **数据持久化**: SQLite数据库
- **UI组件**: Material Design + RecyclerView
- **桌面小部件**: AppWidgetProvider

## 功能建议

欢迎贡献以下功能：

- [ ] 待办事项分类
- [ ] 提醒和通知
- [ ] 数据导出/导入
- [ ] 云同步功能
- [ ] 深色模式优化
- [ ] Widget多种尺寸
- [ ] 任务优先级
- [ ] 搜索功能

## 行为准则

- 尊重所有贡献者
- 保持专业和友善的交流
- 接受建设性批评

## 许可证

提交代码即表示你同意将代码以MIT许可证开源。

## 联系方式

如有疑问，请在GitHub上创建Issue或通过Pull Request讨论。

感谢你的贡献！