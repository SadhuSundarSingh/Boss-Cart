package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import org.json.simple.JSONObject;

import Service.DeliverymanServiceImp;
import Service.UserServiceImp;

/**
 * Servlet Filter implementation class DeliverymanSigninFilter
 */
@WebFilter("/DeliverymanSigninController")
public class DeliverymanSigninFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public DeliverymanSigninFilter() {
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
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		JSONObject user = DeliverymanServiceImp.getInstance().getPassword(mobileNumber);
		JSONObject obj = new JSONObject();
		if(user.get("statusCode").equals("200")) {
			if(user.get("password").toString().equals(password)) {
				chain.doFilter(request, response);
			}
			else {
				obj.put("statusCode", 400);
				response.getWriter().append(obj.toString());
			}
		}
		else {
			obj.put("statusCode", 500);
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
