package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class Lagia {
	private Player lagia = new Player(1, "Dark White");
	public static Domino choosedDomino; 
	public static int[] choosedPosition;
//	private Deck upcomingDominos = new Deck();
	
	public Lagia(Player lagia) {
		this.lagia = lagia;
	}
	
	public void chooseDomino(ArrayList<Domino> onBoardDominos) {
		for (int k = 0 ; k < 4; k++) {
			Domino d = onBoardDominos.get(k);
			if (listPlacable(d).size()>0) { // on teste si il y a des dominos placables
				if (twoCrowns(d)) {			// prendre le domino si il a plus d'une couronne
					choosedDomino = d;
					break;
				}
				else if (oneCrown(d)) {
					
					if (sameTypeZone(d,lagia.board,0) 
						&& bestZone(lagia.board).get(0).getZone().size() > 2 
						&& bestZone(lagia.board).get(0).getCrowns() >= 1 ) { 
						choosedDomino = d;
						break;
					}
					else if (sameTypeZone(d,lagia.board,1) 
							&& bestZone(lagia.board).get(1).getZone().size() > 2 
							&& bestZone(lagia.board).get(1).getCrowns() >= 1 ) { 
							choosedDomino = d;
							break;
					}						
					else {
						choosedDomino = d;
						break;
					}
				}
				else {
					if (sameTypeZone(d,lagia.board,1)) {  
						if (bestZone(lagia.board).get(1).getCrowns() >= 1 ) { 
							choosedDomino = d;
							break;
						}
						else {
							choosedDomino = d;
							break;
						}
					}
					choosedDomino = onBoardDominos.get(0);
					break;
				}
			}
			else {
				choosedDomino = onBoardDominos.get(0);
				break;
			}
		}
		
//		Random rd = new Random();
//		choosedDomino = onBoardDominos.get(rd.nextInt(4));
	}
	
	public void choosePosition(Domino domino) {
		if (listPlacable(domino).size() >= 1) {
			choosedPosition = maxScore(domino).get(0);
		}
		else {
			
		}
	}
	public boolean sameTypeZone(Domino domino,HalfDomino[][] board,int zone) {
		if (domino.getHalf(0).getType() == bestZone(board).get(zone).getType()
			|| domino.getHalf(1).getType() == bestZone(board).get(zone).getType()) {
			return true;	
		} 
		else {
			return false;
		}
	}
	public boolean twoCrowns(Domino domino) {	
		if (domino.getHalf(0).getCrown() >= 2
			|| domino.getHalf(1).getCrown() >= 2 ) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean oneCrown(Domino domino) {	
		if (domino.getHalf(0).getCrown() == 1
			|| domino.getHalf(1).getCrown() == 1 ) {
			return true;
		}
		else {
			return false;
		}
	}
	// si il y a des dominos � +2 couronnes dans la seconde liste prendre le premier 
	// (si il y en a pas dans la premi�re)

	
	
	public List<Zone> bestZone(HalfDomino[][] board) {
		List<Zone> listZone = new ArrayList<Zone>();
		
		for (int i = 0; i <Player.size*2-1; i++) {
			for (int j = 0; j<Player.size*2-1; j++) {
				Zone zone = new Zone(j,i,board);
				int score = zone.scoreZone(j,i,board);
				if (score >= 1) {
					listZone.add(zone);
				}
			}
		}
		Collections.sort(listZone,Collections.reverseOrder());
		return listZone;
	}
	
	public List<int[]> maxScore(Domino domino) {
		List<Integer> listScores = new ArrayList<Integer>();
		List<int[]> listPosMaxScore = new ArrayList<int[]>();
		for (int i = 1; i < listPlacable(domino).size(); i++) {
			int[] testPosition = listPlacable(domino).get(i);
			int x1 = testPosition[0];
			int y1 = testPosition[1];
			int x2 = testPosition[2];
			int y2 = testPosition[3];
			HalfDomino[][] testBoard = lagia.board;
			lagia.placeDomino(x1,y1,x2,y2,domino,testBoard);
			lagia.scoreBoard(testBoard);
			listScores.add(Player.totalScore);
			if (listScores.get(0) > listScores.get(listScores.size()-1)) {
				listScores.remove(listScores.get(listScores.size()-1));
			}
			else if (listScores.get(0) < listScores.get(listScores.size()-1)) {
				listPosMaxScore.add(testPosition);
				listPosMaxScore.remove(listPosMaxScore.get(0));
				listScores.remove(listScores.get(0));
			}
			else {
				listPosMaxScore.add(testPosition);
			}
		}
		return listPosMaxScore;	
	}
	
//	public void upcomingDominos(ArrayList<Domino> onBoardDominos) {
//		if (upcomingDominos.hasNext()) {
//			for (int i = 1; i < 4; i++) {
//				upcomingDominos.remove(onBoardDominos.get(i).getNumber());
//			}	
//		}
//		else {
//			upcomingDominos.importDeck("dominos.csv");
//		}
//	}
	
	
	public List<int[]> listPlacable(Domino domino) {
		List<int[]> placables = new ArrayList<int[]>();
		for (int i = 1; i < Player.size*2-1; i++) {
			for (int j = 1; j < Player.size*2-1; j++) {
				if (lagia.isPlacable(j,i,j+1,i,domino,lagia.board)) {
					int[] position = {j,i,j+1,i};
					placables.add(position);
				}
				if (lagia.isPlacable(j,i,j-1,i,domino,lagia.board)) {
					int[] position = {j,i,j-1,i};
					placables.add(position);
				}
				if (lagia.isPlacable(j,i,j,i-1,domino,lagia.board)) {
					int[] position = {j,i,j,i-1};
					placables.add(position);
				}
				if (lagia.isPlacable(j,i,j,i+1,domino,lagia.board)) {
					int[] position = {j,i,j,i+1};
					placables.add(position);
				}
			}
		}
//		for (int i = 0; i < placables.size(); i++) {
//			System.out.print("[ ");
//			for (int j = 0; j < 4; j++) {
//				System.out.print(placables.get(i)[j]+" ");
//			}
//			System.out.print("],");
//		}
		return placables;
	}
}