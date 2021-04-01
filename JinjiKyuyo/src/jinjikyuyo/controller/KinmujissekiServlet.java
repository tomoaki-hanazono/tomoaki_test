package jinjikyuyo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import jinjikyuyo.bean.AddressBookBean;
import jinjikyuyo.bean.EmploymentInfoBean;
import jinjikyuyo.logic.AddressBookLogic;
import jinjikyuyo.logic.EmploymentInfoLogic;
import jinjikyuyo.util.StringUtil;
import jinjikyuyo.validator.CommonValidator;
import jinjikyuyo.xlsx.XLSXReader;
import jinjikyuyo.xlsx.XLSXSheet;
import jinjikyuyo.xlsx.XLSXWorkbook;

/**
 * 勤務実績Servlet
 */
@WebServlet("/kinmuJisseki/input")
@MultipartConfig(location="/tmp", maxFileSize=1048576)
public class KinmujissekiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// バリデータ
	private CommonValidator validator = new CommonValidator();
	// 住所録Logic
	private AddressBookLogic logic = new AddressBookLogic();
	// メッセージ
	private List<String> message = new ArrayList<>();
	// 
	private StringUtil util = new StringUtil();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KinmujissekiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 社員一覧（住所録一覧）取得
		List<AddressBookBean> list = logic.getAddressBookList();
		Map<String, String> shainMap = createShainMap(list);
		
		// 画面に社員一覧を設定
		request.setAttribute("shainMap", shainMap);
		// 画面にメッセージを設定
		request.setAttribute("message", message);
		
		// 勤務実績取込画面
		String view = "/WEB-INF/view/kinmujisseki/input/input.jsp";
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
		
		// 各変数を定義
		int employeeId = 0;
		String employeeName = null;
		String birthday = null;
		String dependents = null;

		if(validator.isRequired(request.getParameter("shain"))) {
			// 画面：社員IDを取得
			employeeId = Integer.parseInt(request.getParameter("shain"));
			// 社員IDから社員情報を取得
			AddressBookBean ab = logic.getAddressBook(employeeId);
			// 社員名
			employeeName = ab.getFullName();
			// 誕生日
			birthday = ab.getBirthday();
			// 扶養家族
			dependents = ab.getDependents();
		} else {
			message.add("社員を選択してください。");
		}
		// 画面：年を取得
		String year = request.getParameter("year");
		// 画面：月を取得
		String month = request.getParameter("month");
		// 入力チェック
		if(!validator.isRequired(year) || !validator.isRequired(month)) {
			message.add("稼働月を選択してください。");
		}

		if (message.size() == 0) {
			// アップロードファイルの情報を取得
			Part part = request.getPart("kinmujissekihyo");
			
			if(part == null) {
				message.add("勤務実績票を選択してください。");
			} else {
				// ファイル名の取得
				String name = this.getFileName(part);
				// ファイルチェック
				if (!validator.isRequired(name)) {
					message.add("勤務実績票を選択してください。");
				} else if (!name.endsWith(".xlsx")) {
					message.add("ファイル形式が異なります。");
				} else {
					// アップロードファイルを一時保存
					String filename = "/tmp/" + name;
					part.write(filename);
					// エクセルファイルを読み込み
					XLSXWorkbook workbook = (new XLSXReader(filename)).getWorkbook();
					// 1枚目のシートを取得
					XLSXSheet sheet = workbook.getSheetAt(0);
					// シート内の全セルの値を取得
					HashMap<String, String> cells = sheet.getCellAll();
					// セルの値から稼働時間を取得
					String[] operatingTime = checkMap(cells);
					// 稼働時間の取得に成功した場合、取得した各値をリクエストに設定
					if (operatingTime != null) {
						request.setAttribute("employeeId", String.valueOf(employeeId));
						request.setAttribute("employeeName", employeeName);
						request.setAttribute("birthday", birthday);
						request.setAttribute("dependents", dependents);
						request.setAttribute("year", year);
						request.setAttribute("month", month);
						request.setAttribute("operatingTime", operatingTime[0]+ "." +operatingTime[1]);
						request.setAttribute("operatingTime1", operatingTime[0]);
						request.setAttribute("operatingTime2", operatingTime[1]);
					}
					// 一時ファイルを削除
					File del = new File(filename);
					del.delete();
				}
			}
		}

		doGet(request, response);
	}

	/**
	 * 画面用の社員マップを作成する
	 * @param shainList 社員情報一覧
	 * @return 社員マップ<社員ID,社員名>
	 */
	private Map<String, String> createShainMap(List<AddressBookBean> shainList) {
		Map<String, String> shainMap = new HashMap<String, String>();
		if (shainList != null && shainList.size() > 0) {
			for (AddressBookBean ab : shainList) {
				shainMap.put(String.valueOf(ab.getEmployeeId()), ab.getFullName());
			}
		}
		
		return shainMap;
	}
	
	/**
	 * ファイル名の取得
	 * @param part パート
	 * @return ファイル名
	 */
	private String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }
	
	/**
	 * セルの値のマップから稼働時間を取得する.
	 * @param hashMap セルの値のマップ<セルのkey,セルの値>
	 * @return 稼働時間[整数,小数]
	 */
	private String[] checkMap(HashMap<String, String> hashMap) {
		// 変数を設定
		int i = 0;
		int j = 0;
		String[] s = new String[2];
		String[] reslt = new String[2];
		
		// セルの件数分、処理を繰り返す
		for (String key : hashMap.keySet()) {
			// セルの値を取得
			String value = util.convert(hashMap.get(key));
			// セルの値が存在しない場合、処理をスキップ
			if (value == null || value.isEmpty()) continue;
			// 「.」、「:」が含まれない場合
			if (!value.contains(".") && !value.contains(":")) {
				// 数値以外の場合、処理をスキップ
				if(!util.checkNm(value)) continue;
				// 値が3桁以下の場合
				if (value.length() <= 3 && i < Integer.parseInt(value)) {
					i = Integer.parseInt(value);
					j = 0;
				}
			} else if (value.contains(".")) {
				// 稼働時間[時：整数、分：小数]を取得
				s = searchTimeValue(value, ".");
				// 数値以外の場合、処理をスキップ
				if(!util.checkNm(s[0]) || !util.checkNm(s[1])) continue;
				if(i < Integer.parseInt(s[0])) {
					// 時間が大きい場合、時・分を設定
					i = Integer.parseInt(s[0]);
					j = Integer.parseInt(s[1]);
				} else if (i == Integer.parseInt(s[0])
						&& j < Integer.parseInt(s[1])) {
					// 時間が同じ、かつ、分が大きい場合、分を設定
					j = Integer.parseInt(s[1]);
				}
			} else if (value.contains(":")) {
				// 稼働時間[時：整数、分：小数]を取得
				s = searchTimeValue(value, ":");
				// 数値以外の場合、処理をスキップ
				if(!util.checkNm(s[0]) || !util.checkNm(s[1])) continue;
				if(i < Integer.parseInt(s[0])) {
					// 時間が大きい場合、時・分を設定
					i = Integer.parseInt(s[0]);
					j = util.convertMtoH(Integer.parseInt(s[1]));
				} else if (i == Integer.parseInt(s[0])
						&& j < Integer.parseInt(s[1])) {
					// 時間が同じ、かつ、分が大きい場合、分を設定
					j = util.convertMtoH(Integer.parseInt(s[1]));
				}
			}
		}
		
		reslt[0] = String.valueOf(i);
		reslt[1] = String.valueOf(j);
		
		return reslt;
	}
	
	/**
	 * 稼働時間を取得.
	 * @param value セルの値
	 * @param key 分割文字
	 * @return 稼働時間[時：整数、分：小数]
	 */
	private String[] searchTimeValue(String value, String key) {
		String[] reslt = new String[2];
		
		if(value.endsWith(key)) {
			reslt[0] = value.substring(0,value.length()-1);
			if(reslt[0].length() > 3) reslt[0] = "0";
			reslt[1] = "0";
		} else if(value.startsWith(key)) {
			reslt[0] = "0";
			reslt[1] = value.substring(1);
			if(reslt[1].length() > 2) reslt[1] = reslt[1].substring(0,2);
		} else {
			reslt[0] = value.substring(0,value.indexOf(key));
			if(reslt[0].length() > 3) {
				reslt[0] = "0";
				reslt[1] = "0";
			} else {
				reslt[1] = value.substring(value.indexOf(key)+1, value.length());
				if(reslt[1].length() > 2) reslt[1] = reslt[1].substring(0,2);
			}
		}

		return reslt;
	}
	
	private int convertMtoH(int m) {
		if (m < 15) {
			return m;
		} else if (m >= 15 && m < 30) {
			return 25;
		} else if (m >= 30 && m < 45) {
			return 50;
		} else {
			return 75;
		}
	}
}
