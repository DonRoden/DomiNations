package model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Model {
	static Deck deck = new Deck();
	static Player[] player;
	static Random ran = new Random();
	
	public static void main(String[] args) {
    	ObjectInputStream ois;
    	
        try {
        	ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("dominos"))));
        	deck = (Deck)ois.readObject();
        	ois.close();
        }
        catch (FileNotFoundException e) {
        	e.printStackTrace();
        }
        catch (IOException e) {
        	e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
		setupGame();
		System.out.println();
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
}
