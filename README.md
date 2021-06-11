# MTK Firmware Tools Project
## MFT Scripts
MediaTek Firmware Tools has a simple scripting language used for porting firmwares.  
You can read [MtkFwTools scripting documentation](https://github.com/DarkCat09/MtkFwTools/wiki/MFT-scripting) on the wiki.

## MtkFwTools
MediaTek Firmware Tools is a powerful toolbox for ROM-developers.  
> Firmwares are packed using 7-Zip.

### Porting firmware
1. Unpack a stock and a port firmware;
2. Unpack boot.img;
3. Detect firmwares types: AOSP or CM (CyanogenMod-based), Recovery or FlashTool;
4. Port firmware using one of this methods:  
   - Execute selected MFT script, or
   - Copy 3 files and apply a lot of patches/fixes ([three files method by Akella_MC on 4PDA](https://4pda.to/forum/index.php?s=&showtopic=562976&view=findpost&p=58384664));
   - Note that only the MFT-script-method is available for crossporting!
5. Pack boot.img;
6. Pack firmware;
7. Sign firmware using `testsign.jar`.

### Patching firmware
Automatically creates update.zip for specified firmware fixing a chosen problem.

### ADB Tools
One more GUI for ADB. Functional:
 - Perform commands with multiple devices, shown in list;
 - Run shell commands;
 - Install/Uninstall applications (batch multi-package mode is available);
 - A handy tool for reading logcat. Find or filter logs, and save it!
 - Reboot device, reconnect, restart ADB server;
 - FileFly: work with files "on the fly"!  
Push and pull files; Drag&Drop, batch mode is available.  
You can also undo adding/deleting/editing files.

### ApkTool
And one more GUI for ApkTool...
 - Decompiling, compiling APK;
 - A tool for editing resources:
   - Quick action bar with auto-detecting application type:  
   e.g. `Launcher*.apk` -> SystemLauncher -> Actions "Remove Google search bar", "Change app list color"...
   - Built-in color picker;
   - Translate services (Google, Yandex, Bing);
   - Preview of layout (was not planned, so don't expect this feature in the release);
   - Editing pictures;
 - Finding smali code.

### Firmware Lab
Build your firmware!  
This feature is for ROM modification developers.  
You can add and remove applications, edit bootanimations and sounds, include installation scripts like GApps/MicroG or SuperSU/Magisk, check update-script...

FwLab has one more interesting feature: you can add some variations of one component  
(for example: insert into firmware GApps and MicroG) and write a configuration file `firmware.lab`.  
Then, users can open a ZIP-archive with your firmware in MtkFwTools FirmwareLab, choose components (in the example above, they can choose which of services to use: GApps or MicroG) and pack the firmware by pressing a button "Build".
[FwLab Config documentation](https://github.com/DarkCat09/mtkfwtools/wiki/FwLab-Config) on the wiki.

How FwLab builds firmware:
 - Removes extra variations of components, keeping on only the variation selected by user;
 - Packs firmware to ZIP;
 - Signs it using `testsign.jar`.

### Language
Supported languages:  
Russian (Русский!), English, Ukrainian (Український).

## MFT Runtime
A standalone file for executing MFT scripts in console.  
Use it to avoid some routine manual operations with files.

## RegExp
Camera in init.rc: https://regexr.com/5mldo  
Bootclasspath in init.rc: https://regexr.com/5m23o
