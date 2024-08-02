package com.ssafy.fit.model;

public class VideoReview {

	private int videoNo;
	private int reviewNo;
	private String nickName;
	private String content;
	
	public VideoReview(){};
	
	public VideoReview(int videoNo, int reviewNo, String nickname, String content) {
		setVideoNo(videoNo);
		setReviewNo(reviewNo);
		setNickName(nickName);
		setContent(content);
	}

	public int getVideoNo() {
		return videoNo;
	}

	public void setVideoNo(int videoNo) {
		this.videoNo = videoNo;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getNickName() {	
		return nickName;
	}

	public void setNickName(String nickName) {
		if (nickName == null || nickName.isEmpty()) {
			throw new IllegalArgumentException("닉네임은 null이거나 비어있을 수 없습니다.");
		}

		if (nickName.length() < 3 || nickName.length() > 20) {
			throw new IllegalArgumentException("닉네임은 최소 2자 이상, 최대 20자 이하로 작성해주세요.");
		}
		
		this.nickName = nickName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if(content == null) {
			// content point 입력 예외처리 예정
		}else{
		this.content = content;
		}
	}

	@Override
	public String toString() {
		return "VideoReview [video reviewNo=" + reviewNo + ", nickName=" + nickName + ", content="
				+ content + "]";
	}

	
}
