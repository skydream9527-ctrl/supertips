# 更新日志 (Update Logs)

## v1.0.1 (2026-03-26)

### Bug 修复
- 修复 `MainActivity` 中 `registerReceiver()` 在 Android 13+ 上缺少 `RECEIVER_NOT_EXPORTED` 标志导致的 `SecurityException`
- 修复 `TodoAdapter.onBindViewHolder()` 中点击回调使用 `position` 可能导致 `IndexOutOfBoundsException` 的问题
- 添加 `gradle.properties` 配置 AndroidX 支持，修复构建失败问题
- 更新 Gradle Wrapper 脚本到 8.2.0 版本，修复 CI 构建错误
- 添加 `gradle-wrapper.jar` 到仓库，确保 CI 能正常运行

### 改进
- 从 `.gitignore` 移除 `gradle-wrapper.jar` 忽略规则

---

## v1.0.0 (2026-03-25)

### 初始发布

#### 已完成功能
- 添加待办事项：点击浮动按钮输入内容
- 删除待办事项：向左滑动删除
- 完成待办事项：向右滑动标记完成
- 桌面小部件：显示未完成的待办事项列表
- 本地 SQLite 数据库存储
- 实时数据同步：主界面与小部件数据同步
- 滑动动画效果：滑动时显示完成/删除背景

#### 技术特性
- Kotlin 语言开发
- 目标 SDK 34 (Android 14)
- 最低支持 SDK 24 (Android 7.0)
- MVVM 架构模式
- ViewModel + LiveData 数据观察
- RecyclerView + ItemTouchHelper 滑动操作
- AppWidgetProvider 桌面小部件
- SQLite 数据库持久化
- Coroutines 异步处理

#### 项目结构
```
app/src/main/java/com/example/todowidget/
├── MainActivity.kt          # 主界面 Activity
├── Todo.kt                  # 数据模型
├── TodoViewModel.kt         # 视图模型
├── TodoDatabaseHelper.kt    # 数据库帮助类
├── TodoAdapter.kt           # RecyclerView 适配器
├── SwipeCallback.kt         # 滑动手势回调
├── TodoWidgetProvider.kt    # 小部件提供者
└── TodoWidgetService.kt     # 小部件 RemoteViews 服务
```

---

## 待开发功能

- [ ] 待办事项编辑功能
- [ ] 待办事项分类/标签
- [ ] 待办事项提醒通知
- [ ] 深色模式支持
- [ ] 数据备份与恢复
- [ ] 多语言支持