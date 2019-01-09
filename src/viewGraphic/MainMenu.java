package viewGraphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
		
		VBox vBox = new VBox();
		vBox.getChildren().add(play);
		vBox.setTranslateX(425);
		vBox.setTranslateY(400);
		this.root.getChildren().add(vBox);
	}
}
