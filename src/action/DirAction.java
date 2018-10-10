package action;

import com.opensymphony.xwork2.ModelDriven;
import common.UserInfo;
import entity.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ServiceManager;
import service.interfaces.*;

public class DirAction extends BaseAction implements ModelDriven<UserInfo>
{

	private List<DirInfo> dirInfo;



	/**
	 * dirInfo属性的getter方法
	 */
	public List<DirInfo> getDirInfo()
	{
		return dirInfo;
	}

	/**
	 * dirInfo属性的setter方法
	 */
	public void setDirInfo(List<DirInfo> dirInfo)
	{
		this.dirInfo = dirInfo;
	}

	public UserInfo getModel()
	{

		return userInfo;
	}

	public String execute() throws Exception
	{
		
		try
		{
			System.out.println(" in DirAction, parentPath: "+userInfo.getParentPath());
			DirectoryService directoryService = serviceManager
					.getDirectoryService();
			dirInfo = directoryService.getDirInfo(userInfo.getCookieUser(),
					userInfo.getParentPath());
			System.out.println("in DirAction,"+dirInfo);
			return SUCCESS;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return ERROR;
	}
}