package model;

import util.UDPConnection;

public class PeerD {
    public static void main(String[] args) {
        UDPConnection connection = UDPConnection.getInstance();
        // puerto de escucha | recepción
        connection.setPort(5001);
        // se inicializa el hilo de escucha | hilo de recepción
        connection.start();

        //                                                                    puerto de envio
        connection.sendDatagram("Hola desde el peerD", "127.0.0.1", 5000);
    }
}
