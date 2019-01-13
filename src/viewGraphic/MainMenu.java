package viewGraphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainMenu extends Menu{
	
	public MainMenu() {
		this.addTitle("Domi'Nations", 300);
		
		Button play = new Button("Play");
		play.setPrefWidth(200);	
		play.setPrefHeight(75);
		play.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Main.play();
			}
		});
		
		Button settings = new Button("Options");
		settings.setPrefWidth(200);	
		settings.setPrefHeight(75);
		settings.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				SettingsMenu set = new SettingsMenu();
				set.show(Main.primaryStage);
			}
		});
		
		VBox vBox = new VBox();
		vBox.getChildren().add(play);
		vBox.getChildren().add(settings);
		vBox.setTranslateX(445);
		vBox.setTranslateY(400);
		this.root.getChildren().add(vBox);
	}
}
