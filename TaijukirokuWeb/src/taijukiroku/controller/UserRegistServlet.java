package taijukiroku.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import taijukiroku.bean.UserInfo;
import taijukiroku.logic.UserInfoLogic;
import taijukiroku.validator.CommonValidator;

/**
 * Servlet implementation class UserRegistServlet
 */
@WebServlet("/userRegist")
public class UserRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CommonValidator validator = new CommonValidator();
	private LoginServlet lServlet = new LoginServlet();
	private UserInfoLogic logic = new UserInfoLogic();
	private List<String> message = new ArrayList<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/view/regist/regist.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		message = new ArrayList<>();
		UserInfo userInfo = new UserInfo();
		
		boolean errFlg = false;
		String userName = request.getParameter("userName");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String birthday = year + month + day;
		String phoneNum = request.getParameter("phoneNum");
		String height = request.getParameter("height");
		
		if (!validator.isRequired(userName) || !validator.isRequired(year)
				|| !validator.isRequired(month)|| !validator.isRequired(day)
				|| !validator.isRequired(phoneNum) || !validator.isRequired(height)) {
			message.add("未入力の項目があります。");
			errFlg = true;
		} else {
			if (!validator.isDate(birthday)) {
				message.add("日付けが不正です。");
				errFlg = true;
			} else if (!validator.isFuture(birthday)) {
				message.add("未来の日付が選択されています。");
				errFlg = true;
			}
			if (!validator.isPhoneNum(phoneNum)) {
				message.add("電話番号は11桁以内の半角数値で入力してください");
				errFlg = true;
			}
			if (!validator.isBodyInfo(height)) {
				message.add("身長は整数3桁、小数点1桁以内の半角数値で入力してください。");
				errFlg = true;
			}
		}

		if(message.size() == 0) {
			if(logic.countUserInfo(userName, birthday, phoneNum) > 0) {
				message.add("登録済みのユーザです。");
				errFlg = true;
			}
		}

		request.setAttribute("message", message);
		
		if(!errFlg) {
			userInfo.setUserName(userName);
			userInfo.setBirthday(birthday);
			userInfo.setPhoneNum(phoneNum);
			userInfo.setHeight(Double.parseDouble(height));
			
			logic.registUser(userInfo);
			
			lServlet.doGet(request, response);
		} else {
			this.doGet(request, response);
		}
	}

}
