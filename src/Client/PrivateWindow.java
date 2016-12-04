package Client;
import Connection.ConnectionClient;
import Connection.MakeRequest;
import model.Datagram;

public abstract class PrivateWindow {
	private ConnectionClient client;
	
	private String dst;
	private MakeRequest requestmaker;
	//write the string to the left
	protected abstract void displayIncomingTextTowindow(String s);
	
	//write the string to the right
	protected abstract void displayOutcomingTextTowindow(String s);
	
	//this will be called when a IncomingText is got
	public void writeIncomingText(String s){
		displayIncomingTextTowindow(s);
	}
	
	//run this after click send
	protected void writeOutcomingText(String s){
		Datagram d = requestmaker.sendfriendmessage(dst, s);
		client.senddatagram(d);
		//displayOutcomingTextTowindow(s);		
	}
	
	
	public PrivateWindow(ConnectionClient client, String dst, MakeRequest requestmaker){
		this.client = client;
		this.dst = dst;
		this.requestmaker = requestmaker;
	}

	public void sendfile(String file){
		Datagram d =  requestmaker.sendfriendfile(dst, file);
		client.senddatagram(d);
	}
	
	protected abstract void showPrivateWindow();


}
