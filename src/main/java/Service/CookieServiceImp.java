package Service;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServiceImp implements CookieService{
	
	private static CookieServiceImp obj = null;
	private CookieServiceImp() {};
	
	public static CookieServiceImp getInstance() {
		if(obj==null) {
			obj = new CookieServiceImp();
		}
		return obj;
	}

	@Override
	public void addCookie(String key, String value, HttpServletResponse response) {
		Cookie cookie = new Cookie(key,value);
		response.addCookie(cookie);
	}

	@Override
	public Cookie getCookie(HttpServletRequest request, String key) {
		Cookie[] cookieArr = request.getCookies();
		Cookie cookie = null;
		for(Cookie coo : cookieArr) {
			if((coo.getName().equals(key)) && (!coo.getValue().equals(""))) {
				cookie = coo;
				break;
			}
		}
		return cookie;
	}

	@Override
	public void removeCookie(HttpServletRequest request, HttpServletResponse response,String key) {
		Cookie[] cookies = request.getCookies();
		for(Cookie coo : cookies) {
			if(coo.getName().equals(key)) {
				Cookie updateCookie = new Cookie(key,"");
				response.addCookie(updateCookie);
				break;
			}
		}
	}	
}
