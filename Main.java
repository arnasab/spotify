package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.util.Duration;
import sample.Playable.Album;
import sample.Playable.Song;

import sample.User.Artist;
import sample.User.Person;

import java.io.File;
import java.util.LinkedList;
import java.util.List;


public class Main extends Application {
    static Stage mainStage ;
    public static Person mainUser;
    static int width = 1400;
    static int height= 720 ;
    public static List<Song> songs = new LinkedList<>();
    public static List<Artist> artists = new LinkedList<>();
    public static List<Album> albums = new LinkedList<>();
    public static List<Album> geners = new LinkedList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainStage = primaryStage;
        primaryStage.getIcons().add(new Image(new File("image/icon.png").toURI().toString()));
        Parent root = FXMLLoader.load(getClass().getResource("Wellcome.fxml"));
        primaryStage.setTitle("Spotify");
        primaryStage.setScene(new Scene(root, width, height));
        Rectangle clip = new Rectangle(0, 1200);
        Timeline animate = new Timeline(
                new KeyFrame(Duration.millis(2000),
                        new KeyValue(clip.widthProperty(), 1310.0)));
        root.setClip(clip);
        // when animation finishes, remove clip:
        animate.setOnFinished(e -> root.setClip(null));
        animate.play();
        primaryStage.show();

        //    Alert for  exit :)
//        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
//            @Override
//            public void handle(WindowEvent event) {
//
//                // consume event
//                event.consume();
//
//                // show close dialog
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Close Confirmation");
//                alert.setHeaderText("Save changes and Exit ?");
//                alert.initOwner( primaryStage);
//
//                Optional<ButtonType> result = alert.showAndWait();
//                if (result.get() == ButtonType.OK){
//                    saveChanges();
//                    Platform.exit();
//                }
//            }
//        });

    }

    private static void saveChanges() {

        if(mainUser==null )
            return;
        mainUser.save();
        for(Song song:songs)
            song.saveSong();
        for (Artist artist:artists)
                artist.save();

    }


    public static void main(String[] args) {
        launch(args);

    }
}
