/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.byau.homework.servlet.admin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import cn.byau.homework.entity.Clazz;
import cn.byau.homework.entity.Student;
import cn.byau.homework.service.ClazzService;
import cn.byau.homework.service.StudentService;
import cn.byau.homework.servlet.BaseServlet;
import cn.byau.homework.utils.CommonUtils;
import cn.byau.homework.utils.PageBean;

/**
 *
 * @author Administrator
 */
@WebServlet("/admin/StudentServlet")
public class StudentServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(StudentServlet.class);
	StudentService studentService = new StudentService();
	ClazzService clazzService = new ClazzService();

//	public void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//
//		try {
//			List<Student> studentList = studentService.list();
//
//			request.setAttribute("studentList", studentList);
//			String path = "student/listByPage.jsp";
//			request.getRequestDispatcher(path).forward(request, response);
//		} catch (Exception e) {
//			logger.warn("list方法出现错误" + e.getMessage());
//		}
//
//	}

	/*
	 * 添加学生的数据（预备班级信息）
	 * 先从数据库中找到所有的班级信息
	 * 然后将班级信息传递到插入的界面
	 * 在插入界面中输入具体的信息
	 */
	public void insertPre(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException { // 1、接收插入的信息
		
		try {
			List<Clazz> clazzList = clazzService.list();

			request.setAttribute("clazzList", clazzList);
			String path = "student/insert.jsp";
			request.getRequestDispatcher(path).forward(request, response);

		} catch (Exception e) {
			logger.warn("insertPre方法出现错误" + e.getMessage());
		}
	}

	/*
	 * 具体的添加学生的方法实现
	 */
	public void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException { // 1、接收插入的信息

		try {
			Student student = CommonUtils.toBean(request.getParameterMap(), Student.class);
			logger.info("insert方法入参{}" + student);
			PrintWriter out = response.getWriter();
			boolean f = studentService.insert(student);
			if (f) {
				out.println("添加成功，过1秒到学生列表页面");
			} else {
				out.println("添加失败，过1秒到学生列表页面");
			}
			response.setHeader("refresh", "1;StudentServlet?method=listByPage");

		} catch (Exception e) {

			logger.warn("insert方法出现错误" + e.getMessage());
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		try {
			Student student = CommonUtils.toBean(request.getParameterMap(), Student.class);
			logger.info("update方法入参{}" + student);
			PrintWriter out = response.getWriter();
//			boolean f = studentService.update(student);  // 暂时 不用了，
			boolean f = false;
			if (f) {
				out.println("更新成功，过1秒到学生列表页面");

			} else {
				out.println("更新失败，过1秒到学生列表页面");
			}
			response.setHeader("refresh", "1;StudentServlet?method=listByPage");
		} catch (Exception e) {
			logger.warn("update方法出现错误" + e.getMessage());
		}
	}

	/**
	 * 删除学生，为什么删除不了、、、
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void deleteBatchByNos(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("text/html;charset=UTF-8");
		String nos[] = request.getParameterValues("selectFlag");
		PrintWriter out = response.getWriter();
		for (String string : nos) {
			System.out.println("string = " + string);
		}
		
		try {
			boolean f = studentService.deleteBatchByNos(nos);

			if (f) {
				out.println("删除成功，过1秒到学生列表页面");

			} else {
				out.println("删除失败，过1秒到学生列表页面");
			}
			response.setHeader("refresh", "1;StudentServlet?method=listByPage");
		} catch (Exception e) {
			logger.warn("批量删除方法出现错误" + e.getMessage());
		}
	}

	public void updatePre(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 接收参数
		try {
			String no = request.getParameter("no");
			request.setAttribute("s", studentService.getStudent(no));

			List<Clazz> clazzList = clazzService.list();

			request.setAttribute("clazzList", clazzList);

			String path = "student/update.jsp";

			request.getRequestDispatcher(path).forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * 导出学生数据的方法实现
	 */
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		// response.getWriter().write("你看着办");
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + "data.xls");
		try {
			ServletOutputStream out1 = response.getOutputStream();
			// out1.flush();
			HSSFWorkbook wb = studentService.getHSSFStudent();
			wb.write(out1);
			out1.close();
		} catch (Exception e) {
			logger.warn("导出方法出现错误" + e.getMessage());
		}
	}
	

	/*
	 * 搜索学生的信息，如果没有输入具体的学号，那么显示全体
	 * 如果输入的关键字，那么就通过关键字搜索
	 */
	public void listByPage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 接收参数
		try {
			// 1.获取要显示的页码数
			String sCurrentPage = request.getParameter("currentPage");
			if (sCurrentPage == null) {
				sCurrentPage = "1";
			}
			int currentPage = Integer.parseInt(sCurrentPage);

			String no = request.getParameter("no");
			if (no == null) {
				no = "";
			}

			// 2.根据指定的页数，取得响应的信息

			PageBean<Student> pageBean1 = studentService.listByPage(currentPage, no);
			request.setAttribute("pageBean", pageBean1);
			request.setAttribute("no", no);

			// 3.携带信息，跳转页面显示
			request.getRequestDispatcher("student/listByPage.jsp").forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.warn("listByPage方法出现错误" + e.getMessage());
		}

	}

	public void uploadAndImport(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// 验证请求是否满足要求（post 请求 / enctype 是否以multipart打头
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
					File f1 = new File("c:/temp/" + fileName);
					item.write(f1);
					out.println("上传成功");

					if (studentService.importStudentFromExcel(f1)) {
						out.println(",导入成功");

					} else {
						out.println(",但导入失败");

					}
				}
			}
		} catch (Exception e) {
			logger.warn("上传和导入Excel出现错误" + e.getMessage());
		}
	}

}
