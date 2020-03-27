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
 * Servlet implementation class OperatingTimeInputServlet
 */
@WebServlet("/operatingTime/employeeList")
public class OperatingTimeEmployeeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AddressBookLogic logic = new AddressBookLogic();
	private OperatingTimeInputServlet inputServlet = new OperatingTimeInputServlet();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperatingTimeEmployeeListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
List<AddressBookBean> list = logic.getAddressBookList();
		
		request.setAttribute("addressBookList", list);
		
		String view = "/WEB-INF/view/operatingtime/employeeList.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inputServlet.doGet(request, response);
	}

}
