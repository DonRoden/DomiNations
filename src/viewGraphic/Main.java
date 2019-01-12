package viewGraphic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Domino;
import model.Lagia;
import model.Model;

public class Main extends Application {
	public static Stage primaryStage;
	public static Lagia ia;
	
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
		if (nbOrder >= Model.order.length-1) {
			if (!Model.deck.hasNext())
				end();
			else {
				for (int i = 0; i < Model.newOrder.length; i++) {
					Model.order[i] = Model.newOrder[i];
					Model.newOrder[i] = -1;
				}
				Game.newTurn();
				if (Model.player[Model.order[0]].ia != null)
					Game.iaPlaceDomino(0);
				else	
					Game.placeDomino(0);
			}
		}
		else {
			if (Model.chosenDomino[nbOrder].getNumber() == 0) {
				if (Model.player[Model.order[nbOrder+1]].ia != null)
					Game.iaChooseDomino(nbOrder+1);
				else {
					Game.placeDomino(nbOrder+1, false);
				}
			}
			else {
				if (Model.player[Model.order[nbOrder+1]].ia != null)
					Game.iaPlaceDomino(nbOrder+1);
				else {
					Game.placeDomino(nbOrder+1);
				}
			}
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
        if (Model.player[Model.order[0]].ia == null)
        	Game.placeDomino(0, false);
        else
        	Game.iaChooseDomino(0);
        primaryStage.setScene(scene);
	}
	
	public static void end() {
		Menu endMenu = new Menu();
		endMenu.addTitle("Fin", 300);
		
		VBox vbox = new VBox();
			for (int i = 0 ; i < Model.player.length; i++) {
				Model.player[i].scoreBoard(Model.player[i].board);
				vbox.getChildren().add(new Text(Model.player[i].name +"  "+ Model.player[i].totalScore));
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
