
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.border.*;


public class  MainGameUI extends JFrame
{
	Container cp;
	JPanel contentPane, mainPanel, chatPanel, panel_ListClient, panel_Top;
	JButton sendButton, button_Ready, ruleBookButton, endGameButton, voteButton, abilityButton;
	JLabel label_Client1, label_Client2, label_Client3, label_Client4, label_Client5, label_Client6, job, dayLabel, eventLabel;
	Label label_Client1_Info, label_Client2_Info, label_Client3_Info, label_Client4_Info, label_Client5_Info, label_Client6_Info;
	Label label_Client1_Msg, label_Client2_Msg, label_Client3_Msg, label_Client4_Msg, label_Client5_Msg, label_Client6_Msg;
	Label label_Time;
	JScrollPane scrollPane;
	ImageIcon i1, i2;
	Image im, im2;
	JTextArea textArea;
	JTextField textField;
	ImageIcon titleImg = new ImageIcon("Images//코난.png");

	Font font1 = new Font("궁서", Font.PLAIN, 15);
	
	String nickName = Login.nickName;
	String ip = Login.ip;
	String hiMsg = Login.hiMsg;
	Socket s;
	int port = 3333; 

	String clientName, clientIdx;
	boolean gameStart;
	String clientJob="";

	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;


	//TreeSet <String> listNicknamesgot = new TreeSet <String>();
	ImageIcon ii = new ImageIcon("Images//코난.png");
	//Object [] userList = new Object [6];
	Object [] userList3 = {"", "", ""};
	Object [] userList4 = {"", "", "",""};
	Object [] userList5 = {"", "", "","",""};
	Object [] userList6 = {"", "", "","","",""};
	int numberUsers =0;


