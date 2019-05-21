package cn.byau.homework.utils;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({ "/admin/*", "/user/*" })
public class LoginFilter implements Filter {
	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletrequest;
		HttpServletResponse response = (HttpServletResponse) servletresponse;

		HttpSession session = request.getSession();
		String loginPage = request.getContextPath() + "/login.jsp";
		String path = request.getContextPath();

		String loginFlag = "";
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);
		// String paths[]={"login.jsp","LoginServlet","ImagesServlet","LogoutServlet"};
		// if(CommonUtils.isContains(requestURI, paths)){
		// if(requestURI.contains("login.jsp")||
		// requestURI.contains("LoginServlet")){
		// filterchain.doFilter(servletrequest, servletresponse);

		loginFlag = (String) session.getAttribute("loginFlag");
		if (loginFlag == null) {
			response.sendRedirect(loginPage);

		} else if (loginFlag.equals("adminLogin") && requestURI.startsWith(path + "/admin")
				|| loginFlag.equals("userLogin") && requestURI.startsWith(path + "/user"))

		{
			filterchain.doFilter(servletrequest, servletresponse);

		} else {
			response.sendRedirect(loginPage);
		}
	}

	public void destroy() {

	}

	public void init(FilterConfig filterconfig) throws ServletException {

	}
}