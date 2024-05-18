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

            System.out.println();
            System.out.println(String.format("%-12s %-25s %-12s %-10s %-6s %-6s %-6s %-8s %-10s", "PLAYER NO.",
                    "PLAYER NAME", "BACK NUMBER", "POSITION", "SHOOT", "PASS", "DEFEND", "PRICE", "CLUB NO."));

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

                System.out.println(String.format("%-12s %-25s %-12s %-10s %-6s %-6s %-6s %-8s %-10s", pvo.getP_no(),
                        pvo.getP_name(), pvo.getP_backno(), pvo.getP_position(), pvo.getP_shoot(), pvo.getP_pass(),
                        pvo.getP_defend(), pvo.getP_price(), pvo.getC_no()));

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
            } catch (SQLException e) {
                e.printStackTrace();
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return price;
    } // end of getPlayerPrice()

    // 선수생성
    public void setPlayer(PlayerVO pv) {
        String sql = "INSERT INTO PLAYER VALUES (PLAYER_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pv.getP_name());
            pstmt.setString(2, pv.getP_backno());
            pstmt.setString(3, pv.getP_position());
            pstmt.setInt(4, pv.getP_shoot());
            pstmt.setInt(5, pv.getP_pass());
            pstmt.setInt(6, pv.getP_defend());
            pstmt.setInt(7, pv.getP_price());
            pstmt.setInt(8, pv.getC_no());

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println();
                System.out.println("Create Complete.");
            } else {
                System.out.println();
                System.out.println("Create Failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } // end of setPlayer()

    // 선수1명 정보 가져옴
    public void getOnePlayer(String p_name, int c_no) {
        String sql = "SELECT * FROM PLAYER WHERE p_name = ? and c_no = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        PlayerVO pvo = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, p_name);
            pstmt.setInt(2, c_no);
            rs = pstmt.executeQuery();

            System.out.println(String.format("%-12s %-25s %-12s %-10s %-6s %-6s %-6s %-8s %-10s", "PLAYER NO.",
                    "PLAYER NAME", "BACK NUMBER", "POSITION", "SHOOT", "PASS", "DEFEND", "PRICE", "CLUB NO."));

            if (rs.next()) {
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

                System.out.println(String.format("%-12s %-25s %-12s %-10s %-6s %-6s %-6s %-8s %-10s", pvo.getP_no(),
                        pvo.getP_name(), pvo.getP_backno(), pvo.getP_position(), pvo.getP_shoot(), pvo.getP_pass(),
                        pvo.getP_defend(), pvo.getP_price(), pvo.getC_no()));
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    } // end of getOnePlayer()

    // 전체 선수 목록에서 선수 제거
    public void eliminatePlayer(int p_no) {
        String sql = "DELETE FROM PLAYER WHERE p_no = ?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, p_no);

            int i = pstmt.executeUpdate();
            if (i == 1) {
                System.out.println();
                System.out.println(p_no + " Player Delete Success");
            } else {
                System.out.println();
                System.out.println("Delete Failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    } // end of eliminatePlayer()

}
