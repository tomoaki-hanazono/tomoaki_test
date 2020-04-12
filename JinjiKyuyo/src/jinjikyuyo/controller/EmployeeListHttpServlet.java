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
 * 社員一覧Servlet.
 */
@WebServlet("/employee/list")
public class EmployeeListHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// 住所録Logic
	private AddressBookLogic logic = new AddressBookLogic();
	// 雇用情報確認Servlet
	private EmploymentInfoConfirmServlet employmentServlet = new EmploymentInfoConfirmServlet();
	// 作業時間入力Servlet
	private OperatingTimeInputServlet operatingTimeServlet = new OperatingTimeInputServlet();
	// 給与情報一覧Servlet
	private SalaryInfoListServlet salaryServlet = new SalaryInfoListServlet();
	
	// 雇用情報
	private static final String employmentInfo = "employmentInfo";
	// 作業時間入力
	private static final String operatingTime = "operatingTime";
	// 給与情報
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
		// 社員一覧（住所録一覧）取得
		List<AddressBookBean> list = logic.getAddressBookList();
		
		// 画面に社員一覧を設定
		request.setAttribute("addressBookList", list);
		
		// 社員一覧画面
		String view = "/WEB-INF/view/employee/employeeList.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードを設定
		request.setCharacterEncoding("utf-8");
		// 画面：次画面
		String next = request.getParameter("nextAction");
		
		// 遷移先判定
		if(employmentInfo.equals(next)) {
			// 雇用情報画面に遷移
			employmentServlet.doGet(request, response);
		} else if(operatingTime.equals(next)) {
			// 作業時間入力画面に遷移
			operatingTimeServlet.doGet(request, response);
		} else if(salaryInfo.equals(next)) {
			// 給与情報画面に善意
			salaryServlet.doGet(request, response);
		} else {
			// 自画面に遷移
			doGet(request, response);
		}
	}

}
