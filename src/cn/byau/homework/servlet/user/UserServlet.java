/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.servlet.user;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.byau.homework.entity.Clazz;
import cn.byau.homework.entity.User;
import cn.byau.homework.service.ClazzService;
import cn.byau.homework.service.UserService;
import cn.byau.homework.servlet.BaseServlet;
import cn.byau.homework.utils.CommonUtils;
import cn.byau.homework.utils.PageBean;

/**
 *
 * @author Administrator
 */
@WebServlet("/user/UserServlet")
public class UserServlet extends BaseServlet {
	private final Logger logger = Logger.getLogger(UserServlet.class);
	UserService userService = new UserService();
	ClazzService clazzService = new ClazzService();

//	public void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//
//		try {
//			List<User> userList = userService.list();
//
//			request.setAttribute("userList", userList);
//			String path = "admin/user/list.jsp";
//			request.getRequestDispatcher(path).forward(request, response);
//		} catch (Exception e) {
//			logger.warn("list方法出现错误" + e.getMessage());
//		}
//
//	}
//
//	public void insertPre(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException { // 1、接收插入的信息
//
//		try {
//			List<Clazz> clazzList = clazzService.list();
//
//			request.setAttribute("clazzList", clazzList);
//			String path = "admin/user/insert.jsp";
//			request.getRequestDispatcher(path).forward(request, response);
//
//		} catch (Exception e) {
//			System.out.println("方法出现错误" + e.getMessage());
//		}
//	}
//
//	public void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { // 1、接收插入的信息
//
//		try {
//			User user = CommonUtils.toBean(request.getParameterMap(), User.class);
//			logger.info("insert方法入参{}" + user);
//			PrintWriter out = response.getWriter();
//			boolean f = userService.update(user);
//			if (f) {
//				out.println("添加成功，过1秒到学生列表页面");
//			} else {
//				out.println("添加失败，过1秒到学生列表页面");
//			}
//			response.setHeader("refresh", "1;UserServlet?method=listByPage");
//
//		} catch (Exception e) {
//
//			logger.warn("insert方法出现错误" + e.getMessage());
//		}
//	}
//
//	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//
//		try {
//			User user = CommonUtils.toBean(request.getParameterMap(), User.class);
//			logger.info("update方法入参{}" + user);
//			PrintWriter out = response.getWriter();
//			boolean f = userService.update(user);
//			if (f) {
//				out.println("更新成功，过1秒到学生列表页面");
//
//			} else {
//				out.println("更新失败，过1秒到学生列表页面");
//			}
//			response.setHeader("refresh", "1;UserServlet?method=listByPage");
//		} catch (Exception e) {
//			logger.warn("update方法出现错误" + e.getMessage());
//		}
//	}
//
//	public void updatePre(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//		// 接收参数
//		try {
//			String no = request.getParameter("no");
//			request.setAttribute("s", userService.getUser(no));
//
//			List<Clazz> clazzList = clazzService.list();
//
//			request.setAttribute("clazzList", clazzList);
//
//			String path = "admin/user/update.jsp";
//
//			request.getRequestDispatcher(path).forward(request, response);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}

	public void uploadPhoto(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String savePath = getServletContext().getRealPath("/") + "upload";

		HttpSession session = request.getSession();

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		// 如果不满足要求就立即结束对该请求的处理

		if (!isMultipart) {
			return;
		}
		response.setHeader("content-type", "text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		try {
			FileItemFactory fac = new DiskFileItemFactory();
			ServletFileUpload up = new ServletFileUpload(fac);
			up.setHeaderEncoding("UTF-8");
			up.setSizeMax(1024 * 1024 * 30);
			List<FileItem> list = up.parseRequest(request);
			for (FileItem item : list) {
				if (item.isFormField()) {

				} else {
					// System.out.println(item.getFieldName());
					String fileName = item.getName();
					// System.out.println(fileName);
					fileName=CommonUtils.uuid()+fileName.substring(fileName.lastIndexOf("."));
					File f1 = new File(savePath + "/" + fileName);
					
					item.write(f1);
					out.println("上传成功");
					
					
					
					
					String accessPath = request.getContextPath()+"/upload/" + fileName;

					String userID = (String) session.getAttribute("userID");
					if (userService.updatePhoto(userID, accessPath)) {

						out.println(",写入数据库成功");
					} else {
						out.println("写入数据库失败");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
