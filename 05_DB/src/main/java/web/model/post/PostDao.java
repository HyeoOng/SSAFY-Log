package web.model.post;

import java.util.List;

import web.model.user.Study;

public interface PostDao {
	
	// 게시글 모두 조회
	public List<Post> selectAll();
	
	// 같은 날 게시글 모두 조회
	public List<Post> selectDate(int study);
	
	// 게시글 하나 조회 
	public Post selectOne(int postId);
	 
	// 게시글 작성
	public void insertPost(Post post);
	
	// 게시글 수정
	public void updatePost(Post post);
	
	// 게시글 삭제 
	public void deletePost(int postId);
	
	// 가입된 스터디 목록 가져오기
	public List<Study> selectStudyName(String userId);

}
