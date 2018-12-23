package model;

import java.util.ArrayList;

public class Player {
	private String color;
	private int nbKing;
	private int[][] board = new int[11][11];
	private Deck deck;
	public int chateau=49;
	public int vide=50;
	
	
	
	public Player(int nbKing,String color, Deck deck) {
		this.nbKing = nbKing;
		this.color = color;
		this.deck = deck;
		board[5][5] = chateau;
		for (int i=0; i < 11;i++) {
			board[0][i]=vide;
			board[10][i]=vide;
		}
		for (int i=0; i < 11;i++) {
			board[i][0]=vide;
			board[i][10]=vide;
		}
	}
	
	public int[][] getBoard () {
	    return board;
	  }

	public void printBoard() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				System.out.printf("%4d",this.board[j][i]);
			}
			System.out.println();
		}
	}
	
	public boolean isPlacable(int x1, int y1, int x2, int y2, int domino) {
		// 2 cases vides
		if (board[x1][y1]==0 && board[x2][y2]==0) {
			// à coté du chateau ou à coté d'un même type
			if (   board[x1-1][y1]==49
				|| board[x1+1][y1]==49
				|| board[x2-1][y2]==49
				|| board[x2+1][y2]==49 
				|| board[x1][y1+1]==49
				|| board[x2][y2-1]==49
				|| board[x1][y1-1]==49
				|| board[x2][y2+1]==49
				|| getTypeCase(domino)==getTypeCase(board[x1-1][y1])
				|| getTypeCase(domino)==getTypeCase(board[x1+1][y1])
				|| getTypeCase(-domino)==getTypeCase(board[x2-1][y2])
				|| getTypeCase(-domino)==getTypeCase(board[x2+1][y2])
				|| getTypeCase(domino)==getTypeCase(board[x1][y1+1])
				|| getTypeCase(domino)==getTypeCase(board[x1][y1-1])
				|| getTypeCase(-domino)==getTypeCase(board[x2][y2+1])
				|| getTypeCase(-domino)==getTypeCase(board[x2][y2-1]) ) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	public void placeDomino(int x1, int y1, int x2, int y2,int domino) {
		if (isPlacable(x1,y1,x2,y2,domino)) {
			board[x1][y1]= domino;
			board[x2][y2]= -domino;
			if (x1==2 || x2==2) {
				for (int i=0; i < 11;i++) {
					board[7][i]=vide;
					board[8][i]=vide;
					board[9][i]=vide;
				}
			}
			if (x1==8 || x2==8) {
				for (int i=0; i < 11;i++) {
					board[1][i]=vide;
					board[2][i]=vide;
					board[3][i]=vide;
				}
			}
			if (y1==2 || y2==2) {
				for (int i=0; i < 11;i++) {
					board[i][7]=vide;
					board[i][8]=vide;
					board[i][9]=vide;
				}
			}
			if (y1==8 || y2==8) {
				for (int i=0; i < 11;i++) {
					board[i][1]=vide;
					board[i][2]=vide;
					board[i][3]=vide;
				}
			}
			if (x1==1 || x2==1) {
				for (int i=0; i < 11;i++) {
					board[6][i]=vide;
				}
			}
			if (x1==9 || x2==9) {
				for (int i=0; i < 11;i++) {
					board[4][i]=vide;
				}
			}
			if (y1==1 || y2==1) {
				for (int i=0; i < 11;i++) {
					board[i][6]=vide;
				}
			}
			if (y1==9 || y2==9) {
				for (int i=0; i < 11;i++) {
					board[i][4]=vide;
				}
			}
			if (x1==3 || x2==3) {
				for (int i=0; i < 11;i++) {
					board[8][i]=vide;
					board[9][i]=vide;
				}
			}
			if (x1==7 || x2==7) {
				for (int i=0; i < 11;i++) {
					board[1][i]=vide;
					board[2][i]=vide;
				}
			}
			if (y1==3 || y2==3) {
				for (int i=0; i < 11;i++) {
					board[i][8]=vide;
					board[i][9]=vide;
				}
			}
			if (y1==7 || y2==7) {
				for (int i=0; i < 11;i++) {
					board[i][1]=vide;
					board[i][2]=vide;
				}
			}
			if (x1==4 || x2==4) {
				for (int i=0; i < 11;i++) {
					board[9][i]=vide;
				}
			}
			if (x1==6 || x2==6) {
				for (int i=0; i < 11;i++) {
					board[1][i]=vide;
				}
			}
			if (y1==4 || y2==4) {
				for (int i=0; i < 11;i++) {
					board[i][9]=vide;
				}
			}
			if (y1==6 || y2==6) {
				for (int i=0; i < 11;i++) {
					board[i][1]=vide;
				}
			}
		}
		else {
			System.out.println("Vous ne pouvez pas placer cette pièce ici");
		}
	}
	
	
	
//	public int[][] listPlacable() {
//		
//	}
//	
//	public int points() {
//		
//	}
	
	public String getTypeCase(int nbDomino) {
		if (nbDomino < 0) {
			return this.deck.getDomino(-nbDomino).getType2();
		}
		else if (nbDomino == 49 || nbDomino ==50) {
			return null;
		}
		else if (nbDomino > 0) {
			return this.deck.getDomino(nbDomino).getType1();
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
