package dao;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import common.UserInfo;
import dao.interfaces.*;
import entity.*;

public class FileDAOImpl extends DAOSupport implements FileDAO
{

	public FileDAOImpl(HibernateTemplate template)
	{
		super(template);
	}

	public void deleteFiles(UserInfo userInfo, String path)
	{
		template.bulkUpdate("delete from File where user=? and path like ?",
				new Object[]
				{ userInfo.getCookieUser(), path });
	}

	public void delete(UserInfo userInfo, String file)
	{
		template.bulkUpdate("delete from File where user = ? and concat(path, file) = ?",
				new Object[]
				{ userInfo.getCookieUser(), file });
	}

	public void save(File file)
	{
		template.save(file);

	}

	public List<File> getFiles(String username, String path)
	{
		return template
				.findByNamedParam(
						"from File where user = :user and path = :path order by uploadTime",
						new String[]
						{ "user", "path" }, new Object[]
						{ username, path });

	}

	public long getUserDiskSize(String username)
	{
	
		List<Long> fileSize = template.find("select sum(size) from File where user = ?", username);
		if(fileSize.size() > 0)
			return fileSize.get(0);
		return 0;
	}
}
