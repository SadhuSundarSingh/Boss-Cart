package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CartDbImp implements CartDb {
	
	private static CartDbImp obj = null;
	
	private CartDbImp() {};
	
	public static CartDbImp getInstance() {
		if(obj==null) {
			obj = new CartDbImp();
		}
		return obj;
	}	

	@Override
	public void addUser(String mobileNumber) {
		DbConnection.getDBConnection();
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("insert into Cart(userMobileNumber,products) values(?,?)");
			stmt.setString(1,mobileNumber);
			stmt.setString(2,"");
			stmt.executeUpdate();
			}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public ResultSet cartProducts(String mobileNumber) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select products from Cart where userMobileNumber = ?");
			stmt.setString(1, mobileNumber);
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public void updateCartProducts(String mobileNumber, String products) {
		DbConnection.getDBConnection();
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("update Cart set products = ? where userMobileNumber = ?");
			stmt.setString(1, products);
			stmt.setString(2, mobileNumber);
			stmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
