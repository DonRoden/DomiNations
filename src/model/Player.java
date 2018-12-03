package model;

import java.util.ArrayList;

public class Player {
	private String color;
	private int nbKing;
	private Domino[][] board;
	

	public Player(int nbKing, String color, int sizeBoard) {
		this.nbKing = nbKing;
		this.color = color;
		this.board = new Domino[sizeBoard*2-1][sizeBoard*2-1];
		board[sizeBoard-1][sizeBoard-1] = new Domino(0);
	}
	
	public boolean isPlacable(int x1, int y1, int x2, int y2, Domino domino) {
		
	}
	
	public void placeDomino(int x1, int y1, int x2, int y2, Domino domino) {
		
	}
	
	public int[][] listPlacable() {
		
	}
	
	public int points() {
		
	}
}
