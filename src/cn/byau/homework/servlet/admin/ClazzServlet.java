/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import cn.byau.homework.entity.Clazz;
import cn.byau.homework.service.ClazzService;
import cn.byau.homework.servlet.BaseServlet;
import cn.byau.homework.utils.CommonUtils;

/**
 *
 * @author Administrator
 */
@WebServlet("/admin/ClazzServlet")
public class ClazzServlet extends BaseServlet {
	private final Logger logger = Logger.getLogger(ClazzServlet.class);
	ClazzService clazzService = new ClazzService();

	public void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		try {
			List<Clazz> clazzList = clazzService.list();

			request.setAttribute("clazzList", clazzList);
			String path = "clazz/list.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			logger.warn("list方法出现错误" + e.getMessage());
		}

	}

	

	public void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { // 1、接收插入的信息

		try {
			Clazz clazz = CommonUtils.toBean(request.getParameterMap(), Clazz.class);
			logger.info("insert方法入参{}" + clazz);
			PrintWriter out = response.getWriter();
			//clazz.setClazzName("里");
			boolean f = clazzService.insert(clazz);
			if (f) {
				out.println("添加成功，过1秒到学生列表页面");
			} else {
				out.println("添加失败，过1秒到学生列表页面");
			}
			response.setHeader("refresh", "1;ClazzServlet?method=list");

		} catch (Exception e) {

			logger.warn("insert方法出现错误" + e.getMessage());
		}
	}
	public void insertPre(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException { 

		try {
			
			String path = "clazz/insert.jsp";
			request.getRequestDispatcher(path).forward(request, response);

		} catch (Exception e) {
			logger.warn("方法出现错误" + e.getMessage());
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		try {
			request.setCharacterEncoding("UTF-8");
			Clazz clazz = CommonUtils.toBean(request.getParameterMap(), Clazz.class);
			logger.info("update方法入参{}" + clazz);
			PrintWriter out = response.getWriter();
			boolean f = clazzService.update(clazz);
			if (f) {
				out.println("更新成功，过1秒到学生列表页面");

			} else {
				out.println("更新失败，过1秒到学生列表页面");
			}
			response.setHeader("refresh", "1;ClazzServlet?method=list");
		} catch (Exception e) {
			logger.warn("update方法出现错误" + e.getMessage());
		}
	}
	public void updatePre(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 接收参数
		try {
			String clazzNo = request.getParameter("clazzNo");
			logger.info("updatePre方法入参{}" + clazzNo);
			request.setAttribute("clazz", clazzService.getClazz(clazzNo));

		
		
			String path = "clazz/update.jsp";

			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			logger.warn("delete方法出现错误" + e.getMessage());
		}
	}

	public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		try {
			String clazzNo = request.getParameter("clazzNo");

			logger.info("delete方法入参{}" + clazzNo);
			PrintWriter out = response.getWriter();
			boolean f = clazzService.delete(clazzNo);
			if (f) {
				out.println("删除成功，过1秒到学生列表页面");

			} else {
				out.println("删除失败，过1秒到学生列表页面");
			}
			response.setHeader("refresh", "1;ClazzServlet?method=list");
		} catch (Exception e) {
			logger.warn("delete方法出现错误" + e.getMessage());
		}
	}

	

}
