package model;

import java.io.IOException;
import java.net.*;

public class PeerA {

    // hilo principal del lenguaje
    public static void main(String[] args) {
        // mensaje a enviar
        // longitud del mensaje
        // puerto destino
        // ip destino

        // Envio de Infromación
        try {
            DatagramSocket socket = new DatagramSocket();
            String msj = "Hola desde PeerA";
            InetAddress ipAddress = InetAddress.getByName("172.30.183.248");

            // Empaquetador de la información
            // Encapsulamiento de los datos
            //                                         el mensaje     | length     | ip dest  | puerto destino
            DatagramPacket packet = new DatagramPacket(msj.getBytes(), msj.length(), ipAddress, 5001);
            // envia la información
            socket.send(packet);

        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();

        } catch (IOException e) {

        }


        // Recepción de la información
        try {
            // socket habilita la capacidad de escuchar por este puerto
            DatagramSocket socket = new DatagramSocket(5000);

            // empaquetador de la información
            //                                         un arreglo de bytes | tamaño
            DatagramPacket packet = new DatagramPacket(new byte[30], 30);

            System.out.println("Waiting ....");
            // recivir la información, y almacenarla en el paquete
            socket.receive(packet);

            // decodificando la información
            String msj = new String(packet.getData()).trim();
            System.out.println(msj);

        } catch (SocketException e) {

        } catch (IOException e) {

        }



    }



}
