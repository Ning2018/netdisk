package test;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import common.UserInfo;

import entity.*;
import dao.interfaces.*;
import service.interfaces.*;
import service.*;
public class Test
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		ApplicationContext context = new FileSystemXmlApplicationContext(
				"WebRoot\\WEB-INF\\applicationContext.xml");
		ServiceManager sm = (ServiceManager) context.getBean("serviceManager");
		DirectoryService directoryService = sm.getDirectoryService();
		UserInfo userInfo = new UserInfo();
		userInfo.setCookieUser("abced");
		userInfo.setDir("fdfd");
		userInfo.setParentPath("sdfdfsfd");
		userInfo.setUserRoot("ffdfd");
 
		
		try
		{
			directoryService.addDirectory(userInfo);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//System.out.println(userService.verifyUser(user));
		// System.out.println(userDAO)
	}

}
