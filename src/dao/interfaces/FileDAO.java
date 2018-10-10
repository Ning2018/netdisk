package dao.interfaces;

import java.util.*;
import entity.*;
import common.*;

public interface FileDAO
{
    public void save(File file);
	public void deleteFiles(UserInfo userInfo, String path);
	public void delete(UserInfo userInfo, String file);
	public long getUserDiskSize(String username);
	public List<File> getFiles(String username, String path);
	
} 
