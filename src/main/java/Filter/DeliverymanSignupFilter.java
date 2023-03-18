package Filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import org.json.simple.JSONObject;

import Service.AdminServiceImp;

/**
 * Servlet Filter implementation class DeliverymanSignupFilter
 */
@WebFilter("/admin/AddDeliverymanController")
public class DeliverymanSignupFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public DeliverymanSignupFilter() {
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
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String mobileNumber = request.getParameter("mobileNumber");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		JSONObject obj = new JSONObject();
		
		String nameRx = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
		Pattern firstNamePattern=Pattern.compile(nameRx);
		Matcher firstNameMatcher=firstNamePattern.matcher(firstName);

		Pattern lastNamePattern=Pattern.compile(nameRx);
		Matcher lastNameMatcher=lastNamePattern.matcher(lastName);
		
		String userNameRx = "^[a-zA-Z_]+$";
		Pattern userNamePattern=Pattern.compile(userNameRx);
		Matcher userNameMatcher=userNamePattern.matcher(userName);
		
		String phoneNumberRx = "^[6789]\\d{9}$";
		Pattern mobileNumberPattern=Pattern.compile(phoneNumberRx);
		Matcher mobileNumberMatcher=mobileNumberPattern.matcher(mobileNumber);
		
//		String passwordRx = "/^(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$/";
//		Pattern passwordPattern=Pattern.compile(passwordRx);
//		Matcher passwordMatcher=passwordPattern.matcher(password);
		
		if(!firstNameMatcher.find()) {
			obj.put("statusCode", 400);
			obj.put("message","Please enter valid first name");
			response.getWriter().append(obj.toString());
		}
		else if(!lastNameMatcher.find()) {
			obj.put("statusCode", 400);
			obj.put("message","Please enter valid last name");
			response.getWriter().append(obj.toString());
		}
		else if(!mobileNumberMatcher.find()) {
			obj.put("statusCode", 400);
			obj.put("message","Please enter valid mobile number");
			response.getWriter().append(obj.toString());
		}
		else if(!userNameMatcher.find()) {
			obj.put("statusCode", 400);
			obj.put("message","Please enter valid user name");
			response.getWriter().append(obj.toString());
		}
//		else if(!passwordMatcher.find()) {
//			obj.put("statusCode", 400);
//			obj.put("message","Please enter strong password");
//		}
		else if((!password.equals(confirmPassword))) {
			obj.put("statusCode", 400);
			obj.put("message","Incorrect confirm password");
			response.getWriter().append(obj.toString());
		}
		else {
			if(AdminServiceImp.getInstance().isNewNumber(mobileNumber)) {
				chain.doFilter(request, response);
			}
			else {
				obj.put("statusCode", 400);
				obj.put("message","This mobile number already exist");
				response.getWriter().append(obj.toString());
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
