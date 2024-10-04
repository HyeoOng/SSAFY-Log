package web.model.comment;

import java.io.Serializable;

public class Comment implements Serializable{
	
	private int commentId, postId;
	private String commentUserId, comment, commentDate;
	private int n=1;
	
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Comment(int postId, String commentUserId, String comment, String commentDate) {
		super();
		this.commentId = n++;
		this.postId = postId;
		this.commentUserId = commentUserId;
		this.comment = comment;
		this.commentDate = commentDate;
	}


	public int getCommentId() {
		return commentId;
	}


	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}


	public int getPostId() {
		return postId;
	}


	public void setPostId(int postId) {
		this.postId = postId;
	}


	public String getCommentUserId() {
		return commentUserId;
	}


	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public String getCommentDate() {
		return commentDate;
	}


	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}


	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", postId=" + postId + ", commentUserId=" + commentUserId
				+ ", comment=" + comment + ", commentDate=" + commentDate + "]";
	}
	
	
	

}
