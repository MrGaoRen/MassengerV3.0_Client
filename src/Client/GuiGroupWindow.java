package Client;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Connection.ConnectionClient;


public class GuiGroupWindow extends GroupWindow {

	Mainframe groupwindow;
	
	public class Mainframe extends JFrame {
		private JList<String> jlist= new JList<String>(); 

		private JTextArea save=null;
		private JTextArea sendd=null; 
		JScrollPane ssc;

		private JTextField jjtt3 =new JTextField();
		private JLabel jll4=new JLabel("Message"); 

		
		public Mainframe(){
			this.setTitle("Public Chatting");
			init();
			this.setLayout(null); 
			this.setBounds(300, 200, 860, 640); 
			this.setVisible(true); 
			this.setResizable(false); 
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}

		
		public void init(){

			try{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
			}catch(Exception e){
				
			}



			 save=new JTextArea(21,33);
			 sendd=new JTextArea(5,33); 
			 Container contain = this.getContentPane();
		     contain.setLayout(new BorderLayout());

			 JPanel zjpp1 =new JPanel(); 
			 JPanel zjpp2 =new JPanel(); 
			 JPanel zjpp3=new JPanel(); 

			 JLabel jll3=new JLabel("DisplayName:");
			 
			 JPanel left =new JPanel();
			 left.setBorder(new TitledBorder("Friend")); 
			 
			 zjpp1.setBorder(new TitledBorder("User List (double click to open private chatting)")); 
			 zjpp2.setBorder(new TitledBorder("Chat Messages")); 
			 zjpp3.setBorder(new TitledBorder("User-defined")); 
			 
			 JButton senn =new JButton("Enter"); 
//			 JButton qpg=new JButton("File"); 
//			 qpg.addActionListener(new ActionListener(){
//				 
//				 
//				@Override
//				public void actionPerformed(ActionEvent arg0) {
////					// TODO Auto-generated method stub
////					JFileChooser fileChooser = new JFileChooser();
////				 int i = fileChooser.showOpenDialog(getContentPane());
////				 if(i == JFileChooser.APPROVE_OPTION){
////					 File selectedFile = fileChooser.getSelectedFile();
////					 System.out.println(selectedFile.getAbsolutePath());
////				 }
//				}
//			 });

			 JButton bu = new JButton("SetDisplayName");

			 
			 save.setLineWrap(true);
			 save.setEditable(true);
			 JScrollPane scroll = new JScrollPane(save); 
			 scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
			 scroll.setWheelScrollingEnabled(true);
			 save.setCaretPosition(save.getText().length()); 
			 sendd.setLineWrap(false);
			 JScrollPane scrol = new JScrollPane(sendd); 
			 sendd.setCaretPosition(0); 
			 scrol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 

			 left.setLayout(new GridLayout(4, 1)); 

			 left.add(jll3);
			 left.add(jjtt3); 
			
			 left.add(bu);	 
			 left.add(jll4);
			 left.add(jlist);

			 JToolBar gjt= new JToolBar();
			 gjt.setFloatable(false); 
//			 gjt.add(qpg);
			 gjt.add(senn); 
			 
			 zjpp1.setBounds(10, 0, 200, 580); 
			 zjpp2.setBounds(220, 0, 400, 580); 
			 zjpp3.setBounds(630, 0, 210, 580); 
			 jlist.setFixedCellHeight(23);
			 jlist.setFixedCellWidth(150); 
			 jlist.setBounds(2, 5, 180, 500); 
			 jlist.setVisibleRowCount(15); 
			 ssc=new JScrollPane(jlist); 
			 ssc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			 ssc.setViewportView(jlist);
			 
			 contain.add(zjpp1); 
			 contain.add(zjpp2); 
			 contain.add(zjpp3);
			 
			 zjpp1.add(ssc); 
			 zjpp1.add(left); 
			 zjpp2.add(scroll); 
			 zjpp2.add(scrol);
			 zjpp2.add(gjt); 

//			 qpg.addActionListener(new ActionListener(){
//				 
//				 @Override
//				 public void actionPerformed(ActionEvent e){
//					 save.setText(""); 
//				 }
//				 
//			 });
			 
			 sendd.addKeyListener(new KeyListener(){
				 public synchronized void keyPressed(KeyEvent e){
					 if (e.getKeyCode() == KeyEvent.VK_ENTER ){
						 
						 if( sendd.getText().trim().equals("")){
							 JOptionPane.showMessageDialog(Mainframe.this, "Input Field Is Empty!");
						 }else{
							 save.append("Me(" + requestmaker.getdisplayname() +"):\t\n "+sendd.getText().trim()+"\n");
							 save.setSelectionStart(save.getText().length());
							 sendd.setText("");
						 }
					 }
				 }
				 

			 
				 
				 
				 @Override 
				 public void keyTyped(KeyEvent e){}
				 @Override 
				 public void keyReleased(KeyEvent e){}
			 });
			 
			 jlist.addMouseListener(new MouseListener(){
				 
				 @Override 
				 public void mouseReleased(MouseEvent e){}
				 @Override 
				 public void mousePressed(MouseEvent e){}
				 @Override 
				 public void mouseExited(MouseEvent e){}
				 @Override 
				 public void mouseEntered(MouseEvent e){}
				 @Override 
				 public void mouseClicked(MouseEvent e){
					 if(e.getClickCount() == 2){
						 System.out.println("Double Click");
						 JList jlist = (JList) e.getSource();
						 int index = jlist.getSelectedIndex();
						 Object obj = jlist.getModel().getElementAt(index);
						 System.out.println(obj.toString());
						 
						 startnewprivatewindow(obj.toString());
						 
					 }
				 }
				 
				 
				 
				 
				 
				 
				 
			 });
			 

			 senn.addActionListener(new ActionListener(){
				 @Override 
				 public void actionPerformed(ActionEvent e){
					 if( sendd.getText().trim().equals("")){
						 JOptionPane.showMessageDialog(Mainframe.this, "Input Field Is Empty!");
					 }else{ 
						 writeOutcomingText(sendd.getText());
						 save.append("Me(" + requestmaker.getdisplayname() +"):\t\n "+sendd.getText().trim()+"\n");
						 getgroupmember();
						 } 
				 }
			 });
			 
			bu.addActionListener(new ActionListener(){
				 @Override 
				 public void actionPerformed(ActionEvent e){
					 setNewDisplayName(jjtt3.getText());

				 }
			 });
			 
			 

		}
		


	}

	
	
	public GuiGroupWindow(ConnectionClient client, String name) {
		super(client, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void displayIncomingTextTowindow(String s) {
		this.groupwindow.save.append(s + "\n");
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void displayOutcomingTextTowindow(String s) {
		this.groupwindow.save.append(s + "\n");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showDiaplayNameMessage(String s) {
		this.groupwindow.jll4.setText(s);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setNewDisplayName(String name) {
		requestmaker.setdisplayname(name);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void showgroupmemberlist(String[] s) {
		 ListModel jList1Model =  new DefaultComboBoxModel(s);
		 this.groupwindow.jlist.setModel(jList1Model);
		// TODO Auto-generated method stub
		
		
	}

	@Override
	protected void showGroupWindow() {
		groupwindow = new Mainframe();
		requestgroupmember();
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getNewDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

}
