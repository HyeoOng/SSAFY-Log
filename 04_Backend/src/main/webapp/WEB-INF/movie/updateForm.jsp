<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>${movie.getTitle() } 정보 수정</h2>
	   <form action="main" method="POST">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${movie.getId() }">
        <div>
            <label class="label">영화 제목 : </label>
            <input type="text" name="title" value="${movie.getTitle()}">
        </div>
        <div>
            <label class="label">영화 감독 : </label>
            <input type="text" name="director" value="${movie.getDirector()}">
        </div>
        <div>
            <label class="label">상영 시간 : </label>
            <input type="number" name="runningTime" value="${movie.getRunningTime()}">
        </div>
        <div>
            <label class="label">장르 : </label>
            <input type="text" name="genre" value="${movie.getGenre()}">
        </div>
        <div>
            <label class="label">개봉일 : </label>
            <input type="date" name="openDate" value="${movie.getOpenDate()}">
        </div>
        <div>
            <label class="label">요약 : </label>
            <textarea rows="3" cols="15" name="desc">${movie.getDesc()}</textarea>
        </div>
        <br>
        <div>
            포스터 등록<br>
            <label>이미지 url 입력 : </label>
            <input type="text" name="posterUrl" id="posterUrl" oninput="updateImage()" value="${movie.getPosterUrl()}">
        </div>
        <img id="imagePreview" src="" alt="이미지 미리보기">
        <br>
        
        <input type="submit" value="수정하기">    
    </form>
    
    <script>
function updateImage() {
    const url = document.getElementById('posterUrl').value;
    const imagePreview = document.getElementById('imagePreview');
    
    if (url) {
        imagePreview.src = url;
        imagePreview.style.display = 'block';
    } else {
        imagePreview.style.display = 'none';
    }
}
</script>
</body>
</html>