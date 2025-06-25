package web.model.post;

import java.util.List;

import web.model.user.Study;

public class PostServiceImpl implements PostService{

	private static PostService service = new PostServiceImpl();
	
	private PostServiceImpl () {};
	
	public static PostService getInstance() {
		return service;
	}

	private PostDao dao = PostDaoImpl.getInstance();
	
	@Override
	public List<Post> getList() {
		
		return dao.selectAll();
	}

	@Override
	public Post getPostByPostId(int postId) {
		return dao.selectOne(postId);
	}

	@Override
	public List<Post> getPostByDate(int study) {
		return dao.selectDate(study);
	}

	@Override
	public void writePost(Post post) {
		dao.insertPost(post);
	}

	@Override
	public void modifyPost(Post post) {
		dao.updatePost(post);
	}

	@Override
	public void removePost(int postId) {
		dao.deletePost(postId);
	}

	@Override
	public List<Study> getStudyName(String userId) {
		return dao.selectStudyName(userId);
	}

}
