package model;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // lectura de mensajes ..
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String msg = "";
            while ( (msg = reader.readLine()) != null){
                System.out.println("Cliente: " + msg);
                // Echo response
                writer.println("ECHO >>>>" + msg);
            }
            reader.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
