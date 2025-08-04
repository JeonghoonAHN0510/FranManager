package Model.DAO;

import java.sql.*;

public class ProductDao {
    // 싱글톤
    private ProductDao(){connect();}
    private static final ProductDao instance = new ProductDao();
    public static ProductDao getInstance(){
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
    } // func end

    // product02. 제품명 반환(번호 > 이름)
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
            System.out.println("[product02] SQL 기재 실패");
        } // try-catch end
        // 최종적으로 반환
        return proName;
    } // func end
} // class end
