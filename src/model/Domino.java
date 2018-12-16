package model;
import java.io.Serializable;

public class Domino implements Comparable<Domino>, Serializable{
	private int nbDomino;
	private String type1, type2;
	private int nbCrown;
	
	public Domino(int nbDomino) {
		this.nbDomino = nbDomino;
	}
	
	public Domino(int nbCrown, String type1, String type2, int nbDomino) {
		this.nbDomino = nbDomino;
		this.type1 = type1;
		this.type2 = type2;
		this.nbCrown = nbCrown;
	}
	
	public int getNumber() {
		return nbDomino;
	}
	
	public String getType1() {
		return type1;
	}
	
	public String getType2() {
		return type2;
	}
	
	public int getCrown() {
		return nbCrown;
	}

	@Override
	public int compareTo(Domino arg0) {
		if (this.getNumber() < arg0.getNumber())
			return -1;
		else if (this.getNumber() > arg0.getNumber())
			return 1;
		else
			return 0;
	}
}
