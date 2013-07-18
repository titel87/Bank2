package ui;

import bl.main.BankManager;
import bl.main.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class TestProgram extends Application {
	
	public static void main(String[] args) {

		launch(args);
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
		BankManager model = new BankManager();
		//model.main();
		model.initComponentes();
		//TODO write all data from xml to db 
		//ServiceAndCustomerWindow app = new ServiceAndCustomerWindow();
		BankActionsWindow app = new BankActionsWindow();
		Controller controller1 = new Controller(model, app);
		
        try {
        	//TODO read from db clients,atms & bankers
        	//and then call initComponents(clients,atms & bankers)
        	app.start(new Stage(), model.getClients(), model.getAtms(), model.getBankers());
        	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
