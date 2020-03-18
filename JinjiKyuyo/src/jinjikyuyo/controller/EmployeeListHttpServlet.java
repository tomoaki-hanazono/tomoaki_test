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
@WebServlet("/employmentInfo/employeeList")
public class EmployeeListHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AddressBookLogic logic = new AddressBookLogic();
	private EmploymentInfoConfirmServlet confirmServlet = new EmploymentInfoConfirmServlet();
	
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
		
		String view = "/WEB-INF/view/employmentinfo/employeeList.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		confirmServlet.doGet(request, response);
	}

}
