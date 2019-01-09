package viewGraphic;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Model;

public class Main extends Application {
	public static Stage primaryStage;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model.importDeck();
		Model.setNbPlayer(3);
		Model.createPlayer(0, "Red");
	    Model.createPlayer(1, "Red");
	    Model.createPlayer(2, "Red");
	    Model.shuffleDeck();
	    Model.draw();
	    Model.setRandomOrder();
	    
	    for (int i : Model.order) {
	    	System.out.println(i);
	    }
	    
	    for (int i = 0; i < Model.newOrder.length; i++) {
			Model.newOrder[i] = -1;
		}
		System.out.println(Model.newOrder[0]);
		Application.launch(Main.class, args);
	}
	
	public static void play() {
		choosePlayers();
	}
	
	public static void choosePlayers() {
		PlayerMenu player = new PlayerMenu();
		player.show(primaryStage);
	}
	
	public static void setup() {
	}
	
	public static void keepGoing(int nbOrder) {
		if (nbOrder == Model.order.length-1) {
			System.out.println("End of turn");
			for (int i = 0; i < Model.newOrder.length; i++) {
				Model.order[i] = Model.newOrder[i];
				Model.newOrder[i] = -1;
			}
			
			Game.newTurn();
			Game.placeDomino(0);
		}
		else {
			Game.placeDomino(nbOrder+1);
		}
	}
	
	@Override
    public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
        primaryStage.setTitle("Domi'Nations");

        
        
        Scene scene = Game.gameView();
        Game.placeDomino(0);
        
//        MainMenu mainMenu = new MainMenu();
//        mainMenu.show(primaryStage);
		
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
