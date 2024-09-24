import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter; 
import com.zeroc.Ice.Util;
import com.zeroc.Ice.Object;

public class Server{

    public static void main(String[] args){
        
        try(Communicator communicator = Util.initialize(args)){
            ObjectAdapter adapter =
                communicator.createObjectAdapterWithEndpoints("SimplePrinterAdapter", "default -p 10000");
            
            
            // cuidado con el Object de Java ... 
            Object object = new AppI();
            
            adapter.add(object, Util.stringToIdentity("SimplePrinter"));
            adapter.activate();
            communicator.waitForShutdown();
        }
    }
}