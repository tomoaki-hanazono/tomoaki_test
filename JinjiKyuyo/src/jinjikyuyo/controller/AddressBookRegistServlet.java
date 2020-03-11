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

import jinjikyuyo.bean.AddressBookBean;
import jinjikyuyo.logic.AddressBookLogic;

/**
 * Servlet implementation class AddressBookRegistServlet
 */
@WebServlet("/addressbook/regist")
public class AddressBookRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AddressBookLogic logic = new AddressBookLogic();
	private List<String> message = new ArrayList<>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressBookRegistServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String view = "/WEB-INF/view/addressbook/regist/regist.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		message = new ArrayList<>();
		AddressBookBean addressBook = new AddressBookBean();
		
		String familyName = request.getParameter("familyName");
		String firstName = request.getParameter("firstName");
		String fullName = familyName + " " + firstName;
		String kana1 = request.getParameter("kana1");
		String kana2 = request.getParameter("kana2");
		String nameKana = kana1 + " " + kana2;
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String birthday = year + month + day;
		String zipCode = request.getParameter("zipCode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String phoneNumber = request.getParameter("phoneNumber");
		String mobileNumber = request.getParameter("mobileNumber");
		String mailAddress = request.getParameter("mailAddress");
		String company = null;
		String department = null;
		String position = null;
		String keishou = null;
		String remarks = null;
		
		addressBook.setFullName(fullName);
		addressBook.setFamilyName(familyName);
		addressBook.setFirstName(firstName);
		addressBook.setNameKana(nameKana);
		addressBook.setBirthday(birthday);
		addressBook.setZipCode(zipCode);
		addressBook.setAddress1(address1);
		addressBook.setAddress2(address2);
		addressBook.setPhoneNumber(phoneNumber);
		addressBook.setMobileNumber(mobileNumber);
		addressBook.setMailAddress(mailAddress);
		addressBook.setCompany(company);
		addressBook.setDepartment(department);
		addressBook.setPosition(position);
		addressBook.setKeishou(keishou);
		addressBook.setRemarks(remarks);
		
		int reslt = logic.registAddressBook(addressBook);
		
		if (reslt > 0) {
			message.add("登録完了しました。");
		}

		request.setAttribute("message", message);
		this.doGet(request, response);
	}

}
