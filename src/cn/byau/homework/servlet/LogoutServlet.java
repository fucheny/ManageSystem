/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.byau.homework.entity.User;
import cn.byau.homework.service.UserService;
import cn.byau.homework.utils.CommonUtils;

/**
 *
 * @author Administrator
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends BaseServlet {
	private final Logger logger = Logger.getLogger(LogoutServlet.class);
	UserService userService = new UserService();
	// ClazzService clazzService = new ClazzService();

	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("login.jsp");

	}

}
