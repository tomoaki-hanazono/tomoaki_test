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
 * 標準月額取得Servlet
 */
@WebServlet("/monthryRemuneration/get")
public class MonthryRemunerationGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// バリデータ
	private CommonValidator validator = new CommonValidator();
	// 住所一覧Logic
	private AddressBookLogic aLogic = new AddressBookLogic();
	// 給与Logic
	private SalaryLogic sLogic = new SalaryLogic();
	// メッセージ
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
		// メッセージを設定
		request.setAttribute("message", message);
		// 標準月額設定画面
		String view = "/WEB-INF/view/maintenance/monthryRemuneration.jsp";
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
		
		// 画面：対象年度を取得
		String year = request.getParameter("year");
		request.setAttribute("year", year);
		// 入力チェック
		if(!validator.isRequired(year)) {
			message.add("対象年度を選択してください。");
		} else {
			// 住所一覧を取得
			List<AddressBookBean> list = aLogic.getAddressBookList();
			// 対象年の4〜5月の総支給額を取得
			Map<Integer, Map<String, Integer>> salaryMap = sLogic.getMonthryRemunerat(year);
			// 画面に住所一覧を設定
			request.setAttribute("addressBookList", list);
			// 画面に対象年の4〜5月の総支給額を設定
			request.setAttribute("salaryMap", salaryMap);	
		}
		
		doGet(request, response);
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}

}
