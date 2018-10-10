package dao.interfaces;

import java.util.List;
import common.*;
import entity.*;

public interface DirectoryDAO
{
    public void save(Directory directory);
	public void delete(UserInfo userInfo, String path);	
	public List<DirInfo> getDirInfo(String user, String parentPath);
}
