package model;

import java.io.*;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        try {
            // creo la conexión con el servidor
            System.out.println("Conectando al servidor ...");
            Socket socket = new Socket("127.0.0.1", 5000);
            System.out.println("Servidor conectado");

            // entrade de información (consola)
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            // lee la información que llega del socket
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // envio por el canal de conexión (socket)
            // BufferedWriter writerBF = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String msg = "";
            System.out.println("Escribe un mensaje para el servidor: ");
            while ( (msg = reader.readLine()) != null && !msg.equalsIgnoreCase("exit")){
                writer.println(msg);
                String response = socketReader.readLine();
                System.out.println(response);
            }

            // Cerrar los buffers y los canales
            // para escribir información en la consola
            // y enviarlos por el socket
            reader.close();
            writer.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
