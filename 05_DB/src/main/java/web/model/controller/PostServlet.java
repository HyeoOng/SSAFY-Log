package web.model.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.model.post.PostService;
import web.model.post.PostServiceImpl;
import web.model.user.User;

import java.util.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=utf-8"); 
		
		JsonObject json = (JsonObject) JsonParser.parseReader(request.getReader());
		String sign = json.get("sign").getAsString();
		
		switch(sign) {
		case "listload":
			doListLoad(request, response);
			break;
		}
		
	}
	
	private void doListLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		List<Post> posts = service.getList();
		
		// Gson을 통해 posts를 json 객체로 변환하기
		Gson gson = new Gson();
        JsonObject retJson = new JsonObject(); // 비어있는 JsonObject -> 응답하는 내용으로 채울 예정
        
        if (posts != null) {
            // posts 리스트를 JSON 배열로 변환하여 추가
            retJson.add("posts", gson.toJsonTree(posts));
        } else {
        	retJson.addProperty("msg", "등록된 게시글이 존재하지 않습니다.");
        }
        
        out.append(retJson.toString()); // out 버퍼에 넣은 내용이 client에 전달됨
		out.close(); // 명시적으로 close() 해도 됨

	}	

}
