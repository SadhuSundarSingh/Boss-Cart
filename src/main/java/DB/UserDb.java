package DB;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import Model.User;

public interface UserDb {
	public void insertUser(User user);
	public ResultSet isNewMobileNumber(String mobileNumber);
	public ResultSet loginUser(String mobileNumber,String password);
	public ResultSet getWishlist(String mobileNumber);
	public ResultSet getUserPassword(String mobileNumber);
	public ResultSet getUserUserName(String mobileNumber);
	public void updateWishlistProducts(String mobileNumber,String product);
	public ResultSet getUserDetails(String mobileNumber);
	public void updateUserDetails(String firstName,String lastName,String mobileNumber,String userName,String password,String address,String userMobileNumber);
	public void buyProduct(String mobileNumber,String productDetail , String totalAmount,String orderPlace,String deliverymanMobileNumber,String orderDate,String status);
	public ResultSet getHistory(String mobileNumber);
	public ResultSet getHistoryProductNames(String mobileNumber);
	public void addComment(String mobileNumber,String comment,String date,String productName);
	public ResultSet getAllComments(String productName);
	public ResultSet getUrl(String productName);
}
