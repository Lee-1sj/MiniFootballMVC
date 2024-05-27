package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ClubVO;

public class ClubDAO {
    // Club 전체 리스트
    public void getClubTotalList() {
        String sql = "SELECT * FROM club order by c_no";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ClubVO cVo = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            System.out.println();
            System.out.println("CLUB NO\t\tCLUB NAME");

            while (rs.next()) {
                cVo = new ClubVO();
                cVo.setC_no(rs.getInt("c_no"));
                cVo.setC_name(rs.getString("c_name"));
                System.out.println(cVo.getC_no() + "\t\t" + cVo.getC_name());
            }
        } catch (SQLException se) {
            System.out.println(se);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResource(rs, pstmt, con);
        }
    } // end of getClubTotalList()

    // 새 클럽 추가
    public void createNewClub(String c_name) {
        String sql = "INSERT INTO CLUB VALUES (CLUB_SEQ.NEXTVAL, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, c_name);

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println();
                System.out.println(c_name + "Create Complete.");
            } else {
                System.out.println();
                System.out.println("Create Failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeResource(pstmt, con);
        }
    } // end of createNewClub

    // 클럽 삭제
    public void eliminateClub(String c_name) {
        String sql = "DELETE FROM CLUB WHERE c_name = ?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, c_name);

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println();
                System.out.println(c_name + " Delete Success");
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
    } // end of eliminateClub()

}
