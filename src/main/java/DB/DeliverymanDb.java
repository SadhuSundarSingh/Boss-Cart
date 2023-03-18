package DB;

import java.sql.ResultSet;

public interface DeliverymanDb {
	public ResultSet getMobileNumber(String address);
	public ResultSet loginMan(String mobileNumber);
	public ResultSet getUserName(String mobileNumber);
	public ResultSet getWork(String mobileNumber);
	public ResultSet getHistory(String mobileNumber);
	public void delivered(String deliveredDate,int orderId);
	public ResultSet getProfile(String mobileNumber);
}
