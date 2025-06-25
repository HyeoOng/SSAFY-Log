// 페이지 로드 시 서버에서 데이터를 가져옵니다!
document.addEventListener('DOMContentLoaded', async function() {
	// const name = getQueryParam('name'); // URL에서 name 파라미터 추출
    let data = { sign: 'listload', name}; // name을 포함한 데이터 객체 생성
	data = JSON.stringify(data);
	
    let response = await fetch('post', {method: "POST", body:data});  // 서버의 엔드포인트 URL
	
	let result = await response.json();
	console.log(result);
	
	if(result.posts){
		console.log("데이터 불러오기 성공");
		displayPosts(result.posts);
	}else{
		const postModal = document.getElementById('postModal');
		postModal.innerHTML = result.msg;
	}

});

const postGrid = document.getElementById('postGrid');
const modal = document.getElementById('postModal');
const closeBtn = document.getElementsByClassName('close')[0];

function displayPosts(posts) {
    postGrid.innerHTML = ''; // 기존 내용을 지웁니다.
    posts.forEach(post => {
        const postCard = document.createElement('div');
        postCard.className = 'post-card';
        postCard.innerHTML = `
            <h2>문제 번호: ${post.problemNumber}</h2>
            <p>작성자: ${post.userId}</p>
            <p>날짜: ${post.date}</p>
            <p>${post.content.substring(0, 50)}...</p>
        `;
        postCard.onclick = () => showPostDetails(post);
        postGrid.appendChild(postCard);
    });
}

function showPostDetails(post) {
	document.getElementById('modalTitle').textContent = `문제 번호: ${post.problemNumber}`;
	document.getElementById('modalAuthor').textContent = `작성자: ${post.userId}`;
	document.getElementById('modalDate').textContent = `날짜: ${post.date}`;
	document.getElementById('modalContent').textContent = post.content;
	document.getElementById('modalCode').textContent = post.code;
	modal.style.display = 'block';
}

closeBtn.onclick = () => {
	modal.style.display = 'none';
}

window.onclick = (event) => {
	if (event.target == modal) {
		modal.style.display = 'none';
	}
}