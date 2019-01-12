package viewGraphic;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import model.Model;

public class SettingsMenu extends Menu {
	public SettingsMenu() {
		this.addTitle("Options", 200);
		
		VBox vBox = new VBox();
		root.getChildren().add(vBox);
		
		CheckBox empireDuMilieu = new CheckBox("Empire du milieu");
		empireDuMilieu.setSelected(Model.isMiddleEmpire);
		empireDuMilieu.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Model.isMiddleEmpire = empireDuMilieu.isSelected();
			}
		});
		vBox.getChildren().add(empireDuMilieu);
		
		CheckBox harmony = new CheckBox("Harmonie");
		harmony.setSelected(Model.isHarmony);
		harmony.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Model.isHarmony = harmony.isSelected();
				System.out.println(Model.isHarmony);
			}
		});
		vBox.getChildren().add(harmony);
		
		CheckBox bigDuel = new CheckBox("Big Duel");
		bigDuel.setSelected(Model.bigDuel);
		bigDuel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Model.bigDuel = bigDuel.isSelected();
			}
		});
		vBox.getChildren().add(bigDuel);
		
		Button retour = new Button("Retour");
		retour.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				MainMenu a = new MainMenu();
				a.show(Main.primaryStage);
			}
		});
		vBox.getChildren().add(retour);
	}
}
