package sample.User;

import sample.Playable.PlayList;
import sample.Playable.Song;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by asus on 6/7/2017.
 */
public class Contact extends Person {
    @Override
    public void save() {
        FileOutputStream f = null;
        try {
            f = new FileOutputStream("data\\users\\contacts\\"+this.user);
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(this);
            out.close(); }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Contact(String email, String user, String pass) {
        super(email, user, pass);
    }


   public List<PlayList> playLists = new ArrayList<>();
    public List<Song> recntlyPlay = new LinkedList<>();
    public List<Artist> follower = new ArrayList<>();


}
