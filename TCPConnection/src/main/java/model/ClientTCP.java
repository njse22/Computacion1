package model;

import connection.TCPConnection;

import java.io.*;
import java.net.Socket;

public class ClientTCP {

    public static void main(String[] args) {
        TCPConnection clientConnection = TCPConnection.getInstance();
        clientConnection.initAsClient("127.0.0.1", 5000);

        clientConnection.setListener( message -> {
            System.out.println(message);
        });

        clientConnection.start();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String line = "";
            while ( (line = reader.readLine()) != null ){
                clientConnection.sendMessage(line);
            }

        }catch (IOException e){

        }

    }












    //public static void main(String[] args) {
    //    try {
    //        Socket socket = new Socket("127.0.0.1", 5000);

    //        OutputStream os = socket.getOutputStream();
    //        OutputStreamWriter osw = new OutputStreamWriter(os);
    //        BufferedWriter writer = new BufferedWriter(osw);

    //        String line = "Hola desde el cleinte";

    //        writer.write(line+"\n");
    //        writer.flush();

    //    } catch (IOException e) {

    //    }

    //}
}
