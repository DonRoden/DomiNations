package viewGraphic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Domino;
import model.Model;
import model.Player;

public class Main extends Application {
	public static Stage primaryStage;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Application.launch(Main.class, args);
	}
	
	public static void play() {
		choosePlayers();
	}
	
	public static void choosePlayers() {
		PlayerMenu player = new PlayerMenu();
		player.show(primaryStage);
	}
	
	public static void keepGoing(int nbOrder) {
		if (nbOrder == Model.order.length-1) {
			if (!Model.deck.hasNext())
				end();
			else {
				for (int i = 0; i < Model.newOrder.length; i++) {
					Model.order[i] = Model.newOrder[i];
					Model.newOrder[i] = -1;
				}
				Game.newTurn();
				Game.placeDomino(0);
			}
		}
		else {
			if (Model.chosenDomino[nbOrder].getNumber() == 0)
				Game.placeDomino(nbOrder+1, false);
			else
				Game.placeDomino(nbOrder+1);
		}
	}
	
	public static void setup(int nbPlayer) {
		Model.deck.importDeck();
	    Model.shuffleDeck();
	    Model.setRandomOrder();
	    
	    for (int i = 0; i < Model.newOrder.length; i++) {
			Model.newOrder[i] = -1;
			Model.onBoardDominos.add(new Domino(0,0,"X","X",0));
			Model.deck.add(0,0,"X","X",0);
		}
	    
        Scene scene = Game.gameView();
        Game.placeDomino(0, false);
        primaryStage.setScene(scene);
	}
	
	public static void end() {
		Menu endMenu = new Menu();
		endMenu.addTitle("Fin");
		VBox vbox = new VBox();
		for (Player p : Model.player) {
			p.scoreBoard();
			vbox.getChildren().add(new Text("Score : " + p.totalScore));
			System.out.println("Score : " + p.totalScore);
		}
		endMenu.mainPane.setCenter(vbox);
		endMenu.show(primaryStage);
	}
	
	@Override
    public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
        primaryStage.setTitle("Domi'Nations");
        
        MainMenu mainMenu = new MainMenu();
        mainMenu.show(primaryStage);
		
        
        
        primaryStage.show();
    }
}
