package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ClubVO;

public class ClubDAO {
    //Club 전체 리스트
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
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException se) {
            }
        }
    } //end of getClubTotalList()

}
