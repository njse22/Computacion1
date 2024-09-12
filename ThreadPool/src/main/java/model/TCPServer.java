package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(3);

        try {
            // abre un puerto para la escucha
            ServerSocket serverSocket = new ServerSocket(5000);

            while (true){
                // aceptar la conexión del cliente
                System.out.println("Esperando conexión ...");
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado desde la IP: " + socket.getInetAddress()
                        + " Con el Puerto: " + socket.getPort());

                // delega a un ThreadPool la lectura de mensajes ( solicitud )
                pool.execute(new ClientHandler(socket));
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            pool.shutdown();
        }

    }
}
