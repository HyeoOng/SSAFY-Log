package web.model.user;

import java.io.Serializable;

public class User implements Serializable{
	
	private String userId, pw, name;

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userId, String pw, String name) {
		super();
		this.userId = userId;
		this.pw = pw;
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", pw=" + pw + ", name=" + name + "]";
	}
	
	

}
