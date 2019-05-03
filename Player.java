package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import sample.Playable.Song;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import sample.User.Contact;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by asus on 6/22/2017.
 */
public class Player {
    static List<Song> playlist;
    static Song playSong;
    static int index=0;
    static boolean isplay = false;
    static boolean isSuffle = false;
    static String uriString;
    static MediaPlayer player;
    static Duration length;



    static ImageView play;
    static ImageView previus;
    static ImageView next;
    static ImageView shuffle;
    static ImageView playSongPhoto;
    static Label namePlayer;
    static Label ArtistPlayer;
    static Slider sliderPlay;

    private Player() {
    }
    static void   search(Song s){
        for (int i = 0; i < playlist.size(); i++) {
            if(playlist.get(i).equals(s )){
                index=i;
            }
        }
    }

    static void Play(List<Song> list,Song song){
        if(playSong==null){
            playlist=list;


        }else {
            player.pause();
        }
        playSong = song;
        player = new MediaPlayer( new Media(new File(playSong.getPath()).toURI().toString()));
        playSong.countPlay++;
        System.out.println(playSong.countPlay);
        setRecentlyPlayer();

        player.play();
        player.seek(Duration.ZERO);
        setSliderPlay();
        isplay=true;
        set();



    }

    private static void setRecentlyPlayer() {
        List<Song> recntlyPlay = ((Contact) Main.mainUser).recntlyPlay;

        //add recently Play
        if(!recntlyPlay.contains(playSong)|| recntlyPlay ==null)
            recntlyPlay.add(0,playSong);
        else {
            recntlyPlay.remove(playSong);
            recntlyPlay.add(0,playSong);
        }
    }

    private static void Play(List<Song> list,int i){
        if(playSong==null)
            playlist=list;

        else
            player.pause();
        playSong=playlist.get(i);
        player = new MediaPlayer( new Media(new File(playSong.getPath()).toURI().toString()));
        playSong.countPlay++;
        player.seek(Duration.ZERO);
        setSliderPlay();

        player.play();

         setRecentlyPlayer();

        isplay=true;
        set();

    }
   static void setSliderPlay(){


        sliderPlay.setMax(player.getCycleDuration().toSeconds());
        sliderPlay.setMin(0);
        player.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (!sliderPlay.isValueChanging())
                sliderPlay.setValue(newValue.toSeconds());
        });
        sliderPlay.valueChangingProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                player.seek(Duration.seconds(sliderPlay.getValue()));
            }
        });
        sliderPlay.valueProperty().addListener((observable, oldValue, newValue) -> {
            if( !sliderPlay.isValueChanging() && Math.abs(oldValue.doubleValue()-newValue.doubleValue()) > 0.2 )
                player.seek(Duration.seconds(newValue.doubleValue()));
        });
    }



    static void next(){
        player.pause();
        search(playSong);
        if(index<playlist.size()-1)
            index++;
        else
            index=0;
        Play(playlist,index);

    }
    static void previuse(){
        player.pause();
        search(playSong);
        if(index >0)
            index--;
        else
            index=playlist.size()-1;
        Play(playlist,index);
    }
    static void shuffleOFF(){
        search(playSong);
        isSuffle=false;
    }
    static void shuffleON(){
        index = ThreadLocalRandom.current().nextInt(0,playlist.size());
        Play(playlist,index);
        isSuffle=true;
    }
    static void repeat(){
        player.seek(Duration.ZERO);
        setSliderPlay();

       player.play();
        play.setImage(new Image(new File("image\\pause.jpg").toURI().toString()));
       isplay=true;
    }
    static void pause(){
        player.pause();
        play.setImage(new Image(new File("image\\Play.png").toURI().toString()));


        isplay=false;
    }
    static void resume(){

        player.play();
        play.setImage(new Image(new File("image\\pause.jpg").toURI().toString()));

        isplay=true;
    }
   static void set(){
        namePlayer.setVisible(true);
        ArtistPlayer.setVisible(true);
        namePlayer.setText(playSong.getName());
        ArtistPlayer.setText(playSong.getArtist());
        playSongPhoto.setImage(new Image(new File(playSong.getPathPhoto()).toURI().toString()));
       play.setImage(new Image(new File("image\\pause.jpg").toURI().toString()));

    }


}
