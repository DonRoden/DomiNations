package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Model {
	public static Deck deck = new Deck();
	public static Player[] player;
	public static int nbKings;
	public static int boardSize = 5;
	public static int kingPerPlayer = 1;
	public static ArrayList<Domino> onBoardDominos = new ArrayList<>();
	public static int[] order;
	public static int[] newOrder;
	public static Domino[] chosenDomino;
	
	static Random ran = new Random();
	
	/* Quand on creer une nouvelle variable, toujours se demander 
	 * ou on va l'utiliser apres et instancer la variable au bon endroit
	 * Toutes les variables au dessus sont utiles dans l'ensemble du projet
	 * donc elles sont publiques.
	 * Mais certaines variables ne sont que temporaires
	 * pour une fonction par exemple, dans ce cas seulement, on la definit dans la fonction
	 */
	
	
	
	/* Meme pas besoin de main dans Model,
	 * ce fichier ne sert qu'a definir les fonction dont on aura besoin
	 * afin de s'en servir facilement.
	 * On definit les details pour que dans view, il ne reste qu'a :
	 * Creer le deck, piocher, melanger etc...
	 * Mais dans le view, les appels de fonctions sont simples
	 * 
	 * 
	 * L'idee c'est de se dire que si jamais j'ai envie de faire
	 * un autre jeu de dominos avec ce genre de pieces, j'aurai pas a reecrire
	 * les fonctions. J'aurai juste a definir un plateau different, des donnees
	 * de domino differents et des carac de player differents
	 */

	public static void setNbPlayer (int nbPlayer) {
		player = new Player[nbPlayer];
		if (nbPlayer == 2) {
			kingPerPlayer = 2;
		}
		nbKings = nbPlayer*kingPerPlayer;
		order = new int[nbKings];
		newOrder = new int[nbKings];
		chosenDomino = new Domino[nbKings];
	}
	
	public static void createPlayer(int i, String color) {
		player[i] = new Player(kingPerPlayer, color, boardSize);
	}
	
	public static void shuffleDeck() {
		deck.shuffle(12*nbKings);
	}
	
	public static void draw() {
		onBoardDominos = new ArrayList<>(); 
		for(int i = 0; i < nbKings; i++) {
			onBoardDominos.add(deck.nextDomino());
		}
		onBoardDominos.sort(Comparator.comparing(Domino::getNumber));
	}
	
	public static void setRandomOrder() {
		ArrayList<Integer> list = new ArrayList<>();
		for (int timesToRepeat = 0; timesToRepeat < kingPerPlayer; timesToRepeat++) {
			/*On la repete : 
			 * 1 seul fois si 3 ou 4 joueurs
			 * 2 fois si 2 joueurs
			 */
			for (int i = 0; i < player.length; i++) {
				/* Cette boucle ajoute :
				 * 0,1 si 2 joueurs
				 * 0,1,2 si 3 joueurs
				 * 0,1,2,3 si 4 joueurs
				 */
				list.add(i);
			}
		}
		/* Donc list = 0,1,0,1 si 2 joueurs
		 * list = 0,1,2 si 3 joueurs
		 * list = 0,1,2,3 si 4 joueurs
		 */
		
		/* C'est ce genre de choses dont je parle quand je dis 
		 * qu'il faut réfléchir plus pour simplifier les conditions
		 * au lieu de ca :
		 
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
		*/

		for(int i = 0; i < order.length; i++) {
			order[i]=list.remove(ran.nextInt(list.size()));
		}
	}

	public static void importDeck() {
		Scanner sc = null;
		try {
		    sc = new Scanner(new File("dominos.csv"));
		} catch (FileNotFoundException e) {
		    System.out.println("Fichier non trouve");
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
