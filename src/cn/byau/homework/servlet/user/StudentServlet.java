/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.byau.homework.entity.Homework;
import cn.byau.homework.service.ClazzService;
import cn.byau.homework.service.StudentService;
import cn.byau.homework.servlet.BaseServlet;
import cn.byau.homework.utils.CommonUtils;

/**
 *
 * @author Administrator
 */
@WebServlet("/user/StudentServlet")
public class StudentServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(StudentServlet.class);
	StudentService studentService = new StudentService();
	ClazzService clazzService = new ClazzService();

	public void listByHomework(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
//		HttpSession session = request.getSession();
		try {
			List<Homework> homeworkList = studentService.listByHomework();

//			for (Homework homework : homeworkList) {
//				System.out.println(homework.toString());
//			}

			request.setAttribute("homeworkList", homeworkList);
			String path = "student/listByhomework.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			logger.warn("list方法出现错误" + e.getMessage());
		}
	}

	// 通过关键字查询作业
	public void queryHomework(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			List<Homework> homeworkList = studentService.listByHomework(request.getParameter("name"));

			request.setAttribute("homeworkList", homeworkList);
			String path = "student/listByhomework.jsp";
			request.getRequestDispatcher(path).forward(request, response);

		} catch (Exception e) {
			logger.warn("insertPre方法出现错误" + e.getMessage());
		}
	}

	// 更新操作有点问题
	public void updatehomework(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		try {
			Homework homework = CommonUtils.toBean(request.getParameterMap(), Homework.class);
			logger.info("update方法入参{}" + homework);
			PrintWriter out = response.getWriter();
			boolean f = studentService.update(homework);
			if (f) {
				out.println("更新成功，过1秒到作业列表页面");

			} else {
				out.println("更新失败，过1秒到作业列表页面");
			}
			response.setHeader("refresh", "1;StudentServlet?method=listByHomework");
		} catch (Exception e) {
			logger.warn("update方法出现错误" + e.getMessage());
		}
	}

	// 导出操作
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		// response.getWriter().write("你看着办");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + "data.xls");
		System.out.println("这里这里！！");
		try {
			ServletOutputStream out1 = response.getOutputStream();
			// out1.flush();
			HSSFWorkbook wb = studentService.getHSSFWorkbook();
			wb.write(out1);
			out1.close();
		} catch (Exception e) {
			logger.warn("导出方法出现错误" + e.getMessage());
		}
	}

	// 删除操作
	public void deleteBatchByNos(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("text/html;charset=UTF-8");
		String nos[] = request.getParameterValues("selectFlag");
		PrintWriter out = response.getWriter();
		try {
			boolean f = studentService.deleteBatchByNos(nos);

			if (f) {
				out.println("删除成功，过1秒到作业列表页面");

			} else {
				out.println("删除失败，过1秒到作业列表页面");
			}
			response.setHeader("refresh", "1;StudentServlet?method=listByHomework");
		} catch (Exception e) {
			logger.warn("批量删除方法出现错误" + e.getMessage());
		}
	}

}
