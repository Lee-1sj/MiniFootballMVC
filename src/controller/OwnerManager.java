package controller;

import model.OwnerVO;

public class OwnerManager {

    public void showMyPlayers(String memberId) {
        int m_no;
        int p_no;
        OwnerDAO od = new OwnerDAO();
        MemberDAO md = new MemberDAO();
        PlayerDAO pd = new PlayerDAO();
        
        m_no = md.getMemberNum(memberId);   //로그인 성공한 id로 m_no 불러옴
        System.out.println();
        System.out.println("List of My Players");
        p_no = od.getPlayerNum(m_no);   //m_no로 owner테이블에서 innerjoin 처리 
        //SELECT * FROM owner o INNER JOIN player p ON  o.p_no = p.p_no WHERE m_no = ?;




    } //end of showMyPlayers()

}
