package web.model.post;

import java.util.*;

public interface PostService {

	// 전체 게시글 조회
    public abstract List<Post> getList();
    
    // 게시글 조회
    public abstract Post getPostByPostId(int postId);
    
    
    public abstract List<Post> getPostByDate(String date);

    // 게시글 작성
    public abstract void writePost(Post post);

    // 게시글 수정
    public abstract void modifyPost(Post post);

    // 게시글 삭제
    public abstract void removePost(int postId);
}
