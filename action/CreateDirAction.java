package action;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ModelDriven;

import service.ServiceManager;
import service.interfaces.*;
import common.*;

public class CreateDirAction extends BaseAction implements
		ModelDriven<UserInfo>
{
	public UserInfo getModel()
	{
		return userInfo;
	}
	public String execute() throws Exception
	{
		//	userInfo.setCookieUser(getCookieValue("JSESSIONID"));
		//	userInfo.setUserRoot(userInfo.getRoot() + userInfo.getCookieUser());
		try
		{
			DirectoryService directoryService = serviceManager
					.getDirectoryService();
			setResult(directoryService.addDirectory(userInfo));
		//	 HttpSession session = request.getSession();
		//	 session.setAttribute("userroot", userInfo.getParentPath() );
			return SUCCESS;
		}
		catch (Exception e)
		{
			setResult("½¨Á¢Ä¿Â¼Ê§°Ü");
		}
		return ERROR;
	}

}