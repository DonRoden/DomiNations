package viewGraphic;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Model;

public class PlayerSetupMenu extends Menu{
	int nbPlayer;
	
	PlayerSetupMenu(int nbPlayer) {
		this.nbPlayer = nbPlayer;
		mainPane = new BorderPane();
		
		Text title = new Text("Selectionnez les joueurs");
		mainPane.setTop(title);
		
		GridPane playerGrid = new GridPane();
		for (int i = 0; i < nbPlayer; i++) {
			VBox player = new VBox();
			
			player.getChildren().add(new Text("Joueur " + (i+1)));
			
			TextField name = new TextField();
			name.setText("Joueur " + (i+1));
			player.getChildren().add(name);
			
			CheckBox cb = new CheckBox("Ordinateur");
			
			player.getChildren().add(cb);
			playerGrid.add(player, i%2, (int)i/2);
		}
		Button sauver = new Button("Jouer");
		sauver.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				Model.setNbPlayer(nbPlayer);
				for (int i = 0; i < nbPlayer; i++) {
					boolean isIA = ((CheckBox)((VBox)playerGrid.getChildren().get(i)).getChildren().get(2)).isSelected();
					Model.createPlayer(i, "Red", ((TextField)((VBox)playerGrid.getChildren().get(i)).getChildren().get(1)).getText(), isIA);
				}
				Main.setup(nbPlayer);
			}
		});
		mainPane.setBottom(sauver);
		
		mainPane.setCenter(playerGrid);
		
		root.getChildren().add(mainPane);
	}
}
