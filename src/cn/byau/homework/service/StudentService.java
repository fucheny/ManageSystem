package cn.byau.homework.service;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import cn.byau.homework.dao.StudentDAO;
import cn.byau.homework.entity.Student;
import cn.byau.homework.entity.Homework;
import cn.byau.homework.utils.PageBean;

/**
 * 这是学生查询的业务实现
 * 
 * @author
 *
 */
public class StudentService {
	private final Logger logger = Logger.getLogger(StudentDAO.class);
	StudentDAO studentDAO = new StudentDAO();

//	public List<Student> list() throws SQLException {
//
//		StudentDAO studentDAO = new StudentDAO();
//
//		return studentDAO.list();
//	}

	// 查询全体
	public List<Homework> listByHomework() throws SQLException {
		return studentDAO.list();
	}

	// 通过关键字查询作业
	public List<Homework> listByHomework(String name) throws SQLException {
		return studentDAO.list(name);
	}

	/**
	 * 删除学生
	 * 
	 * @param nos， 所勾选的需要删除的学生
	 * @return 返回是否删除成功
	 * @throws SQLException
	 */
	public boolean deleteBatchByNos(String[] nos) throws SQLException {
		return studentDAO.deleteBatchByNos(nos);
	}

	public Student getStudent(String no) throws SQLException {

		return studentDAO.getStudent(no);
	}

	public boolean insert(Student student) throws SQLException {

		return studentDAO.insert(student);
	}

	public boolean delete(String no) throws SQLException {

		return studentDAO.delete(no);

	}

	public boolean update(Homework homework) throws SQLException {

		return studentDAO.update(homework);
	}

	/**
	 * 导出学生时使用
	 * 
	 * @return
	 * @throws Exception
	 */

