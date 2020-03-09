package taijukiroku.bean;

public class UserList implements java.io.Serializable {
	private int userNo;
	private String userName;
	private int sameUserFlg;
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getSameUserFlg() {
		return sameUserFlg;
	}
	public void setSameUserFlg(int sameUserFlg) {
		this.sameUserFlg = sameUserFlg;
	}
}
