package model;

import connection.TCPConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP implements TCPConnection.OnMessageReceivedListener {

    public static void main(String[] args) {
        TCPConnection serverConnection = TCPConnection.getInstance();
        serverConnection.initAsServer(5000);

        ServerTCP server = new ServerTCP();

        serverConnection.setListener(server);
        serverConnection.start();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = "";
            while ( (line = reader.readLine()) != null ){
                serverConnection.sendMessage(line);
            }

        }catch (IOException e){

        }
    }

    @Override
    public void onMessageReceived(String msg) {
        System.out.println(msg);
    }


    // public static void main(String[] args) {
   //     try {
   //         // Recepción

   //         // Creación del socket -> Abrinedo el puerto 5000
   //         Socket socket = new ServerSocket(5000).accept();

   //         // Conecto el lenguaje con la fuente de información
   //         InputStream is = socket.getInputStream();

   //         // Empaqueto para que el lenguaje la pueda interpretar
   //         InputStreamReader isr = new InputStreamReader(is);

   //         // Se la entrego a un lector de la información
   //         BufferedReader reader = new BufferedReader(isr);

   //         String line = reader.readLine();
   //         System.out.println(line);

   //     } catch (IOException e) {

   //     }
   // }

}
