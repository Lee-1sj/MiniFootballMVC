package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.PlayerVO;

public class OwnerDAO {
    // 구매한 선수와 멤버를 맵핑테이블에 저장
    public void savePlayerMember(int p_no, String memberId) {
        String sql = "INSERT INTO OWNER VALUES (OWNER_SEQ.NEXTVAL, (select m_no from members where m_id = ?), ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.setInt(2, p_no);

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println("Purchase & Save Complete.");
            } else {
                System.out.println("Purchase & Save Failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(pstmt, con);
        }
    }// end of savePlayerMember()

    // 오너테이블에서 멤버의 보유 선수 list > 객체를 ArrayList에 저장해서 리턴함
    public ArrayList<PlayerVO> getMemberPlayerList(String memberId) {
        ArrayList<PlayerVO> memberPlayerList = null;
        String sql = "SELECT P.p_no, P.p_name, P.p_backno, P.p_position, P.p_shoot, P.p_pass, P.p_defend, P.p_price, P.c_no FROM OWNER O INNER JOIN PLAYER P ON O.p_no = P.p_no WHERE m_no = (SELECT m_no FROM MEMBERS WHERE m_id = ?)";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            memberPlayerList = new ArrayList<>();
            while (rs.next()) {
                PlayerVO pv = new PlayerVO();
                pv.setP_no(rs.getInt("p_no"));
                pv.setP_name(rs.getString("p_name"));
                pv.setP_backno(rs.getString("p_backno"));
                pv.setP_position(rs.getString("p_position"));
                pv.setP_shoot(rs.getInt("p_shoot"));
                pv.setP_pass(rs.getInt("p_pass"));
                pv.setP_defend(rs.getInt("p_defend"));
                pv.setP_price(rs.getInt("p_price"));
                pv.setC_no(rs.getInt("c_no"));
                memberPlayerList.add(pv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(rs, pstmt, con);
        }
        return memberPlayerList;
    }// end of getMemberPlayerList()

    // 맵핑 테이블에서 선택한 선수 삭제
    public void deletePlayerMember(int p_no, String memberId) {
        String sql = "DELETE FROM OWNER where p_no = ? and m_no = (select m_no from members where m_id = ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, p_no);
            pstmt.setString(2, memberId);

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println();
                System.out.println(p_no + ". Player Delete Success");
            } else {
                System.out.println();
                System.out.println("Delete Failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(pstmt, con);
        }
    }// end of deletePlayerMember()

}