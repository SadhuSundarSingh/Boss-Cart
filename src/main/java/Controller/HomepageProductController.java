package Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import DB.ProductDbImp;
import DB.UserDbImp;
import Model.Product;
import Model.User;
import Service.CartServiceImp;
import Service.ProductServiceImp;
import Service.UserServiceImp;

/**
 * Servlet implementation class HomepageProductController
 */
@WebServlet("/user/HomepageProductController")
public class HomepageProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomepageProductController() {
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
		String mobileNumber = request.getParameter("mobileNumber");
		ArrayList<JSONObject> wishlistProducts = UserServiceImp.getInstance().getWishlistProducts(mobileNumber);
		ArrayList<String> wishlistProductNames = new ArrayList<String>();
		for(int i=0;i<wishlistProducts.size();i++) {
			wishlistProductNames.add(wishlistProducts.get(i).get("name").toString());
		}
		ArrayList<JSONObject> allProducts = ProductServiceImp.getInstance().getAllProducts();
		JSONObject obj = new JSONObject();
		String cartProducts = CartServiceImp.getInstance().getCartProducts(mobileNumber);

		obj.put("cartProducts",cartProducts);
		obj.put("allProducts",allProducts);
		obj.put("wishlistProducts", wishlistProductNames);
		obj.put("mobileNumber", mobileNumber);
		response.getWriter().append(obj.toString());
	}

}
