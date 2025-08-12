package model.dao;

import model.dto.SupplyLogDto;

import java.sql.*;
import java.util.ArrayList;

public class SupplyLogDao extends SuperDao {
    // 싱글톤
    private SupplyLogDao(){ }
    private static final SupplyLogDao instance = new SupplyLogDao();
    public static SupplyLogDao getInstance(){
        return instance;
    }

    // supply01. 발주요청 전체조회
    // 기능설명 : DB에 저장된 요청대기 중인 발주기록울 조회한다. { 발주번호, 가맹점명, 상품명, 주문수량, 메모 }
    // 메소드명 : supplyPrintAll()
    // 매개변수 : X
    // 반환타입 : ArrayList<SupplyLogDto>
    public ArrayList<SupplyLogDto> supplyPrintAll(){
        ArrayList<SupplyLogDto> supplyLogDtos = new ArrayList<>();  // 반환할 빈 ArrayList 생성
        try {
            // 1. SQL 작성 : 요청대기 중인 발주기록 조회
            String SQL = "select supNo, franNo, proNo, supQty, supMemo from SupplyLog where supStatus = 0";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입(필요 시)
            // 4. SQL 실행 -> Select : executeQuery() -> ResultSet으로 반환
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                // SQL 결과에서 값 꺼내기
                int supNo = rs.getInt("supNo");
                int franNo = rs.getInt("franNo");
                int proNo = rs.getInt("proNo");
                int supQty = rs.getInt("supQty");
                String supMemo = rs.getString("supMemo");
                // 꺼낸 값을 넣을 SupplyLogDto 객체 생성 후 값 대입
                SupplyLogDto supplyLogDto = new SupplyLogDto( supNo, franNo, proNo, supQty, "", 0, supMemo );
                // 생성한 객체 ArrayList에 추가
                supplyLogDtos.add( supplyLogDto );
            } // while end
        } catch ( SQLException e ) {
            System.out.println("[supply01] SQL 기재 실패");
        } // try-catch end
        // 최종적으로 ArrayList 반환
        return supplyLogDtos;
    } // func end

    // supply02. 발주요청 선택조회
    // 기능설명 : [발주번호]를 입력받아, 해당하는 발주요청을 출력한다.
    // 메소드명 : supplyPrint()
    // 매개변수 : int supNo
    // 반환타입 : SupplyLogDto
    public SupplyLogDto supplyPrint( int supNo ){
        SupplyLogDto supplyLogDto = new SupplyLogDto();
        try {
            // 1. SQL 작성 : supNo가 매개변수로 받은 supNo와 같은 발주로그를 조회한다.
            String SQL = "select supNo, franNo, proNo, supQty, supMemo from SupplyLog where supNo = ? and supStatus = 0";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, supNo );
            // 4. SQL 실행 -> Select : executeQuery() -> ResultSet으로 반환
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                // 반환받은 값 꺼내오기
                int franNo = rs.getInt("franNo");
                int proNo = rs.getInt("proNo");
                int supQty = rs.getInt("supQty");
                String supMemo = rs.getString("supMemo");
                // 꺼낸 값들 객체에 넣기
                supplyLogDto = new SupplyLogDto( supNo, franNo, proNo, supQty, "", 0, supMemo );
            } // while end
        } catch ( SQLException e ){
            System.out.println("[supply02] SQL 기재 실패");
        } // try-catch end
        // 최종적으로 객체 반환
        return supplyLogDto;
    } // func end

    // supply03. 발주요청 승인
    // 기능설명 : [발주번호와 가맹점명]을 입력받아, view에서 [가맹점명 -> 가맹점번호] 변환 후, 해당하는 발주요청의 발주상태를 1로 변경한다. 재고로그에 해당 { 제품번호, 발주번호, 수량, 입출고일자 }를 추가한다.
    // 메소드명 : supplyApp()
    // 매개변수 : int supNo, int franNo
    // 반환타입 : int -> 0 : 출고 성공, 1 : 가맹점명 일치 X, 2 : 재고 부족
    public int supplyApp( int supNo, int franNo ){
        try {
            // 1. SQL 작성
            // 발주상태 변경 SQL
            String SQL1 = "update SupplyLog set supStatus = 1 where supNo = ? and franNo = ? and supStatus = 0;";
            // 발주번호에 따른, 제품번호 수량을 가져오기 위한 SQL
            String SQL2 = "select proNo, supQty from SupplyLog where supNo = ? and franNo = ?";
            // 2. SQL 기재
            PreparedStatement ps1 = conn.prepareStatement( SQL1 );
            PreparedStatement ps2 = conn.prepareStatement( SQL2 );
            // 3. SQL 매개변수 대입
            ps1.setInt( 1, supNo );
            ps1.setInt( 2, franNo );
            ps2.setInt( 1, supNo );
            ps2.setInt( 2, franNo );
            // 4. SQL 실행
            int count1 = ps1.executeUpdate();
            ResultSet rs = ps2.executeQuery();
            // 5. SQL 결과 반환
            if ( count1 == 1 ){
                while( rs.next() ){
                    int proNo = rs.getInt("proNo");
                    int supQty = rs.getInt("supQty");
                    // 1. SQL 작성
                    String SQL3 = "insert into IOLog ( proNo, IO, ioQty ) values ( ?, ?, ? )";       // 재고로그 추가 SQL
                    // 2. SQL 기재
                    PreparedStatement ps3 = conn.prepareStatement( SQL3 );
                    // 3. SQL 매개변수 대입
                    ps3.setInt( 1, proNo );
                    ps3.setInt( 2, supNo );
                    ps3.setInt( 3, supQty );
                    // 4. SQL 실행
                    int count2 = ps3.executeUpdate();
                    // 5. SQL 결과 반환
                    if ( count2 == 1 ){
                        return 0;
                    } // if end
                } // while end
            } // if end
        } catch ( SQLException e ){
            System.out.println("[supply03] SQL 기재 실패");
        } // try-catch end
        return 1;
    } // func end

    // supply04. 발주요청 취소
    // 기능설명 : [발주번호와 가맹점명]을 입력받아, view에서 [가맹점명 -> 가맹점번호] 변환 후, 해당하는 발주요청의 발주상태를 2로 변경한다.
    // 메소드명 : supplyCancel()
    // 매개변수 : int supNo, int franNo
    // 반환타입 : boolean -> true(성공) / false(실패)
    public boolean supplyCancel( int supNo, int franNo ){
        boolean result = false;     // 반환할 기본 boolean 생성
        try {
            // 1. SQL 작성
            String SQL = "update SupplyLog set supStatus = 2 where supNo = ? and franNo = ? and supStatus = 0";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, supNo );
            ps.setInt( 2, franNo );
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과 반환
            if ( count == 1 ){  // 실행에 성공하면
                return true;    // true 반환
            } // if end
        } catch ( SQLException e ){
            System.out.println("[supply04] SQL 기재 실패");
        } // try-catch end
        return result;
    } // func end

    // supply05. 발주번호 유효성 검사
    // 기능설명 : [발주번호]를 받아 발주리스트에 해당 발주이력이 있는지를 확인한다.
    // 메소드명 : supNoCheck()
    // 매개변수 : int supNo
    // 반환타입 : boolean -> true : 발주 존재 / false : 발주 없음
    public boolean supNoCheck( int supNo ){
        boolean result = false;
        try {
            // 1. SQL 작성 -> 발주번호에 해당하는 행의 개수 조회하기
            String SQL = "select count(*) C from SupplyLog where supNo = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, supNo );
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                int count = rs.getInt("C");
                if ( count == 1 ){  // 행의 개수가 1개라면
                    return true;    // true 반환
                }else {             // 행의 개수가 1개가 아니라면
                    return false;   // false 반환
                } // if end
            } // while end
        } catch ( SQLException e ){
            System.out.println("[supply05] SQL 기재 실패");
        } // try-catch end
        return result;
    } // func end

    // supply06. 총재고 반환(발주번호 > 총재고)
    // 기능설명 : [발주번호]를 받아, 해당하는 제품의 총 재고량을 반환한다.
    // 메소드명 : toTotalQtyChange()
    // 매개변수 : int supNo
    // 반환타입 : int
    public int toTotalQtyChange( int supNo ){
        int totalQty = 0;   // 반환할 int 생성
        try {
            // 1. SQL 작성 : 입고면 +, 출고면 -
            String SQL = "select S.supNo, totalQty from SupplyLog S, (select proNo, sum(case when io = 0 then ioQty else -ioQty end) totalQty from IOLog group by proNo) I where S.proNo = I.proNo and supNo = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, supNo );
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                totalQty = rs.getInt("totalQty");
            } // while end
        } catch ( SQLException e ){
            System.out.println("[supply06] SQL 기재 실패");
        } // try-catch end
        return totalQty;
    } // func end
} // class end
