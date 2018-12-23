package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.Scanner;

public class Model {
	static Deck deck = new Deck();
	static Random ran = new Random();
	public static void main(String[] args) {
		importDeck();
	}
	
	public static void setupGame() {
		/* on choisit le nb de joueur et leurs couleur*/
		int nbPlayer = viewText.Main.nbPlayer();
		player = new Player[nbPlayer];
		for (int i=0; i<nbPlayer;i++) {
					String color = viewText.Main.colorchoice(i);
					if(nbPlayer>2) {
						player[i] = new Player(1,color,5, deck);
					}
					else{
						player[i] = new Player(2,color,5, deck);
					}
		}
		/*on mélange le deck et on pioche les dominos et on donne un ordre de passage*/
		deck.shuffle(nbPlayer*12);
		
		ArrayList<Domino> onboarddominos =new ArrayList<>();
		for(int i=0; i<nbPlayer;i++) {
			onboarddominos.add(deck.nextDomino());
		}
		onboarddominos.sort(Comparator.comparing(Domino::getNumber));
		viewText.Main.showdominos(onboarddominos);
		
		Random random = new Random();
		int order = random.nextInt(nbPlayer)+1;
		Player chosenplayer =player[order];
		
		 /* piocher dominos
		 * random ordre pour jouer
		 * 
		 * 
		 * choisir domino /joueur
		 * pioche dominos
		 */
	}

	public static void play() {
			/* 
			 * pour chaque joueur
			 *		 placer domino 
			*		 choisir dominos 
			*
			*if (c'est fini)
			*		compter les points
			*		dire qui c'est qu'a gagné
			 * piocher donimos
			 */
		
	}
	
	public static void turnRight() {
		
	}
	
	public static void importDeck() {
		Scanner sc = null;
		try {
		    sc = new Scanner(new File("dominos.csv")); 
		} catch (FileNotFoundException e) {
		    System.out.println("Fichier non trouvé");
		}
		
		try {
			sc.nextLine();
			while (sc.hasNext()) {
				String line = sc.nextLine();
			    String[] elements = line.split(",");
			    deck.add(Integer.parseInt(elements[0]), 
			    		Integer.parseInt(elements[2]), 
			    		elements[1], 
			    		elements[3], 
			    		Integer.parseInt(elements[4]));			    
			}
		}
		finally {
			sc.close();
		}
	}
}
