package viewGraphic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class InfosMenu extends Menu{
	public InfosMenu() {
		this.addTitle("R�gles du jeu", 140);
		
		Text infos = new Text();
		
		Scanner sc = null;
		try {
		    sc = new Scanner(new File("src/viewGraphic/infos.txt"));
		} catch (FileNotFoundException e) {
		    System.out.println("Fichier non trouve");
		}

		try {
			sc.nextLine();
			while (sc.hasNext()) {
				String line = sc.nextLine();
			    infos.setText(infos.getText() + line + "\n");
			}
		}
		finally {
			sc.close();
		}
		
		mainPane.setCenter(infos);
		
		Button retour = new Button("Retour");
		retour.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				MainMenu a = new MainMenu();
				a.show(Main.primaryStage);
			}
		});
		
		mainPane.setBottom(retour);
	}
}
