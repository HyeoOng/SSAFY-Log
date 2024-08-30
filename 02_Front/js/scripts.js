document.addEventListener("DOMContentLoaded", function() {
  fetch('data/videos.json')
      .then(response => response.json())
      .then(data => {
          const videoList = document.getElementById('video-list');
          data.forEach(video => {
              const videoCard = `
                  <div class="col-md-4">
                      <div class="card mb-4">
                          <img src="${video.thumbnail}" class="card-img-top" alt="${video.title}">
                          <div class="card-body">
                              <h5 class="card-title">${video.title}</h5>
                              <p class="card-text">${video.description}</p>
                              <a href="#" class="btn btn-primary">Watch Video</a>
                          </div>
                      </div>
                  </div>`;
              videoList.innerHTML += videoCard;
          });
      });
});
