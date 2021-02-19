
# ABOUT PROJECT
#### [ KOPIA ] Création d'une application de Jeu "Loup Garou" en Java : Programmation de Socket en java, Création de Chat, UI & UX Design, Gestion de projet<br><span style="font-size:15px">*( 2020.05.26 ~ 2020.06.01 )*</span>

## 1. Membres de l'équipe

|*Members*|*Contact*|
|:---:|---|
|**Joohyun ANN**|[![Github Badge](https://img.shields.io/badge/-Github-000?style=flat-square&logo=Github&logoColor=white)](http://github.com/catwithhumanface) [![Gmail Badge](https://img.shields.io/badge/-annjh11@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:annjh11@gmail.com)](mailto:annjh11@gmail.com)|


## 2. Exécution de jeu
1. Exécuter MafiaServer.exe
2. Commencer le serveur
3. Exécuter Kopia.exe
   
## 3. Présentation du projet
&nbsp; Ayant eu l'idée de réaliser le jeu "Loup Garou" en Java, j'ai créé le projet KOPIA dans le cadre de la formation Développeur Java pendant une semaine.<br>

> <span style="font-size:13px">*Les Loups-Garous de Thiercelieux est un jeu de société d'ambiance dans lequel chaque joueur incarne un villageois ou un loup-garou, et dont le but général est, pour les villageois (dont certains ont des pouvoirs ou des particularités) : démasquer et tuer tous les loups-garous, pour les loups-garous : d'éliminer tous les villageois et ne pas se faire démasquer*<br></span>

&nbsp; L'application se compose de quatre grandes parties, **Serveur**, **Login**, **ClientModule**, **MainGameUI**.

## 4. Technologie 
Java

# Results
## 1. Codes
### *Server*
**Création du Serveur**, dans la limite de 6 personnes (nombres de jouers maximum)<br>
**Connexion et Déconnexion**, gérer la connexion du serveur<br>
**Contrôle du temps du jeu**, alterner la journée et la nuit, le temps de vote, etc.<br>


### Création Serveur
			try{
				ss = new ServerSocket(port);
				serverStatusLabel.setText("          Server is startig...         ");
				textArea.append(" Server is turned on. " +"\n");
				serverStartButton.setEnabled(false);
				serverCloseButton.setEnabled(true);
				while(true){
					s = ss.accept();
					ocm = new OneClientModule(this);
					clientList.add(ocm);
					if((clientList.size()  ) > MAX_CLIENT) s.close();
					else{
						ocm.start();
					}
				}
			}catch(IOException ie){}
			}
	

---

### *Login*
**Se connecter au jeu**, grâce à l'interface simple, il sutffit de mettre un surnom afin de rentrer au jeu.
**Appel à MainGameUI**, qui amène à l'UI du jeu. Une fois connexion réalisée, l'interface change automatiquement.

### Connexion
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
				}

---

### *MainGameUI*
**Mettre à jour de la liste des jouers** automatiquement, lorsqu'il y a un changement au niveau de participant.
**Chatting**, recevoir et envoyer un message est possible.
**Suivi du jeu**, selon le jouer est montré sur l'interface de chacun.

### Chatting
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
---

### *OneClientModule*
**Distribution des métiers en Random**
**Gestion de jeu**, communquant via sockets avec **Protocole**.

### Random Job
	String Jobs[] = {"Mafia", "Police", "Citizen1", "Citizen2", "Citizen3", "Citizen4"};
		Random r = new Random();
		String job;
		int a[]= new int[6];
		for(int i=0;i<6;i++ ){
			a[i]= r.nextInt(6);
			for(int j=0; j<i; j++){
				if(a[i]==a[j]){
					i--;
				}
			}
		}
		try{
			int k =0;
			for(OneClientModule ocm : ms.clientList){
					Jobgiven = ("//Job"+ Jobs[a[k]]);
					ms.listAlive.add(Jobgiven.substring(5));
					ocm.dos.writeUTF(Jobgiven);
					ocm.dos.flush();
					k++;
				}	
		}catch(IOException ie){}
		for(String ad : ms.listAlive){
			System.out.println(ad);
		}

---
## 2. Views
### *Server*
![Server](md_imgs/server.jpg)
<br>

### *Login*
![Login](md_imgs/login.gif)
<br>

### *Jeu*
![Citizen](md_imgs/citizen.gif)
<br>

![Police](md_imgs/police.gif)
<br>

![Mafia](md_imgs/mafia.gif)
<br>
