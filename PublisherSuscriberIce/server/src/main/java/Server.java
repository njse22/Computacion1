import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import com.google.gson.*;

public class Server {
    public static void main(String[] args) {

	/////////////////////////////////////////////
	//  Gnereal configuration for Ice in java  //
	/////////////////////////////////////////////
        try(Communicator communicator = Util.initialize(args, "properties.cfg")){
            ObjectAdapter adapter = communicator.createObjectAdapter("services");

	    Gson gson =  new Gson();

            // Creación de los servicios de Ice
	    // Se pueden caragar con el Object de Ice: 
            // com.zeroc.Ice.Object object = IceDefinitionServices();
	    // O se pueden crear contra el Objeto especifico implementado: 
            PublisherI publisher = new PublisherI();

            // Agregar los servicios al adapter de ice
	    // adapter -> server stub 
            adapter.add(publisher, Util.stringToIdentity("Publisher"));

            // activate adapter
            adapter.activate();

	    // configuraciones adicionales del proyecto 
	    System.out.println("Envia un mensaje con el formato: NameSuscribe::Mensaje"); 
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	    String msg = "";

	    while( (msg = reader.readLine()) != null){
		String[] command = msg.split("::"); 
		publisher.notifySuscriber(command[0], command[1]); 
	    }

	    // Communicator de Ice, DEBE esperar a cerrar la conexión 
	    communicator.waitForShutdown(); 
	    reader.close();


	}
	catch (IOException e) {
	    e.printStackTrace(); 
	}
    }
}
