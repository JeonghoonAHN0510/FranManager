package Model.DAO;

import Model.DTO.OrderLogDto;

import java.sql.*;
import java.util.ArrayList;

public class OrderLogDao {
    // 싱글톤
    private OrderLogDao(){connect();}
    private static final OrderLogDao instance = new OrderLogDao();
    public static OrderLogDao getInstance(){
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
        }catch (Exception e ){ System.out.println(e);   }
    }

    // order01 판매현황 조회기능 구현
    public ArrayList<OrderLogDto> saleStatsPrint(){
        ArrayList<OrderLogDto> list = new ArrayList<>();
        try{// SQL 작성
                String sql = "select * from orderlog;";
            // SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // SQL 실행
            ResultSet rs = ps.executeQuery();
            // SQL 결과에 따른 로직 확인
            while (rs.next()) {
                // 조회중인 레코드의 속성값 호출해서 DTO 만들기
                int orderNo = rs.getInt("orderNo");
                int franNo = rs.getInt("franNo");
                int proNo = rs.getInt("proNo");
                int orderQty = rs.getInt("orderQty");
                String orderDate = rs.getString("orderDate");
                OrderLogDto orderLogDto = new OrderLogDto( orderNo, franNo, proNo ,orderQty, 0 ,orderDate);
                // 생선된 DTO를 리스트에 담아주기
                list.add( orderLogDto);
            } // while 종료
        }catch (Exception e){System.out.println("[경고] 조회 결과가 없습니다.");}
        return list;
    } // func end

    // order02. 판매현황 페이지네이션
    // 기능설명 : DB에 저장된 판매 현황을 페이지네이션하여 [페이지 번호]를 입력받아 해당 페이지를 조회하여 출력한다.
    // 메소드명 : orderLogPage()
    // 매개변수 : int page
    // 반환타입 : ArrayList<OrderLogDto>
    public ArrayList<OrderLogDto> orderLogPage( int page ){
        ArrayList<OrderLogDto> orderLogDtos = new ArrayList<>();        // 반환할 빈 배열 선언
        try {
            // 1. SQL 작성
            String SQL = "select * from OrderLog limit 50 offset ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입 : 50개씩 출력할 것이기에 offset의 값도 50씩 늘어나야한다.
            ps.setInt( 1, (page - 1) * 50 );
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                // SQL 결과로부터 값 꺼내기
                int orderNo = rs.getInt("orderNo");
                int franNo = rs.getInt("franNo");
                int proNo = rs.getInt("proNo");
                int orderQty = rs.getInt("orderQty");
                String orderDate = rs.getString("orderDate");
                // 배열에 넣을 객체를 생성하며 값 넣기
                OrderLogDto orderLogDto = new OrderLogDto( orderNo, franNo, proNo, orderQty, 0, orderDate );
                // 생성한 객체 배열에 넣기
                orderLogDtos.add( orderLogDto );
            } // while end
        } catch ( SQLException e ){
            System.out.println("[order02] SQL 기재 실패");
        } // try-catch end
        return orderLogDtos;
    } // func end

    // order03. 판매수 반환
    // 기능설명 : DB에 저장된 판매수를 반한환다.
    // 메소드명 : orderNumber()
    // 매개변수 : X
    // 반환타입 : int
    public int orderNumber(){
        try {
            // 1. SQL 작성
            String SQL = "select count(*) count from OrderLog";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                // OrderLog에 저장된 판매현황 수 리턴
                return rs.getInt("count");
            } // while end
        } catch ( SQLException e ){
            System.out.println("[order03] SQL 기재 실패");
        } // try-catch end
        return 0;
    } // func end
} // class end
