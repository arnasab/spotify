package sample.Playable;

import sample.Main;
import sample.User.Person;

import java.io.*;
import java.util.List;

/**
 * Created by asus on 6/7/2017.
 */
public class Playable implements Serializable {
    public String name;
    //String path;
    public String artist;

    public Playable(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Playable playable = (Playable) o;

        if (name != null ? !name.equals(playable.name) : playable.name != null) return false;
        return artist != null ? artist.equals(playable.artist) : playable.artist == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }
}
