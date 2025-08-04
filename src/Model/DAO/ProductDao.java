package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductDao {
    // 싱글톤
    private ProductDao() {
        connect();
    }

    private static final ProductDao instance = new ProductDao();

    public static ProductDao getInstance() {
        return instance;
    }

    // (*) DB 연동
    private String db_url = "jdbc:mysql://localhost:3306/FranManager";
    private String db_user = "root";
    private String db_password = "1234";
    private Connection conn;

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_user, db_password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 메소드

    // [product01] 제품번호 반환 / toIntproNoChange()
    // 매개변수 : String proName
    // 반환타입 : int
    // 반환 : proNo
    public int toIntproNoChange(String proName) {
        int proNo = 0;
        try {
            // [1.1] SQL 작성
            String sql = "select proNo from product where proName=?";
            // [1.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // [1.3] SQL 매개변수 대입
            ps.setString(1,proName);
            // [1.4] SQL 실행
            ResultSet rs = ps.executeQuery();
            // [1.5] SQL 실행 결과 확인

            rs.next();
            proNo = rs.getInt("proNo");

        } catch (Exception e) {
            System.out.println("ProductDao.toIntproNoChange");
            System.out.println("[예외발생] " + e);
        }
        return proNo;
    } // func end
}
