package web.model.post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import web.util.DBUtil;

public class PostDaoImpl implements PostDao{
	
	// 싱글턴 
	private DBUtil util = DBUtil.getInstance();
	
	private PostDaoImpl() {
		
	}
	
	private static PostDao dao = new PostDaoImpl();
	
	public static PostDao getInstance() {
		return dao;
	}
	
	@Override
	public List<Post> selectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Post> postList = new ArrayList<>();
		Post post = null;
		
		try {
			conn = util.getConnection();
			
			String sql = "SELECT * FROM Post";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				post.setPostId(rs.getInt(1));
				post.setUserId(rs.getString(2));
				post.setDate(rs.getString(3));
				post.setProblemNumber(rs.getString(4));
				post.setCode(rs.getString(5));
				post.setContent(rs.getString(6));
				
				postList.add(post);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			util.close(rs, pstmt, conn);
		}
		// TODO Auto-generated method stub
		return postList;
	}

	@Override
	public void insertPost(Post post) {
		Connection conn = null;
        PreparedStatement pstmt = null;

        String sql = "INSERT INTO post VALUES(?,?,?,?,?)";

        try {
            conn = util.getConnection();

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, post.getUserId());
            pstmt.setString(2, post.getDate());
            pstmt.setString(3, post.getProblemNumber());
            pstmt.setString(4, post.getCode());
            pstmt.setString(5, post.getContent());

            int result = pstmt.executeUpdate();


            System.out.println(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            util.close(pstmt, conn);
        }
		
	}

	@Override
	public void updatePost(Post post) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE Post SET ProblemNumber=?, Code=?, Content=? WHERE postId=?";
		
		try {
			conn = util.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, post.getProblemNumber());
			pstmt.setString(2, post.getCode());
			pstmt.setString(3, post.getContent());
			pstmt.setInt(4, post.getPostId());
			
			int result = pstmt.executeUpdate();
			System.out.println("업데이트 성공여부 :: " +result);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}
		
	}

	@Override
	public void deletePost(int postId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM Post WHERE postId = ?";
		
		try {
			conn = util.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, postId);
			
			int result = pstmt.executeUpdate();
			System.out.println("삭제 성공여부 :: " +result);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close(pstmt, conn);
		}
		
	}

	@Override
	public List<Post> selectDate(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Post> postList = new ArrayList<>();
		Post post = null;
		
		try {
			conn = util.getConnection();
			
			String sql = "SELECT * FROM Post WHRER userId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				post.setPostId(rs.getInt(1));
				post.setUserId(rs.getString(2));
				post.setDate(rs.getString(3));
				post.setProblemNumber(rs.getString(4));
				post.setCode(rs.getString(5));
				post.setContent(rs.getString(6));
				
				postList.add(post);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			util.close(rs, pstmt, conn);
		}
		// TODO Auto-generated method stub
		return postList;
	}

	@Override
	public Post selectOne(int postId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Post> postList = new ArrayList<>();
		Post post = null;
		
		try {
			conn = util.getConnection();
			
			String sql = "SELECT * FROM Post WHERE postId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				post.setPostId(rs.getInt(1));
				post.setUserId(rs.getString(2));
				post.setDate(rs.getString(3));
				post.setProblemNumber(rs.getString(4));
				post.setCode(rs.getString(5));
				post.setContent(rs.getString(6));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			util.close(rs, pstmt, conn);
		}
		// TODO Auto-generated method stub
		return post;
	}

}
