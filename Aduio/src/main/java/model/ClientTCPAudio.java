package model;

import javax.sound.sampled.*;
import java.io.*;
import java.net.Socket;

public class ClientTCPAudio {
    public static void main(String[] args) {
        try {

            // conexión con el servidor
            Socket socket = new Socket("127.0.0.1", 5000);

            // Definir el formato de audio
            AudioFormat format = new AudioFormat(44100, 16, 1, true, true);

            // Optener información del microfono -> conexión con el hardware especifico
            DataLine.Info infoMicrophone = new DataLine.Info(TargetDataLine.class, format);

            // Conexión con el microfono
            TargetDataLine microphone = (TargetDataLine) AudioSystem.getLine(infoMicrophone);

            // abrir el microfono
            microphone.open(format);
            // el microfono empieza a escuchar
            microphone.start();

            // Conexión del audio con el socket
            OutputStream os = socket.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);

            // Codificar el audio en bytes
            byte[] buffer = new byte[10240];
            while (true){
                int byteRead = microphone.read(buffer, 0, buffer.length);

                // Enviarlo por el socket
                bos.write(buffer, 0, byteRead);
                bos.flush();
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }


}
