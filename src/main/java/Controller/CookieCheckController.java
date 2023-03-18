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
import Service.UserServiceImp;

/**
 * Servlet implementation class CookieCheckController
 */
@WebServlet("/CookieCheckController")
public class CookieCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieCheckController() {
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
		JSONObject obj = new JSONObject();
		Cookie cookie = CookieServiceImp.getInstance().getCookie(request,"mobileNumber");
		if(cookie!=null) {
			String mobileNumber = cookie.getValue();
			String password = UserServiceImp.getInstance().getUserPassword(mobileNumber);
			obj.put("mobileNumber", mobileNumber);
			obj.put("password", password);
			obj.put("statusCode",200);
		}
		else {
			obj.put("statusCode",400);
		}
		response.getWriter().append(obj.toString());
	}

}
