package taijukiroku.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import taijukiroku.bean.UserInfo;
import taijukiroku.logic.UserInfoLogic;

/**
 * Servlet implementation class UserRegistServlet
 */
@WebServlet("/userRegist")
public class UserRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/view/regist/regist.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LoginServlet lServlet = new LoginServlet();
		UserInfoLogic logic = new UserInfoLogic();
		UserInfo userInfo = new UserInfo();
		
		request.setCharacterEncoding("utf-8");
		
		userInfo.setUserName(request.getParameter("userName"));
		String birthday = request.getParameter("yaer") + request.getParameter("month") + request.getParameter("day");
		userInfo.setBirthday(birthday);
		userInfo.setPhoneNum(request.getParameter("phoneNum"));
		userInfo.setHeight(Double.parseDouble(request.getParameter("height")));
		
		logic.registUser(userInfo);
		
		lServlet.doGet(request, response);

	}

}
