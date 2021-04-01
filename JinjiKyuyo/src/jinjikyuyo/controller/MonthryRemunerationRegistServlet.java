package jinjikyuyo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jinjikyuyo.bean.MonthryRemunerationInfoBean;
import jinjikyuyo.logic.MonthryRemunerationInfoLogic;
import jinjikyuyo.validator.CommonValidator;

/**
 * 標準月額登録Servlet
 */
@WebServlet("/monthryRemuneration/regist")
public class MonthryRemunerationRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// バリデータ
	private CommonValidator validator = new CommonValidator();
	// 月額標準Logic
	MonthryRemunerationInfoLogic logic = new MonthryRemunerationInfoLogic();
	// 月額標準取得Servlet
	MonthryRemunerationGetServlet gServlet = new MonthryRemunerationGetServlet();
	// メッセージ
	private List<String> message = new ArrayList<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonthryRemunerationRegistServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードを設定
		request.setCharacterEncoding("utf-8");
		
		// メッセージを初期化
		message = new ArrayList<>();
		// 画面：社員IDリストを取得
		String[] employeeIdList = request.getParameterValues("employeeId");
		// 画面：対象年度を取得
		String year = request.getParameter("year");
		// リクエストを初期化
		List<MonthryRemunerationInfoBean> monthryRemunerationInfoList = new ArrayList<>();
		int reslt = 0;
		
		// 社員IDが1件以上存在する場合
		if(employeeIdList != null && employeeIdList.length > 0) {
			for(String employeeId : employeeIdList) {
				MonthryRemunerationInfoBean monthryRemunerationInfo = new MonthryRemunerationInfoBean();
				// 社員IDに紐づく、画面：標準月額を取得
				String monthryRemunerat = request.getParameter("monthryRemunerat_" + employeeId);
				// 入力チェック
				if(!validator.isRequired(monthryRemunerat) || !validator.isNumber(monthryRemunerat)) {
					continue;
				} else {
					// 標準月額を設定
					monthryRemunerationInfo.setEmployeeId(Integer.parseInt(employeeId));
					monthryRemunerationInfo.setTargetYear(year);
					monthryRemunerationInfo.setMonthryRemuneration(Integer.parseInt(monthryRemunerat));
					monthryRemunerationInfoList.add(monthryRemunerationInfo);
				}
			}
		}

		// リクエストが1件以上存在する場合
		if(monthryRemunerationInfoList.size() > 0) {
			// 標準月額登録処理
			reslt = logic.registMonthryRemunerationInfo(monthryRemunerationInfoList);
			if(reslt == 0) {
				message.add("登録に失敗しました。");
			}
		} else {
			message.add("対象が選択されていない、もしくは、正しく入力されていません。");
		}
		
		// 月額標準取得Servletにメッセージを設定
		gServlet.setMessage(message);
		// 月額標準取得Servletを呼出
		gServlet.doGet(request, response);
	}
}
