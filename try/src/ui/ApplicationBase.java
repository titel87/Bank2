package ui;

import java.io.IOException;

import listeners.BLEventsListener;
import listeners.UIEventsListener;
import bl.bank_services.Atm;
import bl.bank_services.Banker;
import bl.main.Client;

public interface ApplicationBase{
	
	public void addCustomerToUI(String name);

	public void addATMToUI(String location);

	public void addBankerToUI(String name, double commission);
	
	public void registerListener(UIEventsListener listener);
}
