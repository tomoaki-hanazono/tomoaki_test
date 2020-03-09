package taijukiroku.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import taijukiroku.bean.TaijuInfo;
import taijukiroku.bean.UserInfo;
import taijukiroku.bean.UserList;
import taijukiroku.logic.TaijukirokuLogic;
import taijukiroku.logic.UserInfoLogic;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserInfoLogic uLogic = new UserInfoLogic();
	private TaijukirokuLogic tLogic = new TaijukirokuLogic();
	private List<String> message = new ArrayList<>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<UserInfo> list = uLogic.getUserInfoList();
		List<UserList> userList = this.createList(list);
		request.setAttribute("userInfoList", userList);

		String view = "/WEB-INF/view/login/login.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		message = new ArrayList<>();
		String userNo = request.getParameter("userNo");
		UserInfo userInfo = uLogic.getUserInfo(Integer.valueOf(userNo));
		
		String nextView = null;
		
		if(userInfo == null) {
			message.add("ユーザが存在しません。");
			request.setAttribute("message", message);
			this.doGet(request, response);
		} else {
			List<TaijuInfo> taijuInfoList = tLogic.getTaijuInfoList(Integer.valueOf(userNo));
			request.setAttribute("taijuInfoList", taijuInfoList);
			nextView = "/WEB-INF/view/main/main.jsp";
			request.setAttribute("userInfo", userInfo);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextView);
		    dispatcher.forward(request, response);
		}
	}

	private List<UserList> createList(List<UserInfo> userInfoList) {
		List<UserList> userList = new ArrayList<>();
		Map<String,Integer> setUserMap = new HashMap<>();
		int i = 0;
		
		for (UserInfo userInfo : userInfoList) {
			UserList list = new UserList();
			if(!setUserMap.containsKey(userInfo.getUserName())) {
				list.setUserNo(userInfo.getUserNo());
				list.setUserName(userInfo.getUserName());
				list.setSameUserFlg(0);
				userList.add(list);
				setUserMap.put(userInfo.getUserName(),i);
				i++;
			} else {
				list = userList.get(setUserMap.get(userInfo.getUserName()));
				list.setUserNo(0);
				list.setSameUserFlg(1);
				userList.set(setUserMap.get(userInfo.getUserName()), list);
			}
		}
		return userList;
	}
}
