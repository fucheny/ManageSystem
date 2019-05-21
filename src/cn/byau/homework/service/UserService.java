package cn.byau.homework.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import cn.byau.homework.dao.UserDAO;
import cn.byau.homework.entity.User;
import cn.byau.homework.utils.JDBCUtils;

/**
 * 这是学生查询的业务实现
 * 
 * @author
 *
 */
public class UserService {
	UserDAO userDAO = new UserDAO();

	public List<User> list() throws SQLException {

		List<User> userList = userDAO.list();

		return userList;

	}

	public User login(String userID, String password) throws SQLException, NoSuchAlgorithmException {

		return userDAO.getUserByUserIDAndPassword(userID, password);

	}

//	public User getUser(String userID) throws SQLException {
//
//		return userDAO.getUser(userID);
//	}
//
//	public boolean insert(User user) throws SQLException {
//
//		return userDAO.insert(user);
//	}
//
//	public boolean delete(String no) throws SQLException {
//
//		return userDAO.delete(no);
//
//	}
//
//	public boolean update(User user) throws SQLException {
//
//		return userDAO.update(user);
//	}

	public boolean updatePassword(User user) throws SQLException {

		return userDAO.updatePassword(user);
	}

	public boolean updatePhoto(String userID, String photo) throws SQLException {

		return userDAO.updatePhoto(userID, photo);
	}

}
