package Client;
import java.util.HashMap;

import Connection.ConnectionClient;
import Connection.MakeRequest;
import model.Datagram;

public abstract class GroupWindow {
	
	private ConnectionClient client;
//	private ClientHandler ch = new ClientHandler(gw);
	protected MakeRequest requestmaker;
	public HashMap<String,GuiPrivateWindow> privatewindows = new HashMap<String,GuiPrivateWindow>();
	
	
	
	public GroupWindow(ConnectionClient client,String name){
		 this.client = client;
		 requestmaker = new MakeRequest(name);
		 //displayname is the same as username as defualt
		 requestmaker.setdisplayname(name);
	}
	
	
	public void setdisplayname(String name){
		

	}	

	//write the string to the left
	protected abstract void displayIncomingTextTowindow(String s);
	
	//write the string to the right
	protected abstract void displayOutcomingTextTowindow(String s);
	
	//show error message whil changing the display name: Name Used, Invalid 
	public abstract void showDiaplayNameMessage(String s);
	
	protected abstract String getNewDisplayName();
	
	//every string in s is the information of a user, format "username displayname"
	protected abstract void showgroupmemberlist(String[] s);
	
	protected abstract void showGroupWindow();
	
	public void getgroupmember(){
		Datagram d = requestmaker.getgroupmember();
		client.senddatagram(d);
	}
	
	//this will be called when a IncomingText is got
	public void writeIncomingText(String s){
		displayIncomingTextTowindow(s);
	}
	
	//run this after click send
	protected void writeOutcomingText(String s){
		Datagram d = requestmaker.sendgroupmessage(s);
			
		client.senddatagram(d);
	}
	
	protected void setDisplayName(String s){	
		Datagram d = requestmaker.newdisplayname(s);
		client.senddatagram(d);
	}
	
	//start new private window
	public void startnewprivatewindow(String username){
		if(username.equals(requestmaker.getsrc())){
			
		}else{
			if(privatewindows.get(username) == null){
				privatewindows.put(username,new GuiPrivateWindow(client,username,requestmaker,privatewindows) );
			}
		}
	}


	protected void setNewDisplayName(String name) {
		// TODO Auto-generated method stub
		
	}
	
	protected void requestgroupmember(){
		Datagram d = requestmaker.getgroupmember();
		client.senddatagram(d);
	}


}
