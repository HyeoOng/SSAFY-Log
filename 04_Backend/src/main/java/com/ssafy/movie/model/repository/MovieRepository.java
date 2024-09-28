package com.ssafy.movie.model.repository;

import java.util.List;

import com.ssafy.movie.model.dto.Movie;

public interface MovieRepository {
	// 모든 영화 정보 조회(select * from movie)
	public abstract List<Movie> selectAll();
	
	// 영화 상세 정보(1개) 조회(select * from movie where title=title)
	public abstract Movie selectOne(String title);
	
	// 영화 상세 정보(포함) 조회(select * from movie where title like "%title%")
	public abstract List<Movie> selectGroup(String title);
	
	// 영화 정보 삭제 (delete from movie where title=title)
	public abstract void deleteMovie(String title);
	
	// 영화 정보 등록(insert into movie ..)
	public abstract void insertMovie(Movie movie);
	
	// 영화 정보 수정(update movie set..)
	public abstract void updateMovie(Movie movie);
}
