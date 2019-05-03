package sample.User;

import sample.Playable.Song;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ali on 6/3/17.
 */
public abstract class Person implements Serializable{
    public String email ="";
    public String user="";
    public String Pass = "";
//    public boolean isArtist = false;
   public List<Song> songs = new ArrayList<>();
    List<Song> albums = new LinkedList<>();
   public abstract void save();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (user != null ? !user.equals(person.user) : person.user != null) return false;
        return Pass != null ? Pass.equals(person.Pass) : person.Pass == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (Pass != null ? Pass.hashCode() : 0);
        return result;
    }

    public Person(String email, String user, String pass) {

        this.email = email;
        this.user = user;
        Pass = pass;
    }



}
