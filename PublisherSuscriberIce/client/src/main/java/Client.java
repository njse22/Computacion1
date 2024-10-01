import com.zeroc.Ice.*;
import Demo.SuscriberPrx;
import Demo.PublisherPrx;

public class Client {
    public static void main(String[] args) {

	///////////////////////////////////////////
	//  La conexión general con Ice en Java  //
	///////////////////////////////////////////
       try(Communicator communicator = Util.initialize(args, "properties.cfg") ) {

	   // Necesitamos un adapter para exponer los servicios del cleinte 
	   ObjectAdapter adapter = communicator.createObjectAdapter("Suscriber");

	   // Objetos que tienen implmentados esos servicios
	   SuscriberI suscriber = new SuscriberI();

	   // Exponer los servcios
	   ObjectPrx proxy = adapter.add(suscriber, Util.stringToIdentity("NotNecessaryName"));
	   adapter.activate();
	
	   // Recuperamos los objetos (que tienen servicios) que han sido expuestos por 
	   // otros 
	   SuscriberPrx suscriberPrx = SuscriberPrx.checkedCast(proxy);
	   PublisherPrx publisher = PublisherPrx.checkedCast(communicator.propertyToProxy("publisher.proxy"));

       }
    }
}
