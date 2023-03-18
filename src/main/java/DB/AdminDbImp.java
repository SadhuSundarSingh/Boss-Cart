package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDbImp implements AdminDb{
	
	private static AdminDbImp obj = null;
	
	private AdminDbImp() {};
	
	public static AdminDbImp getInstance() {
		if(obj==null) {
			obj = new AdminDbImp();
		}
		return obj;
	}	

	@Override
	public ResultSet getUserList() {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select userName,mobileNumber from Customer_Details order by userName");
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet getProductList() {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select name,quantity,url from Product_Details order by name");
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet getDeliverymanList() {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select userName,mobileNumber from Delivery_Man_Details order by userName");
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public void addQuantity(String productName, int quantity) {
		DbConnection.getDBConnection();
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("update Product_Details set quantity = ? where name = ?");
			stmt.setInt(1, quantity);
			stmt.setString(2, productName);
			stmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void addDeliveryman(String firstName, String lastName, String mobileNumber, String userName, String password,String adress) {
		DbConnection.getDBConnection();
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("insert into Delivery_Man_Details values(?,?,?,?,?,?)");
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, mobileNumber);
			stmt.setString(4, userName);
			stmt.setString(5, password);
			stmt.setString(6, adress);
			stmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public ResultSet isNewNumber(String mobileNumber) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select userName from Delivery_Man_Details where mobileNumber = ? ");
			stmt.setString(1, mobileNumber);
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public void deleteProduct(String productName) {
		DbConnection.getDBConnection();
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("delete from Product_Details where name = ?");
			stmt.setString(1,productName);
			stmt.execute();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
