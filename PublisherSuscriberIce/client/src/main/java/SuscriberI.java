import com.zeroc.Ice.Current;

public class SuscriberI implements Demo.Suscriber {
    
    @Override
    public void onUpdate(String msg, Current current){
	System.out.println(msg);
    }
}
