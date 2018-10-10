package service;

import java.io.File;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;

import java.text.*;
import common.UserInfo;

import entity.*;
import dao.interfaces.*;
import service.interfaces.*;

public class DirectoryServiceImpl implements DirectoryService
{
	private DirectoryDAO directoryDAO;
	private FileDAO fileDAO;

	public DirectoryServiceImpl(DirectoryDAO directoryDAO, FileDAO fileDAO)
	{
		this.directoryDAO = directoryDAO;
		this.fileDAO = fileDAO;
	}

	public String addDirectory(UserInfo userInfo) throws Exception
	{
		String currentPath = userInfo.getParentPath()+ userInfo.getDir() + "/";
		
	//	currentPath = File.separator.equals("\\") ? currentPath.replaceAll("/",
	//			"\\\\") : currentPath;
		
		System.out.println(" in directory servie, addDirectory, parentPath is "+userInfo.getParentPath());
		System.out.println(" in directory servie, addDirectory, currentPath is "+currentPath);

		Directory directory = new Directory();
		directory.setUser(userInfo.getCookieUser());
		directory.setDir(userInfo.getDir());
		directory.setParentPath(userInfo.getParentPath());
	//	directory.setPath(userInfo.getParentPath() + userInfo.getDir() + File.separator);
		directory.setPath(currentPath);
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		userInfo.setTime(dateFormat.format(date));
 
		directory.setCreateTime(date);
		directoryDAO.save(directory);
		File dir = new File(currentPath);
		if (!dir.exists())
		{
			dir.mkdirs();
		}
	//	HttpSession session = (HttpSession) ActionContext.getContext().getSession();
	//	session.setAttribute("userroot", userInfo.getParentPath() );
		return "成功建立目录";

	}


	public List<DirInfo> getDirInfo(String user, String parentPath)
	{		
		System.out.println("in Directory Service, getDirInfo: "+parentPath);
		return directoryDAO.getDirInfo(user, parentPath);
	}
	public void deleteDirectory(UserInfo userInfo, String path)
	{
		directoryDAO.delete(userInfo, path);
		fileDAO.deleteFiles(userInfo, path);	
		common.MyFile.deleteAny(userInfo.getAbsolutePath(path));
	}
}
