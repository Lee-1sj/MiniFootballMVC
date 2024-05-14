package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.PlayerVO;

public class PlayerDAO {

    // 이적시장 선수 목록 출력
    public void getPlayerFromMarket() {
        String sql = "SELECT * FROM player order by p_no";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PlayerVO pvo = new PlayerVO();

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            System.out.println(
                    "PLAYER NO.\t\tPLAYER NAME\t\tBACK NUMBER\t\tPOSITION\t\tSHOOT\t\tPASS\t\tDEFEND\t\tPRICE\t\tCLUB NO.");

            while (rs.next()) {
                pvo = new PlayerVO();
                pvo.setP_no(rs.getInt("p_no"));
                pvo.setP_name(rs.getString("p_name"));
                pvo.setP_backno(rs.getString("p_backno"));
                pvo.setP_position(rs.getString("p_position"));
                pvo.setP_shoot(rs.getInt("p_shoot"));
                pvo.setP_pass(rs.getInt("p_pass"));
                pvo.setP_defend(rs.getInt("p_defend"));
                pvo.setP_price(rs.getInt("p_price"));
                pvo.setC_no(rs.getInt("c_no"));

                System.out.println(pvo.getP_no() + "\t\t" + pvo.getP_name() + "\t\t" + pvo.getP_backno() + "\t\t" +
                        pvo.getP_position() + "\t\t" + pvo.getP_shoot() + "\t\t" + pvo.getP_pass() + "\t\t" +
                        pvo.getP_defend() + "\t\t" + pvo.getP_price() + "\t\t" + pvo.getC_no());
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

    } // end of getPlayerFromMarket()

    // 선수의 가격을 불러오는 함수
    public int getPlayerPrice(int p_no) {
        String sql = "SELECT p_price FROM player WHERE p_no = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int price = 0;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, p_no);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                price = rs.getInt("p_price");
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
        return price;
    } //end of getPlayerPrice()

}
