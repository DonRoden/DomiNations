package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	public String color;
	public String name;
	private int nbKing;
	public static int size = Model.boardSize;
	public HalfDomino[][] board;
	public static HalfDomino chateau = new HalfDomino(0, "Castle");
	public static HalfDomino vide = new HalfDomino(0, ".");
	public static HalfDomino forbidden = new HalfDomino(0, "x");
	public static int totalScore;
	/*
	 * Chaque case stocke maintenant un demi domino donc les actions a effectuer sont plus claires
	 * Chaque demi-domino a un type et un nombre de couronnes
	 *
	 * Au lieu du "49" et "50" qui n'avait pas vraiment de sens et en plus qui aurait casse le programme si on a plus de 48 dominos,
	 * on a "Chateau" et "Forbidden" comme type de domino sur la case en question
	 *
	 * Au lieu d'un 0 lorsque le plateau n'est pas rempli, il y a un domino de type vide
	 */

//  Les donnees d'avant qui ont ete changees
//	private int[][] board = new int[11][11];
//	public int chateau=49;
//	public int vide=50;

	public Player(int nbKing,String color) {
		this.nbKing = nbKing;
		this.color = color;
		board = new HalfDomino[size*2+1][size*2+1];

		// Je remplis mon board de cases vides
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = vide;
			}
		}

		// Puis je mets le chateau et les cases interdites sur les bords
		board[size][size] = chateau;
		for (int i = 0; i < size*2+1; i++) {
			board[0][i] = forbidden;
			board[size*2][i] = forbidden;
		}
		for (int i = 0; i < size*2+1; i++) {
			board[i][0] = forbidden;
			board[i][size*2] = forbidden;
		}
	}

	public static void main(String[] args) {
		Player p = new Player(1, "a");

		p.placeDomino(6, 5, 7, 5,new Domino(1,0,"Champs", "Mont", 1),p.board);
		p.placeDomino(4, 5, 3, 5,new Domino(1,0,"Champs", "Foret", 8),p.board);
		p.placeDomino(4, 4, 3, 4,new Domino(0,1,"Champs", "Foret", 2),p.board);
//		p.placeDomino(4, 3, 3, 3,new Domino(0,0,"Champs", "Champs", 3),p.board);
//		p.placeDomino(3, 6, 4, 6,new Domino(1,0,"Mont", "Champs", 4),p.board);
//		p.placeDomino(3, 7, 4, 7,new Domino(1,0,"Mont", "Champs", 5),p.board);
//		p.placeDomino(5, 4, 5, 6,new Domino(0,0,"Champs", "Champs", 7));

//		Player p = new Player(1, "a");
//
//		p.placeDomino(7, 6, 7, 5,new Domino(1,0,"Champs", "Mont", 1));
//		p.placeDomino(7, 4, 7, 3,new Domino(1,0,"Mont", "Champs", 4));
//		p.placeDomino(7, 1, 7, 2,new Domino(0,0,"Champs", "Champs", 3));

		p.printBoard(p.board);
		p.scoreBoard(p.board);
	}

	public void printBoard(HalfDomino[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print("\t"+ board[j][i].getType());
			}
			System.out.println();
		}
	}

	public boolean isPlacable(int x1, int y1, int x2, int y2, Domino domino, HalfDomino[][] board) {
		HalfDomino case1 = domino.getHalf(0);
		HalfDomino case2 = domino.getHalf(1);
		// 2 cases vides
		if (board[x1][y1] == vide && board[x2][y2] == vide) {
			// rectangle
			if (isDomino(x1,y1,x2,y2)) {
				// A�cote du chateau ou a�cote d'un meme type
				if (   board[x1-1][y1] == chateau || board[x1+1][y1] == chateau
					|| board[x2-1][y2] == chateau || board[x2+1][y2] == chateau
					|| board[x1][y1+1] == chateau || board[x2][y2-1] == chateau
					|| board[x1][y1-1] == chateau || board[x2][y2+1] == chateau
					|| case1.getType() == board[x1-1][y1].getType() || case1.getType() == board[x1+1][y1].getType()
					|| case2.getType() == board[x2-1][y2].getType() || case2.getType() == board[x2+1][y2].getType()
					|| case1.getType() == board[x1][y1+1].getType() || case1.getType() == board[x1][y1-1].getType()
					|| case2.getType() == board[x2][y2+1].getType() || case2.getType() == board[x2][y2-1].getType() ) {
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
		else {
			return false;
		}
	}

	public void placeDomino(int x1, int y1, int x2, int y2, Domino domino, HalfDomino[][] board) {
		if (isPlacable(x1,y1,x2,y2,domino,board)) {
			board[x1][y1] = domino.getHalf(0);
			board[x2][y2] = domino.getHalf(1);
			fillBoard(x1,y1,x2,y2,board);
		}
		else {
			System.out.println("Vous ne pouvez pas placer cette piece ici");
		}
	}
	public void fillBoard(int x1, int y1, int x2, int y2, HalfDomino[][] board) {
		for (int k=1; k < size;k++) {
			if (x1==k || x2==k) {
				for (int i=0; i < size*2;i++) {
					board[size+k][i] = forbidden;
				}
			}
			if (x1==size*2-k || x2==size*2-k) {
				for (int i=0; i < size*2;i++) {
					board[size-k][i] = forbidden;
				}
			}
			if (y1==k || y2==k) {
				for (int i=0; i < size*2;i++) {
					board[i][size+k] = forbidden;
				}
			}
			if (y1==size*2-k || y2==size*2-k) {
				for (int i=0; i < size*2;i++) {
					board[i][size-k] = forbidden;
				}
			}
		}
	}

	public boolean isDomino(int x1, int y1, int x2, int y2) {
		if (x2==x1-1 || x2==x1+1) {
			if (y2==y1 || y2==y1) {
				return true;
			}
			else {
				return false;
			}
		}
		if (y2==y1-1 || y2==y1+1) {
			if (x2==x1 || x2==x1) {
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

	public void scoreBoard(HalfDomino[][] board) {
		totalScore = 0;
		for (int i = 0; i <size*2-1; i++) {
			for (int j = 0; j<size*2-1; j++) {
				board[j][i].setScored(false);
			}
		}
		for (int i = 0; i <size*2-1; i++) {
			for (int j = 0; j<size*2-1; j++) {
				Zone zone = new Zone(j,i,board);
				totalScore += zone.scoreZone(j,i,board);
			}
		}

		// il faut cr�er un attribut EmpireDuMilieu pour d�terminer si on joue avec cette r�gle

		if (/*isEmpireDuMilieu() &&*/  isCentered(board)) {
			totalScore += 10;
		}

		// pareil ici

		if(/*isHarmonie() &&*/ isFull(board)) {
			totalScore += 5;
		}

		System.out.println(totalScore);
	}

	public boolean isCentered(HalfDomino[][] board) {
		if (board[size+size/2][size] != forbidden && board[size-size/2][size] != forbidden &&
				board[size][size+size/2] != forbidden && board[size][size-size/2] != forbidden) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isFull(HalfDomino[][] board) {
		boolean full = true;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[j][i] == vide) {
					full = false;
					break;
				}
			}
		}
		return full;
	}


}
