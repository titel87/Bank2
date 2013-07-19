package ui;

import java.util.Vector;
import utils.BankActions;
import bl.bank_services.Atm;
import bl.bank_services.Banker;
import bl.bank_services.ClientService;
import bl.main.Client;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import listeners.UIEventsListener;

public class ServiceAndCustomerWindow extends Application implements ApplicationBase{

	private Vector<UIEventsListener> listeners  = new Vector<>();
	private ListView<String> customersList = new ListView<String>();
	ListView<String> ATMsList = new ListView<String>();
	ListView<String> bankersList = new ListView<String>();
	private boolean isBankerSelected = false;
	private boolean isATMSelected = false;
	private boolean isClientSelected = false;
	private ObservableList<String> clientsItemsList;
	private ObservableList<String> bankersItemsList;
	private ObservableList<String> atmsItemsList;
	public final String EMPTY_STRING = "";
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		startView(primaryStage, null, null, null, null);
	}
	public void start(Stage primaryStage, BankActions chosenAction, Client[] clients, Atm[] atms, Banker[] bankers) throws Exception {
		startView(primaryStage, chosenAction, clients, atms, bankers);
	}
	public ObservableList<String> clientsToObservableList(Client clients[]){
		final ObservableList<String> listItems = FXCollections.observableArrayList();
		for(Client c : clients){
			listItems.add(c.getClientName());
		}
		return listItems;
	}
	public ObservableList<String> ATMsToObservableList(Atm[] atms){
		final ObservableList<String> listItems = FXCollections.observableArrayList();
		for(Atm a : atms){
			listItems.add(a.getLocation());
		}
		return listItems;
	}
	public ObservableList<String> bankersToObservableList(Banker[] bankers){
		final ObservableList<String> listItems = FXCollections.observableArrayList();
		for(Banker b : bankers){
			listItems.add(b.getBankerName());
		}
		return listItems;
	}
	public void startView(Stage primaryStage, final BankActions chosenAction, Client[] clients, Atm[] atms, Banker[] bankers){
		if(chosenAction!=null && clients!=null && atms!=null && bankers!=null){ //check
			//build grid
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.TOP_CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			
			//create header labels
			Text headerLabel1 = new Text("  Please select a Client and a ");
			headerLabel1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
			Text headerLabel2 = new Text("Banker / ATM for action:");
			headerLabel2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
			Label ATMsLabel = new Label("ATMs");
			ATMsLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
			Label bankersLabel = new Label("Bankers");
			bankersLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
			Label customersLabel = new Label("Customers");
			customersLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
			
			//create lists
			//-----ATM
			atmsItemsList = ATMsToObservableList(atms);
			ATMsList.setItems(atmsItemsList);
			ATMsList.setPrefHeight(200);
			ATMsList.setPrefWidth(100);
			//-----Bankers
			bankersItemsList = bankersToObservableList(bankers);
			bankersList.setItems(bankersItemsList);
			bankersList.setPrefHeight(200);
			bankersList.setPrefWidth(100);
			//-----Clients
			clientsItemsList = clientsToObservableList(clients);
			customersList.setItems(clientsItemsList);
			customersList.setPrefHeight(200);
			customersList.setPrefWidth(100);
			//-----add selection listeners in listView
			customersList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
				@Override
			    public void changed(ObservableValue observable, String oldValue, String newValue) {
			       //System.out.println("value changed from " + oldValue + " to " + newValue);
		    	   isClientSelected = true;
			    }
			});
			bankersList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
				@Override
			    public void changed(ObservableValue observable, String oldValue, String newValue) {
			       //System.out.println("value changed from " + oldValue + " to " + newValue);
			       isBankerSelected = true;
			    }
			});
			ATMsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
				@Override
			    public void changed(ObservableValue observable, String oldValue, String newValue) {
			       //System.out.println("value changed from " + oldValue + " to " + newValue);
			       isATMSelected = true;
			    }
			});
			
			//create hBoxes to insert new objects
			//-----Client
			Label nameLabel = new Label("Name:"); 
			nameLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
			final TextField nameTextField = new TextField ();
			final HBox nameBox = new HBox();
			nameBox.getChildren().addAll(nameLabel, nameTextField);
			nameBox.setSpacing(10);
			nameBox.setVisible(false); //don't show
			//-----ATM
			Label locationLabel = new Label("Location:"); 
			locationLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
			final TextField locationTextField = new TextField ();
			final HBox locationBox = new HBox();
			locationBox.getChildren().addAll(locationLabel, locationTextField);
			locationBox.setSpacing(10);
			locationBox.setVisible(false); //don't show
			//-----Banker
			Label bankerNameLabel = new Label("Name:"); 
			bankerNameLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
			final TextField bankerNameTextField = new TextField ();
			final HBox bankerNameBox = new HBox();
			bankerNameBox.getChildren().addAll(bankerNameLabel, bankerNameTextField);
			bankerNameBox.setSpacing(10);
			bankerNameBox.setVisible(false); //don't show
			Label commissionLabel = new Label("Commission:");
			commissionLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
			final TextField commissionTextField = new TextField ();
			final HBox commissionBox = new HBox();
			commissionBox.getChildren().addAll(commissionLabel, commissionTextField);
			commissionBox.setSpacing(10);
			commissionBox.setVisible(false); //don't show
			
			
			//create buttons
			//-----Client
	        final Button clientDoneBtn = new Button(); //---adding Client DONE
	        clientDoneBtn.setText("Done");
	        clientDoneBtn.setVisible(false);
	        clientDoneBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                if(!nameTextField.getText().isEmpty()){
	                	addCustomerToUI(nameTextField.getText());
	                }
	            }
	        });
	        final Button clientCancelBtn = new Button(); //---adding Client CANCEL
	        clientCancelBtn.setText("Cancel");
	        clientCancelBtn.setVisible(false);
	        clientCancelBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                nameBox.setVisible(false);
	                nameTextField.setText("");
	                clientDoneBtn.setVisible(false);
	                clientCancelBtn.setVisible(false);
	            }
	        });
	        //-----ATM
	        final Button ATMDoneBtn = new Button(); //---adding ATM DONE
	        ATMDoneBtn.setText("Done");
	        ATMDoneBtn.setVisible(false);
	        ATMDoneBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                if(!locationTextField.getText().isEmpty()){
	                	addATMToUI(locationTextField.getText());
	                }
	            }
	        });
	        final Button ATMCancelBtn = new Button(); //---adding ATM CANCEL
	        ATMCancelBtn.setText("Cancel");
	        ATMCancelBtn.setVisible(false);
	        ATMCancelBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                locationBox.setVisible(false);
	                locationTextField.setText("");
	                ATMDoneBtn.setVisible(false);
	                ATMCancelBtn.setVisible(false);
	            }
	        });
	        //-----Banker 
	        final Button bankerDoneBtn = new Button(); //---adding Banker DONE
	        bankerDoneBtn.setText("Done");
	        bankerDoneBtn.setVisible(false);
	        bankerDoneBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                if(!bankerNameTextField.getText().isEmpty()){
	                	addBankerToUI(bankerNameTextField.getText(), Double.valueOf(commissionTextField.getText().trim()).doubleValue());
	                }
	            }            
	        });
	        final Button bankerCancelBtn = new Button(); //---adding Banker CANCEL
	        bankerCancelBtn.setText("Cancel");
	        bankerCancelBtn.setVisible(false);
	        bankerCancelBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                bankerNameBox.setVisible(false);
	                bankerNameTextField.setText("");
	                commissionBox.setVisible(false);
	                commissionTextField.setText("");
	                bankerDoneBtn.setVisible(false);
	                bankerCancelBtn.setVisible(false);
	            }
	        });
	        //-----General
			Button addATMBtn = new Button();
			addATMBtn.setText("Add ATM");
			addATMBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                locationBox.setVisible(true);
	                ATMDoneBtn.setVisible(true);
	                ATMCancelBtn.setVisible(true);
	            }
	        });
	        Button addBankerBtn = new Button();
	        addBankerBtn.setText("Add Banker");
	        addBankerBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                bankerNameBox.setVisible(true);
	                commissionBox.setVisible(true);
	                bankerDoneBtn.setVisible(true);
	                bankerCancelBtn.setVisible(true);
	            }
	        });
	        Button addClientBtn = new Button();
	        addClientBtn.setText("Add Client");
	        addClientBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                nameBox.setVisible(true);
	                clientDoneBtn.setVisible(true);
	                clientCancelBtn.setVisible(true);
	            }
	        });
	        Button NextBtn = new Button();
	        NextBtn.setText("NEXT ->");
	        NextBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                if(isBankerSelected == true && isATMSelected == true){
	                	MessageBox msgbox = new MessageBox();
	                    Stage anotherStage = new Stage();
	                    try {
							msgbox.start(anotherStage, "You can choose banker OR atm!");
						} catch (Exception e) {
							e.printStackTrace();
						}
	                }
	                else if(isClientSelected == true){
	                	if(isBankerSelected == true){
	                        //BankActionsWindow bankeActions = new BankActionsWindow();
	                        try {
								SpecificActionWindow actionApp = new SpecificActionWindow();
								actionApp.start(new Stage(), chosenAction, customersList.getSelectionModel().getSelectedItem(), 
										EMPTY_STRING, bankersList.getSelectionModel().getSelectedItem());
							} catch (Exception e) {
								e.printStackTrace();
							}
	                		
	                	}
	                	else if(isATMSelected == true){
	                        try {
	                        	//open small action window
	                        	SpecificActionWindow actionApp = new SpecificActionWindow();
								actionApp.start(new Stage(), chosenAction, customersList.getSelectionModel().getSelectedItem(), 
										ATMsList.getSelectionModel().getSelectedItem(),EMPTY_STRING);
							} catch (Exception e) {
								e.printStackTrace();
							}
	                	}
	                }
	                else{
	                	MessageBox msgbox = new MessageBox();
	                    Stage anotherStage = new Stage();
	                    try {
							msgbox.start(anotherStage, "You have to choose banker OR atm AND a client!");
						} catch (Exception e) {
							e.printStackTrace();
						}
	                }
	            }
	        });
	        Button UnselectBtn = new Button();
	        UnselectBtn.setText("Clear All");
	        UnselectBtn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	        		customersList.getSelectionModel().clearSelection();
	        		bankersList.getSelectionModel().clearSelection();
	        		ATMsList.getSelectionModel().clearSelection();
	        		isClientSelected = false;
	        		isATMSelected = false;
	        		isBankerSelected = false;
	            }
	        });
			
			//add header labels to grid
	        grid.add(headerLabel1, 0, 1);
	        grid.add(headerLabel2, 1, 1);
			grid.add(ATMsLabel, 0, 3);
			grid.add(bankersLabel, 1, 3);
			grid.add(customersLabel, 2, 3);
			
			//add lists to grid
			if(chosenAction != BankActions.BankCharge && chosenAction != BankActions.BankCredit){
				grid.add(ATMsList, 0, 4);
				grid.add(bankersList, 1, 4);
			}
			grid.add(customersList, 2, 4);
			
			//add buttons to grid
			grid.add(addATMBtn, 0, 5);
			grid.add(addBankerBtn, 1, 5);
			grid.add(addClientBtn, 2, 5);
			grid.add(clientDoneBtn, 2, 7);
			grid.add(bankerDoneBtn, 1, 8);
			grid.add(ATMDoneBtn, 0, 7);
			grid.add(clientCancelBtn, 2, 8);
			grid.add(bankerCancelBtn, 1, 9);
			grid.add(ATMCancelBtn, 0, 8);
			grid.add(NextBtn, 2, 11);
			grid.add(UnselectBtn, 2, 12);
			
			//add Hboxes to grid
			grid.add(nameBox, 2,6);
			grid.add(locationBox, 0, 6);
			grid.add(bankerNameBox, 1, 6);
			grid.add(commissionBox, 1, 7);
			
			//build scene
			Scene scene = new Scene(grid, 800, 600);
	        
	        primaryStage.setTitle("Bank View and Add");
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
	public void runAnotherApp(Class<? extends Application> anotherAppClass) throws Exception {
        Application app2 = anotherAppClass.newInstance(); 
        Stage anotherStage = new Stage();
        app2.start(anotherStage);
    }
	public static void main(String[] args) {
		launch(args);
	}

	public void addCustomerToUI(String name) {
		try{
			fireAddCustomerToUI(name);
			clientsItemsList.add(name);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}

	public void addATMToUI(String location) {
		try{
			fireAddAtmToUI(location);
			atmsItemsList.add(location);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}

	public void addBankerToUI(String name, double commission) {
		try{
			fireAddBankerToUI(name, commission);
			bankersItemsList.add(name);
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}

	public void registerListener(UIEventsListener listener) {
		listeners.add(listener);
	}
	
	private void fireAddCustomerToUI(String name) {
		for (UIEventsListener l : listeners) {
			l.customerAddedToUIEvent(name);
		}
	}
	
	private void fireAddAtmToUI(String location) {
		for (UIEventsListener l : listeners) {
			l.ATMAddedToUIEvent(location);
		}
	}
	
	private void fireAddBankerToUI(String name, double commission) {
		for (UIEventsListener l : listeners) {
			l.bankerAddedToUIEvent(name,commission);
		}
	}
	@Override
	public void depositInUI(int customerId, double amount,
			ClientService serviceGiver) { 
	}
	@Override
	public void whithdrawInUI(int customerId, double amount,
			ClientService serviceGiver) {
	}
	@Override
	public void addAuthorizationInUI(int customerId, String organization,
			ClientService serviceGiver) {
	}
	@Override
	public void bankChargeInUI(int customerId, double amount) {
	}
	@Override
	public void bankCreditInUI(int customerId, double amount) {
	}
}
