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
            String sql = "select * from orderlog";
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
        }catch (Exception e){System.out.println(e);}
        return list;
    } // func end



}
