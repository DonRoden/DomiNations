package model;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CreateDominos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("dominos"))));
			
			Deck deck = new Deck();
			deck.add(0,"Champs","Champs",1);
			deck.add(0, "Champs", "Champs", 2);
			deck.add(0, "Foret", "Foret", 3);
			deck.add(0,"Foret", "Foret",4);
			deck.add(0, "Foret", "Foret", 5);
			deck.add(0, "Foret", "Foret", 6);
			deck.add(0, "Mer", "Mer", 7);
			deck.add(0, "Mer", "Mer", 8);
			deck.add(0, "Mer", "Mer", 9);
			deck.add(0, "Prairie", "Prairie", 10);
			deck.add(0, "Prairie", "Prairie", 11);
			deck.add(0, "Mine", "Mine", 12);
			deck.add(0, "Champs", "Foret", 13);
			deck.add(0, "Champs", "Mer", 14);
			deck.add(0, "Champs", "Prairie", 15);
			deck.add(0, "Champs", "Mine", 16);
			deck.add(0, "Foret", "Mer", 17);
			deck.add(0, "Foret", "Prairie", 18);
			deck.add(1, "Champs", "Foret", 19);
			deck.add(1, "Champs", "Mer", 20);
			deck.add(1, "Champs", "Prairie", 21);
			deck.add(1, "Champs", "Mine", 22);
			deck.add(1, "Champs", "Montagne", 23);
			deck.add(1, "Foret", "Champs", 24);
			deck.add(1, "Foret", "Champs", 25);
			deck.add(1, "Foret", "Champs", 26);
			deck.add(1, "Foret", "Champs", 27);
			deck.add(1, "Foret", "Mer", 28);
			deck.add(1, "Foret", "Prairie", 29);
			deck.add(1, "Mer", "Champs", 30);
			deck.add(1, "Mer", "Champs", 31);
			deck.add(1, "Mer", "Foret", 32);
			deck.add(1, "Mer", "Foret", 33);
			deck.add(1, "Mer", "Foret", 34);
			deck.add(1, "Mer", "Foret", 35);
			deck.add(1, "Prairie", "Champs", 36);
			deck.add(1, "Prairie", "Mer", 37);
			deck.add(1, "Mine", "Champs", 38);
			deck.add(1, "Mine", "Prairie", 39);
			deck.add(1, "Montagne", "Champs", 40);
			deck.add(2, "Prairie", "Champs", 41);
			deck.add(2, "Prairie", "Mer", 42);
			deck.add(2, "Mine", "Champs", 43);
			deck.add(2, "Mine", "Prairie", 44);
			deck.add(2, "Montagne", "Champs", 45);
			deck.add(2, "Montagne", "Mine", 46);
			deck.add(2, "Montagne", "Mine", 47);
			deck.add(3, "Montagne", "Champs", 48);
			
			oos.writeObject(deck);
			oos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
