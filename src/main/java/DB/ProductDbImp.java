package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import Model.Product;
import Model.User;

public class ProductDbImp implements ProductDb{
	
	private static ProductDbImp obj = null;
	
	private ProductDbImp() {};
	
	public static ProductDbImp getInstance() {
		if(obj==null) {
			obj = new ProductDbImp();
		}
		return obj;
	}	

	@Override
	public ResultSet getAllProducts() {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select category,name,price,url from Product_Details");
			rs = stmt.executeQuery();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;		
	}

	@Override
	public ResultSet[] searchProducts(String searchWord) {
		ResultSet[] rs = new ResultSet[3];
		DbConnection.getDBConnection();
		try {
			PreparedStatement stmt1 = DbConnection.getDbConnection().prepareStatement("select name,price,url from Product_Details where name like ? order by (CASE WHEN name like ? then 0 ELSE 1 END)");
	        PreparedStatement stmt2 = DbConnection.getDbConnection().prepareStatement("select name,price,url from Product_Details where brand like ? and brand not like NULL order by (CASE WHEN name like ? then 0 ELSE 1 END)");
	        PreparedStatement stmt3 = DbConnection.getDbConnection().prepareStatement("select name,price,url from Product_Details where type like ?");
	        stmt1.setString(1, "%"+searchWord+"%");
	        stmt1.setString(2, searchWord+"%");
	        stmt2.setString(1, "%"+searchWord+"%");
	        stmt2.setString(2, searchWord+"%");
	        stmt3.setString(1, "%"+searchWord+"%");
	        ResultSet rs1 = stmt1.executeQuery();
	        ResultSet rs2 = stmt2.executeQuery();
	        ResultSet rs3 = stmt3.executeQuery();
	        rs[0] = rs1;
	        rs[1] = rs2;
	        rs[2] = rs3;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet getProductDetails(String productName) {
		DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.getDbConnection().prepareStatement("select * from Product_Details where name like ?");
			stmt.setString(1, productName);
			rs = stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public ResultSet getPriceAndQuantity(String productName) {
		DB.DbConnection.getDBConnection();
		ResultSet rs = null;
		try {
			PreparedStatement stmt = DbConnection.dbConnection.prepareStatement("select price,quantity from Product_Details where name = ?");
			stmt.setString(1, productName);
			rs=stmt.executeQuery();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	@Override
	public void updateProductQuantity(String productName, int quantity) {
		DB.DbConnection.getDBConnection();
		try {
			PreparedStatement stmt = DbConnection.dbConnection.prepareStatement("update Product_Details set quantity = ? where name = ?");
			stmt.setInt(1, quantity);
			stmt.setString(2, productName);
			stmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
