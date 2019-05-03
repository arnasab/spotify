package sample.Server;

import sample.Main;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by ali on 6/3/17.
 */
public class Client {
    public static boolean isIsConnect() {
        return isConnect;
    }

    static boolean isConnect = false;
    public static void main(String[] args) {
        Socket socket = null;
        try {
           socket  = new Socket("localhost",1234);
           isConnect=true;
            Main.main(args);


        } catch (Exception e) {
            while (true){
                Thread thread = Thread.currentThread();
                try {

                    socket  = new Socket("localhost",1234);
                    isConnect=true;
                    Main.main(args);

                } catch (UnknownHostException e1) {
                    System.out.println("Waiting Connection...");
                    if(socket.isClosed()){
                        System.out.println("Socket is closed");
                    }
                } catch (IOException e1) {
                    System.out.println("Waiting Connection...");
                }
            }
        }
    }
}
