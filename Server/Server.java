package sample.Server;

import sample.Main;
import sample.Playable.Album;
import sample.Playable.Song;
import sample.User.Artist;
import sample.User.Contact;
import sample.User.Person;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by ali on 6/3/17.
 */
public class Server {
    static String src = "data/Contacts.txt";
    static String srcArtist = "data/Artist.txt";

    public static void main(String[] args) {
        try
                (ServerSocket socketsSrv = new ServerSocket(1234)) {
            Socket socket = socketsSrv.accept();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addContact(Contact user) {
        try (
                FileWriter fileWriter = new FileWriter(src,true);) {
            fileWriter.write(user.email + "$" + user.user + "$" + user.Pass + "\n");
            user.save();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addArtist(Artist user) {
        try (
                FileWriter fileWriter = new FileWriter(srcArtist,true);) {
            fileWriter.write(user.email + "$" + user.user + "$" + user.Pass + "\n");
            user.save();
//            FileOutputStream f = new FileOutputStream("data\\users\\artists\\"+user.user);
//            ObjectOutputStream out = new ObjectOutputStream(f);
//            out.writeObject(user);
//            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkUser(Person user) {


        List<String> lines = null;

        try {
            lines = Files.readAllLines(Paths.get(src));

        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).contains(user.email) || lines.get(i).contains(user.user))
                if (lines.get(i).substring(lines.get(i).lastIndexOf('$') +1).equals(user.Pass))
                    return true;
        }


        return false;
    }

    public static boolean checkArtist(Person user) {



        List<String> linesartist = null;
        try {

            linesartist = Files.readAllLines(Paths.get(srcArtist));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < linesartist.size(); i++) {
            if (linesartist.get(i).contains(user.email) || linesartist.get(i).contains(user.user))
                if (linesartist.get(i).substring(linesartist.get(i).lastIndexOf('$') +1).equals(user.Pass))
                    return true;
        }

        return false;
    }




}


