package com.ssafy.movie.model.repository;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.movie.model.dto.Movie;

public class MovieRepositoryImpl implements MovieRepository{
	private static MovieRepository repo = new MovieRepositoryImpl();
	
	private MovieRepositoryImpl() {}
	
	public static MovieRepository getInstance() {
		return repo;
	}
	
	private List<Movie> list = new ArrayList<>();

	@Override
	public List<Movie> selectAll() {
		return list;
	}

	@Override
	public Movie selectOne(String title) {
		for(Movie movie : list) {
			if(movie.getTitle().equals(title)) return movie;
		}
		return null;
	}

	@Override
	public List<Movie> selectGroup(String title) {
		List<Movie> retList = new ArrayList<>();
		for(Movie movie : list) {
			if(movie.getTitle().contains(title)) retList.add(movie);
		}
		return retList;
	}

	@Override
	public void deleteMovie(String title) {
		// System.out.println("Before delation: " + list);
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).getTitle().equals(title)) {
				list.remove(i);
				// System.out.println("After deletion: " + list);
				return;
			}
		}
		
	}

	@Override
	public void insertMovie(Movie movie) {
		list.add(movie);
	}

	@Override
	public void updateMovie(Movie movie) {
		int id = movie.getId();
		for(int i=0; i<list.size(); i++) {
			if(movie.getId()==id) {
				list.set(i, movie);
				return;
			}
		}
		
	}

}
