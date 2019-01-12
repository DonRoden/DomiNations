package viewGraphic;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Model;

public class PlayerSetupMenu extends Menu{
	int nbPlayer;
	
	PlayerSetupMenu(int nbPlayer) {
		this.nbPlayer = nbPlayer;
		mainPane = new BorderPane();
		
		this.addTitle("Selectionnez les joueurs", 200);
//		Text title = new Text("Selectionnez les joueurs");
//		title.setTranslateX(400);
//		mainPane.setTop(title);
		
		Color[] colorList = {Color.CORNFLOWERBLUE, Color.HOTPINK, Color.SEAGREEN, Color.CHOCOLATE};
		
		GridPane playerGrid = new GridPane();
		for (int i = 0; i < nbPlayer; i++) {
			Group box = new Group();
			Rectangle background = new Rectangle(220, 120);
			background.setFill(colorList[i]);
			box.getChildren().add(background);
			
			VBox player = new VBox();
			box.getChildren().add(player);
			
			Text playerNb = new Text("Joueur " + (i+1));
			player.getChildren().add(playerNb);
			player.setMargin(playerNb, new Insets(5,5,5,5));
			
			TextField name = new TextField();
			name.setText("Joueur " + (i+1));
			player.getChildren().add(name);
			player.setMargin(name, new Insets(5,5,5,5));
			
			CheckBox cb = new CheckBox("Ordinateur");
			player.getChildren().add(cb);
			player.setMargin(cb, new Insets(5,5,5,5));
			
			playerGrid.add(box, i%2, (int)i/2);
			playerGrid.setMargin(box, new Insets(10,10,10,10));
			playerGrid.setTranslateX(300);
			playerGrid.setTranslateY(150);
		}
		Button sauver = new Button("Jouer");
		sauver.setPrefWidth(100);
		sauver.setPrefHeight(50);
		sauver.setTranslateX(490);
		sauver.setTranslateY(175);
		sauver.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				Model.setNbPlayer(nbPlayer);
				for (int i = 0; i < nbPlayer; i++) {
					if (Model.bigDuel && Model.player.length == 2)
						Model.boardSize = 7;
					boolean isIA = ((CheckBox)((VBox)((Group)playerGrid.getChildren().get(i)).getChildren().get(1)).getChildren().get(2)).isSelected();
					Model.createPlayer(i, (Color)((Rectangle)((Group)playerGrid.getChildren().get(i)).getChildren().get(0)).getFill(), ((TextField)((VBox)((Group)playerGrid.getChildren().get(i)).getChildren().get(1)).getChildren().get(1)).getText(), isIA);
				}
				Main.setup(nbPlayer);
			}
		});
		mainPane.setBottom(sauver);
		
		mainPane.setCenter(playerGrid);
		
		root.getChildren().add(mainPane);
	}
}
