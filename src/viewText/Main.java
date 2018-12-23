package viewText;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import model.Deck;
import model.Player;
import model.Domino;
import model.Model;

public class Main {
	static Random ran = new Random();
	static Scanner scan = new Scanner(System.in);
	
    public static void main(String[] args) {
    	Model.importDeck();
    	setupGame();
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
    		dominoChoice(i);
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
    
    public static void showDominos(ArrayList<Domino> dominos) {
    	for(Domino d : dominos) {
    		System.out.print(d.getNumber()+ " : ");
    		System.out.println("Le premier territoire est "+ d.getHalf(0).getType() +" avec "+ d.getHalf(0).getCrown() +" couronnes\n"
    				+ "Le deuxieme territoire est "+ d.getHalf(1).getType() +" avec "+ d.getHalf(1).getCrown() +" couronnes\n");
    	}
    }
    
    public static int dominoChoice(int i) {
    	System.out.println("Joueur "+ (i+1) +" quelle domino choisissez vous ? (donner son numéro)");
    	int d=scan.nextInt();
    	scan.nextLine();
    	return d;
    }
    
    
    
    
	public static void play() {
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
