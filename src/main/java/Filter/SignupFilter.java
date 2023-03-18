package Filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import org.json.simple.JSONObject;

import Service.UserServiceImp;


/**
 * Servlet Filter implementation class SignupFilter
 */
@SuppressWarnings("serial")
@WebFilter("/SignupController")
public class SignupFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public SignupFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@SuppressWarnings("unchecked")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String mobileNumber = request.getParameter("mobNumber");
		String address = request.getParameter("address");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		JSONObject obj = new JSONObject();
		DB.DbConnection.getDBConnection();
		if((firstName!="") && (lastName!="") && (mobileNumber!="") && (userName!="") && (password!="") && (address!="")){
			try {
	            if(UserServiceImp.getInstance().isNewMobileNumber(mobileNumber)) {
	            	chain.doFilter(request, response);
	            }
	            else {
	            	obj.put("statusCode", 400);
	    			obj.put("message", "this number already exist!!");
	    			response.getWriter().append(obj.toString());
				}
			}
			catch(Exception ex) {
				System.out.println(ex);
			}
		}
		else {
			obj.put("statusCode", 400);
			obj.put("response","Please enter valid input");
			response.getWriter().append(obj.toString());
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
