package sample;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import sample.Playable.Album;
import sample.Playable.Playable;
import sample.Playable.Song;
import sample.Server.Server;
//import sample.Threads.MusicplayerThread;
import sample.Server.ServerLoader;
import sample.Server.ServerUpdater;
import sample.User.Artist;
import sample.User.Contact;
import sample.User.Person;




import java.io.*;
import java.net.URI;

import java.nio.file.Files;

import java.util.*;
import java.util.List;
import java.util.regex.Pattern;



public class Controller {

    static boolean isRadioON=false;
    /*
    * these FXML are for Menu in Left Side Main Page
    *
    */

    @FXML
    ImageView baralbums;
    @FXML
    ImageView barsongs;
    @FXML
    ImageView imageArtistpage;
    @FXML
    ImageView barartists;
    @FXML
    ImageView barrecently;
    @FXML
    ImageView barbrowse;
    @FXML
    Label songs;
    @FXML
    Label artists;
    @FXML
    Label albums;
    @FXML
    Label browse;
    @FXML
    Label recently;
    @FXML
    Label subject;
    @FXML
    Label overView;
    @FXML
    Label recommendation;
    @FXML
    Label radio;
    @FXML
    FlowPane flowPane;
    @FXML
    Circle circle;
    @FXML
    Ellipse recommendationbar;
    @FXML
    Ellipse overViewbar;
    @FXML
    Button follow;

    /*
    * these methods are for Menu in Left Side Main Page
    *
    */
    public void OverView(MouseEvent mouseEvent) throws IOException {
        menubar(browse,barbrowse,"Browse");
        overView.setTextFill(Color.WHITE);
        overView.setVisible(true);
        showGeners(Main.geners,flowPane);
        overViewbar.setVisible(true);
        recommendation.setVisible(true);
    }
    public void signUp(MouseEvent mouseEvent) throws IOException {

        Scene s = new Scene(FXMLLoader.load(getClass().getResource("sign up.fxml")),Main.width,Main.height);
        Main.mainStage.setScene(s);



    }
    public void login(MouseEvent mouseEvent) throws IOException {
        try {
            Player.player.pause();
        }catch (Exception e ){
            System.out.println(e.getMessage());
        }

        Scene s = new Scene(FXMLLoader.load(getClass().getResource("logIn.fxml")),Main.width,Main.height);
        Main.mainStage.setScene(s);

    }
    public void Songs(MouseEvent mouseEvent)  {

        menubar(songs,barsongs,"Songs");
        ServerUpdater.upadatecontact((Contact)Main.mainUser);

        showSongs(Main.mainUser.songs,flowPane);


    }
    public void RecentlyPlayed (MouseEvent mouseEvent) throws IOException {
        menubar(recently,barrecently,"Recently Played");
        showSongs(((Contact)Main.mainUser).recntlyPlay,flowPane);

    }
    public void Radio (MouseEvent mouseEvent) throws IOException {
        List<Song> radioSongs = new ArrayList<>();
       if(!isRadioON) {
           radio.setTextFill(Color.WHITE);
           isRadioON=true;

           try {
               for (int i = 0; i < Main.songs.size(); i++) {
                   if(Player.playSong.getGenre().equals(Main.songs.get(i).genre))
                       radioSongs.add(Main.songs.get(i));
               }
               Player.playlist=radioSongs;
           }catch (Exception e){
               System.out.println("Player song is null");

           }
       }
       else{
           radio.setTextFill( Color.web("#807e7e"));
           isRadioON=false;
           try {
               for (int i = 0; i < Main.songs.size(); i++) {
                   if(Player.playSong.getAlbum().equals(Main.songs.get(i).getAlbum()))
                       radioSongs.add(Main.songs.get(i));
               }
               Player.playlist=radioSongs;
           }catch (Exception e){
               System.out.println("Player song is null");

           }


    }}
    public void Recommendation (MouseEvent mouseEvent) throws IOException {
        menubar(browse,barbrowse,"Browse");
        List<Song> recSongs = new ArrayList<>();
        try {
            for (int i = 0; i < Main.songs.size(); i++) {
                if(Player.playSong.getGenre().equals(Main.songs.get(i).genre))
                    recSongs.add(Main.songs.get(i));
            }
        }catch (Exception e){
            System.out.println("null");
            flowPane.getChildren().clear();

        }
        recSongs.remove(Player.playSong);

        showSongs(recSongs,flowPane);
        recommendation.setTextFill(Color.WHITE);
        recommendation.setVisible(true);

        recommendationbar.setVisible(true);
        overView.setVisible(true);

    }
    public void Albums (MouseEvent mouseEvent) throws IOException {
        menubar(albums,baralbums,"Albums");
        showAlbums(Main.albums,flowPane);

    }
    public void Artists (MouseEvent mouseEvent) throws IOException {
        menubar(artists,barartists,"Artists");
        showArtist(Main.artists,flowPane);

    }
    public void Browse (MouseEvent mouseEvent) throws IOException {
        menubar(browse,barbrowse,"Browse");
        showSongs(Main.songs,flowPane);
        overView.setVisible(true);
        recommendation.setVisible(true);
        userprofile.setText(Main.mainUser.user);

    }

