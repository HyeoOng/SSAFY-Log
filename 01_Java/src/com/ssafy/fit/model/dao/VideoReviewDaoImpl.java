package com.ssafy.fit.model.dao;
import java.util.*;

import com.ssafy.fit.model.VideoReview;

public class VideoReviewDaoImpl implements VideoReviewDao {
	private static int reviewNo;
	private static Map<Integer, List<VideoReview>> videoReviewDb = new HashMap<>();
	private static VideoReviewDaoImpl instance;

	// 생성자
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
		int videoNo = videoReview.getVideoNo();

		// videoReviewDb에 videoNo로 하는 키가 존재하지 않을 경우
		if (!videoReviewDb.containsKey(videoNo)) {
			videoReviewDb.put(videoNo, new ArrayList<>()); // videoNo를 키로 하는 빈 배열을 할당하고
		}
		videoReviewDb.get(videoNo).add(videoReview); // 배열에 리뷰객체를 추가
		reviewNo++;
		return reviewNo;
	}

	@Override
	public List<VideoReview> selectReview(int videoNo) {
		// 리뷰가 없을 경우 빈 리스트를 반환
		return videoReviewDb.getOrDefault(videoNo, new ArrayList<>());
	}

	public boolean removeReview(int videoNo, String nickName, int reviewNo) {
		List<VideoReview> videoReviewList = videoReviewDb.get(videoNo);
		int removedIdx = -1;
		boolean removed = false;

		// 리스트에 아무것도 없는 경우(리뷰가 없는 경우) => 제거하지 않고 false 반환
		if (videoReviewList == null) return false;
		
		for(int i=0; i<videoReviewList.size(); i++) {
			if(videoReviewList.get(i).getNickName().equals(nickName) && videoReviewList.get(i).getReviewNo()==reviewNo) {
				removed = videoReviewList.remove(videoReviewList.get(i));
				removedIdx = i;
			}
		}
		// 리뷰를 삭제한 후, 남아있는 리뷰(삭제된 리뷰의 뒤에 있는 리뷰들)의 reviewNo를 하나씩 줄여준다.
		if(removed) {
			for(int i=removedIdx; i<videoReviewList.size(); i++) {
				int newReviewNo = videoReviewList.get(i).getReviewNo() - 1;
				videoReviewList.get(i).setReviewNo(newReviewNo);
			}
		}
		
		// 리스트가 비어있는 경우, 맵에서 해당 키를 제거
		if (videoReviewList.isEmpty())
			videoReviewDb.remove(videoNo);

		return removed;

	}
}
