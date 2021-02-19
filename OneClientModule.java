
import java.io.*;
import java.net.*;
import java.util.*;

class OneClientModule extends Thread
{
	MafiaServer ms;
	Socket s;
	InputStream is;
	OutputStream os;
	DataInputStream dis;
	DataOutputStream dos;
	String clientName = "";
	String clientJobSent = "";
	String clientMyjobis ="";
	String Jobgiven="";

	//ArrayList<String> listAlive = new ArrayList<String>();
	ArrayList<String> listNicknames = new ArrayList<String>();

	
	String realmsg, dummy;
	int mafiaVoted =0;
	int policeVoted =0;
	int citizenVoted1 =0;
	int citizenVoted2 =0;
	int citizenVoted3 =0;
	int citizenVoted4 =0;

	OneClientModule(MafiaServer ms){
		this.ms = ms;
		this.s = ms.s;
			try{
				is = s.getInputStream();
				os = s.getOutputStream();
				dis = new DataInputStream(is);
				dos = new DataOutputStream(os);
			}catch(IOException ie){
			}
	}

	public void run(){
		listen();
	}
	
	void listen(){
			try{
				clientName = dis.readUTF();
				//clientJobSent = dis.readUTF();
				showSystemMsg("[ " + clientName + " is in. ]\n(Current number of gamers : " + ms.clientList.size() + " / 6)");  
				//showSystemMsg("clientJobSent: "+clientJobSent);
				ms.textArea.append(" [ List of gamers (Total " + ms.clientList.size() + "Users ON) ]\n");
				ms.clientInfo.put(clientName,dummy);
				setClientStatus();
				
				listNicknames.add(clientName);
				while(true){
					String msg = dis.readUTF();
					filterMsg(msg);
				}
			}catch(IOException io){
				ms.clientList.remove(this);
				ms.clientInfo.remove(clientName);
				setClientStatus();
				showSystemMsg("[ " + clientName + "left the game. ]\n(Current number of gamers : " + ms.clientList.size() + " / 6)");  
				ms.textArea.append("[ List of gamers (Total " + ms.clientList.size() + "Users ON) ]\n");
				ms.gameStart = false;
				showSystemMsg("//GmEnd"); 
			}finally{
				closeAll();
			}
		}
	public void showSystemMsg(String msgToshow){
		try{
			for(OneClientModule ocm : ms.clientList){
				
				ocm.dos.writeUTF(msgToshow);
				ocm.dos.flush();
			}
		}catch(IOException ie){}
	}

