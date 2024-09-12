package connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPConnection extends Thread {

    private static TCPConnection instance;
    private TCPConnection(){}

    private Socket socket;
    private OnMessageReceivedListener listener;

    public synchronized static TCPConnection getInstance(){
        if(instance == null){
            instance = new TCPConnection();
        }
        return instance;
    }

    @Override
    public void run(){
        try {
            // Recepción
            // Conecto el lenguaje con la fuente de información
            InputStream is = socket.getInputStream();

            // Empaqueto para que el lenguaje la pueda interpretar
            InputStreamReader isr = new InputStreamReader(is);

            // Se la entrego a un lector de la información
            BufferedReader reader = new BufferedReader(isr);

            String line = reader.readLine();

            listener.onMessageReceived(line);

        }catch (IOException e){

        }
    }

    public void sendMessage(String message){
        new Thread( ()-> {
            try {
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter writer = new BufferedWriter(osw);

                writer.write(message+"\n");
                writer.flush();
            } catch (IOException e) {

            }
        } ).start();
    }

    public void initAsServer(int port){
        try {
            this.socket = new ServerSocket(port).accept();
        } catch (IOException e) {

        }
    }

    public void initAsClient(String remoteIp, int remotePort){
        try {
            this.socket = new Socket(remoteIp, remotePort);
        } catch (IOException e) {

        }
    }

    public void setListener(OnMessageReceivedListener listener) {
        this.listener = listener;
    }

    public interface OnMessageReceivedListener{
        public void onMessageReceived(String msg);
    }


}
