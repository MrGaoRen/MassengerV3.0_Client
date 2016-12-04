package Client;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class GuiLoginWindow extends LoginWindow {
	
	Loginwindow loginwindow;
	
	public class Loginwindow extends JFrame {

	    private JTextField username;
	    private JLabel mesg;
	    private JPasswordField password;
	    private JTextField serverip;
	    
	    private JLabel jl1;
	    private JLabel jl2;
	    private JLabel jl3;
	    private JLabel jl4;
	    private JLabel jl5;
	    private JButton bu1;
	    private JButton bu2;

	    public Loginwindow() {
	        
	        this.setTitle("CHATTING");
	        
	        init();
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	        this.setLayout(null);
	        this.setBounds(0, 0, 355, 265);
	       
	        Image image = new ImageIcon("e:/a.jpg").getImage();
	        this.setIconImage(image);
	        
	        this.setResizable(false);

	       
	        this.setLocationRelativeTo(null);

	        
	        this.setVisible(true);
	    }


	 
	    public void init() {
	        
	        Container con = this.getContentPane();
	        jl1 = new JLabel();
	        
	        Image image1 = new ImageIcon("e:/background.jpg").getImage();
	        jl1.setIcon(new ImageIcon(image1));
	        jl1.setBounds(0, 0, 355, 265);

	        // 
	        jl2 = new JLabel();  
	        Image image2 = new ImageIcon("e:/b.jpg").getImage();
	        jl2.setIcon(new ImageIcon(image2));
	        jl2.setBounds(40, 95, 50, 60);

	        
	        username = new JTextField();
	        username.setBounds(100, 100, 150, 20);
	        
	        jl3 = new JLabel("UserName");
	        jl3.setBounds(260, 100, 70, 20);
	        
       
	        password = new JPasswordField();
	        password.setBounds(100, 130, 150, 20);
	        
	        
	        serverip = new JTextField();
	        serverip.setBounds(100, 70, 150, 20);
	        
	        jl4 = new JLabel("Password");
	        jl4.setBounds(260, 130, 70, 20);
	        
	        jl5 = new JLabel("Server IP");
	        jl5.setBounds(260, 70, 70, 20);
	        
	        mesg = new JLabel("Message");
	        mesg.setBounds(200, 170, 150, 20);

	        // Button set
	        bu1 = new JButton("Login");
	        bu1.setBounds(280, 200, 65, 20);
	        bu1.addActionListener(new ActionListener(){
	        	
	        	@Override
	        	public void actionPerformed(ActionEvent e){
	        		String str=e.getActionCommand();
	        		if("Login".equals(str)){
		        		if(!readPassword().isEmpty() && !readUsername().isEmpty()){	        		     		
		        			trylogin(serverip.getText(),GuiLoginWindow.this);
		        		}else{
		        			showMessage("Please input informaion");
		        		}
	        			
	        		}
	        		
	        			

	        	}
  	
	        });
	        
	        
	        
	        bu2 = new JButton("Register");
	        bu2.addActionListener(new ActionListener(){
	        	
	        	@Override
	        	public void actionPerformed(ActionEvent e){
	        		String str=e.getActionCommand();
	        		if("Register".equals(str)){
		        		if(!readPassword().isEmpty() && !readUsername().isEmpty()){	        		     		
		        			tryregister(serverip.getText(),GuiLoginWindow.this);
		        		}else{
		        			showMessage("Please input informaion");
		        		}
	        			
	        		}

	        	}
  	
	        });
	        
	        bu2.setBounds(5, 200, 85, 20);	        
	        jl1.add(jl2);
	        jl1.add(jl3);
	        jl1.add(jl4);
	        jl1.add(jl5);
	        jl1.add(bu1);
	        jl1.add(bu2);
	        jl1.add(mesg);
	        con.add(serverip);
	        con.add(jl1);
	        con.add(username);
	        con.add(password);

	    }
	    
	}
	
	public GuiLoginWindow() {
		//super(client);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void showLoginWindow() {
		loginwindow = new Loginwindow();
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String readUsername() {
		return this.loginwindow.username.getText();
		// TODO Auto-generated method stub
		//return null;
	}

	@Override
	protected String readPassword() {
		return String.valueOf(this.loginwindow.password.getPassword());
		// TODO Auto-generated method stub
		//return null;
	}

	@Override
	public void showMessage(String mesg) {
		this.loginwindow.mesg.setText(mesg);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destory() {
		this.loginwindow.dispose();
		// TODO Auto-generated method stub
		
	}

}
