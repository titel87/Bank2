package ui;

import java.util.Vector;

import bl.bank_services.Banker;
import bl.main.BankManager;
import bl.main.Client;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import listeners.BLEventsListener;
import listeners.UIEventsListener;

public class ServiceAndCustomerWindow extends Application implements ApplicationBase{

	private Vector<UIEventsListener> listeners  = new Vector<>();;
	private ListView<String> customersList = new ListView<String>();
	ObservableList<String> items = FXCollections.observableArrayList("tamar", "shani", "keren", "chen", "igor");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//build grid
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
		ListView<String> ATMsList = new ListView<String>();
		//ObservableList<String> items = FXCollections.observableArrayList("tamar", "shani", "keren", "chen", "igor");
		ATMsList.setItems(items);
		ATMsList.setPrefHeight(200);
		ATMsList.setPrefWidth(100);
		ListView<String> bankersList = new ListView<String>();
		bankersList.setItems(items);
		bankersList.setPrefHeight(200);
		bankersList.setPrefWidth(100);
		//ListView<String> customersList = new ListView<String>();
		customersList.setItems(items);
		customersList.setPrefHeight(200);
		customersList.setPrefWidth(100);
		
		//create hBoxes to insert new objects
		Label nameLabel = new Label("Name:");
		nameLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
		final TextField nameTextField = new TextField ();
		final HBox nameBox = new HBox();
		nameBox.getChildren().addAll(nameLabel, nameTextField);
		nameBox.setSpacing(10);
		nameBox.setVisible(false); //don't show
		
		
		//create buttons
        final Button doneBtn = new Button();
        doneBtn.setText("Done");
        doneBtn.setVisible(false);
        doneBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!nameTextField.getText().isEmpty()){
                	addCustomerToUI(nameTextField.getText());
                }
            }
        });
		Button addATMBtn = new Button();
		addATMBtn.setText("Add ATM");
		addATMBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Opening ATM details window -> Adding an ATM");
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
                nameBox.setVisible(true);
                doneBtn.setVisible(true);
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
		grid.add(doneBtn, 2, 7);
		
		//add Hboxes to grid
		grid.add(nameBox, 2,6);
		
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
	@Override
	public void addCustomerToUI(String name) {
		items.add(name);
		fireAddCustomerToUI(name);	
		
	}
	@Override
	public void addATMToUI(String location) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addBankerToUI(String name, double commission) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void registerListener(UIEventsListener listener) {

		listeners.add(listener);
		
	}
	
	private void fireAddCustomerToUI(String name) {
		for (UIEventsListener l : listeners) {
			l.customerAddedToUIEvent(name);
		}
	}
}
