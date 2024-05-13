package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerDAO {

    public int getPlayerNum(int m_no) {
        String sql = "SELECT * FROM owner o INNER JOIN player p ON  o.p_no = p.p_no WHERE m_no = ?";
        int p_no = -1;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, m_no);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                p_no = rs.getInt("p_no");
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
        return p_no;
    } //end of getMyPlayers()

}
