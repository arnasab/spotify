package sample.Server;

import sample.User.Artist;
import sample.User.Contact;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by asus on 6/24/2017.
 */
public class ServerUpdater {
    public static boolean isIsUpdateArtist() {
        return isUpdateArtist;
    }

    public static boolean isIsUpdateContact() {
        return isUpdateContact;
    }

    static boolean isUpdateArtist = false ;
    static boolean isUpdateContact = false ;

    public static void upadateArtist(Artist user) {
        try {
            FileOutputStream f = new FileOutputStream("data\\users\\artists\\"+user.user);
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(user);
            out.close();
            isUpdateArtist = true;


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void upadatecontact(Contact user) {
        try {
            FileOutputStream f = new FileOutputStream("data\\users\\contacts\\"+user.user);
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(user);
            out.close();
            isUpdateContact = true ;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
