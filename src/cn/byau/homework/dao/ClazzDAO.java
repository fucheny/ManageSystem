/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import cn.byau.homework.entity.Clazz;
import cn.byau.homework.utils.JDBCUtils;

/**
 *
 * @author Administrator
 */
public class ClazzDAO {


	/**
	 * 查询所有学生
	 * 
	 * @throws SQLException
	 */

	public List<Clazz> list() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

		String sql = "select clazzNo,clazzName from clazz order by clazzNo";
		return queryRunner.query(sql, new BeanListHandler<Clazz>(Clazz.class));
	}

	/**
	 * 添加学生
	 */

	public boolean insert(Clazz clazz) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

		String sql = "INSERT INTO clazz(" + "clazzNo,clazzName" + " ) VALUES(?,?)";
		return runner.update(sql, clazz.getClazzNo(), clazz.getClazzName()) == 1;
	}

	/**
	 * 根据学号删除学生
	 */

	public boolean delete(String clazzNo) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String deleteString = "delete from clazz " + "where clazzNo=?";
		return runner.update(deleteString, clazzNo) == 1;
	}

	/**
	 * 根据学号查询学生
	 */

	public Clazz getClazz(String clazzNo) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select clazzNo,clazzName " + "from clazz where clazzNo=?";
		return queryRunner.query(sql, new BeanHandler<Clazz>(Clazz.class), clazzNo);
	}

	/**
	 * 更新学生信息
	 */

	public boolean update(Clazz clazz) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String updateString = "update clazz set clazzName=? where clazzNo=?";
		return queryRunner.update(updateString,
				clazz.getClazzName(),
				clazz.getClazzNo()) == 1;
	}

}
