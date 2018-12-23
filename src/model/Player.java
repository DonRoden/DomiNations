package model;

public class Player {
	private String color;
	private int nbKing;
	private HalfDomino[][] board;
	private HalfDomino chateau = new HalfDomino(0, "Chateau");
	private HalfDomino vide = new HalfDomino(0, "Void");
	private HalfDomino forbidden = new HalfDomino(0, "Forbidden");
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

	public Player(int nbKing,String color, int taille) {
		this.nbKing = nbKing;
		this.color = color;
		board = new HalfDomino[taille*2+1][taille*2+1];
		
		// Je remplis mon board de cases vides
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = vide;
			}
		}
		
		// Puis je mets le chateau et les cases interdites sur les bords
		board[taille][taille] = chateau;
		for (int i = 0; i < taille*2+1; i++) {
			board[0][i] = forbidden;
			board[taille*2][i] = forbidden;
		}
		for (int i = 0; i < taille*2+1; i++) {
			board[i][0] = forbidden;
			board[i][taille*2] = forbidden;
		}
	}
	
	public static void main(String[] args) {
		Player p = new Player(1, "a", 5);
		p.printBoard();
		System.out.println(p.isPlacable(4, 5, 3, 5, new Domino(0,0, "a", "b", 5)));
	}

	public void printBoard() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				System.out.print(" "+ this.board[j][i].getType());
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
			if (x1==2 || x2==2) {
				for (int i=0; i < 11;i++) {
					board[7][i] = forbidden;
					board[8][i] = forbidden;
					board[9][i] = forbidden;
				}
			}
			if (x1==8 || x2==8) {
				for (int i=0; i < 11;i++) {
					board[1][i] = forbidden;
					board[2][i] = forbidden;
					board[3][i] = forbidden;
				}
			}
			if (y1==2 || y2==2) {
				for (int i=0; i < 11;i++) {
					board[i][7] = forbidden;
					board[i][8] = forbidden;
					board[i][9] = forbidden;
				}
			}
			if (y1==8 || y2==8) {
				for (int i=0; i < 11;i++) {
					board[i][1] = forbidden;
					board[i][2] = forbidden;
					board[i][3] = forbidden;
				}
			}
			if (x1==1 || x2==1) {
				for (int i=0; i < 11;i++) {
					board[6][i] = forbidden;
				}
			}
			if (x1==9 || x2==9) {
				for (int i=0; i < 11;i++) {
					board[4][i] = forbidden;
				}
			}
			if (y1==1 || y2==1) {
				for (int i=0; i < 11;i++) {
					board[i][6] = forbidden;
				}
			}
			if (y1==9 || y2==9) {
				for (int i=0; i < 11;i++) {
					board[i][4] = forbidden;
				}
			}
			if (x1==3 || x2==3) {
				for (int i=0; i < 11;i++) {
					board[8][i] = forbidden;
					board[9][i]=forbidden;
				}
			}
			if (x1==7 || x2==7) {
				for (int i=0; i < 11;i++) {
					board[1][i] = forbidden;
					board[2][i]=forbidden;
				}
			}
			if (y1==3 || y2==3) {
				for (int i=0; i < 11;i++) {
					board[i][8] = forbidden;
					board[i][9] = forbidden;
				}
			}
			if (y1==7 || y2==7) {
				for (int i=0; i < 11;i++) {
					board[i][1] = forbidden;
					board[i][2] = forbidden;
				}
			}
			if (x1==4 || x2==4) {
				for (int i=0; i < 11;i++) {
					board[9][i] = forbidden;
				}
			}
			if (x1==6 || x2==6) {
				for (int i=0; i < 11;i++) {
					board[1][i] = forbidden;
				}
			}
			if (y1==4 || y2==4) {
				for (int i=0; i < 11;i++) {
					board[i][9] = forbidden;
				}
			}
			if (y1==6 || y2==6) {
				for (int i=0; i < 11;i++) {
					board[i][1] = forbidden;
				}
			}
		}
		else {
			System.out.println("Vous ne pouvez pas placer cette piece ici");
		}
	}



//	public int[][] listPlacable() {
//
//	}
//
//	public int points() {
//
//	}
}
