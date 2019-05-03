package sample.User;


import sample.Playable.Song;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by asus on 6/7/2017.
 */
public class Artist extends Person {
    public String firstname = "";
    public String lastname = "" ;
    public String pathPhoto ;



    @Override
    public void save() {


        try {
            FileOutputStream f = new FileOutputStream("data\\users\\artists\\"+this.user);
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Artist(String email, String user, String pass) {
        super(email, user, pass);
        pathPhoto= "image\\artist\\"+user+".jpg";
    }
}
