package Service;

public interface CartService {
	public void addUser(String mobileNumber);
	public String getCartProducts(String mobileNumber);
	public void addProduct(String mobileNumber,String productName,String quantity);
	public void removeProduct(String mobileNumber,String productName);
}