    //this method set profile for contact
    public  void setProfile(KeyEvent mouseEvent) {
        Artist artist = (Artist)Main.mainUser;
        photoartist.setImage(new Image(new File("image\\artist\\"+artist.user.toLowerCase()+".jpg").toURI().toString()));
        nameArtist.setText(artist.firstname + " " + artist.lastname);
        artistsong.setText(Main.mainUser.user);
    }

    //this method for style LEFT MENU SIDE
    void menubar(Label label , ImageView imageView , String title){
        follow.setVisible(false);
        overViewbar.setVisible(false);
        overView.setVisible(false);
        overView.setTextFill(Color.GRAY);
        recommendationbar.setVisible(false);
        recommendation.setVisible(false);
        recommendation.setTextFill(Color.GRAY);
        imageArtistpage.setVisible(false);
        circle.setVisible(false);
        songs.setTextFill(Color.web("#807e7e"));
        albums.setTextFill(Color.web("#807e7e"));
        artists.setTextFill(Color.web("#807e7e"));
        recently.setTextFill(Color.web("#807e7e"));
        browse.setTextFill(Color.web("#807e7e"));
        barsongs.setVisible(false);
        baralbums.setVisible(false);
        barartists.setVisible(false);
        barbrowse.setVisible(false);
        barrecently.setVisible(false);
        subject.setText(title);

        label.setTextFill(Color.WHITE);
        imageView.setVisible(true);

    }




    public void logInWithFacebook(MouseEvent mouseEvent) throws Exception {
        java.awt.Desktop.getDesktop().browse(new URI("https://login.spotify.com/login-facebook-sso/?csrf=dec8c2de77f194a1991574e564f19265&port=4371&version=1.0.55.487.g256699aa"));


    }

    /*
     * these FXML & method are for sign Up Artist
     *
     */
    @FXML
    TextField firstnameArtst;
    @FXML
    TextField lastnameArtst;
    @FXML
    TextField urlphoto;
    @FXML
    Label complete;
    @FXML
    Label again;
    public void completesignup(MouseEvent mouseEvent) throws Exception {
        FileInputStream f = new FileInputStream("data\\users\\Artists\\"+Main.mainUser.user);
        ObjectInputStream in = new ObjectInputStream(f);

        Artist artist =(Artist)in.readObject();
        artist.firstname = firstnameArtst.getText();
        artist.lastname = lastnameArtst.getText();

        ServerUpdater.upadateArtist(artist);
        complete.setVisible(true);


    }

    //this method for browse set URL Photo
    public void setUrlphoto(MouseEvent mouseEvent) throws Exception {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        File scr = fileChooser.showOpenDialog(Main.mainStage);
        OutputStream out  =  new FileOutputStream("image\\artist\\"+Main.mainUser.user+".jpg");
        byte[] bytes = Files.readAllBytes(scr.toPath());
        out.write(bytes);

        urlphoto.setText(scr.getPath());



    }



    /*
    * these FXML are for Sign up in sign up page
    *
    */
    @FXML
    TextField email;
    @FXML
    PasswordField password;
    @FXML
    TextField confirmEmail;
    @FXML
    TextField username;
    @FXML
    Label error;

    /*
    * these FXML are for Log in at log in page
    *
    */
    @FXML
    TextField EmailLogIn;
    @FXML
    PasswordField PassLogin;
    @FXML
    Label userprofile ;
    @FXML
    javafx.scene.control.Label errorLogIn;


