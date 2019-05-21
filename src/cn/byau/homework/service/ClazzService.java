package cn.byau.homework.service;

import java.sql.SQLException;
import java.util.List;

import cn.byau.homework.dao.ClazzDAO;
import cn.byau.homework.entity.Clazz;
import cn.byau.homework.utils.PageBean;

/**
 * 这是学生查询的业务实现
 * 
 * @author
 *
 */
public class ClazzService {

	ClazzDAO clazzDAO = new ClazzDAO();
	public List<Clazz> list() throws SQLException {
		return clazzDAO.list();
	}

	public Clazz getClazz(String clazzNo) throws SQLException {

		
		return clazzDAO.getClazz(clazzNo);
	}

	public boolean insert(Clazz clazz) throws SQLException {

		
		return clazzDAO.insert(clazz);
	}

	public boolean delete(String clazzNo) throws SQLException {
		
		return clazzDAO.delete(clazzNo);

	}

	public boolean update(Clazz clazz) throws SQLException {
		
		return clazzDAO.update(clazz);
	}

}
