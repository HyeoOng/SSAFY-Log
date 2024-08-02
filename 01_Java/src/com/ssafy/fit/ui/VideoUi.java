package com.ssafy.fit.ui;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ssafy.fit.model.Video;
import com.ssafy.fit.model.VideoReview;
import com.ssafy.fit.model.dao.VideoDaoImpl;
import com.ssafy.fit.model.dao.VideoReviewDaoImpl;
import com.ssafy.fit.util.SsafitUtil;

public class VideoUi {
    private VideoDaoImpl videoDao = VideoDaoImpl.getInstance();
    private VideoReviewDaoImpl videoReviewDao = VideoReviewDaoImpl.getInstance();
    private Scanner sc = new Scanner(System.in);
    private VideoReviewUi videoReviewUi = new VideoReviewUi();

    public void service() {
        while (true) {
        	SsafitUtil.printLine();
        	
            System.out.println("1. 영상목록");
            System.out.println("0. 이전으로");
            
            SsafitUtil.printLine();
            
            System.out.print("메뉴를 선택하세요: ");

            int menu = Integer.parseInt(sc.nextLine());

            switch (menu) {
                case 1:
                    listVideo();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }

    public void listVideo() {
        List<Video> videos = videoDao.selectVideo();
        
        SsafitUtil.printLine();
        
        System.out.println("전체 " + videos.size() + "개");
        
        SsafitUtil.printLine();
        
        for (Video video : videos) {
            System.out.println(video.getNo() + " " + video.getTitle());
        }
        
        SsafitUtil.printLine();

        System.out.println("1. 영상상세");
        System.out.println("0. 이전으로");
        
        SsafitUtil.printLine();
        
        System.out.print("메뉴를 선택하세요: ");
        
        int menu = Integer.parseInt(sc.nextLine());
        if (menu == 1) {
            detailVideo();
        }
    }

    public void detailVideo() {    	
        System.out.print("영상번호를 입력하세요: ");
        int videoNo = Integer.parseInt(sc.nextLine());
        Video video = videoDao.selectVideoByNo(videoNo);
        
        SsafitUtil.printLine();
        
        if (video != null) {
            System.out.println("번호: " + video.getNo());
            System.out.println("제목: " + video.getTitle());
            System.out.println("운동: " + video.getPart());
            System.out.println("영상 URL: " + video.getUrl());
            SsafitUtil.printLine();
            
            List<VideoReview> reviewList = videoReviewDao.selectReview(videoNo);
            
            videoReviewUi.listReview(videoNo);
            
            videoReviewUi.service(videoNo);
            
        } else {
            System.out.println("해당 번호의 영상이 존재하지 않습니다.");
        }
        SsafitUtil.printLine();
    }
}
