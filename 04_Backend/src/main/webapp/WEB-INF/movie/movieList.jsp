<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.ssafy.movie.model.dto.Movie"%>
<%
List<Movie> movieList = (List<Movie>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SSAFY 영화 사이트</title>
</head>
<body>
    <h2>영화 목록</h2>
    
    <div class="movieListTable">
        <table>
            <tr>
                <th> 제목 </th>
                <th> 감독 </th>
                <th> 상영시간 </th>
                <th> 장르 </th>
            </tr>
            <%
            if (movieList != null && !movieList.isEmpty()) {
                for (Movie movie : movieList) {
            %>
            <tr>
                <td> <a href="main?action=detail&title=<%= movie.getTitle() %>"><%= movie.getTitle() %></a></td>
                <td> <%= movie.getDirector() %></td>
                <td> <%= movie.getRunningTime() %></td>
                <td> <%= movie.getGenre() %></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4">영화를 등록해주세요</td>
            </tr>
            <%
            }
            %>
        </table>
        <br>
        <a href="main?action=registForm">영화 추가 등록</a>
    </div>
</body>
</html>