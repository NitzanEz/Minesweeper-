package simpleFX;


	import javafx.application.Application;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.geometry.Insets;
	import javafx.geometry.Pos;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
	import javafx.scene.layout.BackgroundFill;
	import javafx.scene.layout.HBox;
	import javafx.scene.layout.VBox;
	import javafx.scene.paint.Color;
	import javafx.stage.Stage;

		public class SimpleFX extends Application {
			
			public static void main(String[] args) {
				launch(args);
			}
			@Override
			public void start(Stage primaryStage) {
				
					Scene scene = new Scene(createRoot());
					primaryStage.setScene(scene);
					primaryStage.show();
				
			}
			private int cnt = 0; // Count the number of pressing 
		
			private VBox createRoot() { //create buttons and text field
				Button b1 = new Button("Ofra Haza");
				Button b2 = new Button("Yardena Arazi");
				TextField l = new TextField("");
				BackgroundFill b = new BackgroundFill(Color.RED, null, null);
				l.setBackground(new Background(b));
				l.setMaxWidth(Double.MAX_VALUE);
				l.setAlignment(Pos.CENTER);
				
				
				b1.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						cnt++; //increase 1 to the counter each press
						l.setText(""+ cnt);	
					}
				});
				
				b2.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						cnt--; //decrease 1 from the counter each press
						l.setText(""+ cnt);	
					}
				});

				HBox hbox = new HBox(20,b1,b2);
				VBox root = new VBox(20,hbox,l); // put Hbox inside the Vbox
				
				root.setAlignment(Pos.CENTER); 
				root.setPadding(new Insets(10)); // spaces
				return root;
			}

	
	}


