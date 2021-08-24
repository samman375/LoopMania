package unsw.loopmania.controller;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.io.File;

public class FileSelector extends MenuButton {

    public void setDirectory(File file) {
        if (!file.isDirectory()) {
            return;
        }
        for (File f : file.listFiles()) {
            MenuItem newFile = new MenuItem();
            newFile.setText(f.getName());
            this.getItems().add(newFile);
        }
    }
}
