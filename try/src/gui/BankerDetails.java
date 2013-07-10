package gui;

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

public class BankerDetails extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		//build grids
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		
		//create labels
		Label titelLabel = new Label("New Banker Details");
		titelLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		Label nameLabel = new Label("Name:");
		nameLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		TextField nameTextField = new TextField ();
		HBox nameBox = new HBox();
		nameBox.getChildren().addAll(nameLabel, nameTextField);
		nameBox.setSpacing(10);
		Label commissionLabel = new Label("Commission:");
		commissionLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		TextField commissionTextField = new TextField ();
		HBox commissionBox = new HBox();
		commissionBox.getChildren().addAll(commissionLabel, commissionTextField);
		commissionBox.setSpacing(10);	
		
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
		grid.add(nameBox, 0, 2);
		grid.add(commissionBox, 0, 3);
		
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
