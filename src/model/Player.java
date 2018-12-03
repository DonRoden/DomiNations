package model;

public class Player {
	private String color;
	private int nbKing;
	private int[][] board;
	private Deck deck;
	

	public Player(int nbKing, String color, int sizeBoard, Deck deck) {
		this.nbKing = nbKing;
		this.color = color;
		this.board = new int[sizeBoard*2-1][sizeBoard*2-1];
		board[sizeBoard-1][sizeBoard-1] = 0;
		this.deck = deck;
	}
	
//	public boolean isPlacable(int x1, int y1, int x2, int y2, Domino domino) {
//		
//	}
//	
//	public void placeDomino(int x1, int y1, int x2, int y2, Domino domino) {
//		
//	}
//	
//	public int[][] listPlacable() {
//		
//	}
//	
//	public int points() {
//		
//	}
	
	public String getTypeCase(int nbDomino) {
		if (nbDomino < 0) {
			return deck.getDomino(nbDomino).getType2();
		}
		else if (nbDomino == 49 || nbDomino ==50) {
			return null;
		}
		else if (nbDomino > 0) {
			return deck.getDomino(nbDomino).getType1();
		}
		else {
			return null;
		}
	}
	
	public int getCrown(int nbDomino) {
		if (nbDomino > 0) {
			return deck.getDomino(nbDomino).getCrown();
		}
		else {
			return 0;
		}
	}
}