	public void filterMsg(String tempmsg){ 
		if(tempmsg.startsWith("//Chat")){
			realmsg = tempmsg.substring(6);
			showSystemMsg(realmsg);
		}else if(tempmsg.startsWith("//Ready")){
			ms.readyPlayer++;
			showSystemMsg("readyPlayer: "+ms.readyPlayer);
			if(ms.readyPlayer == 6){
				showSystemMsg("Everyone is READY!");
				showSystemMsg("//Start");
				//showSystemMsg("//ReadyAll");
				for(int i=5; i>0; i--){
					try{
						showSystemMsg("Game starts after "+i+"seconds");
						Thread.sleep(1000);
					}catch(InterruptedException ie){}
					}
					ms.gameStart=true;
					giveJob();
					ms.tm.start();
				
			}
		}else if(tempmsg.equals("//Postpone")){	
			//showSystemMsg("//Timer" + (toTime(time)));
		}else if(tempmsg.startsWith("//ReduceTime")){
		}else if(tempmsg.equals("//ShowList")){
			StringBuffer clientNicknamesList = new StringBuffer();
			int numberUsers = 0;
			for(OneClientModule ocm : ms.clientList){
				if(!ocm.clientName.equals("DeadCitizen")){
					clientNicknamesList.append(ocm.clientName+"/");
					numberUsers++;
				}
			
			}

			for(OneClientModule ocm : ms.clientList){
				try{
					ocm.dos.writeUTF("//List"+ clientNicknamesList + numberUsers);
					ocm.dos.flush();
				}catch(IOException ie){}
			}
		}else if(tempmsg.startsWith("//isMyjob")){
				clientMyjobis = tempmsg.substring(9);
		
		
		}else if(tempmsg.startsWith("//Vote")){		//Vote

			String nickVoted = tempmsg.substring(6); 
				for(OneClientModule ocm : ms.clientList){
					if(nickVoted.equals(ocm.clientName)){
						try{
							ocm.dos.writeUTF("//gotV");
							ocm.dos.flush();
							break; 
						}catch(IOException ie){}
					}
				}

		}else if(tempmsg.startsWith("//resultV")){//resultV Citizen(//person who got //gotV write //resultV Job)
			String resultgot = tempmsg.substring(9);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println(resultgot);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			if(resultgot.equals("Mafia")){
				ms.mafiaV++;
				//ms.mafiaV = mafiaVoted;
				System.out.println(ms.mafiaV);
			}else if(resultgot.equals("Police")){
				ms.policeV++;
				//ms.policeV = policeVoted;
				System.out.println(ms.policeV);
			}else if(resultgot.equals("Citizen1")){
				ms.citizen1V++;
				//ms.citizen1V = citizenVoted1;
				System.out.println(ms.citizen1V);
			}else if(resultgot.equals("Citizen2")){
				ms.citizen2V++;
				//ms.citizen2V = citizenVoted2;
				System.out.println(ms.citizen2V);
			}else if(resultgot.equals("Citizen3")){
				ms.citizen3V++;
				//ms.citizen3V = citizenVoted3;
				System.out.println(ms.citizen3V);
			}else if (resultgot.equals("Citizen4")){
				ms.citizen4V++;
				//ms.citizen4V = citizenVoted4;
				System.out.println(ms.citizen4V);
				
			}
		
			
		}else if(tempmsg.startsWith("*Police*")){
			for(OneClientModule ocm : ms.clientList){
				if((ocm.clientMyjobis).equals("Police")){
					try{
						ocm.dos.writeUTF(tempmsg);
						ocm.dos.flush();
					}catch(IOException ie){}

				}	
			}
			
		}else if(tempmsg.equals("//gameEnd")){
			ms.gameStart=false;
		}else if(tempmsg.startsWith("//Mkilled")){
			if(ms.listAlive.size()<3){
				
				showSystemMsg("~~Mafias win the game~~");
				ms.gameStart=false;
				
			}

			for(OneClientModule ocm : ms.clientList){
				if((ocm.clientMyjobis).equals(tempmsg.substring(15))){
					ms.listAlive.remove(tempmsg.substring(15));
					ocm.clientName="DeadCitizen";
			
				}	
			}
		}
		
		else{
			showSystemMsg(tempmsg);
		}
	}