	MainGameUI(){
		init();
		setUI();
		startChat();
		System.out.println("s:" +s);
		System.out.println("nickName: "+ nickName);
		System.out.println("hiMsg: "+ hiMsg);
	}
	
	
	public void init(){
		//List of gamers + panel & container for button READY!
		contentPane = new JPanel();		
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		mainPanel = new JPanel();		
		mainPanel.setBackground(Color.BLACK);
		mainPanel.setOpaque(true);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		job = new JLabel("Job");			
		job.setBounds(60,5,100,30);
		job.setForeground(Color.RED);
		job.setFont(font1);
		mainPanel.add(job);

		//client panel
		panel_ListClient = new JPanel();
		panel_ListClient.setOpaque(true);
		panel_ListClient.setBounds(0,35,268,870);
		panel_ListClient.setBackground(Color.BLACK);
		mainPanel.add(panel_ListClient);
		
		panel_ListClient.setLayout(null);
		//icon2.png
		label_Client1 = new JLabel(new ImageIcon("Images//shadow.png"));
		label_Client1.setBounds(10,0,248,120); //user1 location
		panel_ListClient.add(label_Client1);
		//label_Client1.setFont(font1);
		
		label_Client2 = new JLabel(new ImageIcon("Images//shadow.png"));
		label_Client2.setBounds(10,130,248,120); //user2 location
		panel_ListClient.add(label_Client2);
		//label_Client2.setFont(font1);

		label_Client3 = new JLabel(new ImageIcon("Images//shadow.png"));
		label_Client3.setBounds(10,260,248,120); //user3 location
		panel_ListClient.add(label_Client3);
		//label_Client3.setFont(font1);

		label_Client4 = new JLabel(new ImageIcon("Images//shadow.png"));
		label_Client4.setBounds(10,390,248,120); //user4 location
		panel_ListClient.add(label_Client4);
		//label_Client4.setFont(font1);
		
		label_Client5 = new JLabel(new ImageIcon("Images//shadow.png"));
		label_Client5.setBounds(10,520,248,120); //user5 location
		panel_ListClient.add(label_Client5);
		
		label_Client6 = new JLabel(new ImageIcon("Images//shadow.png"));
		label_Client6.setBounds(10,650,248,120); //user6 location
		panel_ListClient.add(label_Client6);

		button_Ready = new JButton(new ImageIcon("Images//ready7.png"));
		button_Ready.setOpaque(true);
		button_Ready.setBorder(new LineBorder(Color.BLACK, 0 ,false));
		button_Ready.setBackground(new Color(0,156,255));
		button_Ready.setOpaque(false);
		button_Ready.setBounds(10,775,243,61); 
		panel_ListClient.add(button_Ready);
		//Nickname
		label_Client1_Info = new Label("Nickname1");//variation client server.nickname to put
		label_Client1_Info.setFont(font1);
		label_Client1_Info.setForeground(Color.WHITE);
		label_Client1_Info.setAlignment(Label.CENTER);
		label_Client1_Info.setBackground(new Color(38,38,38));
		label_Client1_Info.setBounds(140,20,90,25);
		label_Client1.add(label_Client1_Info);
		//presentation
		label_Client1_Msg = new Label();//variation client server.himsg to put
		label_Client1_Msg.setFont(font1);
		label_Client1_Msg.setForeground(Color.WHITE);
		label_Client1_Msg.setAlignment(Label.CENTER);
		label_Client1_Msg.setBackground(new Color(38,38,38));
		label_Client1_Msg.setBounds(140,35,90,45);
		label_Client1.add(label_Client1_Msg);
		
		label_Client2_Info = new Label("Nickname2");						//to change
		label_Client2_Info.setFont(font1);
		label_Client2_Info.setForeground(Color.WHITE);
		label_Client2_Info.setAlignment(Label.CENTER);
		label_Client2_Info.setBackground(new Color(38,38,38));
		label_Client2_Info.setBounds(140,20,90,25);
		label_Client2.add(label_Client2_Info);

		label_Client2_Msg = new Label();							//to change
		label_Client2_Msg.setFont(font1);
		label_Client2_Msg.setForeground(Color.WHITE);
		label_Client2_Msg.setAlignment(Label.CENTER);
		label_Client2_Msg.setBackground(new Color(38,38,38));
		label_Client2_Msg.setBounds(140,35,90,45);
		label_Client2.add(label_Client2_Msg);

		label_Client3_Info = new Label("Nickname3");						//to change
		label_Client3_Info.setFont(font1);
		label_Client3_Info.setForeground(Color.WHITE);
		label_Client3_Info.setAlignment(Label.CENTER);
		label_Client3_Info.setBackground(new Color(38,38,38));
		label_Client3_Info.setBounds(140,20,90,25);
		label_Client3.add(label_Client3_Info);

		label_Client3_Msg = new Label();							//to change
		label_Client3_Msg.setFont(font1);
		label_Client3_Msg.setForeground(Color.WHITE);
		label_Client3_Msg.setAlignment(Label.CENTER);
		label_Client3_Msg.setBackground(new Color(38,38,38));
		label_Client3_Msg.setBounds(140,35,90,45);
		label_Client3.add(label_Client3_Msg);

		label_Client4_Info = new Label("Nickname4");						//to change
		label_Client4_Info.setFont(font1);
		label_Client4_Info.setForeground(Color.WHITE);
		label_Client4_Info.setAlignment(Label.CENTER);
		label_Client4_Info.setBackground(new Color(38,38,38));
		label_Client4_Info.setBounds(140,20,90,25);
		label_Client4.add(label_Client4_Info);
	
		label_Client4_Msg = new Label();							//to change
		label_Client4_Msg.setFont(font1);
		label_Client4_Msg.setForeground(Color.WHITE);
		label_Client4_Msg.setAlignment(Label.CENTER);
		label_Client4_Msg.setBackground(new Color(38,38,38));
		label_Client4_Msg.setBounds(140,35,90,45);
		label_Client4.add(label_Client4_Msg);

		label_Client5_Info = new Label("Nickname5");						//to change
		label_Client5_Info.setFont(font1);
		label_Client5_Info.setForeground(Color.WHITE);
		label_Client5_Info.setAlignment(Label.CENTER);
		label_Client5_Info.setBackground(new Color(38,38,38));
		label_Client5_Info.setBounds(140,20,90,25);
		label_Client5.add(label_Client5_Info);

		label_Client5_Msg = new Label();								//to change
		label_Client5_Msg.setFont(font1);
		label_Client5_Msg.setForeground(Color.WHITE);
		label_Client5_Msg.setAlignment(Label.CENTER);
		label_Client5_Msg.setBackground(new Color(38,38,38));
		label_Client5_Msg.setBounds(140,35,90,45);
		label_Client5.add(label_Client5_Msg);

		label_Client6_Info = new Label("Nickname6");							//to change
		label_Client6_Info.setFont(font1);
		label_Client6_Info.setForeground(Color.WHITE);
		label_Client6_Info.setAlignment(Label.CENTER);
		label_Client6_Info.setBackground(new Color(38,38,38));
		label_Client6_Info.setBounds(140,20,90,25);
		label_Client6.add(label_Client6_Info);

		label_Client6_Msg = new Label();								//to change
		label_Client6_Msg.setFont(font1);
		label_Client6_Msg.setForeground(Color.WHITE);
		label_Client6_Msg.setAlignment(Label.CENTER);
		label_Client6_Msg.setBackground(new Color(38,38,38));
		label_Client6_Msg.setBounds(140,35,90,45);
		label_Client6.add(label_Client6_Msg);
		
		//Timer panel on the top
		panel_Top = new JPanel();
		panel_Top.setOpaque(true);
		panel_Top.setBounds(268,0,520,250);
		panel_Top.setBackground(Color.BLACK);
		mainPanel.add(panel_Top);
		
		panel_Top.setLayout(null);
		label_Time = new Label("00:00");//variation time to put
		label_Time.setFont(font1);
		label_Time.setForeground(Color.RED);
		label_Time.setBackground(Color.BLACK);
		label_Time.setBounds(460,0,50,40);
		panel_Top.add(label_Time);
		
		dayLabel = new JLabel("1st Day");//to change
		dayLabel.setFont(font1);
		dayLabel.setBounds(110,0,80,30);
		dayLabel.setForeground(Color.RED);
		
		panel_Top.add(dayLabel);
		
		eventLabel = new JLabel(new ImageIcon("Images//day.png"));								//to change
		eventLabel.setBounds(0,40,520,210);
		//eventLabel.setBorder(new LineBorder(new Color(127, 219, 254), 5, false));
		panel_Top.add(eventLabel);

		//chatting panel
		chatPanel = new JPanel();		
		MatteBorder b1 = new MatteBorder(5,5,5,5,Color.BLACK);	//larger of panel
		chatPanel.setBorder(b1);
		chatPanel.setBackground(Color.BLACK);
		chatPanel.setBounds(268,260,520,602);
		chatPanel.setOpaque(true);
		mainPanel.add(chatPanel);
		chatPanel.setLayout(null);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0,0,520,480);
		chatPanel.add(scrollPane);

