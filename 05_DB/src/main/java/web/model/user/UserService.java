package web.model.user;

public interface UserService {
	
	
	
	public User login(String userId, String pw);
	
	public User login(User user);
}
