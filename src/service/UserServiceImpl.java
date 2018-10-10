package service;

import entity.User;
import dao.interfaces.*;
import service.interfaces.*;
public class UserServiceImpl implements UserService
{
	private UserDAO userDAO;
	public UserServiceImpl(UserDAO userDAO)
	{		
		this.userDAO = userDAO;
	}    	
	public boolean verifyUser(User user)
	{
		
		String passwordMD5 = userDAO.getPasswordMD5(user);
		boolean result = false;
		try
		{
			result = (user.getPasswordMD5().equals(passwordMD5))?true:false;
		}
		catch(Exception e)
		{
			
		}
		return result;
	}

	public void addUser(User user) throws Exception
	{
		if (userDAO.exists(user))
		{
			throw new Exception("<" + user + ">ÒÑ¾­´æÔÚ£¡");
		}
		else
		{
			userDAO.save(user);
		}
		
	}
}
