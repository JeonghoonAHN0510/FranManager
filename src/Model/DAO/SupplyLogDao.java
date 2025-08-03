package Model.DAO;

import Model.DTO.SupplyLogDto;

import java.sql.*;
import java.util.ArrayList;

public class SupplyLogDao {
    // 싱글톤
    private SupplyLogDao(){connect();}
    private static final SupplyLogDao instance = new SupplyLogDao();
    public static SupplyLogDao getInstance(){
        return instance;
    }
    // (*) DB 연동
    private String db_url = "jdbc:mysql://localhost:3306/FranManager";
    private String db_user = "root";
    private String db_password = "1234";
    private Connection conn;
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection( db_url , db_user , db_password );
        }catch ( Exception e ){
            System.out.println( e );
        } // try-catch end
    } // func end

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
            String SQL = "select supNo, franNo, proNo, supQty, supMemo from SupplyLog where supNo = ?";
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
    // 기능설명 : [발주번호와 가맹점명]을 입력받아, 해당하는 발주요청의 발주상태를 1로 변경하고, 발주처리날짜를 입력한다. 재고로그에 해당 { 제품번호, 발주번호, 수량, 입출고일자 }를 추가한다.
    // 메소드명 : supplyApp()
    // 매개변수 : int supNo, String franName
    // 반환타입 : boolean -> true(성공) / false(실패)
//    public boolean supplyApp( int supNo, String franName ){
//        try {
//            // 1. SQL 작성
//            // todo 가맹점번호 -> 가맹점번호 변환 함수 생성하여 연결
//            String SQL1 = "update SupplyLog set supStatus = 1 where supNo = ? and franNo = ? and supStatus = 0;";       // 발주상태 변경 SQL
//            String SQL2 = "";       // 재고로그 추가 SQL
//            // 2. SQL 기재
//
//            // 3. SQL 매개변수 대입
//
//            // 4. SQL 실행
//
//            // 5. SQL 결과 반환
//
//        }
//    } // func end

    // supply04. 발주요청 취소
    // 기능설명 : [발주번호와 가맹점명]을 입력받아, 해당하는 발주요청의 발주상태를 2로 변경한다.
    // 메소드명 : supplyCancel()
    // 매개변수 : int supNo, String franName
    // 반환타입 : boolean -> true(성공) / false(실패)


    // supply05. 가맹점명 반환(번호 > 이름)
    // 기능설명 : [가맹점번호]를 매개변수로 받아, 해당하는 가맹점명을 반환한다.
    // 메소드명 : toStoreNameChange()
    // 매개변수 : int franNo
    // 반환타입 : String
    public String toStoreNameChange( int franNo ){
        String franName = "";       // 반환할 빈 String 생성
        try {
            // 1. SQL 작성 : 매개변수로 받은 franNo가 가맹점번호인 가맹점명을 select
            String SQL = "select franName from Fran where franNo = ?;";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, franNo );
            // 4. SQL 실행 -> Select : executeQuery() -> ResultSet으로 반환
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                franName = rs.getString("franName");
            } // while end
        } catch ( SQLException e ){
            System.out.println("[supply05] SQL 기재 실패");
        } // try-catch end
        // 최종적으로 반환
        return franName;
    } // func end

    // supply06. 제품명 반환(번호 > 이름)
    // 기능설명 : [제품번호]를 매개변수로 받아, 해당하는 제품명을 반환한다.
    // 메소드명 : toProNameChange()
    // 매개변수 : int proNo
    // 반환타입 : String
    public String toProNameChange( int proNo ){
        String proName = "";        // 반환할 빈 String 생성
        try {
            // 1. SQL 작성 : 매개변수로 받은 proNo가 제품번호인 제품명을 select
            String SQL = "select proName from Product where proNo = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, proNo );
            // 4. SQL 실행 -> Select : executeQuery() -> ResultSet으로 반환
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                proName = rs.getString("proName");
            } // while end
        } catch ( SQLException e ){
            System.out.println("[supply06] SQL 기재 실패");
        } // try-catch end
        // 최종적으로 반환
        return proName;
    } // func end
} // class end
