package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bl.bank_services.*;
import bl.main.Client;

public class DBWriter {

	public static ArrayList<Client> clients;
	public static ArrayList<Atm> atms;
	public static ArrayList<Banker> bankers;
	
	private Connection connection = null;
	
	public DBWriter(){
		clients = new ArrayList<Client>();
		atms = new ArrayList<Atm>();
		bankers = new ArrayList<Banker>();
	}
	
	public void writeNewClient(Client client){
		
	}
	
	public void writeNewAtm(Atm atm){
		if(atm != null){
			atms.add(atm);
			//write to db
			try {
				openConnection();

				Statement statment = connection.createStatement();
				//create sql query string
				String s = "INSERT INTO `bank_db`.`bankers` (`location`) VALUES ('"
						+ atm.getLocation() + "')";
				//execute the query
				int numOfAffectedRows = statment.executeUpdate(s);
				//for debug:
				System.out.println("numOfAffectedRows=" + numOfAffectedRows);
				
			} catch (InstantiationException  e) {e.printStackTrace();
			} catch (IllegalAccessException e)  {e.printStackTrace();
			} catch (ClassNotFoundException e)  {e.printStackTrace();
			} catch (SQLException e)            {e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}

	}
	
	public void writeNewBanker(Banker banker){
		if(banker != null){
			bankers.add(banker);
			//write to db
			try {
				openConnection();

				Statement statment = connection.createStatement();
				//create sql query string
				String s = "INSERT INTO `bank_db`.`bankers` (`name`) VALUES ('"
						+ banker.getName() + "')";
				//execute the query
				int numOfAffectedRows = statment.executeUpdate(s);
				//for debug:
				System.out.println("numOfAffectedRows=" + numOfAffectedRows);
				
			} catch (InstantiationException  e) {e.printStackTrace();
			} catch (IllegalAccessException e)  {e.printStackTrace();
			} catch (ClassNotFoundException e)  {e.printStackTrace();
			} catch (SQLException e)            {e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	private void openConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		String dbUrl = "jdbc:mysql://localhost/bank_db";
		connection = DriverManager.getConnection(dbUrl, "root", "");
		
		System.out.println("Database connection established");
	}
	
}
