package gui;

import main.BankManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BankView extends Application{


	@Override
	public void start(Stage primaryStage) throws Exception {
		//build grids
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		
		//create labels
		Label ATMsLabel = new Label("ATMs");
		ATMsLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
		Label bankersLabel = new Label("Bankers");
		bankersLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
		Label customersLabel = new Label("Customers");
		customersLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
		
		//create lists
		//CONSIDER DOING THIS WITH LABELS INSTEAD OF LIST VIEWS
		ListView<String> ATMsList = new ListView<String>();
		ObservableList<String> items = FXCollections.observableArrayList("tamar", "shani", "keren", "chen", "igor");
		ATMsList.setItems(items);
		ATMsList.setPrefHeight(200);
		ATMsList.setPrefWidth(100);
		ListView<String> bankersList = new ListView<String>();
		bankersList.setItems(items);
		bankersList.setPrefHeight(200);
		bankersList.setPrefWidth(100);
		ListView<String> customersList = new ListView<String>();
		customersList.setItems(items);
		customersList.setPrefHeight(200);
		customersList.setPrefWidth(100);		
		
		//create buttons
		Button addATMBtn = new Button();
		addATMBtn.setText("Add ATM");
		addATMBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Opening ATM details window -> Adding an ATM");
                ATMDetails newATMForm = new ATMDetails();
                try {
					runAnotherApp(ATMDetails.class);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        Button addBankerBtn = new Button();
        addBankerBtn.setText("Add Banker");
        addBankerBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Opening banker details window -> Adding a Banker");
                try {
					runAnotherApp(BankerDetails.class);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        Button addClientBtn = new Button();
        addClientBtn.setText("Add Client");
        addClientBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Opening client details window -> Adding a Client");
                try {
 					runAnotherApp(ClientDetails.class);
 				} catch (Exception e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
            }
        });
		
		
		//add labels to grid
		grid.add(ATMsLabel, 0, 3);
		grid.add(bankersLabel, 1, 3);
		grid.add(customersLabel, 2, 3);
		
		//add lists to grid
		grid.add(ATMsList, 0, 4);
		grid.add(bankersList, 1, 4);
		grid.add(customersList, 2, 4);
		
		//add buttons to grid
		grid.add(addATMBtn, 0, 5);
		grid.add(addBankerBtn, 1, 5);
		grid.add(addClientBtn, 2, 5);
		
		//build scene
		Scene scene = new Scene(grid, 600, 400);
        
        primaryStage.setTitle("Bank View and Add");
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}
	public void runAnotherApp(Class<? extends Application> anotherAppClass) throws Exception {
        Application app2 = anotherAppClass.newInstance(); 
        Stage anotherStage = new Stage();
        app2.start(anotherStage);
    }
	public static void main(String[] args) {

		launch(args);
	}
}
