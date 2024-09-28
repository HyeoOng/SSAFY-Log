package com.ssafy.movie.controller;

import java.io.IOException;
import java.util.List;

import com.ssafy.movie.model.dto.Movie;
import com.ssafy.movie.model.service.MovieService;
import com.ssafy.movie.model.service.MovieServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MovieService service = MovieServiceImpl.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		switch(action) {
		case "registForm":
			doRegistForm(req, resp);
			break;
		case "regist":
			doRegist(req, resp);
			break;
		case "movieList":
			doList(req, resp);
			break;
		case "detail":
			doDetail(req, resp);
			break;
		case "remove":
			doRemove(req, resp);
			break;
		case "updateForm":
			doUpdateForm(req,resp);
			break;
		case "update":
			doUpdate(req, resp);
			break;
		case "searchForm":
			doSearchForm(req, resp);
		case "search":
			doSearch(req, resp);
		}
	}

	private void doSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String search = req.getParameter("search");
		
		List<Movie> movieList = service.searchMovie(search);
		
		req.setAttribute("list", movieList);
		req.getRequestDispatcher("/WEB-INF/movie/searchForm.jsp").forward(req, resp);
		
	}

	private void doSearchForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/movie/searchForm.jsp").forward(req, resp);
		
	}

	private void doUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		String title = req.getParameter("title");
		String director = req.getParameter("director");
		int runningTime = Integer.parseInt(req.getParameter("runningTime"));
		String genre = req.getParameter("genre");
		String desc = req.getParameter("desc");
		String openDate = req.getParameter("openDate");
		String posterUrl = req.getParameter("posterUrl");
		
		Movie movie = new Movie(title, director, runningTime, genre, desc,openDate, posterUrl);
		movie.setId(id);
		
		service.modifyMovie(movie);
		
		req.setAttribute("movie", movie);
		
		req.getRequestDispatcher("/WEB-INF/movie/detail.jsp").forward(req, resp);
		
	}

	private void doUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		Movie movie = service.getMovie(title);
		
		req.setAttribute("movie", movie);
		req.getRequestDispatcher("/WEB-INF/movie/updateForm.jsp").forward(req, resp);
		
	}

	private void doRemove(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String title = req.getParameter("title");
		service.removeMovie(title);
		
		resp.sendRedirect("main?action=movieList");
	}

	private void doDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		Movie movie = service.getMovie(title);
        req.setAttribute("movie", movie);
        req.getRequestDispatcher("/WEB-INF/movie/detail.jsp").forward(req, resp);
	}

	private void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("list", service.getList());
        req.getRequestDispatcher("/WEB-INF/movie/movieList.jsp").forward(req, resp);
	}

	private void doRegist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String director = req.getParameter("director");
		int runningTime = Integer.parseInt(req.getParameter("runningTime"));
		String genre = req.getParameter("genre");
		String desc = req.getParameter("desc");
		String openDate = req.getParameter("openDate");
		String posterUrl = req.getParameter("posterUrl");
		
		Movie movie = new Movie(title, director, runningTime, genre, desc, openDate, posterUrl);
		
		service.registMovie(movie);
		
		req.setAttribute("list", service.getList());
		req.getRequestDispatcher("/WEB-INF/movie/movieList.jsp").forward(req, resp);		
	}

	private void doRegistForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/movie/registForm.jsp").forward(req, resp);		
	}
}
