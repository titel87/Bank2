package ui;

import java.io.IOException;
import java.util.Vector;
import dal.DBReader;
import utils.BankActions;
import bl.bank_services.ClientService;
import bl.main.Client;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import listeners.UIEventsListener;

public class SpecificActionWindow extends Application implements ApplicationBase{
	
	private Vector<UIEventsListener> listeners  = new Vector<>();
	Client client;
	ClientService serviceGiver;
	DBReader dbReader = new DBReader();
	

	public static void main(String[] args) {
		launch(args);
	}
	
	private void initComponents(String clientName, String atmLocation, String bankerName) throws SecurityException, IOException
	{
		//client extraction
		client = dbReader.findClientByName(clientName);
		if(atmLocation.isEmpty()){
			serviceGiver = dbReader.findBankerByName(bankerName);
		}
		if(bankerName.isEmpty()){
			serviceGiver = dbReader.findATMByLocation(atmLocation);
		}
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		startView(primaryStage, null, null, null, null);
		
	}
	
	public void start(Stage primaryStage, BankActions chosenAction, String clientName, String atmLocation, String bankerName) throws Exception {
		startView(primaryStage, chosenAction, clientName, atmLocation, bankerName);
	}
	
	private void startView(Stage primaryStage, final BankActions chosenAction, String clientName, String atmLocation, String bankerName) throws Exception {
		
		if(chosenAction!=null && clientName!=null && atmLocation!=null && bankerName!=null){ //check
			
			initComponents(clientName, atmLocation, bankerName);
			
			//build grid
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.TOP_CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			
			//create header labels
			Text commandText;
			if(!bankerName.isEmpty()){
				commandText = new Text("Bank Actions for Client: " + clientName + " | Banker: " + bankerName);
			}
			else{
				commandText = new Text("Bank Actions for Client: " + clientName + " | ATM: " + atmLocation);
			}
			commandText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
			
			//create HBoxes for actions
			//-----BOX 1
			Label label1 = new Label(); 
			label1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
			final TextField textField1 = new TextField ();
			final HBox box1 = new HBox();
			box1.getChildren().addAll(label1, textField1);
			box1.setSpacing(10);
			//box1.setVisible(false);
			
			//-----BOX 2 -- just in case
			Label label2 = new Label(); 
			label2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
			final TextField textField2 = new TextField ();
			final HBox box2 = new HBox();
			box2.getChildren().addAll(label2, textField2);
			box2.setSpacing(10);
			//box2.setVisible(false);
			
			
			//populate labels
			if(chosenAction == BankActions.Deposit){
				label1.setText("Amount to deposit:");
				//label1.setVisible(true);
			}
			else if(chosenAction == BankActions.Withdraw){
				label1.setText("Amount to withdraw:");
				//label1.setVisible(true);
			}
			else if(chosenAction == BankActions.Authorization){
				label1.setText("Name to authorize:");
				//label1.setVisible(true);
			}
			else if(chosenAction == BankActions.BankCharge){
				label1.setText("Amount to charge:");
				//label1.setVisible(true);
			}
			else if(chosenAction == BankActions.BankCredit){
				label1.setText("Amount to credit:");
				//label1.setVisible(true);
			}
			
			//create buttons
			final Button doneBtn = new Button(); //---adding Client DONE
	        doneBtn.setText("Done");
	        //doneBtn.setVisible(false);
	        doneBtn.setOnAction(new EventHandler<ActionEvent>() {        		
	            @Override
	            public void handle(ActionEvent event) {
	                if(!textField1.getText().isEmpty()){
	                	if(chosenAction == BankActions.Deposit){
	        				depositInUI((int)client.getId(), Double.parseDouble(textField1.getText()), serviceGiver);
	        			}
	        			else if(chosenAction == BankActions.Withdraw){
	        				whithdrawInUI((int)client.getId(), Double.parseDouble(textField1.getText()), serviceGiver);
	        			}
	        			else if(chosenAction == BankActions.Authorization){
	        				addAuthorizationInUI((int)client.getId(), textField1.getText(), serviceGiver);
	        			}
	        			else if(chosenAction == BankActions.BankCharge){
	        				bankChargeInUI((int)client.getId(), Double.parseDouble(textField1.getText()));
	        			}
	        			else if(chosenAction == BankActions.BankCredit){
	        				bankCreditInUI((int)client.getId(), Double.parseDouble(textField1.getText()));
	        			}
	                }
	            }
	        });
	        
	        //add to grid
	        grid.add(commandText, 0, 0);
	        grid.add(box1, 0, 3);
	        grid.add(doneBtn, 0, 4);
	        
	        
	      //build scene
			Scene scene = new Scene(grid, 800, 600);
	        
	        primaryStage.setTitle("Action details");
	        primaryStage.setScene(scene);
	        primaryStage.show();
		}
		else{
			MessageBox msgbox = new MessageBox();
            Stage anotherStage = new Stage();
            try {
				msgbox.start(anotherStage, "No Data to Present.");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void depositInUI(int customerId, double amount,
			ClientService serviceGiver) {
		try{
			fireDepositInUIEvent(customerId, amount, serviceGiver);
		}
		catch(Exception e){
			System.out.println(e);
		}		
	}
	@Override
	public void whithdrawInUI(int customerId, double amount,
			ClientService serviceGiver) {
		try{
			firewithdrawInUIEvent(customerId, amount, serviceGiver);
		}
		catch(Exception e){
			System.out.println(e);
		}		
	}
	@Override
	public void addAuthorizationInUI(int customerId, String organization,
			ClientService serviceGiver) {
		try{
			fireauthorizationInUIEvent(customerId, organization, serviceGiver);
		}
		catch(Exception e){
			System.out.println(e);
		}		
	}
	@Override
	public void bankChargeInUI(int customerId, double amount) {
		try{
			firebankChargeInUIEvent(customerId, amount);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	@Override
	public void bankCreditInUI(int customerId, double amount) {
		try{
			firebankCreditInUIEvent(customerId, amount);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	private void fireDepositInUIEvent(int customerId, double amount,ClientService serviceGiver){
		for (UIEventsListener l : listeners) {
			l.depositInUIEvent(customerId, amount, serviceGiver);
		}
	}
	private void firewithdrawInUIEvent(int customerId, double amount,ClientService serviceGiver){
		for (UIEventsListener l : listeners) {
			l.withdrawInUIEvent(customerId, amount, serviceGiver);
		}
	}
	private void fireauthorizationInUIEvent(int customerId, String organization,ClientService serviceGiver){
		for (UIEventsListener l : listeners) {
			l.authorizationInUIEvent(customerId, organization, serviceGiver);
		}
	}
	private void firebankChargeInUIEvent(int customerId, double amount){
		for (UIEventsListener l : listeners) {
			l.bankChargeInUIEvent(customerId, amount);
		}
	}
	private void firebankCreditInUIEvent(int customerId, double amount){
		for (UIEventsListener l : listeners) {
			l.bankCreditInUIEvent(customerId, amount);
		}
	}

	@Override
	public void registerListener(UIEventsListener listener) {
		listeners.add(listener);
		
	}
	
	@Override
	public void addCustomerToUI(String name) {}

	@Override
	public void addATMToUI(String location) {}

	@Override
	public void addBankerToUI(String name, double commission) {}


}
