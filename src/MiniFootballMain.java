import java.util.Scanner;

import controller.MemberDAO;
import controller.MemberManager;
import view.MEMBER_CHOICE;
import view.MENU_CHOICE;
import view.MenuViewer;
import view.SIGN_CHOICE;

public class MiniFootballMain {
    public static void main(String[] args) {
        mainMenu();
    } // end of main

    private static void mainMenu() {
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

    // 로그인 메뉴
    public static void logInMenu() {
        Scanner input = new Scanner(System.in);
        MemberDAO md = new MemberDAO();
        MemberManager mm = new MemberManager();
        int choice;
        String yesOrNo;
        String id;
        String pw;
        boolean success = false;
        // 로그인 확인
        System.out.println("Log In");
        do {
            System.out.print("Input ID >> ");
            id = input.nextLine();
            System.out.print("Input PW >> ");
            pw = input.nextLine();

            success = md.getMemberLogin(id, pw);
            if (!success) {
                System.out.println("The account information is not valid. Please re-enter.");
                System.out.println("Return to Main -> press Y ");
                System.out.println("Try Login Again -> press N ");
                System.out.print("select >> ");
                yesOrNo = input.nextLine();
                if (yesOrNo.toUpperCase().equals("Y")) {
                    return;
                }
                System.out.println();
            }
        } while (!success);
        // 로그인 완료되면 로그인 화면 출력
        MenuViewer.logInMenuView();
        choice = MenuViewer.choice.nextInt();
        MenuViewer.choice.nextLine();

        switch (choice) {
            case MEMBER_CHOICE.MANAGE: // 나의 팀 관리
                break;
            case MEMBER_CHOICE.TRANS: // 이적 시장
                break;
            case MEMBER_CHOICE.UPDATE: // 나의 정보 수정
                break;
            case MEMBER_CHOICE.MAIN: // 메인 메뉴
                return;
            default:
                System.out.println("Please only enter the appropriate menu number.");
        } // end of switch

    } // end of logInMenu()

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
