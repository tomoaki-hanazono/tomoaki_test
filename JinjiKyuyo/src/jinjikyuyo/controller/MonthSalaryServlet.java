package jinjikyuyo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jinjikyuyo.bean.Salary;
import jinjikyuyo.logic.SalaryLogic;
import jinjikyuyo.validator.CommonValidator;

/**
 * Servlet implementation class MonthSalaryServlet
 */
@WebServlet("/salaryInfo/month")
public class MonthSalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CommonValidator validator = new CommonValidator();
	private SalaryLogic logic = new SalaryLogic();
	private List<String> message = new ArrayList<>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonthSalaryServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/view/salaryinfo/month/monthsalary.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		message = new ArrayList<>();
		// 画面項目を取得
		
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		if(!validator.isRequired(year) || !validator.isRequired(month)) {
			message.add("照会月を選択してください。");
		}
		
		List<Salary> salaryList = logic.getSalaryInfoList(year+month);
		request.setAttribute("salaryList", salaryList);
		
		doGet(request, response);
	}

}
