package viewGraphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainMenu extends Menu{
	
	public MainMenu() {
		this.addTitle("Domi'Nations");
		
		Button play = new Button("Play");
		play.setPrefWidth(200);	
		play.setPrefHeight(75);
		play.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Main.play();
			}
		});
		
		Button infos = new Button("Règles du jeu");
		infos.setPrefWidth(200);	
		infos.setPrefHeight(75);
		infos.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				InfosMenu info = new InfosMenu();
				info.show(Main.primaryStage);
			}
		});
		
		VBox vBox = new VBox();
		vBox.getChildren().add(play);
		vBox.getChildren().add(infos);
		vBox.setTranslateX(425);
		vBox.setTranslateY(400);
		this.root.getChildren().add(vBox);
	}
}
