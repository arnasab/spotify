package sample.Server;

import sample.Main;
import sample.Playable.Album;
import sample.Playable.Song;
import sample.User.Artist;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by asus on 6/24/2017.
 */
public class ServerLoader {
    static boolean isLoadSong = false;
    static boolean isLoadArtist = false ;
    static boolean isLoadAlbum = false ;
    static boolean isLoadGenre = false ;

    public static boolean isIsLoadSong() {
        return isLoadSong;
    }

    public static boolean isIsLoadArtist() {
        return isLoadArtist;
    }

    public static boolean isIsLoadAlbum() {
        return isLoadAlbum;
    }

    public static boolean isIsLoadGenre() {
        return isLoadGenre;
    }

    public static void loadSongs(){
        try {
            File[] files = new File("data\\songs").listFiles();

            for (File file : files) {
                if (file.isFile()) {
                    FileInputStream f = new FileInputStream("data\\songs\\"+file.getName());
                    ObjectInputStream in = new ObjectInputStream(f);
                    Main.songs.add((Song) in.readObject());
                }
            }
            isLoadSong = true ;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void loadArtists(){
        try {

            File[] files = new File("data\\users\\artists").listFiles();

            for (File file : files) {
                if (file.isFile()) {
                    FileInputStream f = new FileInputStream("data\\users\\artists\\"+file.getName());
                    ObjectInputStream in = new ObjectInputStream(f);
                    Main.artists.add((Artist) in.readObject());
                }
            }
            for (int i = 0; i < Main.songs.size(); i++) {
                for (int j = 0; j < Main.artists.size(); j++) {
                    if(Main.songs.get(i).artist.equals(Main.artists.get(j).user))
                        Main.artists.get(j).songs.add(Main.songs.get(i));
                }


            }
            isLoadArtist = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadAlbums(){
        try {
            for (int i = 0; i < Main.songs.size(); i++) {
                Album album = new Album(Main.songs.get(i).getAlbum(),Main.songs.get(i).getArtist());
                if(!Main.albums.contains(album)||Main.albums==null)
                {
                    Main.albums.add(album);
                    album.addSong(Main.songs.get(i));}

                else {
                    Main.albums.get(Main.albums.indexOf(album)).addSong(Main.songs.get(i));
                }
                isLoadAlbum = true;


            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadGeners(){
        try {List<Album> geners = Main.geners;
            for (int i = 0; i < Main.songs.size(); i++) {
                Album album = new Album(Main.songs.get(i).getGenre(),Main.songs.get(i).getArtist());

                if(!geners.contains(album)|| geners ==null)
                {
                    geners.add(album);
                    album.addSong(Main.songs.get(i));}

                else {
                    geners.get(geners.indexOf(album)).addSong(Main.songs.get(i));
                }



            }isLoadGenre = true;

//            File[] files = new File("data\\geners").listFiles();
//
//            for (File file : files) {
//                if (file.isFile()) {
//                    FileInputStream f = new FileInputStream("data\\geners\\"+file.getName());
//                    ObjectInputStream in = new ObjectInputStream(f);
//                    geners.add((Album) in.readObject());
//                }
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
