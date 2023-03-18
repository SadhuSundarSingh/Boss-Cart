package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Service.CartServiceImp;
import Service.CookieServiceImp;
import Service.ProductServiceImp;
import Service.UserServiceImp;

/**
 * Servlet implementation class ProductDetailController
 */
@WebServlet("/user/ProductDetailController")
public class ProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String productName = request.getParameter("productName");
		Cookie cookie = CookieServiceImp.getInstance().getCookie(request, "mobileNumber");
		String mobileNumber = cookie.getValue();
		String cartProducts = CartServiceImp.getInstance().getCartProducts(mobileNumber);
		ArrayList<JSONObject> wishlist = UserServiceImp.getInstance().getWishlistProducts(mobileNumber);
		ArrayList<String> wishlistProducts = new ArrayList<String>();
		for(int i=0;i<wishlist.size();i++) {
			wishlistProducts.add(wishlist.get(i).get("name").toString());
		}
		JSONObject obj = null;
		obj = ProductServiceImp.getInstance().getProductDetails(productName);
		JSONArray historyProducts = UserServiceImp.getInstance().getHistoryProductname(mobileNumber);
		obj.put("history",historyProducts);
		obj.put("cartProducts", cartProducts);
		obj.put("wishlist",wishlistProducts);
		obj.put("mobileNumber", mobileNumber);
		response.getWriter().append(obj.toString());
	}

}
