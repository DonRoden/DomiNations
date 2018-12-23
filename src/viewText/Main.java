package viewText;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import model.Deck;
import model.Player;
import model.Domino;

public class Main {
	static Deck deck = new Deck();
	static Player[] player;
	static Random ran = new Random();
	
    public static void main(String[] args) {
    	ObjectInputStream ois;
    	
        try {
        	ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("dominos"))));
        	deck = (Deck)ois.readObject();
        	ois.close();
        }
        catch (FileNotFoundException e) {
        	e.printStackTrace();
        }
        catch (IOException e) {
        	e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
        	e.printStackTrace();
        }
             
        
    }

    
    public static void setupGame() {

		
		
		 /* 
		 * piocher dominos
		 * random ordre pour jouer
		 * choisir domino /joueur
		 * pioche dominos
		 */
	}
    public static int nbPlayer() {
    	Scanner scan = new Scanner(System.in);
		System.out.println("Combien de personnes veulent jouer ?");
		int nbPlayer = scan.nextInt();
		scan.nextLine();
		if(nbPlayer<=4) {
			return nbPlayer;
		}
		else {
			System.out.println("Trop de joueurs !");
			return nbPlayer();
		}
    }
    public static String colorchoice(int i){
    	System.out.println("Joueur"+" " +(i+1)+" "+"quelle couleur voulez-vous ? (Rouge, Vert, Jaune, Bleu)");
    	Scanner scan = new Scanner(System.in);
		String color = scan.nextLine();
		return color;
    }
    public static void showdominos(ArrayList<Domino> dominos) {
    	for(Domino d : dominos) {
    		System.out.print(d.getNumber());
    		System.out.println(" le premier territoire est "+ d.getType1()+" le deuxieme territoire est "+d.getType2()+" avec "+d.getCrown()+" couronne sur le premier territoire");
    	}
    }
    public static int dominoChoice(int i) {
    	System.out.println("Joueur"+" "+(i)+" quelle domino choisissez vous ? (donner son numéro)");
    	Scanner scan = new Scanner(System.in);
    	int d=scan.nextInt();
    	scan.nextLine();
    	return d;
    }
    
//    public void game() {
//    	//Combien de joueur ?
//    	int nbPlayer = 2;
//    	player = new Player[nbPlayer];
//    	for (int i = 0; i < nbPlayer; i++) {
//    		//Joueur i, quelle couleur ?
//    		player[i] = new Player(nbKing, color);
//    		player[i].setColor(color);
//    		player[i].setKing();
//    	}
//    	
//    	int playing = ran.nextInt(nbPlayer);
//    	Domino[] pile1 = new Domino[nbPlayer];
//    	for (Domino i : pile1) {
//    		i = deck.nextDomino();
//    	}
//    	
//    	//Joueur playing choisi
//    }
}
