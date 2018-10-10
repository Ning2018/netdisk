package dao;

import java.util.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.orm.hibernate3.HibernateTemplate;
import dao.interfaces.DirectoryDAO;
import entity.DirInfo;
import entity.Directory;
import common.*;

public class DirectoryDAOImpl extends DAOSupport implements DirectoryDAO
{
	public DirectoryDAOImpl(HibernateTemplate template)
	{
		super(template);
	}


	public void delete(UserInfo userInfo, String path)
	{			
		System.out.println("in DirectoryDAO, "+userInfo+path);
		template.bulkUpdate("delete from Directory where user = ? and path = ?",new Object[]{userInfo.getCookieUser(), path});
		template.bulkUpdate("delete from Directory where user=? and parentPath like ?", new Object[]{userInfo.getCookieUser(), path + "%"});
	}

	public void save(Directory directory)
	{
		template.save(directory);

	}

	public List<DirInfo> getDirInfo(String user, String parentPath)
	{

		List<DirInfo> directories = template.findByNamedQueryAndNamedParam("myDirInfo",new String[]{"user", "parentPath"}, new Object[] {
				user, parentPath });
   System.out.println(user);
   System.out.println(parentPath);
   System.out.println(directories);
		return directories;
	}

}
