package viewGraphic;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Deck;
import model.Domino;
import model.Lagia;
import model.Model;
import model.Player;

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
			Model.lastTurn = !Model.deck.hasNext();
				for (int i = 0; i < Model.newOrder.length; i++) {
					Model.order[i] = Model.newOrder[i];
					Model.newOrder[i] = -1;
				}
				Game.newTurn(!Model.lastTurn);
				if (Model.player[Model.order[0]].ia != null)
					Game.iaPlaceDomino(0);
				else	
					Game.placeDomino(0, true, Model.lastTurn);
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
					Game.placeDomino(nbOrder+1, true, Model.lastTurn);
				}
			}
		}
	}
	
	public static void setup(int nbPlayer) {
		Model.deck.importDeck();
	    Model.shuffleDeck();
	    Model.setRandomOrder();
	    Model.onBoardDominos = new ArrayList<>();
	    
	    for (int i = 0; i < Model.newOrder.length; i++) {
			Model.newOrder[i] = -1;
			Model.onBoardDominos.add(new Domino(0,0,"X","X",0));
		}
	    
        Scene scene = Game.gameView();
        primaryStage.setScene(scene);
        if (Model.player[Model.order[0]].ia == null)
        	Game.placeDomino(0, false);
        else
        	Game.iaChooseDomino(0);
        
	}
	
	public static void end() {
		Menu endMenu = new Menu();
		endMenu.addTitle("Fin", 375);
		
		List<Player> winners = Model.winner();
		
		VBox vbox = new VBox();
		
		Group winner = new Group();
		
		Rectangle background = new Rectangle(200, 50 + 30*winners.size());
		background.setFill(Color.ANTIQUEWHITE);
		winner.getChildren().add(background);
		
		VBox a = new VBox();
		
		Text felicitation = new Text("Félicitations !");
		felicitation.setFont(new Font(25));
		a.getChildren().add(felicitation);
		
		for (Player p : winners) {
			Text vainqueur = new Text(p.name + " avec " + p.totalScore + " points");
			a.getChildren().add(vainqueur);
			a.setMargin(vainqueur, new Insets(5,5,5,20));
		}
		
		a.setMargin(felicitation, new Insets(5,5,10,20));
		winner.getChildren().add(a);
		vbox.getChildren().add(winner);
		vbox.setMargin(winner, new Insets(0, 0, 30, 0));
		
		for (int i = 0 ; i < Model.player.length; i++) {
			Model.player[i].scoreBoard(Model.player[i].board);
			Text score = new Text(Model.player[i].name +" : "+ Model.player[i].totalScore + " points");
			score.setFill(Model.player[i].color);
			vbox.getChildren().add(score);
			vbox.setMargin(score, new Insets(5,5,5,25));
		}
		vbox.setTranslateX(450);
		vbox.setTranslateY(200);
		
		Button retour = new Button("Retour");
		retour.setPrefWidth(100);
		retour.setPrefHeight(50);
		retour.setTranslateX(485);
		retour.setTranslateY(250);
		retour.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Model.deck = new Deck();
				MainMenu b = new MainMenu();
				b.show(Main.primaryStage);
			}
		});
		
		endMenu.mainPane.setBottom(retour);
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
