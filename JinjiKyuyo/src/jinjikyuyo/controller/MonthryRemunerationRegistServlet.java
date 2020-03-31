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
 * Servlet implementation class MonthryRemunerationRegistServlet
 */
@WebServlet("/monthryRemuneration/regist")
public class MonthryRemunerationRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CommonValidator validator = new CommonValidator();
	MonthryRemunerationInfoLogic logic = new MonthryRemunerationInfoLogic();
	MonthryRemunerationGetServlet gServlet = new MonthryRemunerationGetServlet();
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
		request.setCharacterEncoding("utf-8");
		
		message = new ArrayList<>();
		String[] employeeIdList = request.getParameterValues("employeeId");
		String year = request.getParameter("year");
		List<MonthryRemunerationInfoBean> monthryRemunerationInfoList = new ArrayList<>();
		int reslt = 0;
		if(employeeIdList != null && employeeIdList.length > 0) {
			for(String employeeId : employeeIdList) {
				MonthryRemunerationInfoBean monthryRemunerationInfo = new MonthryRemunerationInfoBean();
				String monthryRemunerat = request.getParameter("monthryRemunerat_" + employeeId);
				if(!validator.isRequired(monthryRemunerat) || !validator.isNumber(monthryRemunerat)) {
					continue;
				} else {
					monthryRemunerationInfo.setEmployeeId(Integer.parseInt(employeeId));
					monthryRemunerationInfo.setTargetYear(year);
					monthryRemunerationInfo.setMonthryRemuneration(Integer.parseInt(monthryRemunerat));
					monthryRemunerationInfoList.add(monthryRemunerationInfo);
				}
			}
		}
			
		if(monthryRemunerationInfoList.size() > 0) {
			reslt = logic.registMonthryRemunerationInfo(monthryRemunerationInfoList);
			if(reslt == 0) {
				message.add("登録に失敗しました。");
			}
		} else {
			message.add("対象が選択されていない、もしくは、正しく入力されていません。");
		}
		
		gServlet.setMessage(message);
		gServlet.doGet(request, response);
	}
}
