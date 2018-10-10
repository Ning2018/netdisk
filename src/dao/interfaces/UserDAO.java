package dao.interfaces;

import entity.User;

public interface UserDAO
{
    public void save(User user);
    public boolean exists(User user);
	public String getPasswordMD5(User user);
}
