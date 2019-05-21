package cn.byau.homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import cn.byau.homework.utils.JDBCUtils;

public class Test {

	public static void main(String[] args) {
		createHomeworkDate();
	}

	/**
	 * 制造作业数据， 数据数据的内容是 作业标题、课程、布置时间
	 */
	public static void createHomeworkDate() {
		int kk = 1;
		String[] titles = { "课后作业", "强化学习", "自我提升", "具体实现" };
		String[] courses = { "数据结构", "Java", "高等数学", "线性代数", "操作系统", "计算机网络" };

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// 获取当前到1970年的秒数， 这个样子时间变化的幅度大
		long times = System.currentTimeMillis() / 1000;

		String sql = "INSERT INTO homeworkarray values(?, ?, ?, ?)";
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement pstmt = null;

		Random random = new Random();
		int titleNum, courseNum;
		Date date;
		try {
			for (int i = 0; i < 30; i++) {
				titleNum = random.nextInt(titles.length);
				courseNum = random.nextInt(courses.length);

				// 随机生成现在到过去七天的时间
				date = new Date((times - random.nextInt(60 * 60 * 24 * 7)) * 1000);

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, kk++);
				pstmt.setString(2, titles[titleNum]);
				pstmt.setString(3, courses[courseNum]);
				pstmt.setString(4, sdf.format(date));
				// 执行pstmt表示的插入命令
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println("插入操作中出现错误！！！");
			System.out.println(e);
		} finally {
			JDBCUtils.close(conn, pstmt, null);
			System.out.println("生成数据完毕");
		}
	}

	/*
	 * 制造学生数据 分别是序号、学号、姓名、年龄、生日、班级
	 */
	public static void createStudentkDate() {
		StringBuilder name;
		long no = 20164081301L; // 学号

		long times = System.currentTimeMillis() / 1000 - 60 * 60 * 24 * 365 * 20; // 20年前的时间
		String[] classzz = { "一班", "二班", "三班" };

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String sql = "INSERT INTO student values(null, ?, ?, ?, ?, ?)";
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement pstmt = null;

		Random random = new Random();
		Date date;
		try {
			for (int i = 0; i < 60; i++) {
				name = new StringBuilder();
				// 生成名字
				for (int k = 0; k < 3; k++) {
					name.append((char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1))));
				}

				// 随机生成出生日期
				date = new Date((times - 60 * 60 * 24 * random.nextInt(365)) * 1000);

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, (no + i) + "");
				pstmt.setString(2, name.toString());
				pstmt.setInt(3, random.nextInt(3) + 19);
				pstmt.setString(4, sdf.format(date));
				pstmt.setString(5, classzz[random.nextInt(classzz.length)]);
				// 执行pstmt表示的插入命令
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println("插入操作中出现错误！！！");
			System.out.println(e);
		} finally {
			JDBCUtils.close(conn, pstmt, null);
			System.out.println("生成数据完毕");
		}
	}

	/*
	 * 制造班级的数据 班级的ID 和班级的名称？
	 */
}
