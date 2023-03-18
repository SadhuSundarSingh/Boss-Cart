package Service;

import java.sql.ResultSet;

import org.json.simple.JSONObject;

public interface DeliverymanService {
	public String getMobileNumber(String address);
	public JSONObject getPassword(String mobileNumber);
	public String getUserName(String mobileNumber);
	public JSONObject getWork(String mobileNumber);
	public JSONObject getHistory(String mobileNumber);
	public void delivered(String orderId);
	public JSONObject getProfile(String mobileNumber);
}
