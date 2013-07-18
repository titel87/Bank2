package ui;

import listeners.UIEventsListener;
import bl.bank_services.ClientService;

public interface ApplicationBase{
	
	public void addCustomerToUI(String name);

	public void addATMToUI(String location);

	public void addBankerToUI(String name, double commission);
	
	public void depositInUI(int customerId, double amount,ClientService serviceGiver);
	
	public void whithdrawInUI(int customerId, double amount,ClientService serviceGiver);
	
	public void addAuthorizationInUI(int customerId, String organization,ClientService serviceGiver);
	
	public void bankChargeInUI(int customerId, double amount);
	
	public void bankCreditInUI(int customerId, double amount);
	
	public void registerListener(UIEventsListener listener);
}
