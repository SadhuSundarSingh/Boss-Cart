package Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieService {
	public void addCookie(String key , String value,HttpServletResponse response);
	public Cookie getCookie(HttpServletRequest request ,String key);
	public void removeCookie(HttpServletRequest request, HttpServletResponse response,String key);
}
