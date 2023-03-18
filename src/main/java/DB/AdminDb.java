package DB;

import java.sql.ResultSet;

public interface AdminDb {
	public ResultSet getUserList();
	public ResultSet getProductList();
	public ResultSet getDeliverymanList();
	public void addQuantity(String productName,int quantity);
	public void addDeliveryman(String firstName,String lastName,String mobileNumber,String userName,String password,String adress);
	public ResultSet isNewNumber(String mobileNumber);
	public void deleteProduct(String productName);
	//public void editDeliverymanDetail();
}
