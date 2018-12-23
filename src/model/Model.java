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
		int[] order;
		int[] chosenDomino;
		Random random = new Random();

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
		/*on m�lange le deck et on pioche les dominos et on donne un ordre de passage*/
		if(nbPlayer==2) {
			deck.shuffle(nbPlayer*24);
		}
		else {
			deck.shuffle(nbPlayer*12);
		}

		ArrayList<Domino> onBoardDominos =new ArrayList<>();
		if(nbPlayer==2) {
			for(int i=0; i<4;i++) {
				onBoardDominos.add(deck.nextDomino());
			}
		}
		else{
			for(int i=0; i<nbPlayer;i++) {
			onBoardDominos.add(deck.nextDomino());
			}
		}
		onBoardDominos.sort(Comparator.comparing(Domino::getNumber));
		viewText.Main.showdominos(onBoardDominos);


		if(nbPlayer==2) {
			order = new int [4];
			chosenDomino = new int[4];
		}
		else{
			order = new int [nbPlayer];
			chosenDomino = new int[nbPlayer];
		}
		ArrayList<Integer> list =new ArrayList<>();
		if(nbPlayer==2) {
			list.add(1);
			list.add(1);
			list.add(2);
			list.add(2);
		}
		else  if(nbPlayer==3){
			list.add(1);
			list.add(2);
			list.add(3);
		}
		else {
			list.add(1);
			list.add(2);
			list.add(3);
			list.add(4);
		}

		for(int i=0;i<order.length;i++) {
			order[i]=list.remove(random.nextInt(list.size()));
		}


		for(int i:order) {
				viewText.Main.dominoChoice(i);
		}


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
			*		dire qui c'est qu'a gagn�
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
		    System.out.println("Fichier non trouv�");
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
