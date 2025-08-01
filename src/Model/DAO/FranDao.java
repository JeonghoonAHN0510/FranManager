package Model.DAO;

import Model.DTO.FranDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class FranDao {
    // 싱글톤
    private FranDao(){connect();}
    private static final FranDao instance = new FranDao();
    public static FranDao getInstance(){
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
    // fran01 가맹점 추가기능 구현
    public boolean franAdd( FranDto franDto){
        try{
            // SQl 작성
            String sql = "Insert into Fran ( franNo, franName , franAddress , franCall , franOwner , franStatus ) " +
                    "values ( null , ? , ? , ? , ? , ? )";
            // SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // SQL 매개변수 대입 ? 5개
            ps.setString(1, franDto.getFranName());
            ps.setString(2, franDto.getFranAddress());
            ps.setString(3, franDto.getFranCall());
            ps.setString(4, franDto.getFranOwner());
            ps.setBoolean(5, franDto.isFranStatus());
            // SQL 실행
            int count = ps.executeUpdate();
            // SQL 결과에 따른 로직 확인
            if( count >=1 ) return true; // 1개 이상이면 저장 성공
            return false; // 1개 이상이 아니면 저장 실패
        }catch (Exception e){System.out.println(e);}
        return false; // 예외 발생 시 저장 실패
    } // func end

} // class

