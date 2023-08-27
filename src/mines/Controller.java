package mines;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller {
	private Image flag = new Image("file:src/mines/flag.jpg", 20, 20, true, true);
	private Image mine = new Image("file:src/mines/mokesh.jfif", 20, 20, true, true);
	
	

	private GridPane grid = new GridPane(); // create grid
	private VBox box = new VBox();

	@FXML
	private TextField widthTxt;
	
	@FXML
	private TextField heightTxt;
	
	@FXML
	private TextField minesTxt;
	
	@FXML
	private Button set;

	@FXML
	void gameOn(ActionEvent event) {
		/* get the data of the game*/
		int width = Integer.parseInt(widthTxt.getText());	
		int height = Integer.parseInt(heightTxt.getText());	
		int mines = Integer.parseInt(minesTxt.getText());	
		Stage stage = MinesFX.getStage(); // create new stage
		set.setText("Reset"); // the reset button
		Mines m = new Mines(height, width, mines); // create the board game
		grid.setPrefHeight(height*50);
		grid.setPrefWidth(width*50);
		grid.setMinSize(width*50, height*50);
		grid.getChildren().clear(); // clear the board before starts a game
		Button b = null;
		// create all the buttons  
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				b = new Button(m.get(i, j));
				
				b.setUserData(new Location(i, j));
				b.setPrefSize(50,50);
				b.setMinSize(50,50);
				b.setOnMouseClicked(new EventHandler<MouseEvent>() { // mouse clicked events
					@Override
					public void handle(MouseEvent e) {
						
						Button button = (Button) e.getSource(); // create button
						Location location = (Location) button.getUserData(); // create location
						if(m.getOpen(location.getI(), location.getJ())) // if the location is open 
							return; 
						if (e.getButton() == MouseButton.SECONDARY) { // if this location is closed and right clicked
							m.toggleFlag(location.getI(), location.getJ()); // if we have a flag -take it off else -put a flag there
							if (m.getFlag(location.getI(), location.getJ())) { // now if we have flag there
								button.setText(null); // 
								button.setGraphic(new ImageView(flag)); // put flag
							} else { // if we took of the flag we take of the image and update the location of the button 
								button.setGraphic(null); 
								button.setText(m.get(location.getI(), location.getJ()));
							}				
						}
						//check if mines or win
						else if (e.getButton() == MouseButton.PRIMARY) { // if left clicked 
							if (m.open(location.getI(), location.getJ())) { // if this field is open and not have a mine
							
								
								for (Node field : grid.getChildren()) {  
									location = (Location) field.getUserData(); // save this location 
									if (m.getOpen(location.getI(), location.getJ())) { // if this location is open
										((Button) field).setGraphic(null);// clear the flag image
										((Button) field).setText(m.get(location.getI(), location.getJ())); // update the location of the button 
										if (m.getEmpty(location.getI(), location.getJ())) { // if open and not a mine and there are not any mine nearby
											((Button) field).setDisable(true);	// disable this button										
										}
									}
								}
							// if left clicked and this is a mine - lose
							} else if (m.getFlag(location.getI(), location.getJ())==false) {  // if open and a mine 
								m.setShowAll(true); // set show all to ready to show all the board
								
								for (Node field : grid.getChildren()) {
									location = (Location) field.getUserData(); // save this location
									((Button) field).setGraphic(null);// clear the flag image
									((Button) field).setText(m.get(location.getI(), location.getJ()));// update the location of the button 
									if(m.getMine(location.getI(), location.getJ())) { // if this is a mine
										((Button) field).setText(null); // no text
										((Button) field).setGraphic(new ImageView(mine)); // put losing image of mine
									}
									if (m.getEmpty(location.getI(), location.getJ())) // if we have neighbors with no mines
										((Button) field).setDisable(true); 
								}
								VBox box = new VBox();
								Stage s1 = new Stage();
								Scene s2 = new Scene(box);
								Label label1 = new Label("GAME OVER!!!");
								label1.setAlignment(Pos.CENTER);
								Image lose = new Image("file:src/mines/LOSE.gif",750, 750, true, true);
								ImageView lose1 = new ImageView(lose);
								box.getChildren().addAll(label1,lose1);
								box.setAlignment(Pos.TOP_RIGHT);
								s1.setScene(s2);
								s1.show();
								set.setText("Another try"); // ask the player for re-match
							}
						}
						
						if (m.isDone()) { // check if all non mines fields are open
							m.setShowAll(true); // set show all to ready to show all the board
							for (Node field : grid.getChildren()) { 
								location = (Location) field.getUserData(); 
								((Button) field).setText(m.get(location.getI(), location.getJ())); // update the location of the button
								if (!((Button) field).isDisabled()) { // if this button is already opened 
									((Button) field).setGraphic(null);// clear the flag image
									if(m.getMine(location.getI(), location.getJ())) {
										((Button) field).setText(null);
										((Button) field).setGraphic(new ImageView(mine));
									}
									if (m.getEmpty(location.getI(), location.getJ()))// if we have neighbors with  no mines
										((Button) field).setDisable(true); //disable this button
								}
							}
							VBox box = new VBox();
							Stage s1 = new Stage();
							Scene s2 = new Scene(box);
							Label label1 = new Label("OMG!You just Won!!!");
							label1.setAlignment(Pos.CENTER);
							Image win = new Image("file:src/mines/WIN.png",600, 600, true, true);
							ImageView win1 = new ImageView(win);
							box.getChildren().addAll(label1,win1);
							box.setAlignment(Pos.TOP_RIGHT);
							s1.setScene(s2);
							s1.show();
						}
					}
				});
				grid.add(b,j,i);
			}
		}	
		
		stage.sizeToScene();	
	}

	
	private class Location { //save the locations of the fields
		private int i, j;

		public Location(int i, int j) {
			this.i = i;
			this.j = j;
		}

		public int getI() {
			return i;
		}

		public int getJ() {
			return j;
		}
	}

	public GridPane getGrid() { 
		return grid;
	}
	public VBox getbox() { 
		return box;
	}

	

}
