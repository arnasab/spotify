package sample.Test;

import org.junit.Assert;
import org.junit.Test;
import sample.Server.Client;

/**
 * Created by asus on 6/24/2017.
 */
public class TestConnect {
    @Test
   public void test1(){
        Assert.assertFalse(Client.isIsConnect());
    }
}
