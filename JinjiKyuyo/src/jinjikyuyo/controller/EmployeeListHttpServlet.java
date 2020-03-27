package jinjikyuyo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jinjikyuyo.bean.AddressBookBean;
import jinjikyuyo.logic.AddressBookLogic;

/**
 * Servlet implementation class EmployeeListHttpServlet
 */
@WebServlet("/employee/list")
public class EmployeeListHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AddressBookLogic logic = new AddressBookLogic();
	private EmploymentInfoConfirmServlet employmentServlet = new EmploymentInfoConfirmServlet();
	private OperatingTimeInputServlet operatingTimeServlet = new OperatingTimeInputServlet();
	private SalaryInfoListServlet salaryServlet = new SalaryInfoListServlet();
	
	private static final String employmentInfo = "employmentInfo";
	private static final String operatingTime = "operatingTime";
	private static final String salaryInfo = "salaryInfo";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeListHttpServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AddressBookBean> list = logic.getAddressBookList();
		
		request.setAttribute("addressBookList", list);
		
		String view = "/WEB-INF/view/employee/employeeList.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String next = request.getParameter("nextAction");
		
		if(employmentInfo.equals(next)) {
			employmentServlet.doGet(request, response);
		} else if(operatingTime.equals(next)) {
			operatingTimeServlet.doGet(request, response);
		} else if(salaryInfo.equals(next)) {
			salaryServlet.doGet(request, response);
		} else {
			doGet(request, response);
		}
	}

}
