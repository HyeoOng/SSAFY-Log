document.addEventListener("DOMContentLoaded", function() {
  fetch('data/videos.json')
      .then(response => response.json())
      .then(data => {
          const videoList = document.getElementById('video-list');
          data.forEach(video => {
              // 유튜브 비디오 ID 추출
              const videoId = extractYouTubeVideoId(video.url);
              const thumbnailUrl = `https://img.youtube.com/vi/${videoId}/0.jpg`;

              const videoCard = `
                  <div class="col-md-4">
                      <div class="card mb-4">
                          <img src="${thumbnailUrl}" class="card-img-top" alt="${video.title}">
                          <div class="card-body">
                              <h5 class="card-title">${video.title}</h5>
                              <div class="d-flex justify-content-between align-items-center">
                                  <p class="card-text">${video.part}</p>
                                  <p class="card-text">${video.channelName}</p>
                              </div>
                              <a href="${video.url}" target="_blank" class="btn btn-primary">Watch Video</a>
                          </div>
                      </div>
                  </div>`;
              videoList.innerHTML += videoCard;
          });
      });
});

// 유튜브 비디오 ID 추출 함수
function extractYouTubeVideoId(url) {
  const regExp = /^.*(youtu\.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=|shorts\/)([^#\&\?]*).*/;
  const match = url.match(regExp);
  return (match && match[2].length == 11) ? match[2] : null;
}
