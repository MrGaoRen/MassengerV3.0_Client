package Connection;
import java.io.FileInputStream;

import model.Datagram;

public abstract class AllRequest {
		
	public abstract Datagram login(String username, String password);
	public abstract Datagram register(String username, String password);
	

	public abstract Datagram getgroupmember();	
	public abstract Datagram newdisplayname(String groupname);
	
	public abstract Datagram sendfriendmessage(String dst, String mesg);	
	public abstract Datagram sendgroupmessage(String mesg);	
	public abstract Datagram sendfriendfile(String dst, String filename);
	
	
}
