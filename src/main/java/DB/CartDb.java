package DB;

import java.sql.ResultSet;

public interface CartDb {
	public void addUser(String mobileNumber);
	public ResultSet cartProducts(String mobileNumber);
	public void updateCartProducts(String mobileNumber,String products);//add
}
