<div align="center">
  <img src="assets/banner.png" alt="Linacre Uninstaller — safe Android cleanup console & ADB Debloat Studio" width="100%" style="border-radius: 12px; margin-bottom: 16px;" />

  # ⚡ Linacre Uninstaller & ADB Debloat Studio

  **A precision Android debloating suite: native Kotlin mobile app + zero-install web companion for Fire OS, Samsung One UI, Xiaomi HyperOS, and vanilla AOSP.**<br>
  Curated and maintained as part of the [linacre.site](https://www.linacre.site/) ecosystem.

  [![Live Web Companion](https://img.shields.io/badge/Live_Web_App-Interactive_ADB_Studio-0ea5e9?style=for-the-badge&logo=google-chrome&logoColor=white)](https://lin4cre.github.io/LinacreUninstaller/)
  [![Download APK](https://img.shields.io/badge/Download-Latest_APK-38BDF8?style=for-the-badge&logo=android&logoColor=white)](https://github.com/LIN4CRE/LinacreUninstaller/releases)
  [![Kotlin](https://img.shields.io/badge/Kotlin-1.9-7F52FF?style=flat-square&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
  [![Android](https://img.shields.io/badge/Android-7.0%2B-3DDC84?style=flat-square&logo=android&logoColor=white)](https://developer.android.com/)
  [![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg?style=flat-square)](LICENSE)
</div>

---

## 🌐 Live Web Companion & ADB Studio

Launch the interactive web console directly in your browser:  
👉 **[lin4cre.github.io/LinacreUninstaller](https://lin4cre.github.io/LinacreUninstaller/)**

- **Curated OEM Debloat Matrix:** Search 28+ pre-classified telemetry, bloatware, and ad packages across Fire OS, One UI, HyperOS, and AOSP.
- **Safety Tiers:** Color-coded `Safe` (100% risk-free), `Warning` (optional services), and `Critical` (core OS components you should preserve).
- **One-Click Script Generator:** Instantly copy copy-pasteable `pm uninstall -k --user 0` and `cmd package install-existing` restore commands.
- **Batch Script Export:** Download custom `.bat` (Windows) or `.sh` (macOS / Linux) debloat scripts tailored to your selected packages.
- **RAM & Storage Estimator:** Live calculation of reclaimed RAM and reduced background telemetry overhead.

---

## 📱 Mobile App Features (Kotlin Native)

- **Official System Intent Safety:** Never silently purges packages without Android system approval.
- **Launcher Discoverability:** Appears cleanly as **“Uninstaller”** in your app drawer.
- **User vs System Segregation:** Dedicated tabs to prevent accidental modification of critical system binaries.
- **Granular Package Inspection:** View package names, installed version codes, exact disk footprint, and target SDKs.
- **Zero Telemetry:** Strict offline architecture — zero tracking, zero background services, zero analytics.

---

## 🛠️ ADB Quick Commands

Remove a package safely for the current user without root:
```bash
adb shell pm uninstall -k --user 0 <package.name>
```

Restore a previously disabled OEM package:
```bash
adb shell cmd package install-existing <package.name>
```

---

## 🚀 Building from Source

```bash
git clone https://github.com/LIN4CRE/LinacreUninstaller.git
cd LinacreUninstaller
./gradlew assembleDebug
```

Debug APK output location:
```text
app/build/outputs/apk/debug/app-debug.apk
```

---

## 📄 License

MIT © [David Linacre](https://github.com/LIN4CRE) · [linacre.site](https://www.linacre.site/)
