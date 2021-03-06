package ui;

import java.util.Vector;

import utils.BankActions;

import bl.bank_services.Atm;
import bl.bank_services.Banker;
import bl.bank_services.ClientService;
import bl.main.Client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import listeners.UIEventsListener;

public class BankActionsWindow extends Application implements ApplicationBase{
	
	private Vector<UIEventsListener> listeners = new Vector<>();;
	private BankActions chosenAction;
	private Atm atms[];
	private Banker bankers[];
	private Client clients[];
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		startView(primaryStage, null, null, null);
	}
	public void start(Stage primaryStage, Client clients[], Atm[] atms, Banker[] bankers) throws Exception {
		startView(primaryStage, clients, atms, bankers);
	}
	public void startView(Stage primaryStage, final Client clients[], final Atm[] atms, final Banker[] bankers){
		this.atms = atms;
		this.bankers = bankers;
		this.clients = clients;		
		
		//grid defenitions
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		
		//text labels
		Text welcomeText;
		welcomeText = new Text("Welcome to the Bank System!");
		welcomeText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		Text commandText;
		commandText = new Text("What would you like to do?");
		commandText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 18));
		
		
		
		//buttons
		Button depositBtn = new Button(); //---DEPOSIT btn
        depositBtn.setText("Deposit");
        depositBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chosenAction = BankActions.Deposit;
            }
        });
		Button withdrawBtn = new Button(); //---WITHDRAW btn
		withdrawBtn.setText("Withdraw");
		withdrawBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chosenAction = BankActions.Withdraw;
            }
        });
		Button giveAuthorizationBtn = new Button(); //---AUTHORIZATION btn
		giveAuthorizationBtn.setText("Give Authorization");
		giveAuthorizationBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chosenAction = BankActions.Authorization;
            }
        });
		Button bankChargeBtn = new Button();//---BANK CHARGE btn
		bankChargeBtn.setText("Bank Charge");
		bankChargeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chosenAction = BankActions.BankCharge;
            }
        });
		Button bankCreditBtn = new Button();//---BANK CREDIT btn
		bankCreditBtn.setText("Bank Crdeit");
		bankCreditBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                chosenAction = BankActions.BankCredit;
            }
        });
		Button nextBtn = new Button();//---NEXT btn
		nextBtn.setText("NEXT ->");
		nextBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent event) {
            	ServiceAndCustomerWindow app = new ServiceAndCustomerWindow(); //open next window
        		try {
					app.start(new Stage(), chosenAction, clients, atms, bankers);
				} catch (Exception e) {
					e.printStackTrace();
				}
            }
        });

		//add text elements to grid
		grid.add(welcomeText, 0, 0, 3, 1);
		grid.add(commandText, 0, 1, 3, 1);
		//add buttons to grid
		grid.add(depositBtn,0,4,1,1);
		grid.add(withdrawBtn,1,4,1,1);
		grid.add(giveAuthorizationBtn,2,4,1,1);
		grid.add(bankCreditBtn,1,6,1,1);
		grid.add(bankChargeBtn,0,6,1,1);
		grid.add(nextBtn,2,12,1,1);
		
        Scene scene = new Scene(grid, 800, 600);
        
        primaryStage.setTitle("Bank System");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	public static void main(String[] args) {
        launch(args);
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
	@Override
	public void depositInUI(int customerId, double amount, ClientService serviceGiver) {}
	@Override
	public void whithdrawInUI(int customerId, double amount, ClientService serviceGiver) {}
	@Override
	public void addAuthorizationInUI(int customerId, String organization, ClientService serviceGiver) {}
	@Override
	public void bankChargeInUI(int customerId, double amount) {}
	@Override
	public void bankCreditInUI(int customerId, double amount) {}

}
