package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

import common.UserInfo;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ServiceManager;
import service.interfaces.*;
import entity.*;

public class RegisterAction extends BaseAction implements ModelDriven<User>
{
	private User user = new User();
	
	public User getUser() {
		System.out.println("in getUser(): "+user);
		return user;
	}

	public void setUser(User user) {
		System.out.println("in setUser(): "+user);
		this.user = user;
	}

	public User getModel()
	{
		System.out.println("in getModel(): "+user);
		return user;
	}

	public String execute() 
	{
		
		System.out.println("in RegisterAction..."+userInfo.getRoot());
		try
		{
			UserService userService = serviceManager.getUserService();
			
			userService.addUser(user);
			File dir = new File(userInfo.getRoot() + user.getUser());
			if(!dir.exists())
				dir.mkdir();
			//	result = "<" + user.getUser() + ">注册成功！";
			result = "success";
			return result;
		}
		catch (Exception e)
		{
			//	result = e.getMessage();
			System.out.println(e);
		}
		result= "error";
		return result;
	}
	@Override
	public void validate()
	{
		System.out.println(user);
		if("".equals(user.getValidationCode())) return;
		Object obj = ActionContext.getContext().getSession().get(
				"validation_code");
		
		String validationCode = (obj != null) ? obj.toString() : "";

		if (!validationCode.equalsIgnoreCase(user.getValidationCode()))
		{
			if (user.getValidationCode() != null)
			{				
				this.addFieldError("validationCode", "验证码输入错误!");
			}
		}
	}

}