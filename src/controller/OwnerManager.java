package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.PlayerVO;

public class OwnerManager {
    public static Scanner sc = new Scanner(System.in);
    
    //맵핑테이블에서 보유한 선수 목록 출력하기
    public void showMyPlayers(String memberId) {
        ArrayList<PlayerVO> list = new ArrayList<>();
        OwnerDAO od = new OwnerDAO();
        PlayerVO pvo = new PlayerVO();
        
        System.out.println();
        System.out.println("<List of My Players>");
        list = od.getMemberPlayerList(memberId);
        if(list.isEmpty()){
            System.out.println();
            System.out.println("There are no players in possession.");
        } else {
            for(PlayerVO data : list){
                System.out.println();
                System.out.println(data.toString());
            }
        }
    } //end of showMyPlayers()

    //이적시장에서 선수 구매
    public void buyPlayerFromMarket(String memberId) {
        PlayerDAO pd = new PlayerDAO();
        MemberDAO md = new MemberDAO();
        OwnerDAO od = new OwnerDAO();
        int playerPrice = 0;
        int memberBalance = 0;
        int p_no = 0;

        pd.getPlayerFromMarket(); //이적시장 선수목록 출력
        System.out.print("Enter the Player No. >> ");
        p_no = sc.nextInt();
        sc.nextLine();
        playerPrice = pd.getPlayerPrice(p_no); //선수의 가격 조회
        memberBalance = md.getMemberBalance(memberId); //멤버의 잔액 조회

        if(memberBalance >= playerPrice) {
            int newBalance = memberBalance - playerPrice;
            md.setMemberBalance(newBalance, memberId); // 계산 후 잔액을 저장
            od.savePlayerMember(p_no, memberId); //맵핑 테이블에 해당 정보 저장
        } else { //잔액 부족
            System.out.println();
            System.out.println("Payment failed due to insufficient balance.");
        }
    
    } //end of buyPlayerFromMarket()

    //선수 판매
	public void sellPlayerFromMarket(String memberId) {
        PlayerDAO pd = new PlayerDAO();
        MemberDAO md = new MemberDAO();
        OwnerManager om = new OwnerManager();
        OwnerDAO od = new OwnerDAO();
        int playerPrice = 0;
        int memberBalance = 0;
        int p_no = 0;
        om.showMyPlayers(memberId); //멤버가 보유 중인 선수목록 출력
        System.out.print("Enter the Player NO. >> ");
        p_no = sc.nextInt();
        sc.nextLine();
        
        playerPrice = pd.getPlayerPrice(p_no); //선수의 가격 조회
        memberBalance = md.getMemberBalance(memberId); //멤버의 잔액 조회
        int newBalance = memberBalance + playerPrice;
        md.setMemberBalance(newBalance, memberId); // 계산 후 잔액을 저장
        od.deletePlayerMember(p_no, memberId); //맵핑 테이블에서 해당 정보 삭제
        om.showMyPlayers(memberId); //멤버가 보유 중인 선수목록 출력

    } //end of sellPlayerFromMarket()

    //선수 방출
    public void releasePlayerMember(String memberId) {
        OwnerManager om = new OwnerManager();
        OwnerDAO od = new OwnerDAO();
        int p_no = 0;
        
        om.showMyPlayers(memberId); //멤버가 보유 중인 선수목록 출력
        
        System.out.println("select Player No. >> ");
        p_no = sc.nextInt();
        sc.nextLine();
        
        od.deletePlayerMember(p_no, memberId); //선택한 선수 삭제
        System.out.println();
        System.out.println(p_no + " Player Release Success.");

    } //end of releasePlayerMember()

}
