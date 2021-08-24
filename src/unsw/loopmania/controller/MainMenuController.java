package unsw.loopmania.controller;
import unsw.loopmania.view.MusicPlayer;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import java.io.File;
import java.io.IOException;


import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * controller for the main menu.
 */
public class MainMenuController {
    //private static Set<MediaPlayer> activePlayers = new HashSet<MediaPlayer>(); // used in music to avoid garbage collection

    @FXML
    private Pane mainMenu;

    @FXML
    private Button startGameButton;

    @FXML
    private MenuButton gameModeButton;

    @FXML
    private FileSelector worldSelector;



    public MainMenuController() {
    }

    @FXML
    public void initialize() {
        worldSelector.setDirectory(new File("worlds"));
        selectWorld();
        selectGameMode();
    }

    public String getGameMode() {
        return gameModeButton.getText();
    }

    public String getWorld() {
        return worldSelector.getText();
    }

    public void selectWorld() {
        for (final MenuItem world: worldSelector.getItems()) {
            world.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                @Override
                public void handle(javafx.event.ActionEvent actionEvent) {
                    worldSelector.setText(world.getText());
                }
            });
        }
    }

    public void selectGameMode() {
        for (final MenuItem gameMode: gameModeButton.getItems()) {
            gameMode.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
                @Override
                public void handle(javafx.event.ActionEvent actionEvent) {
                    gameModeButton.setText(gameMode.getText());
                }
            });
        }
    }

    // public static void playMusic()
    // {
    //     //music
    //     String musicFile = "src/music/NewbieMelody.wav";
    //     Media sound = new Media(new File(musicFile).toURI().toString());
    //     MediaPlayer mediaPlayer = new MediaPlayer(sound);
    //     activePlayers.add(mediaPlayer); // need to do this shit to avoid javafx garbage collection wiping it
    //     mediaPlayer.play();
    // }


    @FXML
    private void startGame() throws IOException {
        Stage main = (Stage) mainMenu.getScene().getWindow();

        LoopManiaWorldControllerLoader loopManiaLoader = new LoopManiaWorldControllerLoader(worldSelector.getText());
        LoopManiaWorldController mainController = loopManiaLoader.loadController();
        MusicPlayer.stopMusic();
        MusicPlayer.playMusic("src/music/NewbieMelody.wav");
        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/unsw/loopmania/view/LoopManiaView.fxml"));
        gameLoader.setController(mainController);
        Parent gameRoot = gameLoader.load();
        Scene gameScene = new Scene(gameRoot);
        gameRoot.requestFocus();
        gameScene.getRoot().setStyle("-fx-font-family: 'serif'");
        main.setScene(gameScene);
        main.show();

        mainController.setGameMode(gameModeButton.getText());
        mainController.startTimer();

    }
}
