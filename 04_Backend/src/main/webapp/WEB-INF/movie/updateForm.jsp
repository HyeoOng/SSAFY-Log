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
        <input type="hidden" name="id" value="${id}">
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
            <input type="text" name="posterUrl" id="posterUrl" value="${movie.getPosterUrl()}">
            <button id="changePoster" type="button">변경할 이미지 확인하기</button>
        </div>
        <img id="imagePreview" src="${movie.getPosterUrl() }" alt="이미지 미리보기">
        <br>
        
        <input type="submit" value="수정하기">    
    </form>
<script>
const changeBtn = document.getElementById("changePoster");
changeBtn.addEventListener('click', function(event){
    event.preventDefault(); // 폼 제출 방지
    const url = document.getElementById("posterUrl").value;
    const imagePreview = document.getElementById("imagePreview");

    // URL 유효성 검사
    if (url && isValidUrl(url)) {
        imagePreview.src = url;
    } else {
        // URL이 유효하지 않으면 기본 URL 설정
		alert("올바른 Url이 아닙니다!")
        imagePreview.src = posterUrl; // JSP에서 전달된 기본 URL
    }
});

// URL 유효성 검사 함수
function isValidUrl(string) {
    try {
        new URL(string);
        return true;
    } catch (_) {
        return false;  
    }
}

</script>
    
</body>
</html>