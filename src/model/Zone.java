package model;

import java.util.ArrayList;
import java.util.List;

public class Zone {
	public int x;
	public int y;
	public String type;
	public int score;
	public int crowns;
	public List<HalfDomino> zone = new ArrayList<HalfDomino>();;
	
	public Zone(int x, int y, HalfDomino[][] board) {
		this.x = x;
		this.y = y;
	}
	
	public String getType() {
		return type;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getCrowns() {
		return crowns;
	}
	
	public List<HalfDomino> getZone() {
		return zone;
	}
	
	public List<HalfDomino> findZone(int x, int y, HalfDomino[][] board) {
		if (!(board[x][y].isScored())) {
			if (board[x][y] == Player.forbidden || board[x][y] == Player.vide || board[x][y] == Player.chateau) {
			} else {
				zone.add(board[x][y]);
				board[x][y].setScored(true);
				if (board[x+1][y].getType() == board[x][y].getType() && (x+1 != Player.size*2) && !board[x+1][y].isScored()) {
					findZone(x+1,y,board);
				}
				if (board[x-1][y].getType() == board[x][y].getType() && (x-1 != -1) && !board[x-1][y].isScored()) {
					findZone(x-1,y,board);
				}
				if (board[x][y+1].getType() == board[x][y].getType() && (y+1 != Player.size*2) && !board[x][y+1].isScored()) {
					findZone(x,y+1,board);
				}
				if (board[x][y-1].getType() == board[x][y].getType() && (y-1 != -1) && !board[x][y-1].isScored()) {
					findZone(x,y-1,board);
				}
			}
		}
		return zone;
	}
	
	public int scoreZone(int x, int y, HalfDomino[][] board) {

		List<HalfDomino> zone = findZone(x, y, board);
		for (int i = 0; i<zone.size(); i++) {
			crowns += zone.get(i).getCrown();
		}
		score += (zone.size() * crowns);
		return score;
	}
}
