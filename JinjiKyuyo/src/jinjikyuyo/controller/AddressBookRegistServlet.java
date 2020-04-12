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
 * 住所録登録Servlet
 */
@WebServlet("/addressbook/regist")
public class AddressBookRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	// 住所録Logic
	private AddressBookLogic logic = new AddressBookLogic();
	// バリデータ
	private CommonValidator validator = new CommonValidator();
	// メッセージ
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
		// 住所録登録画面
		String view = "/WEB-INF/view/addressbook/regist/regist.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);

	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コード設定
		request.setCharacterEncoding("utf-8");
		
		// メッセージを初期化
		message = new ArrayList<>();
		// 登録結果を初期化
		int reslt = 0;
		// リクエストを初期化
		AddressBookBean addressBook = new AddressBookBean();
		
		// 画面：姓を取得
		String familyName = request.getParameter("familyName");
		// 画面：名を取得
		String firstName = request.getParameter("firstName");
		// 氏名（姓 ＋ 名）
		String fullName = familyName + " " + firstName;
		// 画面：カナ（姓）
		String kana1 = request.getParameter("kana1");
		// 画面：カナ（名）
		String kana2 = request.getParameter("kana2");
		// フリガナ（カナ（姓） ＋ カナ（名））
		String nameKana = kana1 + " " + kana2;
		// 画面：生年月日（年）
		String year = request.getParameter("year");
		// 画面：生年月日（月）
		String month = request.getParameter("month");
		// 画面：生年月日（日）
		String day = request.getParameter("day");
		// 生年月日
		String birthday = year + month + day;
		// 画面：郵便番号
		String zipCode = request.getParameter("zipCode");
		// 画面：住所1
		String address1 = request.getParameter("address1");
		// 画面：住所2
		String address2 = request.getParameter("address2");
		// 画面：固定電話番号
		String phoneNumber = request.getParameter("phoneNumber");
		// 画面：携帯電話番号
		String mobileNumber = request.getParameter("mobileNumber");
		// 画面：メールアドレス
		String mailAddress = request.getParameter("mailAddress");
		// 画面：会社
		String company = null;
		// 画面：部署
		String department = null;
		// 画面：役職
		String position = null;
		// 画面：敬称
		String keishou = null;
		// 画面：備考
		String remarks = null;
		// 画面：扶養家族
		String dependents = request.getParameter("dependents");
		
		// 入力チェック
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
		
		// 入力チェックでエラーが0件の場合
		if (message.size() == 0) {
			// リクエストに画面の値を設定
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
			
			// 登録済みかどうかを確認
			if (logic.countAddressBook(addressBook) > 0) {
				message.add("登録済みです。");
			} else {
				// 住所録登録実施
				reslt = logic.registAddressBook(addressBook);
				
				if (reslt > 0) {
					message.add("登録完了しました。");
				}
			}
		}

		// 画面にメッセージを設定
		request.setAttribute("message", message);
		// 住所録登録画面に遷移
		this.doGet(request, response);
	}

}
