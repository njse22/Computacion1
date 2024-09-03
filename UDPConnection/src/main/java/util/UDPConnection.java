package util;

import java.io.IOException;
import java.net.*;

public class UDPConnection extends Thread {

    private DatagramSocket socket;

    private static UDPConnection instance;

    private UDPConnection () {}

    public static UDPConnection getInstance(){
        if (instance == null){
            instance = new UDPConnection();
        }
        return instance;
    }

    public void setPort(int port) {
        try {
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {

        }
    }

    @Override
    public void run(){
        // Recepción
        try {
            // empaquetador de la información
            //                                         un arreglo de bytes | tamaño
            DatagramPacket packet = new DatagramPacket(new byte[24], 24);

            System.out.println("Waiting ....");
            // recivir la información, y almacenarla en el paquete
            this.socket.receive(packet);

            // decodificando la información
            String msj = new String(packet.getData()).trim();
            System.out.println(msj);

        } catch (SocketException e) {

        } catch (IOException e) {

        }
    }

    public void sendDatagram(String msj, String ipDest, int portDest){
        // Envio de Infromación
        try {
            InetAddress ipAddress = InetAddress.getByName(ipDest);

            // Empaquetador de la información
            // Encapsulamiento de los datos
            //                                         el mensaje     | length     | ip dest  | puerto destino
            DatagramPacket packet = new DatagramPacket(msj.getBytes(), msj.length(), ipAddress, portDest);
            // envia la información
            socket.send(packet);

        } catch (SocketException | UnknownHostException e) {

        } catch (IOException e) {

        }
    }

}
