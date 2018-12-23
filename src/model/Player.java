package model;

public class Player {
	private String color;
	private int nbKing;
	private int[][] board;

	public Player(int nbKing, String color, int sizeBoard) {
		this.nbKing = nbKing;
		this.color = color;
		this.board = new int[sizeBoard*2-1][sizeBoard*2-1];
		board[sizeBoard-1][sizeBoard-1] = 0;
	}
	
	public String getColor() {
		return color;
	}
	
	
	
//public boolean isPlacable(int x1, int y1, int x2, int y2) {
//		if (board[x1][y1]==0 && board[x2][y2]==0) { // 2 cases vides
//			if (x1==x2) { // domino à la verticale 
//				if (   getTypeCase(board[x1][y1])==getTypeCase(board[x1-1][y1])
//					|| getTypeCase(board[x1][y1])==getTypeCase(board[x1+1][y1])
//					|| getTypeCase(board[x2][y2])==getTypeCase(board[x2-1][y2])
//					|| getTypeCase(board[x2][y2])==getTypeCase(board[x2+1][y2]) //test droite/gauche
//					|| board[x1-1][y1]==49
//					|| board[x1+1][y1]==49
//					|| board[x2-1][y2]==49
//					|| board[x2+1][y2]==49) { //test chateau
//						return true;
//				}
//				if (y1>y2) { // position nord
//					if (   getTypeCase(board[x1][y1])==getTypeCase(board[x1][y1+1])
//						|| getTypeCase(board[x2][y2])==getTypeCase(board[x2][y2-1]) //test haut/bas
//						|| board[x1][y1+1]==49
//						|| board[x2][y2-1]==49) { //test chateau
//						return true;
//					}
//					else {
//						return false;
//					}
//				}
//				else { // position sud
//					if (   getTypeCase(board[x1][y1])==getTypeCase(board[x1][y1-1])
//						|| getTypeCase(board[x2][y2])==getTypeCase(board[x2][y2+1]) //test bas/haut
//						|| board[x1][y1-1]==49
//						|| board[x2][y2+1]==49) { //test chateau 
//						return true;
//					}
//					else {
//						return false;
//					}
//				}
//			}
//			else { // domino à l'horizontale
//				if (   getTypeCase(board[x1][y1])==getTypeCase(board[x1][y1+1])
//					|| getTypeCase(board[x1][y1])==getTypeCase(board[x1][y1-1])
//					|| getTypeCase(board[x2][y2])==getTypeCase(board[x2][y2+1])
//					|| getTypeCase(board[x2][y2])==getTypeCase(board[x2][y2-1]) //test haut/bas
//					|| board[x1][y1+1]==49
//					|| board[x1][y1-1]==49
//					|| board[x2][y2+1]==49
//					|| board[x2][y2-1]==49) { //test chateau  
//						return true;
//				}
//				if (x1>x2) { // position est
//					if (   getTypeCase(board[x1][y1])==getTypeCase(board[x1+1][y1])
//						|| getTypeCase(board[x2][y2])==getTypeCase(board[x2-1][y2]) //test droite/gauche
//						|| board[x1+1][y1]==49
//						|| board[x2-1][y2]==49) { // test chateau
//							return true;
//					}
//					else {
//						return false;
//					}
//				}
//				else { // position ouest
//					if (   getTypeCase(board[x1][y1])==getTypeCase(board[x1-1][y1])
//						|| getTypeCase(board[x2][y2])==getTypeCase(board[x2+1][y2]) //test gauche/droite
//						|| board[x1-1][y1]==49
//						|| board[x2+1][y2]==49) { //test chateau
//							return true;
//					}
//					else {
//						return false;
//					}
//				}
//			}
//		}
//		else {
//			return false;
//		}
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
//	
//	public String getTypeCase(int nbDomino) {
//		if (nbDomino < 0) {
//			return deck.getDomino(nbDomino).getType2();
//		}
//		else if (nbDomino == 49 || nbDomino ==50) {
//			return null;
//		}
//		else if (nbDomino > 0) {
//			return deck.getDomino(nbDomino).getType1();
//		}
//		else {
//			return null;
//		}
//	}
//	
//	public int getCrown(int nbDomino) {
//		if (nbDomino > 0) {
//			return deck.getDomino(nbDomino).getCrown();
//		}
//		else {
//			return 0;
//		}
//	}
}
