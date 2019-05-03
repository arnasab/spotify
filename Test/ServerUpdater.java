package sample.Test;

import org.junit.Assert;
import org.junit.Test;
import sample.Server.Client;

/**
 * Created by asus on 6/24/2017.
 */
public class ServerUpdater {
    @Test
    public void testArtsit(){
        Assert.assertTrue(sample.Server.ServerUpdater.isIsUpdateArtist());
    }

    @Test
    public void testContact(){
        Assert.assertTrue(sample.Server.ServerUpdater.isIsUpdateContact());
    }
}
