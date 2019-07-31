import javax.swing.*;
import javax.swing.border.*;

import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Client {
	

	private static ClientThread clientThread;
	public static String userName = "Anonymous";
	public static Socket SOCK;
	public static ObjectOutputStream output;
	
	

	public static JFrame mainWindow = new JFrame();
		public static JPanel gui = new JPanel();
	
			public static JPanel topBar = new JPanel();
				public static JLabel top = new JLabel();
				public static JPanel PLAFContainer = new JPanel();
					public static String[] themeNames;
					public static JComboBox<?> themeChooser;
			
			public static JPanel userList = new JPanel();

				public static JList userOnlineList = new JList();
					public static JScrollPane listScroll = new JScrollPane();
				public static JButton submit = new JButton();
				
			public static JPanel textCenter = new JPanel();
					public static JTextArea displayText = new JTextArea();
				public static JPanel buttonLabelText = new JPanel();
					public static JPanel buttonText = new JPanel();
						public static JTextArea typeText = new JTextArea();
					public static JLabel message = new JLabel("Message:");
	
			

	public static JFrame logInWindow = new JFrame();
		public static JPanel logInWindowGui = new JPanel();
			public static JLabel logInEnterUsername = new JLabel("Enter Username: ");
			public static JTextField logInUsernameBox = new JTextField(20);
			public static JButton logInEnter = new JButton("Enter");


	public static void Connect(){
		
		try{
			final int port = 5555;
			SOCK = new Socket(InetAddress.getLocalHost(),port);


			
			clientThread = new ClientThread(SOCK);

			output = new ObjectOutputStream(SOCK.getOutputStream());
			try{
				output.writeObject(userName);
				output.flush();
			}catch(IOException ioException){
				JOptionPane.showMessageDialog(null, "Error - UserName not Sent!");
			}
			
			top.setText("Online");
			
			Thread X = new Thread(clientThread);
			X.start();
			
			
		}
		catch(Exception x){
			System.out.println(x);
			JOptionPane.showMessageDialog(null, "Server Not Responding");
			System.exit(0);
		}
	}
	
	
	
	
	
	public static void BuildMainWindow(){
		
		mainWindow.setTitle("Project ChatRoom - "+userName);
		
		ConfigureMainWindow();
		MainWindow_Action();
		mainWindow.setVisible(true);
	}
	
	
	
	

	public static void ConfigureMainWindow(){
		
		

		mainWindow.setContentPane(gui);
		mainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainWindow.setMinimumSize(new Dimension(500,300));
		mainWindow.pack();
		mainWindow.setLocationRelativeTo(null);
		try {
			mainWindow.setLocationByPlatform(true);
			mainWindow.setMinimumSize(mainWindow.getSize());
		} 
		catch(Throwable ignoreAndContinue) {
		}
		

		top.setText("Offline");
		
		final UIManager.LookAndFeelInfo[] themes = UIManager.getInstalledLookAndFeels();     
        themeNames = new String[themes.length];
        int ii;
        for (ii=0; ii<themes.length; ii++) {
                themeNames[ii] = themes[ii].getName();
        }
        
        PLAFContainer.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        
        themeChooser = new JComboBox(themeNames);
        themeChooser.setSelectedIndex(ii-1);
        
        
        
        PLAFContainer.add(themeChooser);
        
        topBar.setLayout(new BorderLayout(5,5));
		topBar.setBorder(new TitledBorder(""));
        topBar.add(top, BorderLayout.WEST);
        topBar.add(PLAFContainer, BorderLayout.EAST);
              

        listScroll.setHorizontalScrollBarPolicy(
        		ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        listScroll.setVerticalScrollBarPolicy(
        		ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        listScroll.setViewportView(userOnlineList);
        listScroll.setPreferredSize(new Dimension(130,200));
        listScroll.setMinimumSize(new Dimension(130,200));
        

        submit.setText("SEND");
        submit.setPreferredSize(new Dimension(100,100));
        submit.setMinimumSize(new Dimension(100,30));

        userList.setLayout(new BorderLayout(5,5));
        userList.add(listScroll,BorderLayout.CENTER);
        userList.add(submit,BorderLayout.SOUTH);
                

        displayText.setText("");
        displayText.setBorder(new LineBorder(Color.GRAY));
        displayText.setEditable(false);
            
        

        typeText.setPreferredSize(new Dimension(400,60));
        typeText.setEditable(true);
        typeText.setBorder(new LineBorder(Color.GRAY));
        
        buttonText.setLayout(new BorderLayout(5,5));
		buttonText.add(new JScrollPane(typeText),BorderLayout.CENTER);
		
        buttonLabelText.setLayout(new BorderLayout(5,5));
		buttonLabelText.add(buttonText,BorderLayout.CENTER);
		buttonLabelText.add(message,BorderLayout.WEST);
		
		
        textCenter.setLayout(new BorderLayout(5,5));
        textCenter.add(new JScrollPane(displayText), BorderLayout.CENTER);
		textCenter.add(buttonLabelText,BorderLayout.SOUTH);
		

		gui.setLayout(new BorderLayout(5,5));
		gui.add(topBar, BorderLayout.NORTH);
        gui.add(userList, BorderLayout.EAST);
		gui.add(textCenter,BorderLayout.CENTER);

	}

	
	
	
	
	
	public static void MainWindow_Action(){
		
		mainWindow.addWindowListener(
			new WindowAdapter(){
				public void windowClosing(WindowEvent e){
				       int result = JOptionPane.showConfirmDialog(null, "Are you sure","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

				       if(result == JOptionPane.YES_OPTION){
				        	try {
								output.close();
								clientThread.in.close();
								SOCK.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				        	
				        	
							System.exit(0);
						} else{

				       }
				 }
			}
		);
		
		submit.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try {
						SUBMIT_ACTION();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}
				
			}
		);
		
		userOnlineList.addMouseListener(
			new MouseAdapter(){
				public void mouseClicked(MouseEvent e) {
			        USERONLINELIST_ACTION(e);
				}
			}
		);
		
		themeChooser.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent ae) {
					int index = themeChooser.getSelectedIndex();
					final UIManager.LookAndFeelInfo[] themes = UIManager.getInstalledLookAndFeels();
					try {
						UIManager.setLookAndFeel( themes[index].getClassName() );
						SwingUtilities.updateComponentTreeUI(mainWindow);
						
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		);
	
		
	}
	
	
	
	
	
	
	public static void USERONLINELIST_ACTION(MouseEvent e){
		if (e.getClickCount() == 2) {
			final String selectedUser = (String) userOnlineList.getSelectedValue();
			SwingUtilities.invokeLater(
				new Runnable(){
					public void run() {
						typeText.setText("@" + selectedUser + ": ");
						typeText.requestFocus();
					}
				}
			);
        }
	}
	
	
	
	
	
	
	public static void SUBMIT_ACTION() throws IOException{
		if(!typeText.getText().equals("")){
			clientThread.SEND(typeText.getText());
			typeText.requestFocus();
			typeText.setText("");
		}
	}
	
	
	
	
	
	
	public static void Initialize(){
		submit.setEnabled(false);
		mainWindow.setEnabled(false);
	}
	
	
	
	
	
	
	
	public static void BuildLogInWindow(){
		
		logInWindow.setTitle("Log In");
		
		ConfigureLogInWindow();
		LogInWindow_Action();
		logInWindow.setVisible(true);
	}
	
	
	
	
	
	
	
	public static void ConfigureLogInWindow(){
		logInWindow.setContentPane(logInWindowGui);
		logInWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		logInWindow.setMinimumSize(new Dimension(370,90));
		logInWindow.pack();
		logInWindow.setLocationRelativeTo(null);
		try {
			// 1.6+
			logInWindow.setLocationByPlatform(true);
			logInWindow.setMinimumSize(logInWindow.getSize());
		} 
		catch(Throwable ignoreAndContinue) {
		}
		
		
		logInWindowGui.setLayout(new FlowLayout());
		logInWindowGui.add(logInEnterUsername);
		logInWindowGui.add(logInUsernameBox);
		logInWindowGui.add(logInEnter);
	}

	
	
	
	
	
	
	public static void LogInWindow_Action(){
		logInEnter.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LOGIN_ACTION();
				}
				
			}
		);
		
		logInUsernameBox.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LOGIN_ACTION();
				}					
			}
		);
	}
	
	
	
	
	
	
	
	public static void LOGIN_ACTION() {
		if(!logInUsernameBox.getText().equals("")) {
			userName = logInUsernameBox.getText().trim();
			mainWindow.setTitle("ChatRoom - "+userName);
			logInWindow.dispose();
			submit.setEnabled(true);
			mainWindow.setEnabled(true);
			typeText.requestFocus();
			Connect();
		}
		else {
			JOptionPane.showMessageDialog(null, "Please Enter a name!");
		}
	}


	
	
}