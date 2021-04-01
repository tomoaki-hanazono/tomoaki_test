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
 * 給与一覧（月別）Servlet
 */
@WebServlet("/salaryInfo/month")
public class MonthSalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// バリデータ
	private CommonValidator validator = new CommonValidator();
	// 給与Logic
	private SalaryLogic logic = new SalaryLogic();
	// メッセージ
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
		// 給与一覧（月別）画面
		String view = "/WEB-INF/view/salaryinfo/month/monthsalary.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードを設定
		request.setCharacterEncoding("utf-8");
		
		// メッセージを初期化
		message = new ArrayList<>();
		
		// 画面：照会年を取得
		String year = request.getParameter("year");
		// 画面：照会月を取得
		String month = request.getParameter("month");
		if(!validator.isRequired(year) || !validator.isRequired(month)) {
			message.add("照会月を選択してください。");
		}
		
		// 照会年月の給与一覧を取得
		List<Salary> salaryList = logic.getSalaryInfoList(year+month);
		// 画面に給与一覧を設定
		request.setAttribute("salaryList", salaryList);
		
		doGet(request, response);
	}

}
