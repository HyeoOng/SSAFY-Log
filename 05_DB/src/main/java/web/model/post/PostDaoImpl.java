package web.model.post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import web.model.user.Study;
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
				post = new Post();
				post.setPostId(rs.getInt(1));
				post.setUserId(rs.getString(2));
				post.setStudy(rs.getInt(3));
				post.setDate(rs.getString(4));
				post.setProblemNumber(rs.getString(5));
				post.setCode(rs.getString(6));
				post.setContent(rs.getString(7));
				
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

        String sql = "INSERT INTO post VALUES(?,?,?,?,?,?)";

        try {
            conn = util.getConnection();

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, post.getUserId());
            pstmt.setString(2, post.getDate());
            pstmt.setInt(3, post.getStudy());
            pstmt.setString(4, post.getProblemNumber());
            pstmt.setString(5, post.getCode());
            pstmt.setString(6, post.getContent());

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
	public List<Post> selectDate(int study) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Post> postList = new ArrayList<>();
		Post post = null;
		
		// 각 유저별 가장 최근 등록된 게시글 정보를 가지고 온다.
		String sql = "SELECT postid, userid, study, date, problemNumber, code, content, name\r\n"
				+ "FROM (\r\n"
				+ "    SELECT post.postId, post.userId, post.date, post.study, post.problemNumber, post.code, post.content , user.name as name,\r\n"
				+ "           ROW_NUMBER() OVER (PARTITION BY userId ORDER BY date DESC) as rn\r\n"
				+ "    FROM post left join user on post.userid = user.userid\r\n"
				+ "    WHERE post.study = ?\r\n"
				+ ") sub \r\n"
				+ "WHERE rn = 1;";
		
		try {
			conn = util.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, study);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				post = new Post();
				
				post.setPostId(rs.getInt(1));
				post.setUserId(rs.getString(2));
				post.setStudy(rs.getInt(3));
				post.setDate(rs.getString(4));
				post.setProblemNumber(rs.getString(5));
				post.setCode(rs.getString(6));
				post.setContent(rs.getString(7));
				post.setName(rs.getString(8));
				
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
				post.setStudy(rs.getInt(3));
				post.setDate(rs.getString(4));
				post.setProblemNumber(rs.getString(5));
				post.setCode(rs.getString(6));
				post.setContent(rs.getString(7));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			util.close(rs, pstmt, conn);
		}
		// TODO Auto-generated method stub
		return post;
	}

	@Override
	public List<Study> selectStudyName(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Study> studyNameList = new ArrayList<>();
		Study study;
		
		String sql = "select * from study where study in (select study from studyMember where userId = ?)";
		
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				study = new Study();
				study.setStudy(rs.getString(1));
				study.setStudyName(rs.getString(2));
				studyNameList.add(study);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			util.close(rs, pstmt, conn);
		}

		return studyNameList;
	}

}
