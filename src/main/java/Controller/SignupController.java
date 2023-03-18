package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import Service.CartServiceImp;
import Service.UserServiceImp;

/**
 * Servlet implementation class SignupController
 */
@WebServlet("/SignupController")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupController() {
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
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String mobileNumber = request.getParameter("mobNumber");
		String address = request.getParameter("address");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		JSONObject obj = new JSONObject();
		JSONObject userDetails = new JSONObject();
		
		obj.put("statusCode", 200);
		userDetails.put("firstName", firstName);
		userDetails.put("lastName", lastName);
		userDetails.put("mobileNumber",mobileNumber);
		userDetails.put("address",address);
		userDetails.put("userName",userName);
		userDetails.put("password",password);
		UserServiceImp.getInstance().addNewUser(userDetails);
		CartServiceImp.getInstance().addUser(mobileNumber);
		obj.put("mobileNumber", mobileNumber);
		obj.put("password", password);
		response.getWriter().append(obj.toString());
	}	

}
