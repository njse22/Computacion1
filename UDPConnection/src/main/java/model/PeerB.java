package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class PeerB {
    public static void main(String[] args) {

        try {
            // socket habilita la capacidad de escuchar por este puerto
            DatagramSocket socket = new DatagramSocket(5001);

            // empaquetador de la información
            //                                         un arreglo de bytes | tamaño
            DatagramPacket packet = new DatagramPacket(new byte[16], 16);

            System.out.println("Waiting ....");
            // recivir la información, y almacenarla en el paquete
            socket.receive(packet);

            // decodificando la información
            //String msj = new String(packet.getData()).trim();
            //System.out.println(msj);

            // forma alternativa de decodificar el mensaje
            for (byte b : packet.getData()){
                System.out.print(Character.toString((char) b));
            }


        } catch (SocketException e) {

        } catch (IOException e) {

        }


    }
}
