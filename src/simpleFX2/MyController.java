package simpleFX2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


import javafx.scene.control.TextField;

public class MyController {

    @FXML
    private Button b1;

    @FXML
    private Button b2;

    @FXML
    private TextField text;
    private int cnt=0; // Count the number of pressing
    @FXML
    void press1(ActionEvent event) {
    		
    	text.setText(""+cnt++); 	 //increase 1 to the counter each press
    	
    }

    @FXML
    void press2(ActionEvent event) { //decrease 1 from the counter each press
    	text.setText(""+cnt--);	
    }
   

	
}
