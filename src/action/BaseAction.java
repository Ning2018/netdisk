package action;

import com.opensymphony.xwork2.*;
import java.util.*;
import javax.servlet.http.*;

import common.*;
import service.*;

public class BaseAction extends ActionSupport implements
		org.apache.struts2.interceptor.ServletRequestAware,
		org.apache.struts2.interceptor.ServletResponseAware
{

	protected ServiceManager serviceManager;
	protected UserInfo userInfo;
	protected String result;	
	protected Map<String, String> cookies;
	protected javax.servlet.http.HttpServletResponse response;
	protected javax.servlet.http.HttpServletRequest request;


	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;

	}

	protected String getCookieValue(String name)
	{
		javax.servlet.http.Cookie cookies[] = request.getCookies();
		if (cookies != null)
		{
			for (Cookie cookie : cookies)
			{

				if (!cookie.getName().equals(name))
					continue;
				return cookie.getValue();
			}

		}
		return null;
	}

	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;

		System.out.println("in BaseAction setServletRequest: "+request);
		userInfo.setCookieUser(getCookieValue("user"));
		userInfo.setUserRoot(userInfo.getRoot() + userInfo.getCookieUser()+"/");
		System.out.println("in BaseAction setServletRequest: "+userInfo);
	}

	/**
	 * result属性的getter方法
	 */
	public String getResult()
	{
		return result;
	}


	public void setResult(String result)
	{
		this.result = result;

	}

	public void setServiceManager(ServiceManager serviceManager)
	{
		this.serviceManager = serviceManager;
		System.out.println("in BaseAction: setServiceManager,"+serviceManager);
	}

	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	/**
	 * userInfo属性的setter方法
	 */
	public void setUserInfo(UserInfo userInfo)
	{
		this.userInfo = userInfo;
		System.out.println("in BaseAction: setUserInfo,"+userInfo);

	}
	

	protected void saveCookie(String name, String value, int maxAge)
	{
		javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(name,
				value);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

}