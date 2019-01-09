package model;

public class HalfDomino {
	private String type;
	private int nbCrown;
	private boolean scored = false;
	
	public HalfDomino(int nbCrown, String type) {
		this.type = type;
		this.nbCrown = nbCrown;
	}
	
	public String getType() {
		return  type;
	}
	
	public int getCrown() {
		return nbCrown;
	}
	
	public boolean isScored() {
		return scored;
	}
	
	public void setScored(boolean scored) {
		this.scored = scored;
	}
}
