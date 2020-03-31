package jinjikyuyo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jinjikyuyo.bean.AddressBookBean;
import jinjikyuyo.logic.AddressBookLogic;
import jinjikyuyo.logic.SalaryLogic;
import jinjikyuyo.validator.CommonValidator;

/**
 * Servlet implementation class MonthryRemunerationGetServlet
 */
@WebServlet("/monthryRemuneration/get")
public class MonthryRemunerationGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CommonValidator validator = new CommonValidator();
	private AddressBookLogic aLogic = new AddressBookLogic();
	private SalaryLogic sLogic = new SalaryLogic();
	private List<String> message = new ArrayList<>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonthryRemunerationGetServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("message", message);
		String view = "/WEB-INF/view/maintenance/monthryRemuneration.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		message = new ArrayList<>();
		
		String year = request.getParameter("year");
		request.setAttribute("year", year);
		if(!validator.isRequired(year)) {
			message.add("対象年度を選択してください。");
		} else {
			List<AddressBookBean> list = aLogic.getAddressBookList();
			Map<Integer, Map<String, Integer>> salaryMap = sLogic.getMonthryRemunerat(year);
			request.setAttribute("addressBookList", list);
			request.setAttribute("salaryMap", salaryMap);	
		}
		
		doGet(request, response);
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

}
