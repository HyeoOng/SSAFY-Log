package com.ssafy.movie.model.service;

import java.util.List;

import com.ssafy.movie.model.dto.Movie;
import com.ssafy.movie.model.repository.MovieRepository;
import com.ssafy.movie.model.repository.MovieRepositoryImpl;

public class MovieServiceImpl implements MovieService{
	
	MovieRepository repo = MovieRepositoryImpl.getInstance();
	
	private static MovieService service = new MovieServiceImpl();
	
	private MovieServiceImpl() {}
	
	public static MovieService getInstance() {
		return service;
	}

	@Override
	public List<Movie> getList() {
		return repo.selectAll();
	}

	@Override
	public Movie getMovie(String title) {
		return repo.selectOne(title);
	}

	@Override
	public List<Movie> searchMovie(String title) {
		return repo.selectGroup(title);
	}

	@Override
	public void removeMovie(String title) {
		repo.deleteMovie(title);		
	}

	@Override
	public void modifyMovie(Movie movie) {
		repo.updateMovie(movie);		
	}

	@Override
	public void registMovie(Movie movie) {
		repo.insertMovie(movie);		
	}
	
	

}
