package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.CartStatus;

public class DeliverymanDbImp implements DeliverymanDb{
private static DeliverymanDbImp obj = null;
	
	private DeliverymanDbImp() {};
	
	public static DeliverymanDbImp getInstance() {
		if(obj==null) {
			obj = new DeliverymanDbImp();
		}
		return obj;
	}

	@Override
	public ResultSet getMobileNumber(String address) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select mobileNumber from Delivery_Man_Details where address = ?");
			stmt.setString(1, address);
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet loginMan(String mobileNumber) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select password from Delivery_Man_Details where mobileNumber = ?");
			stmt.setString(1, mobileNumber);
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet getWork(String mobileNumber) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select mobileNumber,productDetail,totalAmount,orderPlace,orderDate,orderId from User_Order_List where deliveryMan = ? and status = ?");
			stmt.setString(1, mobileNumber);
			stmt.setString(2, String.valueOf(CartStatus.NOT_DELIVERD));
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet getHistory(String mobileNumber) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select * from User_Order_List where deliveryMan = ? and status = ?");
			stmt.setString(1, mobileNumber);
			stmt.setString(2, String.valueOf(CartStatus.DELIVERD));
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;		
	}

	@Override
	public void delivered(String deliveredDate,int orderId) {
		DbConnection.getDBConnection();
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("update User_Order_List set deliveredDate = ? , status = ? where orderId = ?");
			stmt.setString(1, deliveredDate);
			stmt.setString(2,String.valueOf(CartStatus.DELIVERD));
			stmt.setInt(3,orderId);
			stmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public ResultSet getUserName(String mobileNumber) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select userName from Delivery_Man_Details where address = ?");
			stmt.setString(1, mobileNumber);
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet getProfile(String mobileNumber) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select * from Delivery_Man_Details where mobileNumber = ?");
			stmt.setString(1, mobileNumber);
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}	
}
