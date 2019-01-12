package viewGraphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Model;

public class PlayerMenu extends Menu {
	
	public PlayerMenu() {
		this.addTitle("Combien de joueurs ?", 250);
		
		Button deux = new Button("2 Joueurs");
		deux.setPrefWidth(200);	
		deux.setPrefHeight(75);
		deux.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				PlayerSetupMenu a = new PlayerSetupMenu(2);
		        a.show(Main.primaryStage);
			}
		});
		
		Button trois = new Button("3 Joueurs");
		trois.setPrefWidth(200);	
		trois.setPrefHeight(75);
		trois.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				PlayerSetupMenu a = new PlayerSetupMenu(3);
		        a.show(Main.primaryStage);
			}
		});
		
		Button quatre = new Button("4 Joueurs");
		quatre.setPrefWidth(200);	
		quatre.setPrefHeight(75);
		quatre.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				PlayerSetupMenu a = new PlayerSetupMenu(4);
		        a.show(Main.primaryStage);
			}
		});
		
		VBox vBox = new VBox();
		vBox.getChildren().add(deux);
		vBox.getChildren().add(trois);
		vBox.getChildren().add(quatre);
		vBox.setTranslateX(445);
		vBox.setTranslateY(400);
		this.root.getChildren().add(vBox);
	}
}
