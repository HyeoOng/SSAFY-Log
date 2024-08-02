import java.util.List;
import java.util.Scanner;

public class VideoReviewUi {
    private VideoReviewDaoImpl videoReviewDao = VideoReviewDaoImpl.getInstance();
    private Scanner sc = new Scanner(System.in);

    public void service(int videoNo) {
        while (true) {
        	SsafitUtil.printLine();
        	
            System.out.println("1. 리뷰등록");
            System.out.println("0. 이전으로");
            
            SsafitUtil.printLine();
            
            System.out.print("메뉴를 선택하세요: ");

            int menu = Integer.parseInt(sc.nextLine());

            switch (menu) {
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
        System.out.print("닉네임을 입력하세요: ");
        String nickname = sc.nextLine();
        System.out.print("내용을 입력하세요: ");
        String content = sc.nextLine();

        VideoReview review = new VideoReview();
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
}
