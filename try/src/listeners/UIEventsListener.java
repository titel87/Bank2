package listeners;

public interface UIEventsListener {
	void customerAddedToUIEvent(String name);
	void ATMAddedToUIEvent(String location);
	void bankerAddedToUIEvent(String name, double commission);
	void depositInUIEvent(int customerId, double amount);
	void withdrawInUIEvent(int customerId, double amount);
	void authorizationInUIEvent(int customerId, double amount);
	void bankChargeInUIEvent(int customerId, double amount);
	void bankCreditInUIEvent(int customerId, double amount);
}
