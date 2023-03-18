package Service;

import org.json.simple.JSONArray;

public interface AdminService {
	public JSONArray getUserList();
	public JSONArray getProductList();
	public JSONArray getDeliverymanList();
	public void addQuantity(String productName,String quantity);
	public void addDeliveryman(String firstName,String lastName,String mobileNumber,String userName,String password,String adress);
	public boolean isNewNumber(String mobileNumber);
	public void deleteProduct(String productName);
}
