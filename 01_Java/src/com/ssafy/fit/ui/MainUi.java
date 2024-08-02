package com.ssafy.fit.ui;
import java.util.Scanner;

import com.ssafy.fit.util.SsafitUtil;

public class MainUi {
    private Scanner sc = new Scanner(System.in);
    private VideoUi videoUi = new VideoUi();
    
    public void service() {
        while (true) {
        	SsafitUtil.printLine();
        	
            System.out.println("자바로 구현하는 SSAFIT");
            SsafitUtil.printLine();
            SsafitUtil.printLine();
            System.out.println("1. 영상정보");
            System.out.println("0. 종료");
            SsafitUtil.printLine();
            System.out.print("메뉴를 선택하세요: ");
            
            int menu = Integer.parseInt(sc.nextLine());
            
            switch (menu) {
                case 1:
                    videoUi.service();
                    break;
                case 0:
                    exit();
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }
    
    public void exit() {
    	SsafitUtil.printLine();
        System.out.println("프로그램을 종료합니다.");
    }
}