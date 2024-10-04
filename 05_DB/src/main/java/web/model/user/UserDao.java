package web.model.user;

public interface UserDao {

	public abstract User login(User user);
	
	public abstract User login(String userId, String pw);
	
}
