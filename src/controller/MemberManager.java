package controller;

import java.util.Scanner;

import model.MemberVO;

public class MemberManager {
    public static Scanner sc = new Scanner(System.in);

    // 회원가입 기능구현
    public void signUpMember() {
        MemberVO mvo = new MemberVO();
        MemberDAO md = new MemberDAO();
        ClubDAO cd = new ClubDAO();

        String m_id;    // 멤버 id
        String m_pw;    // 멤버 pw
        String m_email; // 멤버 email
        int c_no;       //팀 일련번호
        boolean id_check;
        boolean email_check;
        System.out.println("Enter membership information.");
        do {
            // id 입력
            System.out.print("Input ID(Character limit 4~16) >> ");
            m_id = sc.nextLine();
            // id 중복체크
            id_check = md.getMemberIdOverlap(m_id);
            if(id_check){
                System.out.println("This is a duplicate ID. Please re-enter it.");
            }
        } while (id_check);
        // pw 입력
        System.out.print("Input Password(Character limit 4~16) >> ");
        m_pw = sc.nextLine();
        do{
            // email 입력
            System.out.print("Input Email Account >> ");
            m_email = sc.nextLine();
            // email 중복체크
            email_check = md.getMemberEmailOverlap(m_email);
            if(email_check){
                System.out.println("This is a duplicate Email. Please re-enter it.");
            }
        } while (email_check);
        
        // 초기 팀 선택을 위한 팀 리스트 출력
        cd.getClubTotalList();
        // 초기 팀 입력
        System.out.print("Choose the initial team >> ");
        c_no = sc.nextInt();
        sc.nextLine();

        mvo.setM_id(m_id);
        mvo.setM_pw(m_pw);
        mvo.setM_email(m_email);
        mvo.setC_no(c_no);
        md.setMemberRegister(mvo);
        // 가입 회원 정보 출력
        System.out.println();
        System.out.println("=====New Member Info======");
        md.getMember(mvo.getM_id(), mvo.getM_pw());
        System.out.println();

    } //end of singUpMember()

}
