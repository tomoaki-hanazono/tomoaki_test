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
import jinjikyuyo.validator.CommonValidator;

/**
 * Servlet implementation class AddressBookRegistServlet
 */
@WebServlet("/addressbook/regist")
public class AddressBookRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private AddressBookLogic logic = new AddressBookLogic();
	private CommonValidator validator = new CommonValidator();
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
		int reslt = 0;
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
		String dependents = request.getParameter("dependents");
		
		if (!validator.isRequired(familyName) || !validator.isRequired(firstName)) {
			message.add("氏名の入力は必須です。");
		}
		
		if (!validator.isRequired(kana1) || !validator.isRequired(kana2)) {
			message.add("フリガナは必須です。");
		} else if (!validator.isZenkakuKana(kana1) || !validator.isZenkakuKana(kana2)) {
			message.add("フリガナは全角カナで入力してください。");
		}
		
		if (!validator.isRequired(year) || !validator.isRequired(month)
				|| !validator.isRequired(day)) {
			message.add("生年月日は必須です。");
		} else if (!validator.isDate(birthday)) {
			message.add("不正な日付が選択されています。");
		} else if (!validator.isFuture(birthday)) {
			message.add("未来の日付が選択されています。");
		}
			
		if (!validator.isRequired(zipCode)) {
			message.add("郵便番号は必須です。");
		} else if (!validator.isNumber(zipCode) || !validator.isLength(zipCode, 7)) {
			message.add("郵便番号は7桁の半角数値で入力してください。");
		}
		
		if (!validator.isRequired(address1) || !validator.isRequired(address2)) {
			message.add("住所は必須です。");
		}
		
		if(!validator.isRequired(phoneNumber) && !validator.isRequired(mobileNumber)) {
			message.add("電話番号は必須です。<br>固定電話番号と携帯電話番号のどちらかを入力してください。");
		} else {

			if (validator.isRequired(phoneNumber)) {
				if (!validator.isNumber(phoneNumber) || !validator.isLength(phoneNumber, 10)) {
					message.add("固定電話番号は10桁の半角数値で入力してください。");
				}
			}
			
			if (validator.isRequired(mobileNumber)) {
				if (!validator.isNumber(mobileNumber) || !validator.isLength(mobileNumber, 11)) {
					message.add("携帯電話番号は11桁の半角数値で入力してください。");
				}
			}
		}
		
		if (validator.isRequired(mailAddress) && !validator.isMailAddress(mailAddress)) {
			message.add("メールアドレスを正しく入力してください。");
		}
		
		if (!validator.isRequired(dependents)) {
			message.add("扶養家族は必須です。");
		}
		
		if (message.size() == 0) {
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
			addressBook.setDependents(dependents);
			
			if (logic.countAddressBook(addressBook) > 0) {
				message.add("登録済みです。");
			} else {
				reslt = logic.registAddressBook(addressBook);
				
				if (reslt > 0) {
					message.add("登録完了しました。");
				}
			}
		}

		request.setAttribute("message", message);
		this.doGet(request, response);
	}

}
