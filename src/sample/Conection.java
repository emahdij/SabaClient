package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mehdi on 4/16/17.
 */
public class Conection {
    public static String Ping(String in) {
        String m = "";
        try {
            Socket socket = new Socket("localhost", 2466);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader getfile = new java.io.BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter.println(in);
            m = getfile.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Connection refused";
        }

        return m;
    }
}
