package sample.Threads;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sample.Main;

import java.io.File;

/**
 * Created by asus on 6/8/2017.
 */
public class MusicplayerThread implements Runnable{
    @Override
    public void run() {
        int i =Main.mainUser.songs.size()-1;
        String musicFile = Main.mainUser.songs.get(i).path;     // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}
