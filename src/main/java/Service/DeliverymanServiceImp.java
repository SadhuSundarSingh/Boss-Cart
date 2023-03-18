package Service;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONObject;

import DB.DeliverymanDbImp;


public class DeliverymanServiceImp implements DeliverymanService {
	
	private static DeliverymanServiceImp obj = null;
	private DeliverymanServiceImp() {};
	
	public static DeliverymanServiceImp getInstance() {
		if(obj==null) {
			obj = new DeliverymanServiceImp();
		}
		return obj;
	}

	@Override
	public String getMobileNumber(String address) {
		ResultSet rs = DeliverymanDbImp.getInstance().getMobileNumber(address);
		String mobileNumber = null;
		try {
			if(rs.next()) {
				mobileNumber = rs.getString(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return mobileNumber;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getPassword(String mobileNumber) {
		JSONObject obj = new JSONObject();
		ResultSet rs = DeliverymanDbImp.getInstance().loginMan(mobileNumber);
		try {
			if(rs.next()) {
				obj.put("statusCode", "200");
				obj.put("mobileNumber", mobileNumber);
				obj.put("password",rs.getString(1));
			}
			else {
				obj.put("statusCode", "400");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	@Override
	public String getUserName(String mobileNumber) {
		ResultSet rs = DeliverymanDbImp.getInstance().getUserName(mobileNumber);
		String userName = null;
		try {
			if(rs.next()) {
				mobileNumber = rs.getString(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return userName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getWork(String mobileNumber) {
		JSONObject obj = new JSONObject();
		ArrayList<JSONObject> al = new ArrayList<JSONObject>();
		ResultSet rs = DeliverymanDbImp.getInstance().getWork(mobileNumber);
		SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			while(rs.next()) {
				JSONObject curr = new JSONObject();
				curr.put("userMobileNumber", rs.getString(1));
				String[] productDet = rs.getString(2).split("\\$\\$");
				curr.put("productName", productDet[0]);
				curr.put("quantity", productDet[1]);
				curr.put("amount", rs.getString(3));
				curr.put("orderPlace",rs.getString(4));
				Date date = inputDateFormat.parse(rs.getString(5));
				String orderDate = outputDateFormat.format(date);
				curr.put("orderDate",orderDate);
				curr.put("orderId", rs.getInt(6));
				al.add(curr);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		obj.put("workList", al);
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getHistory(String mobileNumber) {
		JSONObject obj = new JSONObject();
		ArrayList<JSONObject> al = new ArrayList<JSONObject>();
		ResultSet rs = DeliverymanDbImp.getInstance().getHistory(mobileNumber);
		SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			while(rs.next()) {
				JSONObject curr = new JSONObject();
				curr.put("userMobileNumber", rs.getString(1));
				String[] productDet = rs.getString(2).split("\\$\\$");
				curr.put("productName", productDet[0]);
				curr.put("quantity", productDet[1]);
				curr.put("amount", rs.getString(3));
				curr.put("orderPlace",rs.getString(4));
				Date Odate = inputDateFormat.parse(rs.getString(6));
				String orderDate = outputDateFormat.format(Odate);
				curr.put("orderDate",orderDate);
				Date Ddate = inputDateFormat.parse(rs.getString(7));
				String deliveredDate = outputDateFormat.format(Ddate);
				curr.put("deliveredDate",deliveredDate);
				/**curr.put("status",rs.getString(8));**/
				curr.put("orderId", rs.getInt(9));
				al.add(curr);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		obj.put("history", al);
		return obj;	
	}

	@Override
	public void delivered(String orderId) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(calendar.getTime());
		int id = 0;
		try {
			id = Integer.parseInt(orderId);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		DeliverymanDbImp.getInstance().delivered(date,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getProfile(String mobileNumber) {
		JSONObject obj = new JSONObject();
		ResultSet rs = DeliverymanDbImp.getInstance().getProfile(mobileNumber);
		try {
			if(rs.next()) {
				obj.put("firstName", rs.getString(1));
				obj.put("lastName", rs.getString(2));
				obj.put("mobileNumber", rs.getString(3));
				obj.put("userName", rs.getString(4));
				obj.put("password", rs.getString(5));
				obj.put("address", rs.getString(6));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return obj;
	}

}
