package com.ssafy.movie.model.service;

import java.util.List;

import com.ssafy.movie.model.dto.Movie;

public interface MovieService {
	// 등록된 모든 영화 정보 조회
	public abstract List<Movie> getList();
	
	// title과 일치하는 영화 정보(1개) 조회
	public abstract Movie getMovie(String title);
	
	// title이 포함된 영화 정보(여러 개) 검색
	public abstract List<Movie> searchMovie(String title);
	
	// title인 영화 정보 삭제
	public abstract void removeMovie(String title);
	
	// title인 영화 정보 수정
	public abstract void modifyMovie(int id, Movie movie);
	
	// 영화 정보 등록
	public abstract void registMovie(Movie movie);
}