		textArea = new JTextArea();
		//textArea.setBorder(new LineBorder(new Color(127, 219, 254), 5, false));	//log for chat, borderline to ignore 
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		textArea.setBackground(Color.WHITE);			
		
		textField = new JTextField();
		//textField.setBorder(new LineBorder(new Color(181,227,0), 4, false));	//textfield color to ignore
		textField.setBackground(Color.WHITE);
		textField.setOpaque(true);
		textField.setBounds(0,560,450,40);
		chatPanel.add(textField);
		textField.requestFocus();
		textField.setColumns(10);
		
		endGameButton = new JButton("Game End");
		endGameButton.setBounds(0,500,170,40);
		chatPanel.add(endGameButton);

		ruleBookButton = new JButton("Game Rules");
		ruleBookButton.setBounds(180,500,170,40);
		chatPanel.add(ruleBookButton);

		voteButton = new JButton("Vote");
		voteButton.setBounds(360,500,170,40);
		chatPanel.add(voteButton);

		sendButton = new JButton("Send");
		sendButton.setBounds(450,560,70,40);
		sendButton.requestFocus();
		chatPanel.add(sendButton);

		abilityButton = new JButton("Use power");
		abilityButton.setBounds(360,500,170,40);
		chatPanel.add(abilityButton);
		abilityButton.setVisible(false);
		abilityButton.setEnabled(false);
	}

	void setUI(){
		setTitle("KOPIA GAME LOG-IN");
		setIconImage(titleImg.getImage());
		setVisible(true);
		setSize(804,910);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//make a chat socket1
	public void startChat(){
		try{
			s = new Socket(ip, port);
			textArea.append("Welcome to KOPIA GAME"+"\n");
			is = s.getInputStream();
			os = s.getOutputStream();
			dis = new DataInputStream(is);
			dos = new DataOutputStream(os);
		
			Speaker speaker = new Speaker();
			Listener listener = new Listener();
			
			//add to button listener
			textField.addKeyListener(speaker);
			sendButton.addActionListener(speaker);
			button_Ready.addActionListener(speaker);
			ruleBookButton.addActionListener(speaker);
			endGameButton.addActionListener(speaker);
			voteButton.addActionListener(speaker);
			abilityButton.addActionListener(speaker);
			
		}catch(UnknownHostException uh){
			JOptionPane.showMessageDialog(null,"Cannot find the host", "Error!", JOptionPane.WARNING_MESSAGE);
		}catch(IOException ie){	
			JOptionPane.showMessageDialog(null,"Failed to connect to Server ! Are you sure the Server is openned?", "Error!",JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}

	public void showListNicknames(){
		try{
			dos.writeUTF("//ShowList");
			dos.flush();
		}catch(IOException ie){}
	}

	class Listener extends Thread{
		

		Listener(){
			start();
		}
		public void run(){//listen
			while(true){
				try{
					//read the msg written on the server
					String msgChatRead = dis.readUTF();
					System.out.println(msgChatRead);
					//do what received
					if(msgChatRead.startsWith("//CList")){//update client list
						clientName = msgChatRead.substring(7,msgChatRead.indexOf("#"));
						clientIdx = msgChatRead.substring(msgChatRead.indexOf("#")+1);
						updateClientList();
					}else if(msgChatRead.startsWith("//Start")){
						gameStart = true;
						//get a random job, upadte popup window(job)
						//update window of picture(day)
						//change bgm
						endGameButton.setEnabled(true);
					}else if(msgChatRead.equals("//EndServer")){
						textArea.append("Failed connexion with Server. Exit after 3 seconds.");
						try{
							Thread.sleep(3000);
							System.exit(0);
						}catch(InterruptedException ire){
						}
					}else if(msgChatRead.equals("//Vote")){
						if( sendButton.isEnabled() ){
							JOptionPane.showMessageDialog(null,"Choose a member to kill as pushing Vote Button","KillKill!",JOptionPane.PLAIN_MESSAGE);
							voteButton.setEnabled(true);
						}
					}else if(msgChatRead.equals("//Day")|| msgChatRead.equals("//FirtDay")){
						ImageIcon imageDay = new ImageIcon ("Images//day.png");
						imageDay.getImage().flush();
						eventLabel.setIcon(imageDay);
						if(job.getText().startsWith("Job")){

							sendButton.setEnabled(true);
							textField.setEnabled(true);
							voteButton.setVisible(true);
							voteButton.setEnabled(false);
							contentPane.repaint();
							contentPane.revalidate();
						}
						//voteButton.setEnabled(true);
					}else if(msgChatRead.equals("//VoteFinished")){
						voteButton.setEnabled(false);
					}else if(msgChatRead.equals("//Night")){
						ImageIcon imageNight = new ImageIcon("Images//night.png");
						imageNight.getImage().flush();
						eventLabel.setIcon(imageNight);
						sendButton.setEnabled(false);
						textField.setEnabled(false);
						if( job.getText().equals("Job : Mafia") || job.getText().equals("Job : Police")){
							abilityButton.setEnabled(true);
						}
					}else if(msgChatRead.equals("//DeleteVote")){
						voteButton.setVisible(false);
						voteButton.setEnabled(false);
						abilityButton.setVisible(true);
						//abilityButton.setEnabled(true);
						contentPane.repaint();
						contentPane.revalidate();
					}else if(msgChatRead.startsWith("//Job")){
						clientJob = msgChatRead.substring(5);
						System.out.println(clientJob);
						if(clientJob.equals("Mafia")){
							job.setText("Job : Mafia");
							dos.writeUTF("//isMyjob"+clientJob);
							System.out.println("//isMyjob"+clientJob);
							dos.flush();
							JOptionPane.showMessageDialog(null, "You're a mafia.","Announce Job",JOptionPane.PLAIN_MESSAGE);
						}else if(clientJob.equals("Police")){
							job.setText("Job : Police");
							dos.writeUTF("//isMyjob"+clientJob);
							System.out.println("//isMyjob"+clientJob);
							dos.flush();
							JOptionPane.showMessageDialog(null, "You're a police.","Announce Job",JOptionPane.PLAIN_MESSAGE);
						}else if(clientJob.equals("Citizen1")){
							job.setText("Job : Citizen1");
							dos.writeUTF("//isMyjob"+clientJob);
							System.out.println("//isMyjob"+clientJob);
							dos.flush();
							JOptionPane.showMessageDialog(null, "You're a citizen.","Announce Job",JOptionPane.PLAIN_MESSAGE);
						}else if(clientJob.equals("Citizen2")){
							job.setText("Job : Citizen2");
							dos.writeUTF("//isMyjob"+clientJob);
							System.out.println("//isMyjob"+clientJob);
							dos.flush();
							JOptionPane.showMessageDialog(null, "You're a citizen.","Announce Job",JOptionPane.PLAIN_MESSAGE);
						}else if(clientJob.equals("Citizen3")){
							job.setText("Job : Citizen3");
							dos.writeUTF("//isMyjob"+clientJob);
							System.out.println("//isMyjob"+clientJob);
							dos.flush();
							JOptionPane.showMessageDialog(null, "You're a citizen.","Announce Job",JOptionPane.PLAIN_MESSAGE);
						}else if(clientJob.equals("Citizen4")){
							job.setText("Job : Citizen4");
							dos.writeUTF("//isMyjob"+clientJob);
							System.out.println("//isMyjob"+clientJob);
							dos.flush();
							JOptionPane.showMessageDialog(null, "You're a citizen.","Announce Job",JOptionPane.PLAIN_MESSAGE);
						}
					}else if(msgChatRead.startsWith("//Timer")){
						String timer = msgChatRead.substring(7);
						label_Time.setText(timer);
						//System.out.println(msgChatRead.substring(7));
					}else if(msgChatRead.equals("//FirstDay")){

						voteButton.setEnabled(false);
					}else if(msgChatRead.startsWith("Hello !")){
						
						String msgDayNight = msgChatRead.substring(7,msgChatRead.indexOf("'"));
						String msgDayNightShow ="";
						if(msgDayNight.equals("1")){
							msgDayNightShow = "1st day";
						}else{
							msgDayNightShow = msgDayNight + "nd day";
						}
						dayLabel.setText(msgDayNightShow);
						JOptionPane.showMessageDialog(null, msgDayNightShow, "Time passed!", JOptionPane.PLAIN_MESSAGE);
					}else if(msgChatRead.startsWith("//List")){
						String realList = msgChatRead.substring(6); // aa/bb/cc/ss/ff/ddd
						String numberUser = realList.substring(realList.lastIndexOf("/")+1);
						System.out.println(numberUser);
						numberUsers = Integer.parseInt(numberUser);
				
						if(numberUsers==3){
							
							String userName0 = realList.substring(0,realList.indexOf("/")); // aa
							userList3[0] = userName0;

							String preUserName1 = realList.substring(realList.indexOf("/")+1); // bb/cc/ss/ff/ddd
							String userName1 = preUserName1.substring(0,preUserName1.indexOf("/")); //bb
							userList3[1] = userName1;
					
							String preUserName2 = preUserName1.substring(preUserName1.indexOf("/")+1); // cc/ss/ff/ddd
							String userName2 = preUserName2.substring(0,preUserName2.indexOf("/"));
							userList3[2] = userName2;
						}else if(numberUsers==4){
							
							String userName0 = realList.substring(0,realList.indexOf("/")); // aa
							userList4[0] = userName0;

							String preUserName1 = realList.substring(realList.indexOf("/")+1); // bb/cc/ss/ff/ddd
							String userName1 = preUserName1.substring(0,preUserName1.indexOf("/")); //bb
							userList4[1] = userName1;
					
							String preUserName2 = preUserName1.substring(preUserName1.indexOf("/")+1); // cc/ss/ff/ddd
							String userName2 = preUserName2.substring(0,preUserName2.indexOf("/"));
							userList4[2] = userName2;

							String preUserName3 = preUserName2.substring(preUserName2.indexOf("/")+1);
							String userName3 = preUserName3.substring(0,preUserName3.indexOf("/"));
							userList4[3] = userName3;
						}else if(numberUsers==5){
							
							String userName0 = realList.substring(0,realList.indexOf("/")); // aa
							userList5[0] = userName0;

							String preUserName1 = realList.substring(realList.indexOf("/")+1); // bb/cc/ss/ff/ddd
							String userName1 = preUserName1.substring(0,preUserName1.indexOf("/")); //bb
							userList5[1] = userName1;
					
							String preUserName2 = preUserName1.substring(preUserName1.indexOf("/")+1); // cc/ss/ff/ddd
							String userName2 = preUserName2.substring(0,preUserName2.indexOf("/"));
							userList5[2] = userName2;

							String preUserName3 = preUserName2.substring(preUserName2.indexOf("/")+1);
							String userName3 = preUserName3.substring(0,preUserName3.indexOf("/"));
							userList5[3] = userName3;

							String preUserName4 = preUserName3.substring(preUserName3.indexOf("/")+1); // cc/ss/ff/ddd
							String userName4 = preUserName4.substring(0,preUserName4.indexOf("/"));
							userList5[4] = userName4;
						}else if(numberUsers==6){
							
							String userName0 = realList.substring(0,realList.indexOf("/")); // aa
							userList6[0] = userName0;

							String preUserName1 = realList.substring(realList.indexOf("/")+1); // bb/cc/ss/ff/ddd
							String userName1 = preUserName1.substring(0,preUserName1.indexOf("/")); //bb
							userList6[1] = userName1;
					
							String preUserName2 = preUserName1.substring(preUserName1.indexOf("/")+1); // cc/ss/ff/ddd
							String userName2 = preUserName2.substring(0,preUserName2.indexOf("/"));
							userList6[2] = userName2;

							String preUserName3 = preUserName2.substring(preUserName2.indexOf("/")+1);
							String userName3 = preUserName3.substring(0,preUserName3.indexOf("/"));
							userList6[3] = userName3;

							String preUserName4 = preUserName3.substring(preUserName3.indexOf("/")+1); // cc/ss/ff/ddd
							String userName4 = preUserName4.substring(0,preUserName4.indexOf("/"));
							userList6[4] = userName4;
							
							String preUserName5 = preUserName4.substring(preUserName4.indexOf("/")+1); // cc/ss/ff/ddd
							String userName5 = preUserName5.substring(0,preUserName5.indexOf("/"));
							userList6[5] = userName5;
						}
					}else if(msgChatRead.equals("//gotV")){
						dos.writeUTF("//resultV"+clientJob);
						dos.flush();
						
						
					}else if(msgChatRead.equals("//Kill")){
						job.setText("Ghost");
						JOptionPane.showMessageDialog(null, "You're killed...","Announce Job",JOptionPane.PLAIN_MESSAGE);
						sendButton.setEnabled(false);
						textField.setEnabled(false);
						voteButton.setEnabled(false);
						abilityButton.setEnabled(false);
						dos.writeUTF("You guys killed me ("+nickName+")...");
						dos.flush();

					}else if(msgChatRead.startsWith("//MafiaKills")){
						String getMafiaKills = msgChatRead.substring(12);
						if(getMafiaKills.equals(nickName)){
							dos.writeUTF("//Mkilled"+job.getText());
							job.setText("Ghost");
							

							dos.writeUTF("Mafia killed "+nickName+" tonight...");
							dos.flush();
							JOptionPane.showMessageDialog(null, "You're killed...","Announce Job",JOptionPane.PLAIN_MESSAGE);
							sendButton.setEnabled(false);
							textField.setEnabled(false);
							voteButton.setEnabled(false);
							abilityButton.setEnabled(false);
						}
					}else if(msgChatRead.startsWith("//policeA")){
						String getPolice = msgChatRead.substring(9);
						if(getPolice.equals(nickName)){
							dos.writeUTF("*Police* the person you want to know "+job.getText());
							dos.flush();
						}
					}else if(msgChatRead.startsWith("!!! A mafia")){
						ImageIcon winCitizen = new ImageIcon ("Images//sss-1.jpg");
						winCitizen.getImage().flush();
						eventLabel.setIcon(winCitizen);
						dos.writeUTF("//gameEnd");
						dos.flush();
						JOptionPane.showMessageDialog(null,"Team Citizen won!!!","End Game",JOptionPane.PLAIN_MESSAGE);
						gameStart = false;
					}else if(msgChatRead.equals("~~Mafia is the winner~~")){
						ImageIcon winMafia = new ImageIcon ("Images//6a7c.jpg");
						winMafia.getImage().flush();
						eventLabel.setIcon(winMafia);
						dos.writeUTF("//gameEnd");
						dos.flush();
						JOptionPane.showMessageDialog(null,"Mafia won!!!","End Game",JOptionPane.PLAIN_MESSAGE);
						gameStart = false;
					
					}else if(msgChatRead.startsWith("!!! A citizen")){
						ImageIcon killedCitizen = new ImageIcon ("Images//killed.jpg");
						killedCitizen.getImage().flush();
						eventLabel.setIcon(killedCitizen);
						JOptionPane.showMessageDialog(null,"Witch-hunting!! A kind citizen was murderd..","Murder!!!",JOptionPane.PLAIN_MESSAGE);
					}else if(msgChatRead.startsWith("!!! A police")){
					ImageIcon killedCitizen = new ImageIcon ("Images//killed.jpg");
						killedCitizen.getImage().flush();
						eventLabel.setIcon(killedCitizen);
						JOptionPane.showMessageDialog(null,"Witch-hunting!! A kind police was murderd....","Murder!!!",JOptionPane.PLAIN_MESSAGE);
					}else{
						textArea.append(msgChatRead+"\n");
						scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
					}
				}catch(StringIndexOutOfBoundsException sibe){
					System.out.println(sibe);
				}catch(IOException ie){
				}
			}
		}
	
		public void updateClientList(){
			ImageIcon imageiconClient;
			if(Integer.parseInt(clientIdx)==0){
				imageiconClient= new ImageIcon("Images//gin.png");
				imageiconClient.getImage().flush();
				label_Client1.setIcon(imageiconClient);
				label_Client1_Info.setFont(font1);
				label_Client1_Info.setText(clientName);
				
				deleteClientList();
			}else if(Integer.parseInt(clientIdx)==1){
				imageiconClient = new ImageIcon("Images//bye.png");
				imageiconClient.getImage().flush();
				label_Client2.setIcon(imageiconClient);
				label_Client2_Info.setFont(font1);
				label_Client2_Info.setText(clientName);
				
				deleteClientList();
			}else if(Integer.parseInt(clientIdx)==2){
				imageiconClient = new ImageIcon("Images//chianti.png");
				imageiconClient.getImage().flush();
				label_Client3.setIcon(imageiconClient);
				label_Client3_Info.setFont(font1);
				label_Client3_Info.setText(clientName);
				deleteClientList();
			}else if(Integer.parseInt(clientIdx)==3){
				imageiconClient = new ImageIcon("Images//kir.png");
				imageiconClient.getImage().flush();
				label_Client4.setIcon(imageiconClient);
				label_Client4_Info.setFont(font1);
				label_Client4_Info.setText(clientName);
				deleteClientList();
			}else if(Integer.parseInt(clientIdx)==4){
				imageiconClient = new ImageIcon("Images//vodka.png");
				imageiconClient.getImage().flush();
				label_Client5.setIcon(imageiconClient);
				label_Client5_Info.setFont(font1);
				label_Client5_Info.setText(clientName);
				deleteClientList();
			}else if(Integer.parseInt(clientIdx)==5){
				imageiconClient = new ImageIcon("Images//vermouth.png");
				imageiconClient.getImage().flush();
				label_Client6.setIcon(imageiconClient);
				label_Client6_Info.setFont(font1);
				label_Client6_Info.setText(clientName);
				deleteClientList();
			}
		}

		void deleteClientList(){
			ImageIcon watingIcon = new ImageIcon("images\\shadow.png");

			if(Integer.parseInt(clientIdx) == 0){
				label_Client2.setIcon(watingIcon);
				label_Client2_Info.setText("Waiting...");
				label_Client2_Info.setFont(font1);
				label_Client3.setIcon(watingIcon);
				label_Client3_Info.setFont(font1);
				label_Client3_Info.setText("Waiting...");
				label_Client4.setIcon(watingIcon);
				label_Client4_Info.setFont(font1);
				label_Client4_Info.setText("Waiting...");
				label_Client5.setIcon(watingIcon);
				label_Client5_Info.setFont(font1);
				label_Client5_Info.setText("Waiting...");
				label_Client6.setIcon(watingIcon);
				label_Client6_Info.setFont(font1);
				label_Client6_Info.setText("Waiting...");
			}else if(Integer.parseInt(clientIdx) == 1){
				label_Client3.setIcon(watingIcon);
				label_Client3_Info.setFont(font1);
				label_Client3_Info.setText("Waiting...");
				label_Client4.setIcon(watingIcon);
				label_Client4_Info.setFont(font1);
				label_Client4_Info.setText("Waiting...");
				label_Client5.setIcon(watingIcon);
				label_Client5_Info.setFont(font1);
				label_Client5_Info.setText("Waiting...");
				label_Client6.setIcon(watingIcon);
				label_Client6_Info.setFont(font1);
				label_Client6_Info.setText("Waiting...");
			}else if(Integer.parseInt(clientIdx) == 2){
				label_Client4.setIcon(watingIcon);
				label_Client4_Info.setFont(font1);
				label_Client4_Info.setText("Waiting...");
				label_Client5.setIcon(watingIcon);
				label_Client5_Info.setFont(font1);
				label_Client5_Info.setText("Waiting...");
				label_Client6.setIcon(watingIcon);
				label_Client6_Info.setFont(font1);
				label_Client6_Info.setText("Waiting...");
			}else if(Integer.parseInt(clientIdx) == 3){	
				label_Client5.setIcon(watingIcon);
				label_Client5_Info.setFont(font1);
				label_Client5_Info.setText("Waiting...");
				label_Client6.setIcon(watingIcon);
				label_Client6_Info.setFont(font1);
				label_Client6_Info.setText("Waiting...");
			}else if(Integer.parseInt(clientIdx) == 4){
				label_Client6.setIcon(watingIcon);
				label_Client6_Info.setFont(font1);
				label_Client6_Info.setText("Waiting...");
			}
		}
	}
	class Speaker extends Thread implements KeyListener, ActionListener
	{
		//in startChat(), get socket and nickname
		Speaker(){
			new Thread(this).start();
		}
		public void run(){//continues
			try{
				dos.writeUTF(nickName);//send a nickname
				//dos.writeUTF(clientJob);
				//textArea.append("gotgot?");
				System.out.println(nickName);
				//System.out.println(clientJob);
			}catch(IOException ie){}
		}
	//JButton sendButton, button_Ready, postponeTime, reduceTime, voteButton, abilityButton;
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == button_Ready){
				try{
					dos.writeUTF("//Chat"+nickName+" is ready!");
					dos.flush();
					dos.writeUTF("//Ready");
					dos.flush();
					button_Ready.setEnabled(false);
				}catch(IOException ie){
				}
			}else if(e.getSource() == ruleBookButton){
				JOptionPane.showMessageDialog(null,
				"You are invited Kopia's village.\nIf you are a citizen and find a mafia and kill him, you will survive.\nIf you are a mafia and kill 3 citizens you will be the winner. ", "Game Rules", JOptionPane.PLAIN_MESSAGE);
			}else if(e.getSource() == endGameButton){
				ImageIcon tempi = new ImageIcon("Images//TEST.JPG");
				int result = JOptionPane.showConfirmDialog(null, "Are you sure to quit the game?","End Game",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,tempi);
				if(result == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}else if(e.getSource() == voteButton){
				showListNicknames();
				Object objVote= null;
				if(numberUsers==3){
					objVote = JOptionPane.showInputDialog(null, "Pick the one to kill!", "Time to Vote", JOptionPane.QUESTION_MESSAGE, ii,
					userList3,
					userList3[2]);
				}else if(numberUsers==4){
					objVote = JOptionPane.showInputDialog(null, "Pick the one to kill!", "Time to Vote", JOptionPane.QUESTION_MESSAGE, ii,
					userList4,
					userList4[3]);
				}else if(numberUsers==5){
					objVote = JOptionPane.showInputDialog(null, "Pick the one to kill!", "Time to Vote", JOptionPane.QUESTION_MESSAGE, ii,
					userList5,
					userList5[4]);
				}else if(numberUsers==6){
					objVote = JOptionPane.showInputDialog(null, "Pick the one to kill!", "Time to Vote", JOptionPane.QUESTION_MESSAGE, ii,
					userList6,
					userList6[5]);
				}
				if(objVote !=null){
					voteButton.setEnabled(false);
					String resultVote = objVote.toString();
					try{
						dos.writeUTF("//Vote"+ resultVote);
						dos.flush();
					}catch(IOException ie){}
				}
			}else if(e.getSource() == sendButton){
				String msgChatButton = textField.getText();
				textField.setText("");
				try{
					dos.writeUTF("//Chat "+nickName+">> " + msgChatButton);
					dos.flush();
				}catch(IOException ie){}
			}else if(e.getSource() == abilityButton){
				System.out.println("job.getTExt():"+job.getText());
				if(job.getText().equals("Job : Mafia")){
					showListNicknames();
					Object objVoteMafia=null;
					if(numberUsers==3){
						objVoteMafia = JOptionPane.showInputDialog(null, "Pick the one to kill!", "Time to Vote", JOptionPane.QUESTION_MESSAGE, ii,
						userList3,
						userList3[2]);
					}else if(numberUsers==4){
						objVoteMafia = JOptionPane.showInputDialog(null, "Pick the one to kill!", "Time to Vote", JOptionPane.QUESTION_MESSAGE, ii,
						userList4,
						userList4[3]);
					}else if(numberUsers==5){
						objVoteMafia = JOptionPane.showInputDialog(null, "Pick the one to kill!", "Time to Vote", JOptionPane.QUESTION_MESSAGE, ii,
						userList5,
						userList5[4]);
					}else if(numberUsers==6){
						objVoteMafia = JOptionPane.showInputDialog(null, "Pick the one to kill!", "Time to Vote", JOptionPane.QUESTION_MESSAGE, ii,
						userList6,
						userList6[5]);
					}
						
					if(objVoteMafia !=null){
						abilityButton.setEnabled(false);
						String resultVoteMafia = objVoteMafia.toString();
						try{
							dos.writeUTF("//MafiaKills"+ resultVoteMafia);
							dos.flush();
						}catch(IOException ie){}
						
					}

				
				}else if(job.getText().equals("Job : Police")){
					System.out.println("job.getTExt():"+job.getText());
					showListNicknames();
					Object abilityPolice=null;
					if(numberUsers==3){
						abilityPolice = JOptionPane.showInputDialog(null, "Pick the one to kill!", "Time to Vote", JOptionPane.QUESTION_MESSAGE, ii,
						userList3,
						userList3[2]);
					}else if(numberUsers==4){
						abilityPolice = JOptionPane.showInputDialog(null, "Pick the one to kill!", "Time to Vote", JOptionPane.QUESTION_MESSAGE, ii,
						userList4,
						userList4[3]);
					}else if(numberUsers==5){
						abilityPolice = JOptionPane.showInputDialog(null, "Pick the one to kill!", "Time to Vote", JOptionPane.QUESTION_MESSAGE, ii,
						userList5,
						userList5[4]);
					}else if(numberUsers==6){
						abilityPolice = JOptionPane.showInputDialog(null, "Pick the one to kill!", "Time to Vote", JOptionPane.QUESTION_MESSAGE, ii,
						userList6,
						userList6[5]);
					}
						
					if(abilityPolice !=null){
						abilityButton.setEnabled(false);
						
						String policeA = abilityPolice.toString();
						try{
							dos.writeUTF("//policeA"+ policeA);
							dos.flush();
						}catch(IOException ie){}
						
					
					}
				}
		}
		}
		public void keyReleased(KeyEvent e){ 
			if(e.getKeyCode()==KeyEvent.VK_ENTER){
				String msgChatEnter = textField.getText();
				textField.setText("");
				try{
					//write msgChat on the server
					dos.writeUTF("//Chat "+nickName+">> " +msgChatEnter);
					dos.flush();
				}catch(IOException ie){}
			}
		}

		public void keyTyped(KeyEvent e){}
		public void keyPressed(KeyEvent e){}
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String args[]){
		new MainGameUI();
	}
}

