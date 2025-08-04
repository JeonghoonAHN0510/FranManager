package Model.DAO;

import java.sql.*;

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
    } // func end

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
            ps.setString(1, proName);
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

    // product02. 제품명 반환(번호 > 이름)
    // 기능설명 : [제품번호]를 매개변수로 받아, 해당하는 제품명을 반환한다.
    // 메소드명 : toProNameChange()
    // 매개변수 : int proNo
    // 반환타입 : String
    public String toProNameChange(int proNo) {
        String proName = "";        // 반환할 빈 String 생성
        try {
            // 1. SQL 작성 : 매개변수로 받은 proNo가 제품번호인 제품명을 select
            String SQL = "select proName from Product where proNo = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(SQL);
            // 3. SQL 매개변수 대입
            ps.setInt(1, proNo);
            // 4. SQL 실행 -> Select : executeQuery() -> ResultSet으로 반환
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while (rs.next()) {
                proName = rs.getString("proName");
            } // while end
        } catch (SQLException e) {
            System.out.println("[product02] SQL 기재 실패");
        } // try-catch end
        // 최종적으로 반환
        return proName;
    } // func end

    // [product03] 상품번호 유효성 검사 / proNoCheck()
    // 매개변수 : int proNo
    // 반환타입 : boolean
    public boolean proNoCheck(int proNo) {
        boolean result = false;
        try {
            // [3.1] SQL 작성
            String sql = "select proNo from product";
            // [3.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // [3.3] SQL 매개변수 / 생략
            // [3.4] SQL 실행
            ResultSet rs = ps.executeQuery();
            // [3.5] SQL 실행 결과
            while (rs.next()){
                if(rs.getInt("proNo") == proNo){
                    result = true;
                    return result;
                }
            }
        } catch (Exception e) {
            System.out.println("ProductDao.toProNameChange");
            System.out.println("[예외발생] " + e);
        }
        return result;
    }//func end

} // class end
