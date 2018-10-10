package service.interfaces;

import java.util.*;
import entity.*;
import common.*;
public interface DirectoryService
{
    public String addDirectory(UserInfo userInfo) throws Exception;   
	public void deleteDirectory(UserInfo userInfo, String path);
	public List<DirInfo> getDirInfo(String user, String parentPath);
}
