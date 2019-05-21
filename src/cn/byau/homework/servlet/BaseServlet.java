/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */

public class BaseServlet extends HttpServlet {

	// final 防：子类复写
	public final void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public final void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");// 处理响应编码
		request.setCharacterEncoding("UTF-8");
		// 1 获得执行的方法名 selectAll
		String methodName = request.getParameter("method");
		// ** 默认方法
		if (methodName == null) {
			System.out.println("dfd" + methodName);
			methodName = "execute";
		}
		System.out.println("Servlet : " + this + " , " + methodName);
		try {
			// 获取真实调用的servlet的Class类的对象
			Class clazz = this.getClass();
			// 获取调用的方法

			Method executeMethod = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			// 执行调用的方法
			executeMethod.invoke(this, request, response);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("执行的方法[" + methodName + "]不存在");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("服务器异常", e);
		}
	}

	/**
	 * 此方法用于复写，方便子类编程，默认执行方法
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
