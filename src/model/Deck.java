package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Deck implements Serializable{
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
}
