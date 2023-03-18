package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Service.ProductServiceImp;

/**
 * Servlet implementation class SearchProductController
 */
@WebServlet("/user/SearchProductController")
public class SearchProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProductController() {
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
		String searchWord = request.getParameter("searchWord");
		JSONObject obj = new JSONObject();
		if(searchWord!=null) {
			ArrayList<String> searchProducts = ProductServiceImp.getInstance().searchProducts(searchWord);
			obj.put("product", searchProducts);
			obj.put("statusCode", 200);
		}
		else {
			obj.put("statusCode", 400);
		}
		response.getWriter().append(obj.toString());
	}

}
