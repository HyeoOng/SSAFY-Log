<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 등록</title>
<style>
    .label {
        display: inline-block;
        width: 90px;
        text-align: left;
        margin-right: 10px;
    }
    .form-group {
        margin-bottom: 10px;
    }
    #imagePreview {
        margin-left: 160px; /* 라벨 너비와 동일하게 설정 */
        display: none; /* 초기에는 숨김 */
    }
</style>
</head>
<body>
<div>
    <h2>영화 등록하기</h2>
    
    <form action="main" method="POST">
        <input type="hidden" name="action" value="regist">
        <div>
            <label class="label">영화 제목 : </label>
            <input type="text" name="title">
        </div>
        <div>
            <label class="label">영화 감독 : </label>
            <input type="text" name="director">
        </div>
        <div>
            <label class="label">상영 시간 : </label>
            <input type="number" name="runningTime">
        </div>
        <div>
            <label class="label">장르 : </label>
            <input type="text" name="genre">
        </div>
        <div>
            <label class="label">개봉일 : </label>
            <input type="date" name="openDate">
        </div>
        <div>
            <label class="label">요약 : </label>
            <textarea rows="3" cols="15" name="desc"></textarea>
        </div>
        <br>
        <div>
            포스터 등록<br>
            <label>이미지 url 입력 : </label>
            <input type="text" name="posterUrl" id="posterUrl" oninput="updateImage()">
        </div>
        <img id="imagePreview" src="" alt="이미지 미리보기">
        <br>
        
        <input type="submit" value="영화 등록하기">    
    </form>
</div>

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