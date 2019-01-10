package viewGraphic;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Menu extends Parent {
	protected Group root = new Group();
	protected Scene scene = new Scene(root, 1080, 720);
	
	public Menu() {}
	
	public void show(Stage primaryStage) {
		primaryStage.setScene(scene);
	}
	
	public void addTitle(String title) {
		Group titleGroup = new Group();
		
		Rectangle titleBox = new Rectangle(800, 100, Color.LIGHTGRAY);
		
		Text titleText = new Text(title);
		titleText.setFont(new Font(35));
        titleText.setFill(Color.GREY);
        titleText.setX(300);
        titleText.setY(55);
		
		titleGroup.setTranslateX(140);
		titleGroup.setTranslateY(50);
		titleGroup.getChildren().add(titleBox);
		titleGroup.getChildren().add(titleText);
		root.getChildren().add(titleGroup);
	}
}
