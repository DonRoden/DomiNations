package viewText;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Deck;
import model.Domino;
import model.Player;

public class Main extends Application{
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
        
        deck.shuffle(48);
        
        while (deck.hasNext()) {
        	System.out.println(deck.nextDomino().getNumber());
        }
        
//        Application.launch(Main.class, args);       
    }
    

    public void start(Stage primaryStage) {
    	Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
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
