package unsw.loopmania.view;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class MusicPlayer {
    //private static String currentSong;
    private static MediaPlayer mediaPlayer;

    private static Set<MediaPlayer> activePlayers = new HashSet<MediaPlayer>(); // used in music to avoid garbage collection

    public static void playMusic(String musicFile){
        //music
        //String musicFile = "src/music/ScapeMain.wav";


        Media song = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(song);
        activePlayers.add(mediaPlayer); // need to do this shit to avoid javafx garbage collection wiping it
        mediaPlayer.play();
    }

    public static void stopMusic(){
        mediaPlayer.stop();
    }
}
