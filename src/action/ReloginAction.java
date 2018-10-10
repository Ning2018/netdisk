package action;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.UserInfo;
import service.ServiceManager;

public class ReloginAction extends BaseAction
{

	public String execute() throws Exception
	{
		try
		{
			Cookie cookie = new Cookie("JSESSIONID", "");
			cookie.setMaxAge(0);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);			
			return SUCCESS;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}
}