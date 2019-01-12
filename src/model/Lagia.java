package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.paint.Color;

import java.util.Collections;

public class Lagia {
	/*Classe de l'IA */
	
	private Player lagia;
	private Player test = new Player(1, Color.WHITE);
	public Domino choosedDomino;
	public int[] choosedPosition;
//	private Deck upcomingDominos = new Deck();
	
	public Lagia(Player lagia) {
		this.lagia = lagia;
	}
	
	public int chooseDomino(ArrayList<Domino> onBoardDominos) {
		HalfDomino[][] board = test.board;
		int finalChoice = 0;
		for (int i = Model.newOrder.length-1; i >= 0; i--) {
			if (Model.newOrder[i] == -1) {
				choosedDomino = onBoardDominos.get(i);
				finalChoice = i;
			}
		}
		for (int k = 0 ; k < onBoardDominos.size(); k++) {
			if (Model.newOrder[k] == -1) {
				Domino d = onBoardDominos.get(k);
				if (listPlacable(d).size()>0) { // on teste si il y a des dominos placables
					if (twoCrowns(d)) {			// prendre le domino si il a plus d'une couronne
						choosedDomino = d;
						return k;
					}
					else if (oneCrown(d)) {
							
						if (sameTypeZone(d,board,0) 
							&& (lagia.bestZone(board).get(0).getZone().size() > 2 
							|| lagia.bestZone(board).get(0).getCrowns() >= 1 )) { 
							choosedDomino = d;
							finalChoice = k;
						}
						else if (sameTypeZone(d,board,1) 
								&& (lagia.bestZone(board).get(1).getZone().size() > 2 
								|| lagia.bestZone(board).get(1).getCrowns() >= 1 )) { 
								choosedDomino = d;
								finalChoice = k;
						}						
						else {
							choosedDomino = d;
							finalChoice = k;
						}
					}
					else {
						if (sameTypeZone(d,board,1)) {  
							if (lagia.bestZone(board).get(1).getCrowns() >= 1 ) { 
								choosedDomino = d;
								finalChoice = k;
							}
							else {
								choosedDomino = d;
								finalChoice = k;
							}
						}
					}
				}
			}
		}
		
		return finalChoice;
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
		if (domino.getHalf(0).getType() == lagia.bestZone(board).get(zone).getType()
			|| domino.getHalf(1).getType() == lagia.bestZone(board).get(zone).getType()) {
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
	// si il y a des dominos à +2 couronnes dans la seconde liste prendre le premier 
	// (si il y en a pas dans la première)

	
	public List<int[]> maxScore(Domino domino) {
		List<Integer> listScores = new ArrayList<Integer>();
		List<int[]> listPosMaxScore = new ArrayList<int[]>();
		for (int i = 1; i < listPlacable(domino).size(); i++) {
			int[] testPosition = listPlacable(domino).get(i);
			int x1 = testPosition[0];
			int y1 = testPosition[1];
			int x2 = testPosition[2];
			int y2 = testPosition[3];
			HalfDomino[][] testBoard = test.board;
			test.placeDomino(x1,y1,x2,y2,domino);
			test.scoreBoard(testBoard);
			listScores.add(test.totalScore);
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
				if (lagia.isPlacable(j,i,j+1,i,domino)) {
					int[] position = {j,i,j+1,i};
					placables.add(position);
				}
				if (lagia.isPlacable(j,i,j-1,i,domino)) {
					int[] position = {j,i,j-1,i};
					placables.add(position);
				}
				if (lagia.isPlacable(j,i,j,i-1,domino)) {
					int[] position = {j,i,j,i-1};
					placables.add(position);
				}
				if (lagia.isPlacable(j,i,j,i+1,domino)) {
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
