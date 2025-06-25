package web.model.post;

import java.util.*;

import web.model.user.Study;

public interface PostService {

	// 전체 게시글 조회
    public abstract List<Post> getList();
    
    // 게시글 조회
    public abstract Post getPostByPostId(int postId);
    
    // 유저 별 가장 최근 등록된 게시글 정보 조회
    public abstract List<Post> getPostByDate(int study);

    // 게시글 작성
    public abstract void writePost(Post post);

    // 게시글 수정
    public abstract void modifyPost(Post post);

    // 게시글 삭제
    public abstract void removePost(int postId);
    
    // 가입된 스터디명 조회
    public abstract List<Study> getStudyName(String userId);
}
