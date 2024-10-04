package web.model.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.post.PostService;
import web.model.post.PostServiceImpl;
import java.util.*;
import web.model.post.Post;

@WebServlet("/post")
public class PostServlet extends HttpServlet{
		
	private PostService service = PostServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}
	
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		switch(action) {
		case "sign-in":
			doSign(req, resp);
			break;
		}
	}

	private void doSign(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Post> list = service.getList();
		req.setAttribute("posts", list);
		
		req.getRequestDispatcher("post.html").forward(req, resp);
	}
	
	
	

}
