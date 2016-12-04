package Client;
import java.util.HashMap;

import Connection.ConnectionClient;
import Connection.MakeRequest;
import model.Datagram;

public abstract class LoginWindow {
	final int port = 6666;
	private ConnectionClient client;
	private ClientHandler ch ;
	private MakeRequest requestmaker = new MakeRequest("Unknow");
	private boolean loginstatus = false;
	private String username;
	private Listener ls;
	
	//set the connection
	public LoginWindow(){
		//this.client = client;
	}
	
	//display the window TO DO
	protected abstract void showLoginWindow();
	
	//get the user name TO DO
	protected abstract String readUsername();
	
	//get the pass word TO DO
	protected abstract String readPassword();
	
	//show the message: incorrect password, successful, please register first, the name has been used TO DO
	public abstract void showMessage(String mesg);
	
	//just close this window, it will be called by the main TO DO
	public abstract void destory();
	
	
	//click the login button, then run this method
	protected void trylogin(String IP,GuiLoginWindow lw){
		
		
		client = new ConnectionClient(IP,port);
		
		if(client.getconnectionresult()){
			Datagram d = requestmaker.login(username = readUsername(), readPassword());
			client.senddatagram(d);
			ch = new ClientHandler();
			ch.setGuiLoginWindow(lw);
			ls = new Listener(client,ch);		
			ls.start();
		}else{
			client = null;
			lw.showMessage("Can not find the server");
			
		}


		
		
	}
	
	//click the register button, then run this method
	protected void tryregister(String IP,GuiLoginWindow lw){
		
		client = new ConnectionClient(IP,port);
		
		if(client.getconnectionresult()){
			Datagram d = requestmaker.register(username = readUsername(), readPassword());
			client.senddatagram(d);
			
			ch = new ClientHandler();
			ch.setGuiLoginWindow(lw);
			ls = new Listener(client,ch);		
			ls.start();
		}else{
			client = null;
			lw.showMessage("Can not find the server");
			
		}
		
		
//		Datagram d = requestmaker.register(readUsername(), readPassword());
//		client.senddatagram(d);

	}
	
	//get login status
	public boolean getstatus(){
		return loginstatus;
	}
	public void setstatus(boolean status){
		loginstatus = status;
	}
	public String getusername(){
		return username;
	}
	
	public ConnectionClient getclient(){
		return client;
	}

}
