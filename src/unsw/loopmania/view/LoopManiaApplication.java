package unsw.loopmania.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import unsw.loopmania.controller.*;

/**
 * the main application
 * run main method from this class
 */
public class LoopManiaApplication extends Application {

    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws IOException {

        // set title on top of window bar
        primaryStage.setTitle("Loop Mania");

 

        // prevent human player resizing game window (since otherwise would see white space)
        // alternatively, you could allow rescaling of the game (you'd have to program resizing of the JavaFX nodes)
        primaryStage.setResizable(false);

        this.primaryStage = primaryStage;
        MusicPlayer.playMusic("src/music/ScapeMain.wav");

        MainMenuController mainMenuController = new MainMenuController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuView.fxml"));
        loader.setController(mainMenuController);
        Parent mainMenuRoot = loader.load();
        Scene scene = new Scene(mainMenuRoot);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

