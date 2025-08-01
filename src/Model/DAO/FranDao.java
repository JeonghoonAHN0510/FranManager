package Model.DAO;

import Model.DTO.FranDto;

import java.sql.*;
import java.util.ArrayList;

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
        try{// SQl 작성
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

    // fran02 가맹점 전체조회기능 구현
    public ArrayList<FranDto> franPrint(){
        ArrayList<FranDto> list = new ArrayList<>();
        try{// SQL 작성
            String sql = "select *from Fran";
            // SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // SQL 실행
            ResultSet rs = ps.executeQuery();
            // SQL 결과에 따른 로직 확인
                // select 조회 결과를 레코드 단위 하나씩 조회
            while (rs.next()) { // ResultSet 인터페이스가 갖는 결과테이블에서 다음레코드로 이동
                // 조회중인 레코드의 속성값 호출해서 DTO 만들기
                int franNo = rs.getInt("franNo"); // rs.get타입("가져올속성 or 번호")
                String franName = rs.getString("franName");
                String franAddress = rs.getString("franAddress");
                String franCall = rs.getString("franCall");
                String franOwer = rs.getString("franOwner");
                boolean franStatus = rs.getBoolean("franStatus");
                FranDto franDto = new FranDto( franNo , franName , franAddress , franCall , franOwer , franStatus ); // 레코드1개 = DTO
                // 생성된 DTO를 리스트에 담아주기
                list.add( franDto );
            } // while 종료
        }catch (Exception e){ System.out.println(e);}
        return list;
    } // func end


} // class

