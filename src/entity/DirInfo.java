package entity;

import java.text.SimpleDateFormat;
import java.util.*;

public class DirInfo
{
	private String user;
	private String path;
	private String parentPath;
	private String dir;
	private Date createTime;
	private String time;
	private int count;
	private long size;

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

	/**
	 * count属性的getter方法
	 */
	public int getCount()
	{
		return count;
	}

	/**
	 * count属性的setter方法
	 */
	public void setCount(int count)
	{
		this.count = count;
	}

	/**
	 * size属性的getter方法
	 */
	public long getSize()
	{
		return size;
	}

	/**
	 * size属性的setter方法
	 */
	public void setSize(long size)
	{
		this.size = size;
	}

	/**
	 * time属性的getter方法
	 */
	public String getTime()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");		
		return dateFormat.format(createTime);
	}

}
