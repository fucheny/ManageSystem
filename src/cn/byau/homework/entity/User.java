package cn.byau.homework.entity;

public class User {

	// 表中所有字段
	private String userID;
	private String userName;
	private String password;
	private String email;
	private String userType;
	private String photo;
	private String clazzNo;
	private String clazzName;

	public User() {
		super();
	}

	public User(String userID, String userName, String password, String email, String userType, String photo,
			String clazzNo) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.userType = userType;
		this.photo = photo;
		this.clazzNo = clazzNo;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getClazzNo() {
		return clazzNo;
	}

	public void setClazzNo(String clazzNo) {
		this.clazzNo = clazzNo;
	}

	public String getClazzName() {
		return clazzName;
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", userType=" + userType + ", photo=" + photo + ", clazzNo=" + clazzNo + "]";
	}

}