	public void getOut(){

		try{
			if(ms.mafiaV>ms.policeV && ms.mafiaV>ms.citizen1V && ms.mafiaV>ms.citizen2V && ms.mafiaV>ms.citizen3V && ms.mafiaV>ms.citizen4V){
				for(OneClientModule ocm : ms.clientList){
					if((ocm.clientMyjobis).equals("Mafia")){
						ocm.dos.writeUTF("//Kill");
						ocm.dos.flush();
						
						//listNicknames.remove(ocm.clientName);
						ocm.clientName="DeadCitizen";
						ms.gameStart=false;

					}	
				}
				showSystemMsg("!!! A mafia is killed !!!");
				ms.listAlive.remove("Mafia");
				


			}else if(ms.policeV>ms.mafiaV && ms.policeV>ms.citizen1V && ms.policeV>ms.citizen2V && ms.policeV>ms.citizen3V && ms.policeV>ms.citizen4V){
				for(OneClientModule ocm : ms.clientList){
					if((ocm.clientMyjobis).equals("Police")){
						ocm.dos.writeUTF("//Kill");
						ocm.dos.flush();
						
						//listNicknames.remove(ocm.clientName);
						ocm.clientName="DeadCitizen";
					}
				}
				showSystemMsg("!!! A police is killed !!!");
				ms.listAlive.remove("Police");
			}else if(ms.citizen1V>ms.policeV && ms.citizen1V>ms.citizen4V && ms.citizen1V>ms.citizen2V && ms.citizen1V>ms.citizen3V && ms.citizen1V>ms.mafiaV){
				for(OneClientModule ocm : ms.clientList){
					if((ocm.clientMyjobis).equals("Citizen1")){
						ocm.dos.writeUTF("//Kill");
						ocm.dos.flush();
					
						//listNicknames.remove(ocm.clientName);
						ocm.clientName="DeadCitizen";
					}
				}
				showSystemMsg("!!! A citizen is killed !!!");
				ms.listAlive.remove("Citizen1");
			}else if(ms.citizen2V>ms.policeV && ms.citizen2V>ms.citizen4V && ms.citizen2V>ms.citizen2V && ms.citizen2V>ms.citizen3V && ms.citizen2V>ms.citizen1V){
				for(OneClientModule ocm : ms.clientList){
					if((ocm.clientMyjobis).equals("Citizen2")){
						ocm.dos.writeUTF("//Kill");
						ocm.dos.flush();
						
						//listNicknames.remove(ocm.clientName);
						ocm.clientName="DeadCitizen";
					}
				}
				ms.listAlive.remove("Citizen2");
				showSystemMsg("!!! A citizen is killed !!!");
			}else if(ms.citizen3V>ms.policeV && ms.citizen3V>ms.citizen4V && ms.citizen3V>ms.citizen2V && ms.citizen3V>ms.citizen1V && ms.citizen3V>ms.mafiaV){
				for(OneClientModule ocm : ms.clientList){
					if((ocm.clientMyjobis).equals("Citizen3")){
						ocm.dos.writeUTF("//Kill");
						ocm.dos.flush();
					
						//listNicknames.remove(ocm.clientName);
						ocm.clientName="DeadCitizen";
					}
				}
				ms.listAlive.remove("Citizen3");
				showSystemMsg("!!! A citizen is killed !!!");
			}else if(ms.citizen4V>ms.policeV && ms.citizen4V>ms.citizen1V && ms.citizen4V>ms.citizen2V && ms.citizen4V>ms.citizen3V && ms.citizen4V>ms.mafiaV){
				for(OneClientModule ocm : ms.clientList){
					if((ocm.clientMyjobis).equals("Citizen4")){
						ocm.dos.writeUTF("//Kill");
						ocm.dos.flush();
						
						//listNicknames.remove(ocm.clientName);
						ocm.clientName="DeadCitizen";

					}
				}
				ms.listAlive.remove("Citizen4");
				showSystemMsg("!!! A citizen is killed !!!");
			}else{
				showSystemMsg("The vote has been voided.");
			}
		}catch(IOException ie){}
		

	}
	void setClientStatus(){
		String [] keys = new String[ms.clientInfo.size()];
		int index = 0;
		for(Map.Entry<String, String> mapEntry : ms.clientInfo.entrySet()){
			keys[index] = mapEntry.getKey();
			index++;
		}
		for(int i=0; i<ms.clientList.size(); i++){
			showSystemMsg("//CList" + keys[i] + "#"+ i);
		}

	}



	public void resetVote(){
			System.out.println("ms.listALive.size"+ms.listAlive.size());
		
			if(ms.listAlive.size()<3){
				showSystemMsg("~~Mafia is the winner~~");
				ms.gameStart=false;
			}
						
		ms.mafiaV =0;
		ms.policeV =0;
		ms.citizen1V =0;
		ms.citizen2V =0;
		ms.citizen3V =0;
		ms.citizen4V =0;
	}

	public void giveJob(){
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
		System.out.println("listalivesize"+ms.listAlive.size());
	}

	
	public void closeAll(){
			try{
				if(dos != null) dos.close();
				if(dis != null) dis.close();
				if(s != null) s.close();
			}catch(IOException ie){}
		}
}

	

