	package simpleFX2;
	import java.io.IOException;

	import javafx.application.Application;
	import javafx.fxml.FXMLLoader;
	import javafx.scene.Scene;
	
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


	public class Simple extends Application {

		public static void main(String[] args) {
			launch(args);
		}
		
		@Override
		public void start(Stage stage) {
			VBox root;
			
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("SimpleFX2.fxml"));
				root = loader.load();
				
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
			
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}


