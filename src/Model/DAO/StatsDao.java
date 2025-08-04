package Model.DAO;

import Model.DTO.StatsDto;

import java.sql.*;
import java.util.ArrayList;

public class StatsDao {
    // 싱글톤
    private StatsDao(){connect();}
    private static final StatsDao instance = new StatsDao();
    public static StatsDao getInstance(){
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
        }catch (Exception e ){ System.out.println(e); }
    } // func end

    // stats01. 상품별 통계 조회
    // 기능설명 : DB에 저장된 상품별 최근30일 판매금액의 상위 10개를 판매금액 기준 내림차순으로 출력한다.
    // 메소드명 : proStatsPrint()
    // 매개변수 : X
    // 반환타입 : ArrayList<StatsDto>
    public ArrayList<StatsDto> proStatsPrint(){
        ArrayList<StatsDto> statsDtos = new ArrayList<>();  // 반환할 빈 배열 생성
        try {
            // 1. SQL 작성
            String SQL = "select proNo, sum(orderPrice) totalPrice from OrderLog group by proNo order by totalPrice desc limit 10";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                // SQL 결과로부터 값 꺼내기
                int proNo = rs.getInt("proNo");
                int totalPrice = rs.getInt("totalPrice");
                // 객체 생성하고 꺼낸 값 넣기
                StatsDto statsDto = new StatsDto( proNo, totalPrice );
                // 생성한 객체 배열에 넣기
                statsDtos.add( statsDto );
            } // while end
        } catch ( SQLException e ){
            System.out.println("[stats01] SQL 기재 실패");
        } // try-catch end
        // 최종적으로 반환하기
        return statsDtos;
    } // func end

    // stats02. 지역별 통계 조회
    // 기능설명 : DB에 저장된 지역별(서울/인천:구, 경기:시) 최근 30일 매출액의 상위 10개를 매출액 기준 내림차순으로 출력한다."
    // 메소드명 : regionStatsPrint()
    // 매개변수 : X
    // 반환타입 : ArrayList<StatsDto>
    public ArrayList<StatsDto> regionStatsPrint(){
        ArrayList<StatsDto> statsDtos = new ArrayList<>();  // 반환할 빈 배열 생성
        try {
            // 1. SQL 작성
            String SQL = "select substring_index(F.franAddress, ' ', 2) region, sum(O.orderPrice) totalPrice from Fran F inner join OrderLog O on F.franNo = O.franNo group by region order by totalPrice desc limit 10";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                // SQL로부터 값 꺼내기
                String region = rs.getString("region");
                int totalPrice = rs.getInt("totalPrice");
                // 객체 생성하고 꺼낸 값 넣기
                StatsDto statsDto = new StatsDto( region, totalPrice );
                // 생성한 객체 배열에 넣기
                statsDtos.add( statsDto );
            } // while end
        } catch ( SQLException e ){
            System.out.println("[stats02] SQL 기재 실패");
        } // try-catch end
        // 최종적으로 반환
        return statsDtos;
    } // func end

    // stats03. 시간대별 통계 조회
    // 기능설명 : DB에 저장된 매출액을 시간대별로 출력한다.
    // 메소드명 : hourStatsPrint()
    // 매개변수 : X
    // 반환타입 : ArrayList<StatsDto>
    public ArrayList<StatsDto> hourStatsPrint(){
        ArrayList<StatsDto> statsDtos = new ArrayList<>();      // 반환할 빈 배열 생성
        try {
            // 1. SQL 작성
            String SQL = "select hour(orderDate) hour, sum(orderPrice) totalPrice from OrderLog group by hour order by hour";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                // SQL로부터 값 꺼내기
                int hour = rs.getInt("hour");
                int totalPrice = rs.getInt("totalPrice");
                // 객체 생성하여 꺼낸 값 넣기
                StatsDto statsDto = new StatsDto( hour, totalPrice );
                // 생성한 객체 배열에 추가하기
                statsDtos.add( statsDto );
            } // while end
        } catch ( SQLException e ){
            System.out.println("[stats03] SQL 기재 실패");
        } // try-catch end
        // 최종적으로 반환
        return statsDtos;
    } // func end
} // class end
