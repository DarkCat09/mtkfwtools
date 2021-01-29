package ru.darkcat09.mtkfwtools;

import com.sun.istack.internal.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MftRuntime {

    private String scriptFileName = "";
    private final String[] stockDirs;
    private final String[] portDirs;

    public MftRuntime(String filename) {
        scriptFileName  = filename;
        stockDirs       = new String[2];
        portDirs        = new String[2];
    }

    public void setStockDirs(@NotNull String systemDir, @NotNull String bootDir) {
        stockDirs[0] = systemDir;
        stockDirs[1] = bootDir;
    }
    public void setPortDirs(@NotNull String systemDir, @NotNull String bootDir) {
        portDirs[0] = systemDir;
        portDirs[1] = bootDir;
    }

    private void replaceText(@NotNull String stockFile, @NotNull String portFile,
                             @NotNull String regex) {

        try {
            String stockFileText = new String(Files.readAllBytes(new File(stockFile).toPath()), StandardCharsets.UTF_8);
            Matcher m = Pattern.compile(regex).matcher("");
        }
        catch (Exception ex) {
            Logging.LogEvent(3, "Error happened while executing script!",
                    Thread.currentThread().getName());
        }
    }

    private String getFwFilePath(@NotNull String mftPath, boolean isPort) {

        if (isPort) {
            return mftPath
                    .replaceFirst("^fw/", portDirs[0])
                    .replaceFirst("^kn/", portDirs[1]);
        }
        else {
            return mftPath
                    .replaceFirst("^fw/", stockDirs[0])
                    .replaceFirst("^kn/", stockDirs[1]);
        }
    }
    public void executeScript(MftEventHandler completeHandler) {

        Thread scriptThread = new Thread(() -> {

            try {

                FileReader scriptFile = new FileReader(scriptFileName);
                BufferedReader br = new BufferedReader(scriptFile);
                String line;

                while ((line = br.readLine()) != null) {

                    if (line.trim().equals(""))
                        continue;

                    String[] cmdArr = line.split("\\s");

                    if (cmdArr[0].matches("^ReplaceFile$|^rf$")) {
                        try {

                            String stockFile    = getFwFilePath(cmdArr[1], false);
                            String portFile     = getFwFilePath(cmdArr[1], true);

                            Files.copy(
                                    new File(stockFile).toPath(), new File(portFile).toPath(),
                                    StandardCopyOption.REPLACE_EXISTING
                            );
                        }
                        catch (Exception ex) {
                            Logging.LogEvent(
                                    2, "Error happened while executing script " +
                                            scriptFileName + "\n" + ex.toString(),
                                    Thread.currentThread().getName()
                            );
                        }
                    }

                    if (cmdArr[0].matches("^ReplaceText$|^rt$")) {

                        try {
                            String stockFileText = new String(
                                    Files.readAllBytes(new File(
                                            getFwFilePath(cmdArr[1], false)
                                    ).toPath()),
                                    StandardCharsets.UTF_8
                            );

                            String portFileText = new String(
                                    Files.readAllBytes(new File(
                                            getFwFilePath(cmdArr[1], true)
                                    ).toPath()),
                                    StandardCharsets.UTF_8
                            );

                            Matcher m = Pattern.compile(cmdArr[2]).matcher(stockFileText);
                            if (m.matches()) {
                                portFileText = portFileText.replaceFirst(cmdArr[2], m.group(1));
                                // TODO
                            }
                        }
                        catch (Exception ex) {
                            Logging.LogEvent(
                                    2, "Error happened while executing script " +
                                            scriptFileName + "\n" + ex.toString(),
                                    Thread.currentThread().getName()
                            );
                        }
                    }
                }

                br.close();
                scriptFile.close();
                completeHandler.onSuccess();
            }
            catch (IOException ex) {
                Logging.LogEvent(
                        3, "Error happened while executing script " +
                                scriptFileName + "\n" + ex.toString(),
                        Thread.currentThread().getName()
                );
                completeHandler.onError();
            }
        });
        scriptThread.start();
    }
}
