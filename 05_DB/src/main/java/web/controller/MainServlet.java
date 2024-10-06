package web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.model.user.User;
import web.model.user.UserService;
import web.model.user.UserServiceImpl;

public class MainServlet extends HttpServlet {
	UserService userService;

	@Override
	public void init() throws ServletException {
		userService = UserServiceImpl.getInstance();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html; charset=utf-8"); 

		JsonObject json = (JsonObject) JsonParser.parseReader(request.getReader());
		String sign = json.get("sign").getAsString();

		JsonObject retJson = new JsonObject(); // 비어있는 JsonObject -> 응답하는 내용으로 채울 에정

		switch (sign) {
		case "login":
			String id = json.get("id").getAsString();
			String pw = json.get("pw").getAsString();

			try {
				User user = userService.login(id, pw);
				System.out.println("login은 성공");
				if (user != null) {
					HttpSession session = request.getSession();
					session.setAttribute("user", user);

					retJson.addProperty("name", user.getName());
					// out.print("login ok");
				} else {
					retJson.addProperty("msg", "다시 로그인 해주세요.");
					// out.print("다시 로그인 해주세요");
				}
			} catch (Exception e) {
				retJson.addProperty("msg", "다시 로그인 해주세요.");
				// out.print("다시 로그인 해주세요");
			}

			out.append(retJson.toString()); // out 버퍼에 넣은 내용이 client에 전달됨
			out.close(); // 명시적으로 close() 해도 됨
		}

	}
}