	public HSSFWorkbook getHSSFStudent() throws Exception {
		StudentDAO studentDAO = new StudentDAO();
		System.out.println("学生数据导出 到这里了吗？ 1");
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		List<Student> studentkList = studentDAO.listAllStudent();
		// logger.info("getHSSFWorkbook方法入参{}" + studentList);
		HSSFWorkbook wb = new HSSFWorkbook();
		// 标题行抽出字段
		String[] title = { "学号", "姓名", "年龄", "出生日期", "班级" };
		// 设置sheet名称，并创建新的sheet对象
		String sheetName = "学生信息一览";
		Sheet stuSheet = wb.createSheet(sheetName);
		// 获取表头行
		Row titleRow = stuSheet.createRow(0);
		// 创建单元格，设置style居中，字体，单元格大小等
		CellStyle style = wb.createCellStyle();
		Cell cell = null;
		// 把已经写好的标题行写入excel文件中
		for (int i = 0; i < title.length; i++) {
			cell = titleRow.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
		// 把从数据库中取得的数据一一写入excel文件中
		Row row = null;
		for (int i = 0; i < studentkList.size(); i++) {
			// 创建list.size()行数据
			row = stuSheet.createRow(i + 1);
			// 把值一一写进单元格里
			// 设置第一列为自动递增的序号
			row.createCell(0).setCellValue(studentkList.get(i).getNo());
			row.createCell(1).setCellValue(studentkList.get(i).getName());
			row.createCell(2).setCellValue(studentkList.get(i).getAge());
			row.createCell(3).setCellValue(studentkList.get(i).getBirthday());
			row.createCell(4).setCellValue(studentkList.get(i).getClazzNo());
			// 把时间转换为指定格式的字符串再写入excel文件中
//	        if (studentList.get(i).getEnterTime() != null) {
//	            row.createCell(4).setCellValue(sdf.format(studentList.get(i).getEnterTime()));
//	        }
		}
		// 设置单元格宽度自适应，在此基础上把宽度调至1.5倍
		for (int i = 0; i < title.length; i++) {
			stuSheet.autoSizeColumn(i, true);
			stuSheet.setColumnWidth(i, stuSheet.getColumnWidth(i) * 15 / 10);
		}
		System.out.println("学生数据导出 运行到这里了吗？？？？");
		return wb;

	}

	public HSSFWorkbook getHSSFWorkbook() throws Exception {
		StudentDAO studentDAO = new StudentDAO();
		System.out.println("到这里了吗？ 1");
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		List<Homework> homeworkList = studentDAO.list();
		// logger.info("getHSSFWorkbook方法入参{}" + studentList);
		HSSFWorkbook wb = new HSSFWorkbook();
		// 标题行抽出字段
		String[] title = { "序号", "标题", "课程", "布置时间" };
		// 设置sheet名称，并创建新的sheet对象
		String sheetName = "学生作业信息一览";
		Sheet stuSheet = wb.createSheet(sheetName);
		// 获取表头行
		Row titleRow = stuSheet.createRow(0);
		// 创建单元格，设置style居中，字体，单元格大小等
		CellStyle style = wb.createCellStyle();
		Cell cell = null;
		// 把已经写好的标题行写入excel文件中
		for (int i = 0; i < title.length; i++) {
			cell = titleRow.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
		// 把从数据库中取得的数据一一写入excel文件中
		Row row = null;
		for (int i = 0; i < homeworkList.size(); i++) {
			// 创建list.size()行数据
			row = stuSheet.createRow(i + 1);
			// 把值一一写进单元格里
			// 设置第一列为自动递增的序号
			row.createCell(0).setCellValue(i + 1);
			row.createCell(1).setCellValue(homeworkList.get(i).getTitle());
			row.createCell(2).setCellValue(homeworkList.get(i).getKecheng());
			row.createCell(3).setCellValue(homeworkList.get(i).getOktime());

			// 把时间转换为指定格式的字符串再写入excel文件中
//	        if (studentList.get(i).getEnterTime() != null) {
//	            row.createCell(4).setCellValue(sdf.format(studentList.get(i).getEnterTime()));
//	        }
		}
		// 设置单元格宽度自适应，在此基础上把宽度调至1.5倍
		for (int i = 0; i < title.length; i++) {
			stuSheet.autoSizeColumn(i, true);
			stuSheet.setColumnWidth(i, stuSheet.getColumnWidth(i) * 15 / 10);
		}
		System.out.println("到这里了吗？？？？");
		return wb;

	}

	/**
	 * 从excel格式的文件中导入学生
	 *
	 * @param fileName 参数fileName是文件名
	 * @throws Exception
	 */
	public boolean importStudentFromExcel(File f) {
		// logger.info("importStudentFromExcel方法入参{}" + "file=" + f.getAbsolutePath());
		StudentDAO studentDAO = new StudentDAO();

		List<Student> studentList = new ArrayList<Student>();

		boolean flag = true;
		try {
			// 通过得到的文件输入流inputstream创建一个HSSFWordbook对象
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(f));
			HSSFSheet studentSheet = wb.getSheetAt(0);// 第一个工作表

			// 遍历所有的行
			for (Row row : studentSheet) {
				// 第一行标题行不读取
				if (row.getRowNum() < 1) {
					continue;
				}

				String no = row.getCell(0).toString().trim();
				String name = row.getCell(1).toString().trim();
				int age = (int) row.getCell(2).getNumericCellValue();
				String birthday = row.getCell(3).toString().trim();
				String clazzNo = row.getCell(4).toString().trim();

				Student student = new Student(no, name, age, birthday, clazzNo);

				studentList.add(student);

			}
			flag = studentDAO.insertBatch(studentList);
		} catch (Exception e) {
			logger.warn("importStudentFromExcel方法出现错误" + e.getMessage());
			flag = false;
		}
		return flag;

	}

	/**
	 * 通过给定页查询学生信息
	 */

	public PageBean<Student> listByPage(int currentPage, String no) throws SQLException {

		// 生成分页显示的bean类型
		PageBean<Student> pageBean = new PageBean<Student>();
		pageBean.setCurrentPage(currentPage); // 设置当前页
		pageBean.setPageSize(StudentDAO.PAGE_SIZE);// 设置每一页显示的数量
		StudentDAO studentDao = new StudentDAO();
		List<Student> list = studentDao.listByPage(currentPage, no);
		pageBean.setList(list); // 设置当前页面的学生数据

		int count = studentDao.count(no); // count是总记录数
		pageBean.setTotalSize(count); // 设置总记录数
		pageBean.setTotalPage(
				count % StudentDAO.PAGE_SIZE == 0 ? count / StudentDAO.PAGE_SIZE : (count / StudentDAO.PAGE_SIZE) + 1); // 设置总页数
		return pageBean;
	}

}
