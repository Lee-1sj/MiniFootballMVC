package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.MemberVO;

public class MemberDAO {
    // new member 등록
    public void setMemberRegister(MemberVO mvo) {
        String sql = "INSERT INTO MEMBERS(m_no, m_id, m_pw, m_email, c_no) VALUES (MEMBERS_SEQ.NEXTVAL, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, mvo.getM_id());
            pstmt.setString(2, mvo.getM_pw());
            pstmt.setString(3, mvo.getM_email());
            pstmt.setInt(4, mvo.getC_no());

            int i = pstmt.executeUpdate();
            if(i == 1){
                System.out.println("Sign Up Complete.");
            } else {
                System.out.println("Sing up Failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(pstmt != null){
                    pstmt.close();
                }
                if(con != null){
                    con.close();
                }                
            } catch (SQLException e) {
            }
        }

    } //end of setMemberRegister

    //ID 중복 체크
    public boolean getMemberIdOverlap(String isOverlap_id) {
        String sql = "SELECT * FROM MEMBERS WHERE m_id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isOverlap = false;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, isOverlap_id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                isOverlap = true; // 아이디 중복됨
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }               
                if(con != null){
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return isOverlap;
    } //end of getMemberIdOverlap()

    //email 중복 체크
    public boolean getMemberEmailOverlap(String isOverlap_email) {
        String sql = "SELECT * FROM MEMBERS WHERE m_email = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isOverlap = false;
        
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, isOverlap_email);
            rs = pstmt.executeQuery();
            if(rs.next()){
                isOverlap = true; // email 중복됨
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }               
                if(con != null){
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return isOverlap;
    } //end of getMemberEmailOverlap()

    // 멤버객체 하나 불러오기 (id, pw 이용)
    public void getMember(String id, String pw) {
        String sql = "SELECT * FROM members WHERE m_id = ? AND m_pw = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberVO mvo = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();

            System.out.println("No.\tID\t\tE-mail\t\tBalance\t\tTeam No.");

            if(rs.next()){
                mvo = new MemberVO();
                mvo.setM_no(rs.getInt("m_no"));
                mvo.setM_id(rs.getString("m_id"));
                mvo.setM_email(rs.getString("m_email"));
                mvo.setM_balance(rs.getInt("m_balance"));
                mvo.setC_no(rs.getInt("c_no"));

                System.out.println(mvo.getM_no() + "\t" + mvo.getM_id() + "\t\t" + mvo.getM_email() + "\t" + mvo.getM_balance()
                + "\t\t" + mvo.getC_no());              
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }               
                if(con != null){
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
    } //end of getMember()

    //로그인 기능
    public boolean getMemberLogin(String id, String pw) {
        String sql = "SELECT * FROM members WHERE m_id = ? AND m_pw = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean loginSuccess = false;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();

            if(rs.next()){
                System.out.println("login success.");
                loginSuccess = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }               
                if(con != null){
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return loginSuccess;
    } //end of getMemberLogin()

    //관리자 로그인 기능
    public boolean getAdminLogin(String id, String pw) {
        String sql = "SELECT m_isadmin FROM MEMBERS WHERE m_id = ? AND m_pw = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean loginSuccess = false;
        int verify;
        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();

            if(rs.next()){
                verify = rs.getInt("m_isadmin");
                if(verify == 1){
                    System.out.println();
                    System.out.println("Admin mode success");
                    loginSuccess = true;
                } else {
                    System.out.println();
                    System.out.println("Admin mode failed");
                }
            } else {
                System.out.println();
                System.out.println("No matching account exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }               
                if(con != null){
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return loginSuccess;

    } //end of getAdminLogin()

    // memberId를 가지고 db를 찾아서 m_no를 불러옴
    public int getMemberNum(String memberId) {
        String sql = "SELECT m_no FROM MEMBERS WHERE m_id = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int m_no = -1;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if(rs.next()){
                m_no = rs.getInt("m_no");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null){
                    rs.close();
                }
                if(pstmt != null){
                    pstmt.close();
                }
                if(con != null){
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return m_no;
    }

} //end of class
