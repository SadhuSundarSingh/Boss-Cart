package Service;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public interface ProductService {
	public ArrayList<JSONObject> getAllProducts();
	public ArrayList<String> searchProducts(String searchWord);
	public JSONObject getProductDetails(String productName);
	public String getProductsPrice(String productName,String quantity);
	public void updateProductQuantity(String productName,int quantity);
}
