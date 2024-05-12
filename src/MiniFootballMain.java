import java.util.Scanner;

import controller.MemberDAO;
import controller.MemberManager;
import controller.OwnerManager;
import view.ACCOUNT_CHOICE;
import view.ADMIN_CHOICE;
import view.CUSTOM_CHOICE;
import view.MEMBER_CHOICE;
import view.MENU_CHOICE;
import view.MenuViewer;
import view.SIGN_CHOICE;
import view.TRANSFER_CHOICE;

public class MiniFootballMain {
    public static void main(String[] args) {
        mainMenu();
    } // end of main

    public static void mainMenu() {
        int choiceNum;

        while (true) {
            try {
                MenuViewer.mainMenuView();
                choiceNum = MenuViewer.choice.nextInt();
                MenuViewer.choice.nextLine();

                switch (choiceNum) {
                    case MENU_CHOICE.SIGN:
                        signUpMenu();
                        break;
                    case MENU_CHOICE.LOGIN:
                        logInMenu();
                        break;
                    case MENU_CHOICE.ADMIN:
                        adminMenu();
                        break;
                    case MENU_CHOICE.EXIT:
                        System.out.println("Exit the program.");
                        return;
                    default:
                        System.out.println("Please only enter the appropriate menu number.");
                        break;
                } // end of switch
            } catch (Exception e) {
                System.out.println(e.toString() + "\nPlease restart the program");
                return;
            }
        } // end of while
    } // end of mainMenu()
    
    
    // 관리자 메뉴
    public static void adminMenu() {
        int choice;
        MemberManager mm = new MemberManager();
        mm.verifyAdmin(); //관리자 검증
        // 관리자 메뉴 출력
        MenuViewer.adminMenuView();
        choice = MenuViewer.choice.nextInt();
        MenuViewer.choice.nextLine();

        switch (choice) {
            case ADMIN_CHOICE.CREATE:       //팀 추가
                break;
            case ADMIN_CHOICE.DELETE:       //팀 삭제
                break;
            case ADMIN_CHOICE.MAKE:         //선수 추가
                break;
            case ADMIN_CHOICE.ELIMINATE:    //선수 삭제
                break;
            case ADMIN_CHOICE.MAIN:         //메인
                return;
            default:
                System.out.println("Please only enter the appropriate menu number.");
        }
    }

    // 로그인 메뉴
    public static void logInMenu() {
        MemberManager mm = new MemberManager();
        int choice;
        String memberId = mm.verifyMember();  //로그인 검증

        // 유저 메뉴 출력
        MenuViewer.logInMenuView();
        choice = MenuViewer.choice.nextInt();
        MenuViewer.choice.nextLine();

        switch (choice) {
            case MEMBER_CHOICE.MANAGE: // 나의 팀 관리
                customMenu(memberId);
                break;
            case MEMBER_CHOICE.TRANS: // 이적 시장
                transferMenu();
                break;
            case MEMBER_CHOICE.UPDATE: // 나의 정보 관리
                memberMenu();
                break;
            case MEMBER_CHOICE.MAIN: // 메인 메뉴
                return;
            default:
                System.out.println("Please only enter the appropriate menu number.");
        } // end of switch

    } // end of logInMenu()

    // 나의 팀 관리 메뉴
    public static void customMenu(String memberId) {
        int choice;
        MenuViewer.customMenuView();
        choice = MenuViewer.choice.nextInt();
        MenuViewer.choice.nextLine();
        OwnerManager om = new OwnerManager();

        switch (choice) {
            case CUSTOM_CHOICE.LIST:        //내 팀 선수 목록
                om.showMyPlayers(memberId);
                break;
            case CUSTOM_CHOICE.DELETE:      //내 팀 선수 삭제
                break;
            case CUSTOM_CHOICE.BALANCE:     //잔고 확인
                break;
            case CUSTOM_CHOICE.MAIN:        //메인 메뉴
                return;
            default:
                System.out.println("Please only enter the appropriate menu number.");
        }

    } // end of customMenu()

    // 이적 시장 메뉴
    public static void transferMenu() {
        int choice;
        MenuViewer.transferMenuView();
        choice = MenuViewer.choice.nextInt();
        MenuViewer.choice.nextLine();

        switch (choice) {
            case TRANSFER_CHOICE.LIST:      //이적시장 선수목록
                break;
            case TRANSFER_CHOICE.BUY:       //이적시장 선수구매
                break;
            case TRANSFER_CHOICE.SELL:      //이적시장 선수판매
                break;
            case TRANSFER_CHOICE.MYLIST:    //보유한 선수목록
                break;
            case TRANSFER_CHOICE.BALANCE:   //잔고확인
                break;
            case TRANSFER_CHOICE.BACK:      //메인 메뉴
                return;
            default:
                System.out.println("Please only enter the appropriate menu number.");
        }

    } // end of transferMenuView()

    // 나의 정보 관리
    public static void memberMenu() {
        int choice;
        MenuViewer.memberMenuView();
        choice = MenuViewer.choice.nextInt();
        MenuViewer.choice.nextLine();

        switch (choice) {
            case ACCOUNT_CHOICE.SHOW: // 계정 정보 보기
                break;
            case ACCOUNT_CHOICE.UPDATE: // 계정 정보 수정
                break;
            case ACCOUNT_CHOICE.DELETE: // 계정 탈퇴
                break;
            case ACCOUNT_CHOICE.MAIN: // 메인 메뉴
                return;
            default:
                System.out.println("Please only enter the appropriate menu number.");
        } // end of switch

    } // end of memberMenu()

    // 회원가입 메뉴
    public static void signUpMenu() {
        int choice;

        MemberManager mm = new MemberManager();
        MenuViewer.signUpMenuView();
        choice = MenuViewer.choice.nextInt();
        MenuViewer.choice.nextLine();

        switch (choice) {
            case SIGN_CHOICE.SIGN:
                System.out.println();
                mm.signUpMember();
                break;
            case SIGN_CHOICE.MAIN:
                return;
            default:
                System.out.println("Please only enter the appropriate menu number.");
        } // end of switch

    } // end of signUpMenu()

} // end of class
