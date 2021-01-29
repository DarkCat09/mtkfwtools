package ru.darkcat09.mtkfwtools;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipFile;

import static ru.darkcat09.mtkfwtools.Main.currentLang;
import static ru.darkcat09.mtkfwtools.Main.langs;

public class Controller {

    @FXML public GridPane headingPane;
    @FXML public GridPane buttonsPane;
    @FXML public ProgressBar progressBar1;

    public void exitFromApp(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void changeLang(ActionEvent actionEvent) {

        if (((ToggleButton)(actionEvent.getSource())).isSelected())
            currentLang = "rus";
        else
            currentLang = "eng";

        for (Node child : headingPane.getChildren()) {
            ((Labeled)child).setText(langs.getLocalizedString(currentLang, child.getAccessibleText()));
        }
        for (Node child : buttonsPane.getChildren()) {
            ((Labeled)child).setText(langs.getLocalizedString(currentLang, child.getAccessibleText()));
        }
    }

    public void openToolsWindow(ActionEvent actionEvent) {
        String sourceButton = ((Button)(actionEvent.getSource())).getAccessibleText();
        switch (sourceButton) {
            case "Porting":
            case "Patching":
                try {
                    Stage fwwStage = new Stage();
                    Parent fwwindow = FXMLLoader.load(getClass().getResource("fwwindow.fxml"));
                    fwwStage.setTitle("Firmware Tools | MTK Firmware Tools by DarkCat09");
                    fwwStage.setScene(new Scene(fwwindow, 380, 354));
                    fwwStage.setResizable(false);
                    fwwStage.show();
                }
                catch (Exception ignored) {}
                break;
            case "InstallRootEtc":
                //
                break;
            case "AdbTools":
            case "LogcatReader":
                try {
                    Stage adbStage = new Stage();
                    Parent adbwindow = FXMLLoader.load(getClass().getResource("adbtools.fxml"));
                    adbStage.setTitle("ADB Tools | MTK Firmware Tools by DarkCat09");
                    adbStage.setScene(new Scene(adbwindow, 380, 354));
                    adbStage.setResizable(false);
                    adbStage.show();
                }
                catch (Exception ignored) {}
                break;
            default:
                Logging.LogEvent(2, "User clicked on undefined button!",
                        Thread.currentThread().getName());
                Alert alert =
                        new Alert(
                                Alert.AlertType.WARNING,
                                langs.getLocalizedString(
                                        currentLang, "ButtonWarning",
                                        "You clicked on undefined button!"
                                ),
                                ButtonType.OK
                        );
                alert.setHeaderText("Error");
                alert.show();
        }
    }

    public void extractFiles(ActionEvent actionEvent) {

        MftEventHandler extractHandler = new MftEventHandler() {
            @Override
            public void onProgressChanged(int completed, int total) {
                progressBar1.setProgress(100.0 / total * completed);
            }

            @Override
            public void onSuccess() {
                progressBar1.setProgress(0.0);
                Alert successAlert = new Alert(
                        Alert.AlertType.INFORMATION,
                        "Files extraction completed successfully!",
                        ButtonType.OK
                );
                successAlert.setHeaderText("Success");
                successAlert.showAndWait();
            }

            @Override
            public void onError() {
                progressBar1.setProgress(0.0);
                Alert errorAlert = new Alert(
                        Alert.AlertType.ERROR,
                        "An error has occurred! Open the log-file for more information?",
                        ButtonType.YES, ButtonType.NO
                );
                errorAlert.setHeaderText("Error");
                errorAlert.showAndWait();
            }
        };

        //

        try {

            // Copying Zip from resources
            InputStream resInputStream = getClass().getResource("mtkfwtools.zip").openStream();
            FileOutputStream fos = new FileOutputStream("mtkfwtools.zip", false);
            byte[] buff = new byte[1024];
            while (resInputStream.available() > 0) {
                resInputStream.read(buff);
                fos.write(buff);
            }
            fos.close();
            resInputStream.close();

            // Extracting Zip-Archive
            final Enumeration<? extends java.util.zip.ZipEntry> filesInZipEnum =
                    new ZipFile("mtkfwtools.zip").entries();

            int filesCount = 0;
            while (filesInZipEnum.hasMoreElements()) {
                filesInZipEnum.nextElement();
                filesCount++;
            }

            ProcessBuilder pb = new ProcessBuilder("7z", "x", "mtkfwtools.zip");
            Process p = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            int filesExtractedCount = 0;

            while ((line = br.readLine()) != null) {

                Logging.LogEvent(0, "7-Zip: " + line, Thread.currentThread().getName());

                if (line.startsWith("Extracting")) {
                    filesExtractedCount++;
                    extractHandler.onProgressChanged(filesExtractedCount, filesCount);
                }
            }

            br.close();
            p.wait();
        }
        catch (Exception ex)  {
            Logging.LogEvent(
                    3, "Error happened while extracting files!\n" + ex.toString(),
                    Thread.currentThread().getName()
            );
        }
    }
}
