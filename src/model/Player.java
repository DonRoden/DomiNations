package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private String color;
	private int nbKing;
	private int size;
	private HalfDomino[][] board;
	private HalfDomino chateau = new HalfDomino(0, "Castle");
	private HalfDomino vide = new HalfDomino(0, "Void");
	private HalfDomino forbidden = new HalfDomino(0, "X");
	List<HalfDomino> zone = new ArrayList<HalfDomino>();
	private int totalScore = 0;
	private boolean centered = false;
	private boolean full = false;
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

	public Player(int nbKing,String color, int size) {
		this.nbKing = nbKing;
		this.color = color;
		this.size = size;
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
		Player p = new Player(1, "a", 5);
		
		p.placeDomino(4, 5, 3, 5,new Domino(1,0,"Champs", "Mont", 1));
		p.placeDomino(4, 4, 3, 4,new Domino(1,0,"Champs", "Foret", 2));
		p.placeDomino(4, 3, 3, 3,new Domino(0,0,"Champs", "Champs", 3));
		p.placeDomino(3, 6, 4, 6,new Domino(1,0,"Mont", "Champs", 4));
		p.placeDomino(3, 7, 4, 7,new Domino(1,0,"Mont", "Champs", 5));
		p.placeDomino(6, 5, 7, 5,new Domino(1,0,"Champs", "Mont", 1));
		
//		Player p = new Player(1, "a", 7);
//		
//		p.placeDomino(7, 6, 7, 5,new Domino(1,0,"Champs", "Mont", 1));
//		p.placeDomino(7, 4, 7, 3,new Domino(1,0,"Mont", "Champs", 4));
//		p.placeDomino(7, 1, 7, 2,new Domino(0,0,"Champs", "Champs", 3));
		
		p.printBoard();
		p.scoreBoard();
	}

	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print("\t"+ board[j][i].getType());
			}
			System.out.println();
		}
	}

	public boolean isPlacable(int x1, int y1, int x2, int y2, Domino domino) {
		HalfDomino case1 = domino.getHalf(0);
		HalfDomino case2 = domino.getHalf(1);
		// 2 cases vides
		if (board[x1][y1] == vide && board[x2][y2] == vide) {
			// A  cote du chateau ou a  cote d'un meme type
			if (   board[x1-1][y1] == chateau
				|| board[x1+1][y1] == chateau
				|| board[x2-1][y2] == chateau
				|| board[x2+1][y2] == chateau
				|| board[x1][y1+1] == chateau
				|| board[x2][y2-1] == chateau
				|| board[x1][y1-1] == chateau
				|| board[x2][y2+1] == chateau
				|| case1.getType() == board[x1-1][y1].getType()
				|| case1.getType() == board[x1+1][y1].getType()
				|| case2.getType() == board[x2-1][y2].getType()
				|| case2.getType() == board[x2+1][y2].getType()
				|| case1.getType() == board[x1][y1+1].getType()
				|| case1.getType() == board[x1][y1-1].getType()
				|| case2.getType() == board[x2][y2+1].getType()
				|| case2.getType() == board[x2][y2-1].getType() ) {
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

	public void placeDomino(int x1, int y1, int x2, int y2, Domino domino) {
		if (isPlacable(x1,y1,x2,y2,domino)) {
			board[x1][y1] = domino.getHalf(0);
			board[x2][y2] = domino.getHalf(1);
			fillBoard(x1,y1,x2,y2);
		}
		else {
			System.out.println("Vous ne pouvez pas placer cette piece ici");
		}
	}
	public void fillBoard(int x1, int y1, int x2, int y2) {
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


//	public int[][] listPlacable() {
//
//	}
	
	public void findZone(int x, int y) {

		if (!(board[x][y].isScored())) {
			if (board[x][y] == forbidden || board[x][y] == vide || board[x][y] == chateau) {

			} else {
				zone.add(board[x][y]);
				board[x][y].setScored(true);
				if (board[x+1][y].getType() == board[x][y].getType() && (x+1 != size*2) && !board[x+1][y].isScored()) {
					findZone(x+1,y);
				}
				if (board[x-1][y].getType() == board[x][y].getType() && (x-1 != -1) && !board[x-1][y].isScored()) {
					findZone(x-1,y);
				}
				if (board[x][y+1].getType() == board[x][y].getType() && (y+1 != size*2) && !board[x][y+1].isScored()) {
					findZone(x,y+1);
				}
				if (board[x][y-1].getType() == board[x][y].getType() && (y-1 != -1) && !board[x][y-1].isScored()) {
					findZone(x,y-1);
				}
			}
		}
	}

	public void scoreZone(int x, int y) {

		findZone(x, y);
		int nbCrowns = 0;
		for (int i = 0; i<zone.size(); i++) {
			nbCrowns += zone.get(i).getCrown();
		}
		totalScore += (zone.size() * nbCrowns);
		zone = new ArrayList<HalfDomino>();
	}

	public void scoreBoard() {
		for (int i = 0; i <size*2-1; i++) {
			for (int j = 0; j<size*2-1; j++) {
				scoreZone(j,i);
			}
		}
		
		// il faut créer un attribut EmpireDuMilieu pour déterminer si on joue avec cette règle
		
		if (/*isEmpireDuMilieu() &&*/  isCentered()) {
			totalScore += 10;
		}
		
		// pareil ici
		
		if(/*isHarmonie() &&*/ isFull()) {
			totalScore += 5;
		}
		
		System.out.println(totalScore);
	}
	
	public boolean isCentered() {
		if (board[size+size/2][size] != forbidden && board[size-size/2][size] != forbidden && 
				board[size][size+size/2] != forbidden && board[size][size-size/2] != forbidden) {
			centered = true;
		}
		return centered;
	}
	
	public boolean isFull() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (this.board[j][i] != vide) {
					full = true;
				}
			}
		}
		return full;
	}
	
	
}