    //this page for login page
    public void LoginUsers(MouseEvent mouseEvent) {
        String mail = EmailLogIn.getText();
        String passwordText =PassLogin.getText();

        if(Server.checkUser(new Contact(mail,mail, passwordText))){
            Scene s = null;
            try {
                FileInputStream f = new FileInputStream("data\\users\\contacts\\"+mail);
                ObjectInputStream in = new ObjectInputStream(f);
                //Load songs
                Main.mainUser =(Person)in.readObject();
                ServerLoader.loadSongs();
                ServerLoader.loadArtists();
                ServerLoader.loadAlbums();
                ServerLoader.loadGeners();
                for (int i = 0; i <Main.songs.size() ; i++) {
                    System.out.println(Main.songs.get(i).name+" ");
                }
                System.out.println(Main.songs.size());

                s = new Scene(FXMLLoader.load(getClass().getResource("songs.fxml")), Main.width,Main.height);

            } catch (Exception e) {
                e.printStackTrace();
            }
            Main.mainStage.setScene(s);

//            userprofile.setText(Main.mainUser.user);

            Transition animateStage = new Transition() {
                {
                    setCycleDuration(Duration.millis(1000));
                }
                @Override
                protected void interpolate(double t) {
                    Main.mainStage.setHeight(t * 750.0);
                }
            };
            animateStage.play();



        }else{
            if(Server.checkArtist(new Artist(mail,mail, passwordText))){
                Scene s = null;
                try {
                    FileInputStream f = new FileInputStream("data\\users\\artists\\"+mail);
                    ObjectInputStream in = new ObjectInputStream(f);

                    Main.mainUser = (Person) in.readObject();
                    Artist artist = (Artist)Main.mainUser;



                    s = new Scene(FXMLLoader.load(getClass().getResource("addsong.fxml")), Main.width,Main.height);
                    System.out.println(artist.firstname + " " + artist.lastname);
//                   photoartist.setImage(new Image(new File("spot\\image\\artist\\"+mail.toLowerCase()+"jpg").toURI().toString()));
                    //                 nameArtist.setText(artist.firstname + " " + artist.lastname);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Main.mainStage.setScene(s);
                //             userprofile.setText(Main.mainUser.user);
            }else{
                errorLogIn.setText("username or Password is not correct");
                errorLogIn.setVisible(true);
            }}


    }


    /*
     * these FXML are for Player in button Main Page
     *
     */
    @FXML
    ImageView play;
    @FXML
    ImageView previus;
    @FXML
    ImageView next;
    @FXML
    ImageView shuffle;
    @FXML
    ImageView playSongPhoto;
    @FXML
    Label namePlayer;
    @FXML
    Label ArtistPlayer;
    @FXML
    Slider sliderPlay;



    /*
     * these methods are for Player in button Main Page
    *
    */
    public void setPlayer() {
        Player.play = play;
        Player.previus = previus;
        Player.next = next;
        Player.shuffle = shuffle;
        Player.playSongPhoto = playSongPhoto;
        Player.namePlayer= namePlayer;
        Player.ArtistPlayer=ArtistPlayer;
        Player.sliderPlay=sliderPlay;
    }
    public void PlayPause(MouseEvent mouseEvent) {
        if(Player.isplay) {
            Player.pause();
        }else{
            Player.resume();
        }
    }
    public void nextPlay(MouseEvent mouseEvent) {
        Player.next();
    }
    public void repeat(MouseEvent mouseEvent) {
        Player.repeat();
    }
    public void PreviusPlay(MouseEvent mouseEvent) {
        Player.previuse();
    }
    public void ShufflePlay(MouseEvent mouseEvent) {
        if(Player.isSuffle) {
            Player.shuffleOFF();
            shuffle.setImage(new Image(new File("image\\shuffel.png").toURI().toString()));
        }else{
            Player.shuffleON();
            shuffle.setImage(new Image(new File("image\\shuffleOn.png").toURI().toString()));
        }

    }



    /*
    * these FXML are for ADD song at AddSongPage.fxml
    *
    */
    @FXML
    TextField titlesong;
    @FXML
    TextField artistsong;
    @FXML
    TextField albumsong;
    @FXML
    TextField genersong;
    @FXML
    TextField urlsong;
    @FXML
    TextField urlphotosong;
    @FXML
    Label messagesucces;



    /*
    * these metthods are for add Songs
    *
    */
    public void againaddSongs(MouseEvent mouseEvent) {
        titlesong.clear();
        albumsong.clear();
        artistsong.clear();
        urlsong.clear();
        genersong.clear();
        urlphotosong.clear();
        messagesucces.setVisible(false);

        //System.out.println(artist.user.toLowerCase());

    }
    public void addSongs(MouseEvent mouseEvent) {
        Main.songs.add(new Song(titlesong.getText(),artistsong.getText(),albumsong.getText(),genersong.getText()));

        messagesucces.setVisible(true);
    }



    /*
    * these metthods are for Show a new Stage for  Browse new Song
    *
    */

    public void setAddUrlSong(MouseEvent mouseEvent) {

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Song Files", "*.mp3", "*.avi", "*.wav");
        fileChooser.getExtensionFilters().add(extFilter);
        File scr = fileChooser.showOpenDialog(Main.mainStage);
        OutputStream out  = null;
        try {
            out = new FileOutputStream("server\\songs\\"+ titlesong.getText()+".mp3");

            byte[] bytes = Files.readAllBytes(scr.toPath());
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        urlsong.setText(scr.getPath());





    }
    public void setAddUrlPhotoSong(MouseEvent mouseEvent) {

        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        File scr = fileChooser.showOpenDialog(Main.mainStage);
        OutputStream out  = null;
        try {
            out = new FileOutputStream("image\\songs\\"+ titlesong.getText()+".jpg");

        byte[] bytes = Files.readAllBytes(scr.toPath());
        out.write(bytes);
    } catch (Exception e) {
        e.printStackTrace();
    }
        urlphotosong.setText(scr.getPath());
    }



    /*
    * these FXML are for ArtistPage
    *
    */
    @FXML
    ImageView photoartist;
    @FXML
    Label nameArtist ;


    /*
    * these metthods are for Show Songs & Albums & Artists & ... at Main border in MainPage
    *
    */
    public void showArtistPage(Artist user) {
        subject.setText(user.firstname+ " " +user.lastname);

        circle.setVisible(true);

        circle.setFill(new ImagePattern(new Image(new File(user.pathPhoto).toURI().toString())));
        if (Platform.isSupported(ConditionalFeature.EFFECT)) {
            circle.setEffect(new DropShadow(10, Color.rgb(255, 255, 255, 0.8)));
        }
        showSongs(user.songs,flowPane);
        follow.setVisible(true);


    }
    public void showAlbumPage(Album album,boolean isShowArtist) {
        subject.setText(album.getName());
        imageArtistpage.setVisible(true);
        imageArtistpage.setImage(new Image(new File(album.path).toURI().toString()));

        circle.setVisible(true);
        if(isShowArtist) {
            Artist artist = new Artist("", "", "");
            for (int i = 0; i < Main.artists.size(); i++) {
                if (album.artist.equals(Main.artists.get(i).user))
                    artist = Main.artists.get(i);
            }
            circle.setFill(new ImagePattern(new Image(new File(artist.pathPhoto).toURI().toString())));

            if (Platform.isSupported(ConditionalFeature.EFFECT)) {
                circle.setEffect(new DropShadow(10, Color.rgb(255, 255, 255, 0.8)));
            }
        }else
            circle.setVisible(false);
        showSongs(album.songs,flowPane);


    }
    public  void showSongs(List<Song> list, FlowPane browsFlowPane) {
        browsFlowPane.getChildren().clear();

        for (int i = 0; i < list.size(); i++) {
            File file = new File(list.get(i).getPathPhoto());
            Image image = new Image(file.toURI().toString());

            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);

            //  imageView.setPreserveRatio(true);
            Label songName = new Label( list.get(i).getName());
            songName.setTextFill(Color.WHITE);
            Label artistName = new Label( list.get(i).getArtist());
            artistName.setMinWidth(75);
            artistName.setTextFill(Color.GRAY);
            HBox hBox= new HBox(0);
            Button addtoMysong = new Button( "+");
            addtoMysong.setTextFill(Color.WHITE);

            addtoMysong.setStyle(
                    "-fx-background-radius: 40;"+
                            "-fx-background-color: RGB(29,185,84);");
            int k=i;
            addtoMysong.setOnMouseClicked(event -> {

                if(!Main.mainUser.songs.contains(list.get(k)))
                    Main.mainUser.songs.add(list.get(k));
                Main.mainUser.save();
                addtoMysong.setStyle("-fx-background-color:RGB(226,84,87); ");
                addtoMysong.setText("-");
            });

            VBox songProperties = new VBox(10);
            songProperties.getChildren().add(imageView);
            songProperties.getChildren().add(songName);
            hBox.getChildren().add(artistName);
            hBox.getChildren().add(addtoMysong);
            songProperties.getChildren().add(hBox);
            songProperties.setStyle("-fx-padding: 10;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 5;" +
                    "-fx-border-radius: 5;" );

            int j = i;
            imageView.setOnMouseClicked(event -> {
                if(Player.playSongPhoto==null)
                    setPlayer();
                Player.Play(list,list.get(j));

            });
            browsFlowPane.getChildren().add(songProperties);

        }
    }
    public  void showArtist(List<Artist> list, FlowPane browsFlowPane) {
        browsFlowPane.getChildren().clear();

        for (int i = 0; i < list.size(); i++) {
            File file = new File(list.get(i).pathPhoto);
            Image image = new Image(file.toURI().toString());

            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);

            //  imageView.setPreserveRatio(true);
            Label songName = new Label( list.get(i).firstname);
            songName.setTextFill(Color.WHITE);
            Label artistName = new Label( list.get(i).lastname);
            artistName.setMinWidth(75);
            artistName.setTextFill(Color.WHITE);
            HBox hBox= new HBox(0);
            Button addtoMysong = new Button( "+");
            addtoMysong.setTextFill(Color.WHITE);

            addtoMysong.setStyle(
                    "-fx-background-radius: 40;"+
                            "-fx-background-color: RGB(29,185,84);");
            int k=i;
            addtoMysong.setOnMouseClicked(event -> {


                addtoMysong.setStyle("-fx-background-color:RGB(226,84,87); ");
                addtoMysong.setText("-");
            });


            VBox songProperties = new VBox(10);

            songProperties.getChildren().add(imageView);
            songProperties.getChildren().add(songName);
            hBox.getChildren().add(artistName);
            hBox.getChildren().add(addtoMysong);
            songProperties.getChildren().add(hBox);
            songProperties.setStyle("-fx-padding: 10;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 5;" +
                    "-fx-border-radius: 5;" );


            int j = i;
            imageView.setOnMouseClicked(event -> {
                showArtistPage(list.get(j));


            });
            browsFlowPane.getChildren().add(songProperties);
//            browsFlowPane.getChildren().add(pane);

        }
    }
    public  void showAlbums(List<Album> list, FlowPane browsFlowPane) {
        browsFlowPane.getChildren().clear();

        for (int i = 0; i < list.size(); i++) {
            File file = new File(list.get(i).path);
            Image image = new Image(file.toURI().toString());

            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);

            //  imageView.setPreserveRatio(true);
            Label songName = new Label( list.get(i).name);
            songName.setTextFill(Color.WHITE);
            Label artistName = new Label( list.get(i).artist);
            artistName.setMinWidth(75);
            artistName.setTextFill(Color.GRAY);
            HBox hBox= new HBox(0);
            Button addtoMysong = new Button( "+");
            addtoMysong.setTextFill(Color.WHITE);

            addtoMysong.setStyle(
                    "-fx-background-radius: 40;"+
                            "-fx-background-color: RGB(29,185,84);");
            int k=i;
            addtoMysong.setOnMouseClicked(event -> {


                addtoMysong.setStyle("-fx-background-color:RGB(226,84,87); ");
                addtoMysong.setText("-");
            });


            VBox songProperties = new VBox(10);

            songProperties.getChildren().add(imageView);
            songProperties.getChildren().add(songName);
            hBox.getChildren().add(artistName);
            hBox.getChildren().add(addtoMysong);
            songProperties.getChildren().add(hBox);
            songProperties.setStyle("-fx-padding: 10;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 5;" +
                    "-fx-border-radius: 5;" );


            int j = i;
            imageView.setOnMouseClicked(event -> {
                showAlbumPage(list.get(j),true);

            });
            browsFlowPane.getChildren().add(songProperties);
//            browsFlowPane.getChildren().add(pane);

        }
    }
    public  void showGeners(List<Album> list, FlowPane browsFlowPane) {
        browsFlowPane.getChildren().clear();

        for (int i = 0; i < list.size(); i++) {
            File file = new File(list.get(i).path);
            Image image = new Image(file.toURI().toString());

            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);

            //  imageView.setPreserveRatio(true);
            Label songName = new Label( list.get(i).name);
            songName.setTextFill(Color.WHITE);
            songName.setMinSize(75,20);
            VBox songProperties = new VBox(10);

            songProperties.getChildren().add(imageView);
            songProperties.getChildren().add(songName);


            songProperties.setStyle("-fx-padding: 10;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 5;" +
                    "-fx-border-radius: 5;" );


            int j = i;
            imageView.setOnMouseClicked(event -> {
                showAlbumPage(list.get(j),false);

            });
            browsFlowPane.getChildren().add(songProperties);


        }
    }

}
