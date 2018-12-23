package model;

public class HalfDomino {
	private String type;
	private int nbCrown;
	
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
}
