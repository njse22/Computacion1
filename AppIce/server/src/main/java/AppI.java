public class AppI implements Demo.Printer {
    
    public void printString(String s, com.zeroc.Ice.Current current){
        System.out.println(s);
    }
}