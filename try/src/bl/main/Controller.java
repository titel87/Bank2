package bl.main;

import java.io.IOException;

import ui.ApplicationBase;

import javafx.application.Application;
import listeners.BLEventsListener;
import listeners.UIEventsListener;

public class Controller implements BLEventsListener, UIEventsListener{

	private BankManager bankModel;
	private ApplicationBase bankView;
	
	public Controller(BankManager bankModel, ApplicationBase bankView) {
		super();
		this.bankModel = bankModel;
		this.bankView = bankView;
		
		bankModel.registerListener(this);
		bankView.registerListener(this);
	}

	@Override
	public void customerAddedToUIEvent(String name) {
		// user added customer to UI --> now notify BL to add this customer
		try {
			bankModel.addCustomerToSystem(name);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ATMAddedToUIEvent(String location) {
		// user added atm to UI --> now notify BL to add this atm
			try {
				bankModel.addATMToSystem(location);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public void bankerAddedToUIEvent(String name, double commission) {
		// user added banker to UI --> now notify BL to add this banker
					try {
						bankModel.addBankerToSystem(name, commission);
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
		
	}

	@Override
	public void depositInUIEvent(int customerId, double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdrawInUIEvent(int customerId, double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void authorizationInUIEvent(int customerId, double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bankChargeInUIEvent(int customerId, double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bankCreditInUIEvent(int customerId, double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void customerAddedToBLEvent(String name) {
		// user added customer to BL --> now notify UI to add this customer
		bankView.addCustomerToUI(name);
		
	}

	@Override
	public void ATMAddedToBLEvent(String location) {
		//user added atm to BL --> now notify UI to add this atm
		bankView.addATMToUI(location);
		
	}

	@Override
	public void bankerAddedToBLEvent(String name, double commission) {
		//user added banker to BL --> now notify UI to add this banker
		bankView.addBankerToUI(name, commission);
		
	}

}
