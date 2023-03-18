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

import Service.CookieServiceImp;
import Service.UserServiceImp;

/**
 * Servlet Filter implementation class SigninFilter
 */
@WebFilter("/LoginController")
public class SigninFilter extends HttpFilter implements Filter {
       
    private static final long serialVersionUID = 5953826494333396002L;

	/**
     * @see HttpFilter#HttpFilter()
     */
    public SigninFilter() {
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
		System.out.println("LoginFilter");
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		JSONObject user = UserServiceImp.getInstance().loginUser(mobileNumber, password);
		JSONObject obj = new JSONObject();
		if(user!=null) {
			chain.doFilter(request, response);
		}
		else {
			obj.put("statusCode", 400);
			response.getWriter().append(obj.toString());
		}
		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
