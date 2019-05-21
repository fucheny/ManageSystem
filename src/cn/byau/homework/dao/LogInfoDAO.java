/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.byau.homework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cn.byau.homework.entity.LogInfo;
import cn.byau.homework.utils.JDBCUtils;

/**
 *
 * @author Administrator
 */
public class LogInfoDAO {
	public boolean save(String userid) {
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		String sql = "insert into loginfo values(null, ?, ?)";
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement pstmt = null;
		boolean insertSuccessFlag = false;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, date1);
			// 执行pstmt表示的插入命令
			pstmt.executeUpdate();
			insertSuccessFlag = true;
		} catch (SQLException e) {
			System.out.println("插入操作中出现错误！！！");
			System.out.println(e);
		} finally {
			JDBCUtils.close(conn, pstmt, null);
		}
		return insertSuccessFlag;
	}

	public ArrayList<LogInfo> list() {
		ArrayList<LogInfo> all = new ArrayList<LogInfo>();
		LogInfo logInfo = null;
		String sql = "SELECT u.userid, username, logintime FROM user u, loginfo log where u.userid=log.userid";
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 此处为什么不用while,用while可不可以
			while (rs.next()) {
				logInfo = new LogInfo(rs.getString(1), rs.getString(2), rs.getString(3));
				all.add(logInfo);
			}

		} catch (SQLException e) {
			System.out.println("操作中出现错误！！！");
			System.out.println(e.getMessage());
		} finally {
			JDBCUtils.close(conn, pstmt, rs);
		}
		return all;
	}
}
