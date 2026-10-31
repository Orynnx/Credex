# Credex

Credex 是一款面向移动端的服务余额与配额查看工具。它可将已添加服务的账户余额、Token Plan 或 Coding Plan 配额集中展示，并提供 Android 原生桌面小部件。

## 仓库定位与版本

本仓库是 Credex 的原始源码仓库，目前用于协作开发与同步 [NickWoluff/Credex](https://github.com/NickWoluff/Credex) 的改进。NickWoluff/Credex 是本仓库的 fork，当前 1.1.6 安装包由其发布，本仓库不重复发行相同版本。

2026-10-04 合并 [PR #3](https://github.com/Orynnx/Credex/pull/3) 后，应用版本为 **1.1.6**，应用源码与 NickWoluff/Credex 的 `3515708712f6836d2d35e557f0874a3de3d9cbdc` 对齐。

## 下载与安装

- **当前 1.1.6 APK**：[Credex-v1.1.6-NW.apk](https://github.com/NickWoluff/Credex/releases/download/v1.1.6/Credex-v1.1.6-NW.apk)（[版本说明](https://github.com/NickWoluff/Credex/releases/tag/v1.1.6)）。
- **后续版本**：查看 [NickWoluff/Credex Releases](https://github.com/NickWoluff/Credex/releases)。
- **可选背屏资源**：[RearDisplayResources.zip](https://github.com/NickWoluff/Credex/releases/download/v1.1.0/RearDisplayResources.zip)。资源包独立于 APK，沿用 v1.1.0 附件入口；它不是 1.1.6 APK。

> 本仓库 Releases 中的 **v0.10.0、v0.9.0 均为历史版本**，其中的 Codex quota companion/debug APK 和早期背屏资源不代表当前 1.1.6。安装当前应用请使用上面的下载入口。

## 功能

- 支持 OpenAI Codex、DeepSeek、SiliconFlow、Xiaomi MIMO、火山引擎、OpenCode、Kimi、GLM 及自定义接口。
- 支持账户余额、Token Plan、Coding Plan、Agent Plan 和 Codex 时间窗口配额等服务类型。
- 可选择 Material 或 Miuix 界面风格，并提供主题、小部件和通知设置。
- 支持拖拽排序、服务独立配置、内置登录与加密凭据存储。
- 提供 Android 桌面小部件；可选择主服务和副服务，并调整展示样式。
> 注意：背屏应用卡功能当前仅适配小米17/18 Pro 系列，且需借助 Xposed 模块导入。若有需求，可[点击此处](https://github.com/NickWoluff/Credex/releases/download/v1.1.0/RearDisplayResources.zip)下载背屏应用卡资源，通过[OuterView](https://github.com/Orynnx/OuterView)（Credex 官方适配支持，已适配HyperOS 4）或其它背屏模块导入后使用。

## 项目结构

- `credex/`：Android 应用、服务适配、小部件和设置页面。

## 构建与测试

项目使用 JDK 17 和仓库自带的 Gradle Wrapper：

```powershell
.\gradlew.bat :credex:testDebugUnitTest
.\gradlew.bat :credex:assembleDebug
```

Debug APK 输出路径：`build/Credex-app/outputs/apk/debug/Credex-v<版本号>-debug.apk`。

## 隐私与安全

凭据仅保存在本机的 Android Keystore 加密存储中，不会通过展示 Provider 或小部件暴露。部分平台接口和网页登录流程可能随服务商调整而变化；刷新失败时应用会保留上一次成功获取的展示数据。
