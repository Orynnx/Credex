# 🐾 Credex

![Latest Version](https://img.shields.io/github/v/release/NickWoluff/Credex?style=flat-square&color=brightgreen)
![Android](https://img.shields.io/badge/Android-10%2B-3DDC84?style=flat-square&logo=android&logoColor=white)



## ✨ 核心特性

- **多平台统一管理**：适配多种模型服务商，提供自定义接口，AI 余额一站式管理。
- **灵活自定义接口**：按请求路径格式识别各类接口，无需为每个中转站单独配置。
- **多种配额展示**：兼容账户余额、Token Plan 和时间窗口配额等类型。
- **现代友好界面**：提供 Miuix 与 Material 两种界面风格，高效美观。
- **可靠后台同步**：支持系统定时刷新、持续同步、常驻通知和刷新失败时保留上次成功数据。
- **本机安全存储**：登录凭据仅保存在 Android Keystore 加密存储中，不会通过展示组件或小部件暴露。
- **桌面小部件**：可选择双服务灵活展示，展示剩余或已使用配额，并自由调整卡片高度、偏移和数字单位。
- **背屏应用卡**：已适配小米 17/18 Pro 系列背屏，可配合 OuterView 或其它背屏模块导入资源。

## 👆 使用说明

1. 打开 Credex，点击右上角的添加按钮。
2. 选择要添加的平台，或选择“自定义接口”配置中转站。
3. 根据平台要求填写 API Key、用户名密码，或进入内置网页完成登录。
4. 返回首页后即可查看余额、配额使用比例、重置时间和最后更新时间。
5. 在小部件设置中可分别选择主服务与副服务，并配置展示样式。

自定义接口需要填写完整的 **API 根地址**，不能填写登录页、控制台页或完整余额接口。

已确认的接口格式与根地址可查看 [自定义接口适配列表](doc/custom-endpoint-adapters.md)。应用会依据根地址选择对应登录与余额接口；未登记的地址会按预设顺序尝试兼容格式。

## 🔄 更新说明

“自动检查更新”默认开启，每次打开应用时检查一次 GitHub Releases。可以在设置中关闭自动检查，改为手动检查。

发现新版本后，应用会显示版本说明和 Release 页面入口；安装新版需自行下载并完成安装。刷新失败、网络不可用或当前已是最新版时，不会影响本地已经缓存的展示数据。

## 📥 下载与安装

**GitHub 安装**：

前往 [Releases](https://github.com/NickWoluff/Credex/releases) 下载最新 APK。

**背屏资源**：

背屏应用卡资源可通过 [RearDisplayResources.zip](https://github.com/NickWoluff/Credex/releases/download/v1.1.0/RearDisplayResources.zip) 下载。资源包独立于 APK，可配合 [OuterView](https://github.com/Orynnx/OuterView) 或其它背屏模块导入使用。


## 🛠️ 项目结构与构建

- `credex/`：Android 应用、平台适配、自定义接口、桌面小部件和设置页面。
- `doc/custom-endpoint-adapters.md`：自定义接口根地址、请求路径和兼容规则。

项目使用 JDK 17 和仓库自带的 Gradle Wrapper：

```powershell
.\gradlew.bat :credex:testDebugUnitTest
.\gradlew.bat :credex:assembleDebug
```

Debug APK 输出路径：

```text
build/Credex-app/outputs/apk/debug/Credex-v<版本号>-debug.apk
```

## 🔐 隐私与安全

凭据仅保存在本机的 Android Keystore 加密存储中，展示组件不会获得账号密码、Cookie 或访问令牌。部分平台接口和网页登录流程可能随服务商调整而变化；刷新失败时，应用会继续保留上一次成功获取的展示数据。

## 💬 交流与反馈

使用时遇到问题或有功能建议，欢迎提交至 [Orynnx/Credex Issues](https://github.com/Orynnx/Credex/issues)。



© 2026-2027 Orynnx & Nick Woluff. All Rights Reserved.
