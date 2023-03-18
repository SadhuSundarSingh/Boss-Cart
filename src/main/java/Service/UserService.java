package Service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Model.User;

public interface UserService {
	public JSONObject loginUser(String mobileNumber, String password);
	public ArrayList<JSONObject> getWishlistProducts(String mobileNumber);
	public boolean isNewMobileNumber(String mobileNumber);
	public void addNewUser(JSONObject obj);
	public String getUserPassword(String mobileNumber);
	public String getUserUserName(String mobileNumber);
	public void addToWishlist(String mobileNumber,String productName);
	public void removeFromWishlist(String mobileNumber,String productName);
	public JSONObject getUserDetails(String mobileNumber);
	public boolean updateUserDetails(HttpServletResponse response,String details);
	public boolean buyProduct(HttpServletRequest request);
	public ArrayList<JSONObject> getHistory(String mobileNumber);
	public JSONArray getHistoryProductname(String mobileNumber);
	public void addComment(String mobileNumber,String comment,String productName);
	public ArrayList<JSONObject> getComments(String productName);
	public String getUrl(String productName);
}
