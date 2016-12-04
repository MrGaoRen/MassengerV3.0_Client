package Client;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class RunClient {
	
	public synchronized static void main(String[] arg) throws InterruptedException{
		System.out.println("Client Start");
		Scanner s = new Scanner(System.in);
		
		InetAddress hostip = null;
		try {
			hostip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		GuiLoginWindow lw = new GuiLoginWindow();
		lw.showLoginWindow();		

	}

}
