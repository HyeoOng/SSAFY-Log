package web.model.post;

import java.util.List;

public interface PostDao {
	
	// 게시글 모두 조회
	public List<Post> selectAll();
	
	// 같은 날 게시글 모두 조회
	public List<Post> selectDate(String userId);
	
	// 게시글 하나 조회 
	public Post selectOne(int postId);
	 
	// 게시글 작성
	public void insertPost(Post post);
	
	// 게시글 수정
	public void updatePost(Post post);
	
	// 게시글 삭제 
	public void deletePost(int postId);

}
