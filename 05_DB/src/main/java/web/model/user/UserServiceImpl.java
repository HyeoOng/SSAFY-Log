package web.model.user;

public class UserServiceImpl implements UserService{
	
	private static UserService instance = new UserServiceImpl();
	UserDao dao;
	
	private UserServiceImpl() {
		dao = UserDaoImpl.getInstance();
	}
	
	public static UserService getInstance() {
		return instance;
		
	}
	
	public User login(String userId, String pw) {
		return dao.login(userId, pw);
	}
	
	public User login(User user) {
		return dao.login(user);
	}

}
