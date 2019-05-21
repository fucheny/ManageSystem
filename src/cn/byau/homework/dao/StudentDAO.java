/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;

import cn.byau.homework.entity.Student;
import cn.byau.homework.entity.Homework;
import cn.byau.homework.utils.JDBCUtils;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("unused")
public class StudentDAO {
	private final Logger logger = Logger.getLogger(StudentDAO.class);
	public static int PAGE_SIZE = 10;

	/**
	 * 查询学生的作业
	 * 
	 * @throws SQLException
	 */

	public List<Homework> list() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

		String sql = "SELECT * FROM homeworkarray";
		return queryRunner.query(sql, new BeanListHandler<Homework>(Homework.class));
	}

	/**
	 * 查询所有的学生数据，用于导出
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Student> listAllStudent() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

		String sql = "SELECT * FROM student";
		return queryRunner.query(sql, new BeanListHandler<Student>(Student.class));
	}

	// 部分关键字查询
	public List<Homework> list(String name) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

		String sql = "SELECT * FROM homeworkarray where title like \"%" + name + "%\"";
		System.out.println("sql = " + sql);
		return queryRunner.query(sql, new BeanListHandler<Homework>(Homework.class));
	}

	/**
	 * 普通用户,管理自己班级的学生时使用
	 * 
	 * @param clazzNo
	 * @return
	 * @throws SQLException
	 */

	public List<Student> listByClazzNo(String clazzNo) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

		String sql = "SELECT no,name,age,birthday,s.clazzNo,clazzName "
				+ "FROM student s LEFT JOIN clazz c ON s.clazzNo=c.clazzNo WHERE s.clazzNo=?";
		return queryRunner.query(sql, new BeanListHandler<Student>(Student.class), clazzNo);
	}

	/**
	 * 批量添加学生，从Excel中导入数据使用
	 */

	public boolean insertBatch(List<Student> studentList) {
		logger.info("insertBatch方法入参{}" + studentList);
		String sql = "INSERT INTO student(" + "no,name,age,birthday,clazzNo" + " ) VALUES(?,?,?,?,?)";
		boolean insertSuccessFlag = false;
		try {
			QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
			// 得到连接对象
			Object[][] params = new Object[studentList.size()][];
			for (int i = 0; i < params.length; i++) {
				params[i] = new Object[] { studentList.get(i).getNo(), studentList.get(i).getName(),
						studentList.get(i).getAge(), studentList.get(i).getBirthday(),
						studentList.get(i).getClazzNo() };
			}
			runner.batch(sql, params);

			// 如果插入成功，则肯定能执行到此段代码
			insertSuccessFlag = true;

		} catch (SQLException e) {
			logger.warn("insertBatch方法出现错误" + e.getMessage());
		}
		return insertSuccessFlag;
	}

	public boolean insert(Student student) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());

		String sql = "INSERT INTO student(no,name,age,birthday,clazzNo) VALUES(?,?,?,?,?)";
		// 处理一下下拉框的问题
		return runner.update(sql, student.getNo(), student.getName(), student.getAge(), student.getBirthday(),
				student.getClazzNo()) == 1;
	}

	/**
	 * 根据学号删除学生
	 */
	public boolean delete(String no) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		String deleteString = "delete from student " + "where no=?";
		return runner.update(deleteString, no) == 1;
	}

	/**
	 * 批量删除学生
	 * 
	 * @param nos
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteBatchByNos(String[] nos) throws SQLException {
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		boolean successFlag = false;
		Object[][] params = new Object[nos.length][];// 高维也就是行数确定执行sql语句的次数，低维也就是列数是给？赋值
		for (int i = 0; i < params.length; i++) {// 循环行数,决定SQL语句执行的次数
			params[i] = new Object[] { nos[i] };// 给低维也就是列数“？”赋值，每行只给一列赋值，决定每条SQL语句的参数个数
		}

		runner.batch("DELETE FROM student WHERE no=?", params);
		// 如果插入成功，则肯定能执行到此段代码
		successFlag = true;
		return successFlag;
	}

	/**
	 * 根据学号查询学生
	 */

	public Student getStudent(String no) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT no,name,age,birthday,clazzNo " + "from student  where no=?";
		return queryRunner.query(sql, new BeanHandler<Student>(Student.class), no);
	}

	/**
	 * 更新学生信息
	 */

	public boolean update(Homework homework) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String updateString = "update homeworkarray set title=?, kecheng=?, oktime=? where float=?";
		int count = queryRunner.update(updateString, homework.getTitle(), homework.getKecheng(), homework.getOktime(),
				homework.getNo());
		return count == 1;
	}

	/**
	 * 根据页码查询学生信息
	 */

	public List<Student> listByPage(int currentPage, String no) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT no,name,age,birthday,s.clazzNo,clazzName "
				+ "FROM student s LEFT JOIN clazz c ON s.clazzNo=c.clazzNo WHERE s.no like ?" + " limit ? offset ?";
		// 第一个?号：需要查询多少条数据 第二个?号：跳过之前多少条记录
		// 第一页 --- 5 0 （currentPage-1）*5
		// 第二页 --- 5 5 （currentPage-1）*5
		// 第三页 --- 5 10 （currentPage-1）*5
		return queryRunner.query(sql, new BeanListHandler<Student>(Student.class), "%" + no + "%", PAGE_SIZE,
				(currentPage - 1) * PAGE_SIZE);
	}

	/**
	 * 查询有多少条记录
	 */

	// ScalarHandler 返回查询结果第一行 某一个字段的值
	public int count(String no) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT count(*)  FROM student s LEFT JOIN clazz c ON s.clazzNo=c.clazzNo WHERE s.no like ?";
		Long result = (Long) queryRunner.query(sql, new ScalarHandler(1), "%" + no + "%");
		return result.intValue();
	}

}
