package web.model.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.model.post.Post;
import web.util.DBUtil;

public class UserDaoImpl implements UserDao{
	
	private static UserDao dao = new UserDaoImpl() {
		
	};
	
	private UserDaoImpl() {
		
	};
	
	public static UserDao getInstance() {
		return dao;
	}
	
	private DBUtil util = DBUtil.getInstance();
	

	@Override
	public User login(User user) {
		return login(user.getUserId(), user.getPw());
	}

	@Override
	public User login(String userId, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// List<Post> postList = new ArrayList<>();
		User user = null;
		
		String sql ="SELECT * FROM user WHERE userId=? AND pw=?";
		
		try {
			conn = util.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = new User();
				user.setName(rs.getString("name"));
				user.setUserId(rs.getString("userId"));
				user.setPw(rs.getString("pw"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}
		
		return user;
	}

}
