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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;


/**
 * Servlet Filter implementation class UserCookieFilter
 */
@WebFilter("/user/*")
public class UserCookieFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public UserCookieFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		Cookie[] cookies = req.getCookies();
		DB.DbConnection.getDBConnection();
        Connection cn = DB.DbConnection.dbConnection;
		for(Cookie coo:cookies) {
			if(coo.getName().equals("mobileNumber") && coo.getValue()!=null) {
				try {
					PreparedStatement ps = cn.prepareStatement("select * from Customer_Details where mobileNumber = ?");
					ps.setString(1,coo.getValue());
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						chain.doFilter(request, response);
					}
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
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
