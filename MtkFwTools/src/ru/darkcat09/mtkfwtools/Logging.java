package ru.darkcat09.mtkfwtools;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logging {

    public static void LogEvent(int level, String message, String threadName) {
        try {

            threadName =
                    (threadName != null) ?
                            (!(threadName.trim().equals(""))) ? threadName : "Thread" :
                            "Thread";

            String logLevelStr;
            switch (level) {
                case 1:
                    logLevelStr = "D";
                    break;
                case 2:
                    logLevelStr = "W";
                    break;
                case 3:
                    logLevelStr = "E";
                    break;
                case 4:
                    logLevelStr = "F";
                    break;
                default:
                    logLevelStr = "I";
            }

            FileWriter fw = new FileWriter(System.getProperty("") + "/mtkfwtools.log");

            fw.append(
                    new SimpleDateFormat("dd.MM.yyyy HH:mm:ss.SSS ").format(new Date())
            );
            fw.append("[").append(threadName).append("] ").append(logLevelStr).append(": ");
            fw.append(message.replace("\n", "\t\t\t\t\t\t\n")).append("\n");

            fw.close();
        }
        catch (IOException ignored) {}
    }
}
