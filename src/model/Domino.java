package model;

public class Domino implements Comparable<Domino>{
	private int nbDomino;
	private HalfDomino[] half = new HalfDomino[2];
	
	public Domino(int nbDomino) {
		this.nbDomino = nbDomino;
	}
	
	public Domino(int nbCrown1, int nbCrown2, String type1, String type2, int nbDomino) {
		this.nbDomino = nbDomino;
		half[0] = new HalfDomino(nbCrown1, type1);
		half[1] = new HalfDomino(nbCrown2, type2);
	}
	
	public int getNumber() {
		return nbDomino;
	}
	
//	public String getType1() {
//		return type1;
//	}
//	
//	public String getType2() {
//		return type2;
//	}
//	
//	public int getCrown() {
//		return nbCrown;
//	}

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
