package view;

import java.util.Scanner;

public class MenuViewer {
    public static Scanner choice = new Scanner(System.in);
    //메인 메뉴
    public static void mainMenuView() {
        System.out.println();
        System.out.println("=====Mini Football Market=====");
        System.out.println("Select the menu.");
        System.out.println("1. Sign Up");               //회원가입
        System.out.println("2. Log In");                //로그인
        System.out.println("3. Run as Administrator");  //관리자
        System.out.println("4. Quit");                  //종료
        System.out.print("select >> ");
    }
    //회원가입 메뉴
    public static void signUpMenuView() {
        System.out.println();
        System.out.println("Select the menu.");
        System.out.println("1. Sign Up");           //회원가입 하기
        System.out.println("2. Return to Main");    //메인으로 돌아가기
        System.out.print("select >> ");
    }
    //로그인 성공 - 유저 메뉴
    public static void logInMenuView() {
        System.out.println();
        System.out.println("Select the menu.");
        System.out.println("1. Manage My Team");    //내 팀 관리
        System.out.println("2. Transfer Market");   //이적시장
        System.out.println("3. Modify My Account"); //내 계정 정보 수정
        System.out.println("4. Return to Main");    //메인으로 돌아가기
        System.out.print("select >> ");
    }

}
