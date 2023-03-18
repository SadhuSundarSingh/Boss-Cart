package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Service.CookieServiceImp;
import Service.DeliverymanServiceImp;
import Service.UserServiceImp;

/**
 * Servlet implementation class DeliverymanCookieCheck
 */
@WebServlet("/DeliverymanCookieCheckController")
public class DeliverymanCookieCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeliverymanCookieCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject obj = new JSONObject();
		Cookie cookie = CookieServiceImp.getInstance().getCookie(request,"deliveryManMobileNumber");
		if(cookie!=null) {
			String mobileNumber = cookie.getValue();
			JSONObject objj = DeliverymanServiceImp.getInstance().getPassword(mobileNumber);
			String password = objj.get("password").toString();
			obj.put("mobileNumber", mobileNumber);
			obj.put("password", password);
			obj.put("statusCode",200);
		}
		else {
			obj.put("statusCode",400);
		}
		response.getWriter().append(obj.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
