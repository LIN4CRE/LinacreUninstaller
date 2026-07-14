<div align="center">
  <img src="banner.png" alt="Linacre Uninstaller Banner" width="100%" />

  # 🗑️ Linacre Uninstaller

  **An elegant, powerful, and safe Android application uninstaller and debloat utility.**<br>
  *Part of the [Linacre.site](https://www.linacre.site/) Open Source Ecosystem.*
  [![Download Latest APK](https://img.shields.io/github/v/release/LIN4CRE/LinacreUninstaller?label=Download%20Latest%20APK&style=for-the-badge&color=2563EB)](https://github.com/LIN4CRE/LinacreUninstaller/releases/latest)


  [![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=flat-square&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
  [![Android](https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=android&logoColor=white)](https://developer.android.com/)
  [![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg?style=flat-square)](https://opensource.org/licenses/MIT)
</div>

---

## ✨ Features

- **Pristine UI**: A beautiful, responsive Material dark-themed interface matching the Linacre portfolio design language.
- **Lightning Fast Search**: Find any system or user app instantly.
- **Smart Debloat Tiers**: Three meticulously categorized tiers for system cleanup:
  - 🟢 **Simple Debloat**: Safe to remove. Standard third-party bloat (Facebook, Netflix, TikTok, etc.).
  - 🟡 **Medium Debloat**: Non-essential OEM utilities and Google background apps.
  - 🔴 **Hardcore Debloat**: Aggressive mode targeting telemetry, tracking, and deep system analytics. *(Proceed with caution!)*
- **Batch Processing**: Select a debloat tier and automatically queue the uninstallation process to clean up your device rapidly.
- **Safety Indicators**: Clear, color-coded badges let you know exactly what is safe to remove before you touch anything.


## 📸 Screenshots

<div align="center">
  <img src="assets/screen1.png" alt="App List View" width="30%">
  &nbsp;&nbsp;
  <img src="assets/screen2.png" alt="Batch Uninstall View" width="30%">
</div>

## 🚀 Getting Started

### Prerequisites

- Android 7.0 (API level 24) or higher.
- *(Optional)* For seamless silent uninstalls without prompts, root access or Shizuku/ADB privileges are recommended (planned for future releases).

### Installation

1. Go to the [Releases](../../releases) tab.
2. Download the latest `LinacreUninstaller.apk`.
3. Install the APK on your Android device (you may need to allow "Install from Unknown Sources").
4. Launch the app and start cleaning!

### ⚠️ Warning & Recovery
Using the **Hardcore Debloat** tier can remove critical system components depending on your manufacturer. If you experience boot loops or app crashes after a hardcore debloat, you can restore packages via ADB using a PC:
```bash
adb shell cmd package install-existing <package.name>
```

## 🛠️ Build from Source

To build this project locally:

```bash
git clone https://github.com/LIN4CRE/LinacreUninstaller.git
cd LinacreUninstaller
./gradlew assembleDebug
```

The APK will be generated at `app/build/outputs/apk/debug/app-debug.apk`.

## 🎨 Design & Architecture

Built with modern Android standards:
- **Language**: Kotlin 1.9
- **Build System**: Gradle 8.5
- **UI Toolkit**: XML + Material Components
- **Architecture**: Coroutines for async package fetching, ensuring a jank-free 60fps scrolling experience even with thousands of installed apps.

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the [issues page](../../issues).

## 📄 License

This project is [MIT](LICENSE) licensed.

---
<div align="center">
  <b>Developed by <a href="https://github.com/LIN4CRE">David Linacre</a></b><br>
  <i>Full-stack & AI systems engineer</i>
</div>
