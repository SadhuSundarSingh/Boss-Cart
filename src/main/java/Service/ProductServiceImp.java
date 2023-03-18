package Service;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import DB.ProductDbImp;
import Model.Product;

public class ProductServiceImp implements ProductService{
	
	private static ProductServiceImp obj = null;
	private ProductServiceImp() {};
	
	public static ProductServiceImp getInstance() {
		if(obj==null) {
			obj = new ProductServiceImp();
		}
		return obj;
	}	

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<JSONObject> getAllProducts() {
		ResultSet rs = ProductDbImp.getInstance().getAllProducts();
		ArrayList<JSONObject> al = new ArrayList<JSONObject>();
		try {
			while(rs.next()) {
				Product product = new Product();
				product.setCategory(rs.getString(1));
				product.setName(rs.getString(2));
				product.setPrice(rs.getInt(3));
				JSONObject obj = new JSONObject();
				obj.put("Category", product.getCategory());
				obj.put("Name", product.getName());
				obj.put("Price", product.getPrice());
				obj.put("url", rs.getString(4));
				al.add(obj);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return al;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> searchProducts(String searchWord) {
		ResultSet[] rs = ProductDbImp.getInstance().searchProducts(searchWord);
		ArrayList<String> products = new ArrayList<String>();
		ArrayList<String> productNames = new ArrayList<String>();
		try {
			while(rs[0].next()) {
				productNames.add(rs[0].getString(1));
	        	JSONObject jObj = new JSONObject();
	        	jObj.put("name", rs[0].getString(1));
	        	jObj.put("price", rs[0].getString(2));
	        	jObj.put("url", rs[0].getString(3));
	        	products.add(jObj.toString());
	        }
	        while(rs[1].next()) {
	        	if(!productNames.contains(rs[1].getString(1))) {
	        		productNames.add(rs[1].getString(1));
	        		JSONObject jObj = new JSONObject();
	            	jObj.put("name", rs[1].getString(1));
	            	jObj.put("price", rs[1].getString(2));
	            	jObj.put("url", rs[1].getString(3));
	            	products.add(jObj.toString());
	        	}
	        }
	        while(rs[2].next()) {
	        	if(!productNames.contains(rs[2].getString(1))) {
	            	JSONObject jObj = new JSONObject();
	            	jObj.put("name", rs[2].getString(1));
	            	jObj.put("price", rs[2].getString(2));
	            	jObj.put("url", rs[2].getString(3));
	            	products.add(jObj.toString());
	        	}
	        }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getProductDetails(String productName) {
		ResultSet rs = ProductDbImp.getInstance().getProductDetails(productName);
		JSONObject obj = new JSONObject();
		try {
	        if(rs.next()) {
	        	obj.put("url", rs.getString("url"));
	        	obj.put("Quantity", rs.getString("quantity"));
	        	obj.put("category", rs.getString(1));
	        	String cat = (String) obj.get("category");
	        	switch(cat) {
	        	case "GROCERY":
	            case "STATIONERY":
	            	obj.put("Name", rs.getString(3));
	        		obj.put("Price", rs.getString(4) + " Rs");
	        		obj.put("type", rs.getString(15));
	                break;
	            case "ELECTRONICS":
	            	obj.put("electronicType", rs.getString(2));
	                String et = (String) obj.get("electronicType");
	                switch (et) {
	                    case "WIRED":
	                    	obj.put("Name", rs.getString(3));
	                    	obj.put("Price", rs.getString(4) + " Rs");
	                		obj.put("Brand", rs.getString(6));
	                		obj.put("Model", rs.getString(7));
	                		obj.put("type", rs.getString(15));
	                        break;
	                    case "WIRELESS":
	                    	obj.put("Name", rs.getString(3));
	                    	obj.put("Price", rs.getString(4) + " Rs");
	                		obj.put("Brand", rs.getString(6));
	                		obj.put("Model", rs.getString(7));
	                		obj.put("Battery", rs.getString(8) + " mAh");
	                		obj.put("type", rs.getString(15));
	                        break;
	                    case "PORTABLE":
	                    	obj.put("Name", rs.getString(3));
	                    	obj.put("Price", rs.getString(4) + " Rs");
	                		obj.put("Brand", rs.getString(6));
	                		obj.put("Model", rs.getString(7));
	                		obj.put("Battery", rs.getString(8) + " mAh");
	                		obj.put("Ram", rs.getString(9) + " GB");
	                		obj.put("Storage", rs.getString(10) + " GB");
	                		obj.put("Camera", rs.getString(11) + " MP");
	                		obj.put("Screensize", rs.getString(12) + " inch");
	                		obj.put("type", rs.getString(15));
	                        break;
	                    case "ACCESSORY":
	                    	obj.put("Name", rs.getString(3));
	                    	obj.put("Price", rs.getString(4) + " Rs");
	                		obj.put("type", rs.getString(15));
	                        break;
	                }
	                break;
	            case "FASHION":
	            	obj.put("Name", rs.getString(3));
	            	obj.put("Price", rs.getString(4) + " Rs");
	        		obj.put("Brand", rs.getString(6));
	        		obj.put("Size", rs.getString(13) + " cm");
	        		obj.put("type", rs.getString(15));
	                break;
	            case "APPLIANCE":
	            	obj.put("Name", rs.getString(3));
	            	obj.put("Price", rs.getString(4) + " Rs");
	        		obj.put("Brand", rs.getString(6));
	        		obj.put("Warranty", rs.getString(14) + " year");
	        		obj.put("type", rs.getString(15));
	                break;
	            }
	        	obj.put("Rating",rs.getString(16) + " star");
	        }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	@Override
	public String getProductsPrice(String productName, String quantity) {
		ResultSet rs = ProductDbImp.getInstance().getPriceAndQuantity(productName);
		int availableQuantity = 0;
		int price = 0;
		String returnAns = null;
		try {
			if(rs.next()) {
				price = rs.getInt(1);
				availableQuantity = rs.getInt(2);
			}
			if(Integer.parseInt(quantity) > availableQuantity) {
				returnAns = "Currently unavailable";
			}
			else {
				returnAns = "" + (Integer.parseInt(quantity)*price);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return returnAns;
	}

	@Override
	public void updateProductQuantity(String productName, int quantity) {
		ProductDbImp.getInstance().updateProductQuantity(productName, quantity);
	}
}
