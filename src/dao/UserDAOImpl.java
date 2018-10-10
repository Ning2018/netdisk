package dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import dao.interfaces.UserDAO;
import entity.User;

public class UserDAOImpl extends DAOSupport implements UserDAO
{	
	public UserDAOImpl(HibernateTemplate template)
	{
		super(template);
	}

	public void save(User user)
	{
		template.save(user);		
	}
	
	public String getPasswordMD5(User user)
	{
		String hql = "select passwordMD5 from User where user = ?";

		java.util.List<String> passwordMD5 = template.find(hql, user.getUser());
		
		if(passwordMD5.size() > 0)
			return passwordMD5.get(0);
		return null;
	}

	public boolean exists(User user)
	{		
		return (getPasswordMD5(user) != null)?true:false;
	}



}
