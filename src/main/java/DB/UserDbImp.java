package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import Model.User;

public class UserDbImp implements UserDb{
	
	private static UserDbImp obj = null;
	
	private UserDbImp() {};
	
	public static UserDbImp getInstance() {
		if(obj==null) {
			obj = new UserDbImp();
		}
		return obj;
	}

	@Override
	public void insertUser(User user) {
		DbConnection.getDBConnection();
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("insert into Customer_Details values(?,?,?,?,?,?,?)");
			stmt.setString(1, user.getFirstName());
	        stmt.setString(2, user.getLastName());
	        stmt.setString(3, user.getMobileNumber());
	        stmt.setString(4, user.getUserName());
	        stmt.setString(5, user.getPassword());
	        stmt.setString(6, user.getAddress());
	        stmt.setString(7, "");
	        stmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public ResultSet loginUser(String mobileNumber, String password) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select userName,password,Address from Customer_Details where mobileNumber = ?");
			stmt.setString(1,mobileNumber);
			rs = stmt.executeQuery();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet getWishlist(String mobileNumber) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select wishlist from Customer_Details where mobileNumber = ?");
			stmt.setString(1,mobileNumber);
			rs = stmt.executeQuery();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;		
		
	}

	@Override
	public ResultSet isNewMobileNumber(String mobileNumber) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select mobileNumber from Customer_Details where mobileNumber = ?");
			stmt.setString(1,mobileNumber);
			rs = stmt.executeQuery();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;				
	}

	@Override
	public ResultSet getUserPassword(String mobileNumber) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select password from Customer_Details where mobileNumber = ?");
			stmt.setString(1, mobileNumber);
			rs = stmt.executeQuery();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	
	public ResultSet getUserUserName(String mobileNumber) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select userName from Customer_Details where mobileNumber = ?");
			stmt.setString(1, mobileNumber);
			rs = stmt.executeQuery();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public void updateWishlistProducts(String mobileNumber, String product) {
		DbConnection.getDBConnection();
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("update Customer_Details set wishlist = ? where mobileNumber = ?");
			stmt.setString(1, product);
			stmt.setString(2, mobileNumber);
			stmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public ResultSet getUserDetails(String mobileNumber) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select firstName,lastName,mobileNumber,address,userName,password from Customer_Details where mobileNumber = ?");
			stmt.setString(1, mobileNumber);
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public void updateUserDetails(String firstName, String lastName, String mobileNumber, String userName,String password, String address, String userMobileNumber) {
		DbConnection.getDBConnection();
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("update Customer_Details set firstName = ? , lastName = ? , mobileNumber = ? , userName = ? , password = ? , Address = ? where mobileNumber = ?");
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, mobileNumber);
			stmt.setString(4,userName);
			stmt.setString(5, password);
			stmt.setString(6, address);
			stmt.setString(7, userMobileNumber);
			stmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void buyProduct(String mobileNumber, String productDetail, String totalAmount, String orderPlace,String deliverymanMobileNumber,String orderDate,String status) {
		DbConnection.getDBConnection();
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("insert into User_Order_List(mobileNumber,productDetail,totalAmount,orderPlace,deliveryMan,orderDate,status) values(?,?,?,?,?,?,?)");
			stmt.setString(1, mobileNumber);
			stmt.setString(2,productDetail);
			stmt.setString(3, totalAmount);
			stmt.setString(4, orderPlace);
			stmt.setString(5, deliverymanMobileNumber);
			stmt.setString(6,orderDate);
			stmt.setString(7, status);
			stmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public ResultSet getHistory(String mobileNumber) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select * from User_Order_List where mobileNumber = ? order by orderDate desc");
			stmt.setString(1, mobileNumber);
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet getHistoryProductNames(String mobileNumber) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select productDetail,status from User_Order_List where mobileNumber = ?");
			stmt.setString(1, mobileNumber);
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public void addComment(String mobileNumber, String comment, String date, String productName) {
		DbConnection.getDBConnection();
		try{
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("insert into Review values(?,?,?,?)");
			stmt.setString(1, mobileNumber);
			stmt.setString(2, comment);
			stmt.setString(3, date);
			stmt.setString(4, productName);
			stmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public ResultSet getAllComments(String productName) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try{
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select * from Review where productName = ? order by reviewDate desc");
			stmt.setString(1, productName);
			rs = stmt.executeQuery();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet getUrl(String productName) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try{
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select url from Product_Details where name = ?");
			stmt.setString(1, productName);
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
}
