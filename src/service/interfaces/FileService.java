package service.interfaces;

import java.util.*;
import entity.*;
import common.*;
public interface FileService
{
    public void addFiles(UploadFile uploadFile) throws Exception;   
	public void deleteFile(UserInfo userInfo, String file);
	public long getUserDiskSize(String username);
	public List<File> getFiles(String username, String path);
	
}
