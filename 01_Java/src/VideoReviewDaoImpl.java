import java.util.*;

public class VideoReviewDaoImpl implements VideoReviewDao {
	private static int reviewNo;
	private static Map<Integer, List<VideoReview>> videoReviewDb = new HashMap<>();
	private static VideoReviewDaoImpl instance;

	private VideoReviewDaoImpl() {
	}

	public static VideoReviewDaoImpl getInstance() {
		if (instance == null) {
			instance = new VideoReviewDaoImpl();
		}
		return instance;
	}

	@Override
	public int insertReview(VideoReview videoReview) {
		int videoN = videoReview.getVideoNo();
		if (videoReviewDb.containsKey(videoN)) {
			videoReviewDb.get(videoN).add(videoReview);
		} else {
			List<VideoReview> videoReviewList = new ArrayList<>();
			videoReviewDb.put(videoN, videoReviewList);
		}
		reviewNo++;
		return reviewNo;
	}

	@Override
	public List<VideoReview> selectReview(int videoNo) {

		return videoReviewDb.get(videoNo);
	}
	
	
	public boolean removeReview(VideoReview videoReview) {
		int videoNo = videoReview.getVideoNo();
		boolean rm = false;
		List<VideoReview> videoReviewList = videoReviewDb.get(videoNo);
		for(int i=0; i<videoReviewList.size(); i++) {
			if(videoReviewList.get(i) == videoReview) {
				videoReviewList.remove(i);
				rm = true;
				break;
			}
		}
		if(rm) {
			videoReviewDb.put(videoNo, videoReviewList);
		} 
		return rm;
		
	}
}
