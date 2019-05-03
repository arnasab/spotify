package sample.Playable;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.*;
import java.sql.Time;
import java.time.LocalDateTime;

/**
 * Created by asus on 6/7/2017.
 */
public class Song extends Playable  {
 public    String Album ;
    public  String Timeadd;
    public Duration time;
    public String path ;
    public String pathPhoto ;
    public String genre ;
    public int countPlay  ;

    public Song(String name, String artist, String album,String Genre) {
        super(name, artist);
        Album = album;
        Timeadd = LocalDateTime.now().getHour()+ " : " +LocalDateTime.now().getMinute();
        genre=Genre;

        path="server\\songs\\"+name+".mp3";
        pathPhoto = "image\\songs\\"+name+".jpg";
        time = new MediaPlayer(new Media(new File(path).toURI().toString())).getCurrentTime();
        countPlay = 0 ;

       saveSong();

    }

    public String getAlbum() {
        return Album;
    }

    public String getTimeadd() {
        return Timeadd;
    }

    public Duration getTime() {
        return time;
    }

    public String getPath() {
        return path;
    }

    public String getPathPhoto() {
        return pathPhoto;
    }

    public String getGenre() {
        return genre;
    }

    public int getCountPlay() {
        return countPlay;
    }
    public void saveSong(){
        try {

            FileOutputStream f = new FileOutputStream("data\\songs\\"+name);
            ObjectOutputStream out = new ObjectOutputStream(f);
            Song song = this;
            out.writeObject(song);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

