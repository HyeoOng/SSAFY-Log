/**
 * 
 */

const userData = {
            userId: 'a',
            studies: [
                { id: 1, name: 'ㅎ알고리즘 스터디' },
                { id: 2, name: 'CS 스터디' }
            ],
            studyMembers: {
                1: [
                    { userId: 'a', name: '우성문', posted: true, content: '알고리즘 스터디 내용입니다.' },
                    { userId: 'b', name: '박다희', posted: false },
                    { userId: 'c', name: '나혜원', posted: true, content: '알고리즘 공부했습니다.' }
                ],
                2: [
                    { userId: 'a', name: '우성문', posted: false },
                    { userId: 'b', name: '박다희', posted: true, content: 'CS 스터디 내용입니다.' },
                    { userId: 'd', name: '김철수', posted: true, content: 'CS 공부했습니다.' }
                ]
            }
        };

        function createStudyButtons() {
            const buttonContainer = document.getElementById('studyButtons');
            userData.studies.forEach(study => {
                const button = document.createElement('button');
                button.textContent = study.name;
                button.className = 'study-button';
                button.onclick = () => showStudyMembers(study.id);
                buttonContainer.appendChild(button);
            });
        }

        function showStudyMembers(studyId) {
            const currentStudy = document.getElementById('currentStudy');
            const memberList = document.getElementById('memberList');
            const study = userData.studies.find(s => s.id === studyId);
            
            currentStudy.textContent = study.name;
            memberList.innerHTML = '';

            userData.studyMembers[studyId].forEach(member => {
                const li = document.createElement('li');
                li.className = `member-item ${member.posted ? 'posted' : ''}`;
                li.textContent = member.name;
                
                if (member.posted) {
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

        // 초기화
        createStudyButtons();
        if (userData.studies.length > 0) {
            showStudyMembers(userData.studies[0].id);
        }