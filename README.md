# MTK Firmware Tools Project
## MFT Scripts
MediaTek Firmware Tools has a simple scripting language used for porting firmwares.  
You can read documentation on the wiki [about MtkFwTools scripts](https://github.com/DarkCat09/MtkFwTools/wiki/MFT-scripting).

## MtkFwTools
MediaTek Firmware Tools is a powerful toolbox for ROM-developers.  

### Porting firmware
1. Unpack a stock and a port firmware;
2. Unpack boot.img;
3. Detect firmwares types: AOSP or CM (CyanogenMod-based), Recovery or FlashTool;
4. Port firmware using one of this methods:  
   - Execute selected MFT script, or
   - Copy 3 files and apply a lot of patches/fixes ([three files method by Akella_MC on 4PDA](https://4pda.to/forum/index.php?s=&showtopic=562976&view=findpost&p=58384664));
5. Pack boot.img;
6. Pack firmware;
7. Sign firmware using `testsign.jar`.

### Patching firmware
Automatically creates update.zip fixing a chosen problem.

### ADB Tools
One more GUI for ADB. Functional:
...

## MFT Runtime
A standalone file for executing MFT scripts in console.  
You can use it to avoid some routine manual operations with files.

## RegExp
Camera in init.rc: https://regexr.com/5mldo  
Bootclasspath in init.rc: https://regexr.com/5m23o
