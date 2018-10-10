package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import common.*;

import dao.interfaces.*;
import service.interfaces.*;

public class FileServiceImpl implements FileService
{
	private FileDAO fileDAO;

	public FileServiceImpl(FileDAO fileDAO)
	{
		this.fileDAO = fileDAO;
	}

	private String saveFile(File uploadFile, String fn) throws Exception
	{
		File file = new File(fn);
		int index = 0;
		String filename = file.getName();
		while (file.exists())
		{
			int extIndex = filename.lastIndexOf(".");

			if (extIndex > 0)
				fn = filename.substring(0, extIndex) + "("
						+ String.valueOf(index) + ")"
						+ filename.substring(extIndex);
			else
				fn = filename + "(" + String.valueOf(index) + ")";
			fn = file.getPath().substring(0,
					file.getPath().lastIndexOf(file.getName()))
					+ fn;
			file = new File(fn);
			index++;
		}
		FileOutputStream fos = new FileOutputStream(fn);
		InputStream is = new java.io.FileInputStream(uploadFile);
		byte[] buffer = new byte[8192];
		int count = 0;
		while ((count = is.read(buffer)) > 0)
		{
			fos.write(buffer, 0, count);
		}
		fos.close();
		is.close();
		return file.getName();

	}
			
	
	public void addFiles(UploadFile uploadFile) throws Exception
	{
		int i = 0;			
		for(File f: uploadFile.getUpload())
		{			
		//	String currentPath = uploadFile.getUserInfo().getUserRoot()
		//			+ (File.separator.equals("\\") ? uploadFile.getUploadPath().replaceAll("/",
		//					"\\\\") : uploadFile.getUploadPath());
			String currentPath = uploadFile.getUploadPath();
			String fn = saveFile(f, currentPath + uploadFile.getUploadFileName().get(i));
			entity.File file = new entity.File();
			file.setUser(uploadFile.getUserInfo().getCookieUser());
			file.setFile(new File(fn).getName());
			file.setPath(uploadFile.getUploadPath());
			file.setSize(f.length());
			file.setUploadTime(new java.util.Date());
			fileDAO.save(file);			
			i++;
		}

	}
 
	public void deleteFile(UserInfo userInfo, String file)
	{
		fileDAO.delete(userInfo, file);	
		common.MyFile.deleteAny(userInfo.getAbsolutePath(file));
	}


	public List<entity.File> getFiles(String username, String path)
	{		
		return fileDAO.getFiles(username, path);
	}
 
	public long getUserDiskSize(String username)
	{		
		return fileDAO.getUserDiskSize(username);
	}
}
