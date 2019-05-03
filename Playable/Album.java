package sample.Playable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by asus on 6/7/2017.
 */
public class Album extends Playable {
   public List<Song> songs = new LinkedList<>();
   public String path;

    public Album(String name, String artist) {
        super(name, artist);

        path="image\\albums\\"+name+".jpg";
    }
    public void addSong(Song song){
        songs.add(song);
    }

    public  void save() {


        try {
            FileOutputStream f = new FileOutputStream("data\\albums\\"+name);
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
