package Service;

import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import DB.AdminDbImp;

public class AdminServiceImp implements AdminService {
	
	private static AdminServiceImp obj = null;
	
	private AdminServiceImp() {};
	
	public static AdminServiceImp getInstance() {
		if(obj==null) {
			obj = new AdminServiceImp();
		}
		return obj;
	}	

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getUserList() {
		JSONArray jsonArr = new JSONArray();
		ResultSet rs = AdminDbImp.getInstance().getUserList();
		try {
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("userName",rs.getString(1));
				obj.put("mobileNumber",rs.getString(2));
				jsonArr.add(obj);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return jsonArr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getProductList() {
		JSONArray jsonArr = new JSONArray();
		ResultSet rs = AdminDbImp.getInstance().getProductList();
		try {
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("productName",rs.getString(1));
				obj.put("quantity",rs.getString(2));
				obj.put("url", rs.getString(3));
				jsonArr.add(obj);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return jsonArr;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getDeliverymanList() {
		JSONArray jsonArr = new JSONArray();
		ResultSet rs = AdminDbImp.getInstance().getDeliverymanList();
		try {
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("deliverymanName",rs.getString(1));
				obj.put("mobileNumber",rs.getString(2));
				jsonArr.add(obj);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return jsonArr;
	}

	@Override
	public void addQuantity(String productName, String quantity) {
		int quantityy = 0;
		try {
			quantityy = Integer.parseInt(quantity);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		AdminDbImp.getInstance().addQuantity(productName, quantityy);
	}

	@Override
	public void addDeliveryman(String firstName, String lastName, String mobileNumber, String userName, String password,String adress) {
		AdminDbImp.getInstance().addDeliveryman(firstName, lastName, mobileNumber, userName, password, adress);
	}

	@Override
	public boolean isNewNumber(String mobileNumber) {
		ResultSet rs = AdminDbImp.getInstance().isNewNumber(mobileNumber);
		boolean ans  = true;
		try {
			if(rs.next()) {
				ans = false;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ans;
	}

	@Override
	public void deleteProduct(String productName) {
		AdminDbImp.getInstance().deleteProduct(productName);
	}
}
