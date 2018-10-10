package action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ModelDriven;
import common.UploadFile;
import common.UserInfo;
import service.ServiceManager;
import service.interfaces.*;

public class UploadAction extends BaseAction implements ModelDriven<UploadFile>
{
	private UploadFile uploadFile = new UploadFile();
    
	public UploadFile getModel()
	{		
		return uploadFile;
	}

	public String execute() throws Exception
	{	
		System.out.println("in upload action,");
		try
		{
			uploadFile.setUserInfo(userInfo);
			FileService fileService = serviceManager.getFileService();
			fileService.addFiles(uploadFile);
			return SUCCESS;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return ERROR;
	}
}