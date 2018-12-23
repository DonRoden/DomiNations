import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class menugraph extends Application {
	GameMenu gamemenu;
	public static void main(String[] args) {
		launch(args);

	}
	@Override
	public void start(Stage stage) throws IOException {
		Pane root = new Pane();
		root.setPrefSize(900,700);
		InputStream is = Files.newInputStream(Paths.get("res/image/gamebackground.jpeg"));
		Image img = new Image(is);
		is.close();
		ImageView imgV = new ImageView(img);
		
		gamemenu = new GameMenu();
		
		root.getChildren().addAll();
	}
	private class GameMenu{
		GameMenu (){
			VBox root = new VBox(10);
		}
	}
}
