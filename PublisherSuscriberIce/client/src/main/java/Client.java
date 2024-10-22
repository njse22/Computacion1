import com.zeroc.Ice.*;
import Demo.SuscriberPrx;
import Demo.PublisherPrx;

import java.io.*;

public class Client {
    public static void main(String[] args) {

	///////////////////////////////////////////
	//  La conexión general con Ice en Java  //
	///////////////////////////////////////////
       try(Communicator communicator = Util.initialize(args, "properties.cfg") ) {

	   System.out.println("Give me a name: "); 
	   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	   String name = reader.readLine();
	   reader.close();

	   // Necesitamos un adapter para exponer los servicios del cleinte 
	   ObjectAdapter adapter = communicator.createObjectAdapter("Suscriber");

	   // Objetos que tienen implmentados esos servicios
	   SuscriberI suscriber = new SuscriberI();

	   // Exponer los servcios
	   ObjectPrx proxy = adapter.add(suscriber, Util.stringToIdentity("NotNecessaryName"));
	   adapter.activate();
	
	   // Recuperamos los objetos (que tienen servicios) que han sido expuestos por 
	   // otros 

	   // Se recupera el Suscriber del mismo cliente 
	   SuscriberPrx suscriberPrx = SuscriberPrx.checkedCast(proxy);

	   // Se recupera el Publisher del servidor
	   PublisherPrx publisher = PublisherPrx.checkedCast(communicator.propertyToProxy("publisher.proxy"));

	   if(publisher == null){
	       throw new Error("Publisher is Null"); 
	   }


	   publisher.addSuscriber(name, suscriberPrx);

	   publisher.onEcho("Hola desde: " + name);
	   communicator.waitForShutdown();

       }
       catch(IOException e){
	   e.printStackTrace();
       }
    }
}
