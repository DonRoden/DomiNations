package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Deck {
	private ArrayList<Domino> deck = new ArrayList<>();
	private int iterator = 0;
	
	public Deck() {}
	
	public void shuffle(int taille) {
		Random rd = new Random();
		ArrayList<Domino> deckShuffled = new ArrayList<>();
		
		//On prend n= taille dominos random dans la pile
		for (int i = 0; i < taille; i++) {
			deckShuffled.add(deck.remove(rd.nextInt(deck.size())));
		}
		deck = deckShuffled;
	}
	
	public Domino getDomino(int nbDomino) {
		for (Domino d : deck) {
			if (d.getNumber() == nbDomino) {
				return d;
			}
		}
		return null;
	}
	
	public boolean hasNext() {
		return iterator < deck.size();
	}
	
	public Domino nextDomino() {
		if (this.hasNext()) {
			iterator ++;
			return deck.get(iterator-1);
		}
		else {
			return null;
		}
	}
	
	public void add(int nbCrown1, int nbCrown2, String type1, String type2, int nb) {
		deck.add(new Domino(nbCrown1, nbCrown2, type1, type2, nb));
	}
	
	public void importDeck() {
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
			    this.add(Integer.parseInt(elements[0]),
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
