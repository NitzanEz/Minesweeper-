package mines;

import javafx.scene.Scene;

import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.io.IOException;

public class MinesFX extends Application {
	private static Stage stage;

		public static void main(String[] args) {
			launch(args);
		}
		
		@Override
		public void start(Stage stage) {
			MinesFX.stage = stage;
			HBox hbox;
			Controller controller;
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("MinesFX.fxml"));
				hbox = loader.load();		
				controller = loader.getController();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			hbox.getChildren().add(controller.getGrid());
			Scene s = new Scene(hbox);
			stage.setTitle("The amazing Mines Sweeper");
			stage.setScene(s);
			stage.show();
		}
		
		public static Stage getStage() {
			return stage;
		}


		}


