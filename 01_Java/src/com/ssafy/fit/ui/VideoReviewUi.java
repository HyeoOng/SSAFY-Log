package com.ssafy.fit.ui;
import java.util.List;
import java.util.Scanner;

import com.ssafy.fit.model.VideoReview;
import com.ssafy.fit.model.dao.VideoReviewDaoImpl;
import com.ssafy.fit.util.SsafitUtil;

public class VideoReviewUi {
    private VideoReviewDaoImpl videoReviewDao = VideoReviewDaoImpl.getInstance();
    private Scanner sc = new Scanner(System.in);

    public void service(int videoNo) {
        while (true) {
        	SsafitUtil.printLine();
        	
            System.out.println("0. 이전으로");
            System.out.println("1. 리뷰등록");
            System.out.println("2. 리뷰삭제");
            
            SsafitUtil.printLine();
            
            System.out.print("메뉴를 선택하세요: ");

            int menu = Integer.parseInt(sc.nextLine());

            switch (menu) {
            	case 2:
            		removedReview(videoNo);
            		break;
                case 1:
                    registReview(videoNo);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }

    public void listReview(int videoNo) {
        List<VideoReview> reviews = videoReviewDao.selectReview(videoNo);
        
        SsafitUtil.printLine();
        
        System.out.println("영상리뷰: " + reviews.size() + "개");
        
        SsafitUtil.printLine();
        
        for (VideoReview review : reviews) {
            System.out.println(review.getReviewNo() + " " + review.getNickName() + " " + review.getContent());
        }
        
    }

    public void registReview(int videoNo) {
    	List<VideoReview> reviews = videoReviewDao.selectReview(videoNo);
    	
        System.out.print("닉네임을 입력하세요: ");
        String nickname = sc.nextLine();
        System.out.print("내용을 입력하세요: ");
        String content = sc.nextLine();

        VideoReview review = new VideoReview();
        review.setReviewNo(reviews.size()+1);
        review.setVideoNo(videoNo);
        review.setNickName(nickname);
        review.setContent(content);

        int result = videoReviewDao.insertReview(review);
        if (result > 0) {
            System.out.println("리뷰가 성공적으로 등록되었습니다.");
        } else {
            System.out.println("리뷰 등록에 실패했습니다.");
        }
    }
    
    // 추가한 기능 - 리뷰 삭제 기능
    public void removedReview(int videoNo) {
    	List<VideoReview> reviews = videoReviewDao.selectReview(videoNo);
    	
    	if(reviews.size()==0) {
    		System.out.println("리뷰가 존재하지 않습니다. 리뷰를 입력해주세요.");
    		return;
    	} else {
	    	System.out.print("삭제할 리뷰를 작성한 닉네임을 입력하세요: ");
	    	String nickname = sc.nextLine();
	        System.out.print("삭제할 리뷰의 번호를 입력하세요: ");
	        int reviewNo = Integer.parseInt(sc.nextLine());
	        
	        boolean removed = videoReviewDao.removeReview(videoNo, nickname, reviewNo);
	        
	        if(removed) {
	        	System.out.println("리뷰가 성공적으로 삭제되었습니다.");
	        } else {
	        	System.out.println("해당 리뷰가 존재하지 않습니다.");
	        }
    	}
    }
    
}
