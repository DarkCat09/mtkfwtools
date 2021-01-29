package ru.darkcat09.mtkfwtools;

import java.util.HashMap;
import java.util.Map;

public class Languages {

    Map<String, String> engLang = new HashMap<>();
    Map<String, String> rusLang = new HashMap<>();

    public Languages() {
        
        // English
        engLang.put("AppHeading", "MtkFwTools");
        engLang.put("RusLang", "Russian Language");
        engLang.put("Quit", "Quit");
        engLang.put("Porting", "Firmware Porting");
        engLang.put("Patching", "Firmware Patching");
        engLang.put("InstallRootEtc", "Install Root, GApps, etc.");
        engLang.put("LogcatReader", "Logcat Reader");
        engLang.put("AdbTools", "ADB Tools");
        engLang.put("ExtractMftFiles", "Extract Needed Files");
        engLang.put("ChooseStockDir", "Choose a directory with a stock FW");
        engLang.put("ChoosePortDir", "Choose a directory with a port FW");
        engLang.put("ChooseMftScript", "Choose an MFT script-file");
        engLang.put("Browse", "Browse...");
        engLang.put("FixBootloop", "Fix bootloop");
        engLang.put("StartExcm", "Start!"); // Excm = Exclamation
        
        // Russian
        rusLang.put("AppHeading", "MtkFwTools");
        rusLang.put("RusLang", "Русский язык");
        rusLang.put("Quit", "Выход");
        rusLang.put("Porting", "Портирование прошивки");
        rusLang.put("Patching", "Патчинг прошивки");
        rusLang.put("InstallRootEtc", "Установка Root, GApps, и др.");
        rusLang.put("LogcatReader", "Просмотр логов");
        rusLang.put("AdbTools", "ADB-инструменты");
        rusLang.put("ExtractMftFiles", "Извлечь нужные файлы");
        rusLang.put("ChooseStockDir", "Выберите каталог со стоковой прошивкой");
        rusLang.put("ChoosePortDir", "Выберите каталог с портируемой прошивкой");
        rusLang.put("ChooseMftScript", "Выберите MFT-скрипт");
        rusLang.put("Browse", "Обзор...");
        rusLang.put("FixBootloop", "Исправить бутлуп");
        rusLang.put("StartExcm", "Старт!"); // Excm = Exclamation = Восклицание
    }

    public String getLocalizedString(String lang, String name, String def) {
        lang = lang.toLowerCase();
        String localizedStr = (lang.equals("rus")) ? rusLang.get(name) : engLang.get(name);
        return (localizedStr != null) ? localizedStr : def;
    }
    public String getLocalizedString(String lang, String name) {
        return getLocalizedString(lang, name, "");
    }
}
