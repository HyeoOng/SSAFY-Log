// 페이지 로드 시 서버에서 데이터를 가져옵니다!
document.addEventListener('DOMContentLoaded', async function() {
	// 해당 유저의 가입되어 있는 스터디 목록을 불러오는 과정
	let data = {sign: "studyload", date: getTodayDate()};
	data = JSON.stringify(data);
	
	let response = await fetch('post', {method: "post", body: data});
	
	data = await response.json();
	
	if (data.name) {
	    document.getElementById('studyTitle').textContent = `${data.name}님의 스터디 페이지`;
	}
	
	if(data.studies){
		userData.studies = data.studies;
		createStudyButtons();
		if (userData.studies.length > 0) {
		    showStudyMembers(userData.studies[0].study);
		}
	}

});

const userData = {
    studies: null,
};
function createStudyButtons() {
	const buttonContainer = document.getElementById('studyButtons');
    buttonContainer.innerHTML = ''; // 기존 버튼을 초기화
    if (Array.isArray(userData.studies)) {
        userData.studies.forEach(study => {
            const button = document.createElement('button');
            button.textContent = study.studyName;
            button.className = 'study-button';
            button.onclick = () => showStudyMembers(study.study);
            buttonContainer.appendChild(button);
        });
    }
}

async function showStudyMembers(studyId) {
	let data = {sign: "studyFirstPostLoad", studyId : studyId};
	data = JSON.stringify(data);
	
	let response = await fetch('post', {method: "POST", body:data});
	
	/*if (!response.ok) {
        console.error('서버 응답 오류:', response.statusText);
        return;
    }*/
	
	data = await response.json();
	
	let studyMembers = data.studyMembers;
	
    const currentStudy = document.getElementById('currentStudy');
    const memberList = document.getElementById('memberList');
    const study_id = userData.studies.find(s => s.study === studyId);
    
    currentStudy.textContent = study_id.studyName;
    memberList.innerHTML = '';

    studyMembers.forEach(member => {
        const li = document.createElement('li');
        li.className = `member-item ${member.date==getTodayDate() ? 'posted' : ''}`;
        li.textContent = member.name;
        
        if (member.date==getTodayDate()) {
            const postContent = document.createElement('div');
            postContent.className = 'post-content';
            postContent.textContent = member.content;
            postContent.style.display = 'none';
            li.appendChild(postContent);

            li.onclick = () => {
                postContent.style.display = postContent.style.display === 'none' ? 'block' : 'none';
            };
        }

        memberList.appendChild(li);
    });
}

function getTodayDate() {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 1을 더합니다.
    const day = String(today.getDate()).padStart(2, '0'); // 일자를 2자리로 맞춥니다.

    return `${year}-${month}-${day}`;
}
