import com.zeroc.Ice.Current; 

public class AppI implements Demo.Printer {
    
    public void printString(String s, Current current){
        System.out.println(s);
    }
}