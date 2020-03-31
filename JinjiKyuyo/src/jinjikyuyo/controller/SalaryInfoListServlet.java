package jinjikyuyo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jinjikyuyo.bean.SalaryBean;
import jinjikyuyo.logic.SalaryLogic;

/**
 * Servlet implementation class SalaryInfoRegistServlet
 */
@WebServlet("/salaryInfo/list")
public class SalaryInfoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private SalaryLogic logic = new SalaryLogic();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaryInfoListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		String employeeName = request.getParameter("fullName_" + employeeId);
		String birthday = request.getParameter("birthday_" + employeeId); 
		
		List<SalaryBean> list = logic.getSalaryInfoList(employeeId);
		
		request.setAttribute("salaryList", list);
		
		if (request.getAttribute("employeeId") == null || request.getAttribute("employeeId").equals("")) {
			request.setAttribute("employeeId", employeeId);
		}
		if (request.getAttribute("employeeName") == null || request.getAttribute("employeeName").equals("") ) {
			request.setAttribute("employeeName", employeeName);
		}
		if (request.getAttribute("birthday") == null || request.getAttribute("birthday").equals("") ) {
			request.setAttribute("birthday", birthday);
		}
		
		String view = "/WEB-INF/view/salaryinfo/list/list.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
