package DB;

import java.sql.ResultSet;
import java.util.ArrayList;

import Model.Product;

public interface ProductDb {
	public ResultSet getAllProducts();
	public ResultSet[] searchProducts(String searchWord);
	public ResultSet getProductDetails(String productName);
	public ResultSet getPriceAndQuantity(String productName);
	public void updateProductQuantity(String productName,int quantity);
}
