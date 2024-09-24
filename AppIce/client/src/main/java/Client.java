import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Util;
import com.zeroc.Ice.ObjectPrx;

public class Client {
    public static void main(String[] args){
        try(Communicator communicator = Util.initialize(args)){
        com.zeroc.Ice.ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -p 10000");

        Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);
        
        if(printer == null){
            throw new Error("Invalid proxy");
        }
        
        printer.printString("Hola Mundo!!");
        
        }
    } 
}
