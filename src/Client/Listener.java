package Client;
import Connection.ConnectionClient;
import model.Datagram;

public class Listener extends Thread {
	ConnectionClient client;
	ClientHandler ch;
	public Listener(ConnectionClient client, ClientHandler ch){
		this.client = client;
		this.ch = ch;
	}
	
	
	public void run(){
		try{
			while(true){
				Datagram d = client.receivedatagram();
				System.out.println(d.getdatagram());
				ch.process(d);
		}
		}catch(Exception e){
			this.stop();	
		}
	}
}
