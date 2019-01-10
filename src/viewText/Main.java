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
	static Player lagia = new Player(1, "a", 5);
	static int [] newOrder= new int[4];

	
    public static void main(String[] args) {
    	Model.importDeck();
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
    	showDominos(Model.onBoardDominos);
    	
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
    	System.out.println("Joueur"+" " +(i+1)+" "+"quelle couleur voulez-vous ? (Rouge, Vert, Jaune, Bleu)");
		String color = scan.nextLine();
		Model.createPlayer(i, color);

    }
    
    public static void showDomino(Domino d) {
    	System.out.print(d.getNumber()+ " : ");
		System.out.println("Le premier territoire est "+ d.getHalf(0).getType() +" avec "+ d.getHalf(0).getCrown() +" couronnes\n"
				+ "Le deuxieme territoire est "+ d.getHalf(1).getType() +" avec "+ d.getHalf(1).getCrown() +" couronnes\n");
    }
    
    public static void showDominos(ArrayList<Domino> dominos) {
    	for(Domino d : dominos) {
    		showDomino(d);
    	}
    }
    
    public static int dominoChoice(int i) {
    	System.out.println("Joueur "+ (i+1) +" quelle domino choisissez vous ? (donner son numéro)");
    	int d=scan.nextInt();
    	scan.nextLine();
    	return d;
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
						//aide pour débuguer
						System.out.println("tu peut le placer : " + Model.player[j].listPlacable(d));
						dominoPlace(j,d);
						
//						montre les dominos suivant pour qu'il choisisse son prochain domino
						
						showDominos(Model.onBoardDominos);
						int dc = dominoChoice(j);
						System.out.println(Model.deck.getDomino(dc));
						System.out.println(Model.dominosPlaying.get(j));
//						Recupere l'ordre du domino qu'il a choisi pour avoir l'ordre de jeu
						
							for(int k=0; k< Model.onBoardDominos.size(); k++) {
								System.out.println(Model.deck.getDomino(dc)==Model.onBoardDominos.get(k));
								if(Model.deck.getDomino(dc)==Model.onBoardDominos.get(k)) {
									newOrder2[j]=Model.onBoardDominos.indexOf(Model.onBoardDominos.get(k));
								}
							}
					}
			}	
				//met à jour l'ordre de jeu
				System.out.println(newOrder2);
				for(int z = 0; z < Model.onBoardDominos.size(); z++) {
					newOrder[z]=newOrder2[z];
				}

			
		}

		/*
		 * pour chaque joueur
		 *		 placer domino
		*		 choisir dominos
		*
		*if (c'est fini)
		*		compter les points
		*		dire qui c'est qu'a gagnï¿½
		 * piocher donimos
		 */
	}	
}
