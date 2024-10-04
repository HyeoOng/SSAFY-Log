package web.model.post;

import java.io.Serializable;

public class Post implements Serializable{
	
	private int postId;
	private String userId, date, problemNumber, code, content;
	private int n = 1;
	
	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Post(String userId, String date, String problemNumber, String code, String content) {
		super();
		this.postId = n++;
		this.userId = userId;
		this.date = date;
		this.problemNumber = problemNumber;
		this.code = code;
		this.content = content;
	}


	public int getPostId() {
		return postId;
	}


	public void setPostId(int postId) {
		this.postId = postId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getProblemNumber() {
		return problemNumber;
	}


	public void setProblemNumber(String problemNumber) {
		this.problemNumber = problemNumber;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return "Post [postId=" + postId + ", userId=" + userId + ", date=" + date + ", problemNumber=" + problemNumber
				+ ", code=" + code + ", content=" + content + "]";
	}
	
	

}


