package Service;

import java.sql.ResultSet;

import org.apache.catalina.tribes.util.Arrays;

import DB.CartDbImp;

public class CartServiceImp implements CartService{
	
	private static CartServiceImp obj = null;
	private CartServiceImp() {};
	
	public static CartServiceImp getInstance() {
		if(obj==null) {
			obj = new CartServiceImp();
		}
		return obj;
	}

	@Override
	public void addUser(String mobileNumber) {
		DB.CartDbImp.getInstance().addUser(mobileNumber);
	}

	@Override
	public String getCartProducts(String mobileNumber) {
		ResultSet rs = CartDbImp.getInstance().cartProducts(mobileNumber);
		String product = "";
		try {
			if(rs.next()) {
				product = rs.getString(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return product;
	}

	@Override
	public void addProduct(String mobileNumber, String productName,String quantity) {
		String cartProducts = CartServiceImp.getInstance().getCartProducts(mobileNumber);
		String updateCartProducts = cartProducts + productName + "##" + quantity +"$$";
		CartDbImp.getInstance().updateCartProducts(mobileNumber, updateCartProducts);
	}

	@Override
	public void removeProduct(String mobileNumber, String productName) {
		String cartProducts = CartServiceImp.getInstance().getCartProducts(mobileNumber);
		String[] products = cartProducts.split("\\$\\$");
		String updateProducts = "";
		for(String str : products) {
			if(str!= "" && !str.startsWith(productName)) {
				updateProducts += str + "$$";
			}
		}
		CartDbImp.getInstance().updateCartProducts(mobileNumber, updateProducts);
	}
}
