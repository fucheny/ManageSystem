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

import cn.byau.homework.dao.LogInfoDAO;
import cn.byau.homework.entity.User;
import cn.byau.homework.service.UserService;
import cn.byau.homework.utils.CommonUtils;

/**
 *
 * @author Administrator
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(LoginServlet.class);
	UserService userService = new UserService();
	// ClazzService clazzService = new ClazzService();

	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		LogInfoDAO infoDAO = new LogInfoDAO();
		try {

			String code = (String) session.getAttribute("code");
			// logger.warn("code"+code);
			String input = request.getParameter("verifyCode");
			if (!code.equalsIgnoreCase(input)) {
				out.println("<script>alert('验证码错误');window.location='login.jsp';</script>");
				return;
			}

			String userID = request.getParameter("userID");
			String password = request.getParameter("password");

			User user = userService.login(userID, password);

			if (user != null) {
				infoDAO.save(userID); // 登录的是正确的，后台日志记录

				if (user.getUserType().equals(CommonUtils.ADMIN_ROLE)) {
					session.setAttribute("userID", userID);
					session.setAttribute("pw", password);
					session.setAttribute("uname", user.getUserName());
					session.setAttribute("loginFlag", "adminLogin");

					response.sendRedirect("admin/index.jsp");

				} else if (user.getUserType().equals(CommonUtils.USER_ROlE)) {
					session.setAttribute("userID", userID);
					session.setAttribute("pw", password);
					session.setAttribute("uname", user.getUserName());
					session.setAttribute("clazzNo", user.getClazzNo());
					session.setAttribute("loginFlag", "userLogin");

					response.sendRedirect("user/index.jsp");
				}
			} else {
				out.println("<script>alert('用户名或密码错误');window.location='login.jsp';</script>");
			}
		} catch (Exception e) {
			logger.warn("login方法出现错误" + e.getMessage());
		}

	}

}
