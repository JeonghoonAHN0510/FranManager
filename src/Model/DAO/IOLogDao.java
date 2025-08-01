package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class IOLogDao {
    // 싱글톤
    private IOLogDao(){connect();}
    private static final IOLogDao instance = new IOLogDao();
    public static IOLogDao getInstance(){
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
}
