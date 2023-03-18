package Service;

import java.io.BufferedReader;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import DB.ProductDbImp;
import DB.UserDbImp;
import Model.CartStatus;
import Model.User;

public class UserServiceImp implements UserService{
	
	private static UserServiceImp obj = null;
	private UserServiceImp() {};
	
	public static UserServiceImp getInstance() {
		if(obj==null) {
			obj = new UserServiceImp();
		}
		return obj;
	}	

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject loginUser(String mobileNumber, String password) {
		ResultSet rs = UserDbImp.getInstance().loginUser(mobileNumber,password);
		JSONObject objj = null;
		try {
			if((rs.next()) && (rs.getString(2).equals(password))) {
				JSONObject obj = new JSONObject();
				obj.put("mobileNumber", mobileNumber);
				obj.put("userName", rs.getString(1));
				obj.put("address", rs.getString(3));
				obj.put("statusCode", 200);
				objj = obj;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return objj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<JSONObject> getWishlistProducts(String mobileNumber) {
		ArrayList<JSONObject> al = new ArrayList<JSONObject>();
		ResultSet rs = UserDbImp.getInstance().getWishlist(mobileNumber);
		try {
			if(rs.next()) {
				String[] wishlistProduct = rs.getString(1).trim().split("\\$\\$");
				for(String str: wishlistProduct) {
					if(str!="") {
						JSONObject obj = new JSONObject();
						obj.put("name",str);
						String url = UserServiceImp.getInstance().getUrl(str);
						obj.put("url", url);
						al.add(obj);
					}
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return al;
	}

	@Override
	public boolean isNewMobileNumber(String mobileNumber) {
		ResultSet rs = UserDbImp.getInstance().isNewMobileNumber(mobileNumber);
		try {
			if(rs.next()) {
				return false;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return true;
	}

	@Override
	public void addNewUser(JSONObject obj) {
		User user = new User();
		user.setFirstName(obj.get("firstName").toString());
		user.setLastName(obj.get("lastName").toString());
		user.setAddress(obj.get("address").toString());
		user.setMobileNumber(obj.get("mobileNumber").toString());
		user.setUserName(obj.get("userName").toString());
		user.setPassword(obj.get("password").toString());
		UserDbImp.getInstance().insertUser(user);
	}

	@Override
	public String getUserPassword(String mobileNumber) {
		ResultSet rs = UserDbImp.getInstance().getUserPassword(mobileNumber);
		String password = "";
		try {	
			if(rs.next()) {
				password = rs.getString(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return password;
	}

	@Override
	public void addToWishlist(String mobileNumber, String productName) {
		ArrayList<JSONObject> wishlistProducts = UserServiceImp.getInstance().getWishlistProducts(mobileNumber);
		String[] arr = new String[wishlistProducts.size()];
		for(int i=0;i<arr.length;i++) {
			JSONObject jsonObj =  wishlistProducts.get(i);
			if(arr[i]!=null) {
				arr[i] = jsonObj.get("name").toString();
			}
		}
		
		String updateWishlistProducts = String.join("$$",arr) + "$$" + productName + "$$";
		UserDbImp.getInstance().updateWishlistProducts(mobileNumber, updateWishlistProducts);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void removeFromWishlist(String mobileNumber, String productName) {
		ArrayList<JSONObject> wishlistProducts = UserServiceImp.getInstance().getWishlistProducts(mobileNumber);
		String[] arr = new String[wishlistProducts.size()-1];
		for(int i=0;i<arr.length;i++) {
			JSONObject jsonObj =  wishlistProducts.get(i);
			if(arr[i]!= null && !arr[i].equals(productName)) {
				arr[i] = jsonObj.get("name").toString();
			}
		}
		//wishlistProducts.remove(productName);
		String updateWishlistProducts = String.join("$$",arr) + "$$";
		UserDbImp.getInstance().updateWishlistProducts(mobileNumber, updateWishlistProducts);
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getUserDetails(String mobileNumber) {
		ResultSet rs = UserDbImp.getInstance().getUserDetails(mobileNumber);
		JSONObject obj = new JSONObject();
		try {
			if(rs.next()) {
				obj.put("firstName", rs.getString(1));
				obj.put("lastName", rs.getString(2));
				obj.put("mobileNumber", rs.getString(3));
				obj.put("address", rs.getString(4));
				obj.put("userName", rs.getString(5));
				obj.put("password", rs.getString(6));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	@Override
	public boolean updateUserDetails(HttpServletResponse response,String details) {
		JSONObject obj = null;
		JSONParser parser = new JSONParser();
		try {
			obj = (JSONObject)parser.parse(details);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		String firstName = obj.get("firstName").toString();
		String lastName = obj.get("lastName").toString();
		String mobileNumber = obj.get("mobileNumber").toString();
		String address = obj.get("address").toString();
		String userName = obj.get("userName").toString();
		String password = obj.get("password").toString();
		String userMobileNumber = obj.get("userMobileNumber").toString();
		if((mobileNumber.equals(userMobileNumber)) || (UserServiceImp.getInstance().isNewMobileNumber(mobileNumber))) {
			UserDbImp.getInstance().updateUserDetails(firstName, lastName, mobileNumber, userName, password, address, userMobileNumber);
			CookieServiceImp.getInstance().addCookie("mobileNumber", mobileNumber, response);
			return true;
		}
		else {
			return false;	
		}
	}

	@Override
	public boolean buyProduct(HttpServletRequest request) {
		BufferedReader br = null;
		String str = "";
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		int quantity = 0;
		int availableQuantity = 0;
		ResultSet rs = null;
		try {
			br = request.getReader();
			str = br.readLine();
			obj = (JSONObject) parser.parse(str);
			quantity = Integer.parseInt(obj.get("quantity").toString());
			rs = ProductDbImp.getInstance().getPriceAndQuantity( obj.get("productName").toString());
			if(rs.next()) {
				availableQuantity = rs.getInt(2);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		if(availableQuantity<quantity) {
			return false;
		}
		else {
			int currentQuantity = availableQuantity - quantity;
			ProductServiceImp.getInstance().updateProductQuantity(obj.get("productName").toString(), currentQuantity);
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dateFormat.format(calendar.getTime());
			String productDetails = obj.get("productName").toString() + "$$" + obj.get("quantity").toString();
			String mobileNumber = DeliverymanServiceImp.getInstance().getMobileNumber(obj.get("address").toString());
			UserDbImp.getInstance().buyProduct(obj.get("mobileNumber").toString(), productDetails, obj.get("price").toString(), obj.get("address").toString(),mobileNumber,date,String.valueOf(CartStatus.NOT_DELIVERD));		
		}
		return true;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<JSONObject> getHistory(String mobileNumber) {
		ArrayList<JSONObject> orders = new ArrayList<JSONObject>();
		ResultSet rs = UserDbImp.getInstance().getHistory(mobileNumber);
	
		SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String deliveredDate = null;
		try {
			while(rs.next()) {
				JSONObject currProduct = new JSONObject();
				String[] nameAndQuantity = rs.getString(2).split("\\$\\$");
				currProduct.put("productName",nameAndQuantity[0]);
				String url = UserServiceImp.getInstance().getUrl(nameAndQuantity[0]);
				currProduct.put("url", url);
				currProduct.put("quantity", nameAndQuantity[1]);
				currProduct.put("amount",rs.getString(3));
				currProduct.put("orderPlace",rs.getString(4));
				currProduct.put("deliveryManMobileNumber",rs.getString(5));
				Date odate = inputDateFormat.parse(rs.getString(6));
				String orderDate = outputDateFormat.format(odate);
				currProduct.put("orderDate",orderDate);
				if(rs.getString(7)==null) {
					deliveredDate = "Coming soon!!";
				}
				else {
					Date ddate = inputDateFormat.parse(rs.getString(7));
					deliveredDate = outputDateFormat.format(ddate);
				}
				currProduct.put("deliveredDate",deliveredDate);
				currProduct.put("status",rs.getString(8));
				orders.add(currProduct);
			}
		}catch(Exception ex) {
				ex.printStackTrace();
			}
		return orders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getHistoryProductname(String mobileNumber) {
		ResultSet rs = UserDbImp.getInstance().getHistoryProductNames(mobileNumber);
		JSONArray jsonArray = new JSONArray();
		try {
			while(rs.next()) {
				JSONObject jsonArr = new JSONObject();
				String[] det = rs.getString(1).split("\\$\\$");
				jsonArr.put("productName", det[0]);
				jsonArr.put("status", rs.getString(2));
				jsonArray.add(jsonArr);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public void addComment(String mobileNumber, String comment, String productName) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(calendar.getTime());
		UserDbImp.getInstance().addComment(mobileNumber, comment, date, productName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<JSONObject> getComments(String productName) {
		 ArrayList<JSONObject> obj = new  ArrayList<JSONObject>();
		 ResultSet rs = UserDbImp.getInstance().getAllComments(productName);
		 SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		 try {
			 while(rs.next()) {
				 JSONObject curr = new JSONObject();
				 String userName = UserServiceImp.getInstance().getUserUserName(rs.getString(1));
				 String comment = rs.getString(2);
				 Date date = inputDateFormat.parse(rs.getString(3));
				 String commentDate = outputDateFormat.format(date);
				 curr.put("userName",userName);
				 curr.put("comment", comment);
				 curr.put("date", commentDate);
				 obj.add(curr);
			 }
		 }catch(Exception ex) {
			 ex.printStackTrace();
		 }
		return obj;
	}

	@Override
	public String getUserUserName(String mobileNumber) {
		ResultSet rs = UserDbImp.getInstance().getUserUserName(mobileNumber);
		String userName = null;
		try {
			if(rs.next()) {
				userName = rs.getString(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return userName;
	}

	@Override
	public String getUrl(String productName) {
		ResultSet rs = UserDbImp.getInstance().getUrl(productName);
		String url = null;
		try {
			if(rs.next()) {
				url = rs.getString(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return url;
	}
}
