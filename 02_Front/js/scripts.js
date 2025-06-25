document.addEventListener("DOMContentLoaded", function () {
    fetch('data/videos.json')
        .then(response => response.json())
        .then(data => {
            const videoList = document.getElementById('video-list');
            let carouselInner = '';

            // 카드들을 3개씩 묶어서 슬라이드를 생성
            const chunkSize = 3;
            for (let i = 0; i < data.length; i += chunkSize) {
                const chunk = data.slice(i, i + chunkSize);
                let carouselItems = chunk.map(video => {
                    // 유튜브 비디오 ID 추출
                    const videoId = extractYouTubeVideoId(video.url);
                    if (videoId) {
                        const thumbnailUrl = `https://img.youtube.com/vi/${videoId}/0.jpg`;

                        // 제목 길이 제한 (예: 30자)
                        const maxTitleLength = 30;
                        let truncatedTitle = video.title;
                        if (truncatedTitle.length > maxTitleLength) {
                            truncatedTitle = truncatedTitle.substring(0, maxTitleLength) + '...';
                        }

                        return `
                            <div class="col-md-4 mb-4">
                                <a href="${video.url}" target="_blank" class="card h-100" style="text-decoration: none; color: inherit;">
                                    <div class="card h-100">
                                        <img src="${thumbnailUrl}" class="card-img-top" alt="${video.title}">
                                        <div class="card-body d-flex flex-column">
                                            <h5 class="card-title">${truncatedTitle}</h5>
                                            <div class="d-flex justify-content-between align-items-center mt-auto">
                                                <p class="card-text badge rounded-pill text-bg-warning mb-0">${video.part}</p>
                                                <p class="card-text mb-0">${video.channelName}</p>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                            </div>`;
                    }
                    return ''; // 비디오 ID가 없는 경우 빈 문자열 반환
                }).join('');

                carouselInner += `
                    <div class="carousel-item ${i === 0 ? 'active' : ''}">
                        <div class="row">
                            ${carouselItems}
                        </div>
                    </div>`;
            }

            videoList.innerHTML = carouselInner;
        })
        .catch(error => console.error('비디오 데이터를 가져오는 중 오류가 발생했습니다:', error));
});

// 유튜브 비디오 ID 추출 함수
function extractYouTubeVideoId(url) {
    const regExp = /^.*(youtu\.be\/|v\/|u\/\w\/|embed\/|watch\?v=|&v=|shorts\/)([^#\&\?]*).*/;
    const match = url.match(regExp);
    return (match && match[2].length === 11) ? match[2] : null;
}
