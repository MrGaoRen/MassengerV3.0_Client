package Connection;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import model.Datagram;

public class MakeRequest extends AllRequest {
	private String src;
	private String displayname;
	
	public MakeRequest(String name){
		src = name;
	}
	
	public void setdisplayname(String name){
		displayname = name;
	}
	
	public String getdisplayname(){
		return displayname;
	}	
	
	@Override
	public Datagram login(String username, String password) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("username", username);
		data.put("password", password);
		Datagram res = new Datagram("requestlogin",data);
			
		return res;
	}

	@Override
	public Datagram register(String username, String password) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("username", username);
		data.put("password", password);
		Datagram res = new Datagram("requestregister",data);
		return res;
	}

	
	@Override	
	public Datagram getgroupmember(){
		HashMap<String, String> data = new HashMap<String, String>();		
		Datagram res = new Datagram("requestgetgroupmember",data);	
		return res;
		
	}
	

	@Override
	public Datagram sendfriendmessage(String dst, String mesg) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("dst", dst);
		data.put("mesg", mesg);
		Datagram res = new Datagram("requestsendfriendmessage",data);	
		data.put("src", src);


		return res;
	}

	@Override
	public Datagram sendgroupmessage(String mesg) {
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("mesg", mesg);
		data.put("src", src);
		data.put("displayname", displayname);
		Datagram res = new Datagram("requestsendgroupmessage",data);	


		return res;
	}
	
	
	

	@Override
	public Datagram sendfriendfile(String dst, String filename) {
		
		
		try {
			FileInputStream fin = new FileInputStream(filename);
			
			BufferedInputStream buffin = new BufferedInputStream(fin);			
			HashMap<String, String> data = new HashMap<String, String>();
			DataInputStream datain = new DataInputStream(buffin);	
			data.put("dst", dst);	
			
			Path p = Paths.get(filename);
			String realfilename = p.getFileName().toString();
			data.put("filename", realfilename);	
			System.out.println("filelength"  + datain.available());
			data.put("filelength", "" + datain.available());	
			data.put("src", src);
			byte[] tempbyte = new byte[2*datain.available()];
			int i = 0;
		
			while (datain.available() > 0) {
				byte t = datain.readByte();
				tempbyte[2*i] = (byte) (t&15);
				tempbyte[2*i + 1] = (byte) ((t&240)>>4);
				i ++ ;
			}
	
			String content = new String(tempbyte,"US-ASCII");
			System.out.println("stringlength"  + content.getBytes().length);
			data.put("filecontent", content);		
			
			datain.close();
			buffin.close();
			fin.close();
			Datagram res = new Datagram("requestsendfriendfile",data);
						
			return res;
	
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Datagram newdisplayname(String newname){
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("newname", newname);
		Datagram res = new Datagram("requestnewdisplayname",data);	
		return res;
		
	}
	
	public String getsrc(){
		return src;
	}
	
	
}
