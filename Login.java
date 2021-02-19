
import java.awt.*;
import java.awt.Font;//
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;//
import javax.swing.BorderFactory;//
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;//
import javax.swing.text.*;//
import java.awt.image.BufferedImage;
import java.io.*;
import javax.sound.sampled.*; //
//import javafx.scene.media.*; //

public class Login extends JFrame implements ActionListener{
	Font font1 = new Font("Tahoma Bold", Font.PLAIN, 20);
	Container cp;
	JPanel bigJP;
	JButton loginButton;
	JLabel labelID, labelIP;
	JTextField idText, ipText;
	JButton loginB;
	ImageIcon titleImg = new ImageIcon("Images//코난.png");

	ImageIcon backGround = new ImageIcon("Images//Conan Black Tissue.jpg");
	Image im = backGround.getImage();

	public static String ip, nickName, hiMsg;

	void init(){
		
		cp = getContentPane();
		bigJP = new JPanel();
		
		
		//big pannel with place(position)
		cp.add(bigJP, BorderLayout.CENTER);
		bigJP.setBorder(BorderFactory.createEmptyBorder(385,50,0,0));
		bigJP.setOpaque(false);
		
		//Login button to put
		loginB = new JButton(new ImageIcon("Images//loginButton.png"));
		loginB.setOpaque(true);
		loginB.setSize(10,5);
		loginB.setBorder(new LineBorder(Color.BLACK, 0 ,false));
		loginB.setBackground(new Color(0,156,255));
		loginB.setOpaque(false);
		loginB.addActionListener(this);

		//make ID window 
		labelID = new JLabel("ID");
		labelID.setFont(font1);
		labelID.setForeground(Color.WHITE);
		bigJP.add(labelID);

		idText = new JTextField();
		idText.setText("");
		idText.setColumns(15);

		bigJP.add(idText);
		
		//make ID window 
		labelIP = new JLabel("IP");
		labelIP.setFont(font1);
		labelIP.setForeground(Color.WHITE);
		bigJP.add(labelIP);

		ipText = new JTextField();
		ipText.setText("127.0.0.1");
		ipText.setColumns(15);
		ipText.setBounds(20, 10, 20, 20);
		bigJP.add(ipText);
		bigJP.add(loginB);
		setUi();
		
	}

	//background for pannel
	class PanelPaint extends JPanel{
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(im,0,0,getWidth(),getHeight(),this);
		}
	}

	//make window
	void setUi(){
		setBounds(200,100,804,870); 
		setTitle("Log-in to KOPIA GAME");
		setIconImage(titleImg.getImage());
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelPaint panelP = new PanelPaint();
		add(panelP);
		
	}

	public void actionPerformed(ActionEvent e){
		//ImageIcon iconImg1 = new ImageIcon("Images//icon1.png");
		ImageIcon iconImg2 = new ImageIcon("Images//icon2.png");
		Object obj = e.getSource();
		idText.requestFocus();
		if(obj == loginB){
			playSound("LoginButtonSound.wav");
			if(idText.getText().equals("") || ipText.getText().equals("")){
				JOptionPane.showMessageDialog(null,"Put correct informations.", "Login Error", JOptionPane.QUESTION_MESSAGE, iconImg2); 
			}else if(idText.getText().trim().length()>6){
				JOptionPane.showMessageDialog(null,"Put an ID with less than 6 characters.", "IP Error", JOptionPane.QUESTION_MESSAGE, iconImg2);
				idText.setText("");
				idText.requestFocus();
			}else{
				nickName = idText.getText().trim();
				String ipTemp = ipText.getText();
				if(ipTemp.matches("(^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$)")){
				ip =ipTemp;
				playSound("LoginButtonSound.wav");
				
				JOptionPane.showMessageDialog(null, "Success to log-in!", "LOGIN", JOptionPane.INFORMATION_MESSAGE, iconImg2);
				loginB.setEnabled(false);
				idText.setEnabled(false);
				ipText.setEnabled(false);
				setVisible(false);
				MainGameUI mgUI = new MainGameUI();
			}else{
				 JOptionPane.showMessageDialog(null, "Put a right IP Address! ", "ERROR!", JOptionPane.WARNING_MESSAGE, iconImg2);
			}
			}

		}
	}

	
	static void playSound(String filename){ 
		File file = new File("./waves/" + filename);
		if(file.exists()){ 
			try{
				AudioInputStream stream = AudioSystem.getAudioInputStream(file);
				Clip clip = AudioSystem.getClip();
				clip.open(stream);
				clip.start();
			}catch(Exception e){
				System.out.println("Put your audio on if you want to put the music on");
			}
		}else{ 
			System.out.println("File Not Found!");
		}
	}	




	public static void main(String[] args) 
	{
		new Login().init();
		playSound("LoginBackSound.wav");
	}
}
