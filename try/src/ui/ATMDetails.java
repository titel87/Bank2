package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ATMDetails extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		//build grids
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		
		//create labels
		Label titelLabel = new Label("New ATM Details");
		titelLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		Label locationLabel = new Label("Location:");
		locationLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		TextField nameTextField = new TextField ();
		HBox locationBox = new HBox();
		locationBox.getChildren().addAll(locationLabel, nameTextField);
		locationBox.setSpacing(10);
			
				
		
		//create buttons
        Button doneBtn = new Button();
        doneBtn.setText("Done");
        doneBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Saving new ATM in lists and in DB");
            }
        });
		
		
		//add labels to grid
        grid.add(titelLabel, 0, 0);
		grid.add(locationBox, 0, 2);
		
		//add buttons to grid
		grid.add(doneBtn, 0, 5);
		
		//build scene
		Scene scene = new Scene(grid, 400, 300);
        
        primaryStage.setTitle("New Client");
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
}
