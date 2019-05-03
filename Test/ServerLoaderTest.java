package sample.Test;

import org.junit.Assert;
import org.junit.Test;
import sample.Server.Client;
import sample.Server.ServerLoader;

/**
 * Created by asus on 6/24/2017.
 */
public class ServerLoaderTest {
    @Test
    public void testAlbum(){
        Assert.assertFalse(ServerLoader.isIsLoadAlbum());
    }

    @Test
    public void testArtist(){
        Assert.assertFalse(ServerLoader.isIsLoadArtist());
    }
    @Test
    public void testGenre(){
        Assert.assertFalse(ServerLoader.isIsLoadGenre());
    }
    @Test
    public void testSong(){
        Assert.assertFalse(ServerLoader.isIsLoadSong());
    }
}
