package entity;
import java.util.*;
public class Directory
{
    private int id;
    private String user;
    private String path;
    private String parentPath;
    private String dir;
    private Date createTime;
	/**
	 * id属性的getter方法
	 */
	public int getId()
	{
		return id;
	}
	/**
	 * id属性的setter方法
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	/**
	 * user属性的getter方法
	 */
	public String getUser()
	{
		return user;
	}
	/**
	 * user属性的setter方法
	 */
	public void setUser(String user)
	{
		this.user = user;
	}
	/**
	 * path属性的getter方法
	 */
	public String getPath()
	{
		return path;
	}
	/**
	 * path属性的setter方法
	 */
	public void setPath(String path)
	{
		this.path = path;
	}
	/**
	 * parentPath属性的getter方法
	 */
	public String getParentPath()
	{
		return parentPath;
	}
	/**
	 * parentPath属性的setter方法
	 */
	public void setParentPath(String parentPath)
	{
		this.parentPath = parentPath;
	}
	/**
	 * dir属性的getter方法
	 */
	public String getDir()
	{
		return dir;
	}
	/**
	 * dir属性的setter方法
	 */
	public void setDir(String dir)
	{
		this.dir = dir;
	}
	/**
	 * createTime属性的getter方法
	 */
	public Date getCreateTime()
	{
		return createTime;
	}
	/**
	 * createTime属性的setter方法
	 */
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
}
