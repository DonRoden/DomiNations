package viewText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import model.*;



public class Main {
	static Random ran = new Random();
	static Scanner scan = new Scanner(System.in);
	static List<Integer> coord=new ArrayList<>();
	static Player lagia = new Player(1, "a");
	static int [] newOrder= new int[4];
	static List<Integer> dc=new ArrayList<>();

	
    public static void main(String[] args) {
    	Model.deck.importDeck();
    	Model.deckTest.importDeck();
    	setupGame();
    	play();

    }

    public static void setupGame() {
    	/*
    	 * Le setupGame est très simple, chaque action fais une ligne, 
    	 * sauf lors d'une boucle pour les 4 joueurs (ex: couleur)
    	 * 
    	 * De plus certaines fonctions ont besoin de donnees de l'utilisateur,
    	 * dans ce cas on les definit ici (ex; choisir une couleur ou un nombre de joueur)
    	 * Les fonctions qui n'ont pas besoin de l'entree clavier sont definies dans Model
    	 * (ex: piocher, melanger)
    	 */
    	
    	// on choisit le nb de joueurs
    	nbPlayer();
    	
    	//On choisit leur couleur
    	for (int i = 0; i < Model.player.length; i++) {
    		colorChoice(i);
    	}
    	
    	//On mélange le deck
    	Model.shuffleDeck();
    	
    	//On pioche
    	Model.draw();
    	
    	//On affiche la pioche
    	dc.add(0);
    	showDominos(Model.onBoardDominos,dc);
    	
    	//On definit un ordre aleatoire pour le premier tour
    	Model.setRandomOrder();
    	
    	//Chaque joueur choisit un domino

    	for (int i : Model.order) {
    		int dc = dominoChoice(i);
    		for(int k=0; k< Model.order.length; k++) {
    			if(Model.deck.getDomino(dc)==Model.onBoardDominos.get(k))
    				newOrder[k]=i;
    		}
    	}
	}
    
    public static void nbPlayer() {
    	//On demande a l'utilisateur le nombre de joueur
		System.out.println("Combien de personnes veulent jouer ?");
		int nbPlayer = scan.nextInt();
		scan.nextLine();
		
		if(nbPlayer<=4) {
			//Si le nombre de joueurs est adéquat, on définit le nombre de joueur dans Model
			Model.setNbPlayer(nbPlayer);
		}
		else {
			//Sinon il suffit de relancer la fonction pour redemander le nombre
			System.out.println("Trop de joueurs !");
			nbPlayer();
		}
    }
    
    public static void colorChoice(int i){
    	System.out.println("Joueur"+" " +(i+1)+" "+" comment vous appellez-vous ? (alex, paul...)");
		String name = scan.nextLine();
		Model.createPlayer(i, "", name);

    }
    
    public static void showDomino(Domino d) {
    	System.out.print(d.getNumber()+ " : ");
		System.out.println("Le premier territoire est "+ d.getHalf(0).getType() +" avec "+ d.getHalf(0).getCrown() +" couronnes\n"
				+ "Le deuxieme territoire est "+ d.getHalf(1).getType() +" avec "+ d.getHalf(1).getCrown() +" couronnes\n");
    }
    
    public static void showDominos(ArrayList<Domino> dominos, List<Integer> dc) {
    	for(Domino d : dominos) {
    		if(dc.size()==1) {
    			showDomino(d);
    		}
    		else {
    			if(!dc.contains(d.getNumber())) {
    				showDomino(d);	
    			}
    		}
    	}
    }
    public static Domino getOnBoardDomino(int nbDomino) {
		for (Domino dom : Model.onBoardDominos) {
			if (dom.getNumber() == nbDomino) {
				return dom;
			}
		}
		return null;
	}
    
    public static int dominoChoice(int i) {
    	System.out.println("Joueur "+ (i+1) +" quelle domino choisissez vous ? (donner son numéro)");
    	int d=scan.nextInt();
    	scan.nextLine();
    	
    	if(getOnBoardDomino(d)!=Model.deck.getDomino(d) || d>48) {
    		dominoChoice(i);
    		return d;
    		
    	}
    	else {
        	return d;
    	}
    }
    
    public static void getCoord(int i) {
    	System.out.println("Joueur "+ (i+1) +", veuillez rentrer la coordonnée selon x de la première partie du domino");
    	coord.add(scan.nextInt());
    	scan.nextLine();
    	System.out.println("Joueur "+ (i+1) +" veuillez rentrer la coordonnée selon y de la première partie du domino");
    	coord.add(scan.nextInt());
    	scan.nextLine();
    	System.out.println("Joueur "+ (i+1) +" veuillez rentrer la coordonnée selon x de la seconde partie du domino ");
    	coord.add(scan.nextInt());
    	scan.nextLine();
    	System.out.println("Joueur "+ (i+1) +" veuillez rentrer la coordonnée selon y de la seconde partie du domino");
    	coord.add(scan.nextInt());
    	scan.nextLine();
    }
    
    public static void dominoPlace(int j,Domino d) {
    	getCoord(j);
		if(Model.player[j].isPlacable(coord.get(0),coord.get(1),coord.get(2),coord.get(3),d)) {
			Model.player[j].placeDomino(coord.get(0),coord.get(1),coord.get(2),coord.get(3),d);
		}
		else {
			System.out.println("Vous ne pouvez pas placer cette piece ici");
			coord.clear();
			dominoPlace(j,d);
		}
    }
    
    
    
	public static void play() {
		while(Model.deck.hasNext()) {
			System.out.println("\n\nNouveau tour \n");
			Model.dominosPlaying = Model.onBoardDominos;
			Model.draw();
			// création d'une liste temporaire pour faire l'ordre de jeu
				int[] newOrder2 = new int[4];
				
				for(int i=0; i<Model.nbKings;i++) {
					
					int j = newOrder[i];
					
					if(Model.player[j]==lagia) {
						
					}
					else {
//						affiche le board et le domino choisi puis le place
						
						Model.player[j].printBoard();
						Domino d=Model.dominosPlaying.get(i);
						showDomino(d);
						//si le joueurs peut placer le domino on l'autorise à le placer sinon on passe au choix
						
							if(Model.player[j].listPlacable(d).size()!=0) {
								dominoPlace(j,d);
								coord.clear();
							}
							
						
//						montre les dominos suivant pour qu'il choisisse son prochain domino
						
						showDominos(Model.onBoardDominos,dc);
						
						int newDc =dominoChoice(j);
						dc.add(newDc);
//						Recupere l'ordre du domino qu'il a choisi pour avoir l'ordre de jeu
						
							for(Domino domino:Model.onBoardDominos) {
								if(Model.deck.getDomino(newDc)==domino) {
									newOrder2[Model.onBoardDominos.indexOf(domino)]=j;
								}
							}
					}
			}	
				//met à jour l'ordre de jeu
					newOrder=newOrder2;
					dc.clear();
					dc.add(0);
				}
				//fin de la partie, on compte les points
				System.out.println("La partie est finie  !!!");
				for (Player p : Model.player) {
					p.scoreBoard(p.board);
					System.out.println("Score : " + p.totalScore);
				}
			
		}
	}	

