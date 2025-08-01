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
    private String db_url = "jdbc:mysql://sql.freedb.tech:3306/freedb_FreeDB_OngTK";
    private String db_user ="freedb_OngTK";
    private String db_password = "Wj!r2ParMG3K@5R";
    private Connection conn;
    
    // DB 연동 메소드
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection( db_url , db_user , db_password );
        }catch (Exception e ){ System.out.println(e);   }
    }
}
