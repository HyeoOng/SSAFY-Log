<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 상세 정보</title>
</head>
<body>
	<h2>영화 상세 정보 페이지</h2>
	
	<br>
	<h3>제목 : ${movie.getTitle()} </h3>
	
	<img id="posterImg" src="${movie.getPosterUrl()}" alt="포스터 이미지">
	
	<div>감    독 : ${movie.getDirector()}</div><br>
	
	<div>상영 시간 : ${movie.getRunningTime()}</div><br>
	
	<div>장    르 : ${movie.getGenre()}</div><br>
	
	<div>요    약<br>
	 ${movie.getDesc()}</div><br><br>
	 
	 <a href="main?action=updateForm&title=${movie.getTitle()}">수정</a>
	 <a href="main?action=remove&title=${movie.getTitle()}">삭제</a>
	 <a href="main?action=movieList">목록으로</a>
	
	
</body>
</html>