package Client;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import model.Datagram;

public class ClientHandler {
	//private MakeRequest resuqestemaker = new MakeRequest();
	
	private GuiGroupWindow gw;
	private GuiLoginWindow lw;
	
	public void setGuiGroupWindow(GuiGroupWindow gw){
		this.gw = gw;
	}
	
	public void setGuiLoginWindow(GuiLoginWindow lw){
		this.lw = lw;
	}	
	
	public ClientHandler(){
		
	}	
	
	public  HashMap<String, String> getinformation(Datagram data){
		return data.getdatagram().get("information");
		
	}
	
	public  HashMap<String, String> getdata(Datagram data){
		return data.getdatagram().get("data");
		
	}
	public  String getname(Datagram data){
		return getinformation(data).get("name");
		
	}
	
	
	public void process(Datagram data){

		switch (getname(data)){
		case "replylogin": handlelogin(data); break;
		case "replyregister":handleregister(data) ;break;	
		case "replygetgroupmember": handlegetgroupmember(data);break;		
		case "replynewdisplayname": handlenewdisplayname(data);break;		
		case "friendmessagerelay": handlefriendmessagerelay(data);break ;
		case "groupmessagerelay": handlegroupmessagerelay(data);break;
		case "friendfilerelay": handlefriendfilerelay(data);break ;
		
	
		}
			
	}
	
	//write all method to show the input information
	//zhongxiao chen's job
	
	public void handlenewdisplayname(Datagram indata){
		HashMap<String, String> data = getdata(indata);
		gw.showDiaplayNameMessage(data.get("result"));
		
	}
	
	public void handlegetgroupmember(Datagram indata){
		
		HashMap<String, String> data = getdata(indata);
		int cnt = 1;
		
		Set<String> username = data.keySet();
		String[] us = username.toArray(new String[data.size()]);
		
		
		gw.showgroupmemberlist(us);
	}
	
	
	public void handlefriendmessagerelay(Datagram indata){			
		HashMap<String, String> data = getdata(indata);
		String src = data.get("src");
		String mesg = data.get("mesg");		
		if(gw.privatewindows.get(src) == null){
			gw.startnewprivatewindow(src);
		}	
		GuiPrivateWindow pw = gw.privatewindows.get(src);	
		String display = src + ":" + "\n " + mesg;
		pw.displayIncomingTextTowindow(display);			
		
		
	}
	
	public void handlegroupmessagerelay(Datagram indata){
		HashMap<String, String> data = getdata(indata);
		String src = data.get("displayname");
		String mesg = data.get("mesg");
		String display = src + ":" + "\n " + mesg;
		gw.displayIncomingTextTowindow(display);	
	}
	
	
	public  void handlelogin(Datagram indata){
		HashMap<String, String> data = getdata(indata);
		if (data.get("result").equals("successful")) {
			lw.setstatus(true);
	
			GuiGroupWindow gw = new GuiGroupWindow(lw.getclient(),lw.getusername());
			lw.destory();
			this.setGuiGroupWindow(gw);
			gw.showGroupWindow();
					

		}else{
			lw.showMessage(data.get("result"));
			lw.getclient().close();
		}
		
	}
	
	
	public  void handleregister(Datagram indata){
		HashMap<String, String> data = getdata(indata);

			lw.showMessage(data.get("result"));
			lw.getclient().close();
			
		
	}
	

	
	public void handlefriendfilerelay(Datagram indata){
		

		HashMap<String, String> data = getdata(indata);
		String src = data.get("src");
		String mesg = data.get("mesg");		
		if(gw.privatewindows.get(src) == null){
			gw.startnewprivatewindow(src);
		}	
		GuiPrivateWindow pw = gw.privatewindows.get(src);	

		try {	
			
			
			
			String filename = data.get("filename");			
			String dst = "Receive";				
			File theDir = new File("Receive");
			theDir.mkdir();
			FileOutputStream fout = new FileOutputStream((dst+"/"+filename),false);
			BufferedOutputStream buffout = new BufferedOutputStream(fout);
			DataOutputStream dataout = new DataOutputStream(buffout);
			String filecontent = data.get("filecontent");		
			byte[] con = filecontent.getBytes();	
			for (int i = 0; i < con.length/2; i++) {
				dataout.writeByte(con[2*i] + (con[2*i+1]<<4) );

			}			
			System.out.println("received string length" +filecontent.length() );
			dataout.flush();
			buffout.flush();
			fout.flush();
			dataout.close();
			buffout.close();
			fout.close();			
//			String src = data.get("src");
//			if(gw.privatewindows.get(src) != null){
//				gw.startnewprivatewindow(src);
//			}	
//			GuiPrivateWindow pw = gw.privatewindows.get(src);	
			String display = src + " send a file to you and it has been put into Receive folder";
			pw.displayIncomingTextTowindow(display);	
			
		}catch (IOException e) {
				System.out.println("Error -- " + e.toString());
			}	
		
	}

}
