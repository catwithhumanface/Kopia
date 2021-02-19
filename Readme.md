
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

> <span style="font-size:13px">*Les Loups-Garous de Thiercelieux est un jeu de société d'ambiance dans lequel chaque joueur incarne un villageois ou un loup-garou, et dont le but général est : <br>
  1. pour les villageois (dont certains ont des pouvoirs ou des particularités) : démasquer et tuer tous les loups-garous<br>
  2. pour les loups-garous : d'éliminer tous les villageois et ne pas se faire démasquer*<br></span>

&nbsp; L'application se compose de quatre grandes parties, **Serveur**, **Login**, **ClientModule**, **MainGameUI**.

## 4. Technologie 
Java

# Results
## 1. Codes
### *Server*
  
![MafiaServer](md_imgs/server.gif)
<br>
**Création du Serveur**, dans la limite de 6 personnes (nombres de jouers maximum)<br>
**Connexion et Déconnexion**, gérer la connexion du serveur<br>
**Contrôle du temps du jeu**, alterner la journée et la nuit, le temps de vote, etc.<br>


### 1.2.1. 장점
	1. 간결하다.
	2. 별도의 도구없이 작성가능하다.
	3. 다양한 형태로 변환이 가능하다.
	3. 텍스트(Text)로 저장되기 때문에 용량이 적어 보관이 용이하다.
	4. 텍스트파일이기 때문에 버전관리시스템을 이용하여 변경이력을 관리할 수 있다.
	5. 지원하는 프로그램과 플랫폼이 다양하다.
### 1.2.2. 단점
	1. 표준이 없다.
	2. 표준이 없기 때문에 도구에 따라서 변환방식이나 생성물이 다르다.
	3. 모든 HTML 마크업을 대신하지 못한다.

---

### *Login*
![Login](md_imgs/login.gif)
<br>
**Se connecter au jeu**, grâce à l'interface simple, il sutffit de mettre un surnom afin de rentrer au jeu.
**Appel à MainGameUI**, qui amène à l'UI du jeu. Une fois connexion réalisée, l'interface change automatiquement.

---

### *MainGameUI*
![Login](md_imgs/mainGame.gif)
<br>
**Mettre à jour de la liste des jouers** automatiquement, lorsqu'il y a un changement au niveau de participant.
**Chatting**, recevoir et envoyer un message est possible.
**Suivi du jeu**, selon le jouer est montré sur l'interface de chacun.

---

### *OneClientModule*
![client](md_imgs/mainGame.gif)
<br>
**Distribution des métiers en Random**
**Gestion de jeu**, communquant via sockets avec **Protocole**.

---
