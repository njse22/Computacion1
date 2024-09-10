package model;

import java.io.IOException;
import java.net.*;

public class PeerC {
    public static void main(String[] args) {
        // Envio de Infromación
        try {
            DatagramSocket socket = new DatagramSocket();
            String msj = "Hola desde PeerC";
            InetAddress ipAddress = InetAddress.getByName("172.30.183.248");

            // Empaquetador de la información
            // Encapsulamiento de los datos
            //                                         el mensaje     | length     | ip dest  | puerto destino
            DatagramPacket packet = new DatagramPacket(msj.getBytes(), msj.length(), ipAddress, 5000);
            // envia la información
            socket.send(packet);

        } catch (SocketException | UnknownHostException e) {

        } catch (IOException e) {

        }
    }
}
