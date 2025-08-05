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
            String sql = "select f.franNo,f.franName,f.franAddress,f.franCall,f.franOwner, ifnull(SUM(orderQty * orderPrice), 0) AS P from fran f left join OrderLog o on f.franNo=o.franNo where franStatus=0 group by f.franNo,f.franName,f.franAddress,f.franCall,f.franOwner order by franNo asc;";
            // select f.franNo,f.franName,f.franAddress,f.franCall,f.franOwner, ifnull(SUM(orderQty * orderPrice), "0원") AS P from fran f left join OrderLog o on f.franNo=o.franNo group by f.franNo,f.franName,f.franAddress,f.franCall,f.franOwner order by franNo asc;
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
                int P = rs.getInt("P");
                FranDto franDto = new FranDto( franNo , franName , franAddress , franCall , franOwer , false , P ); // 레코드1개 = DTO
                // 생성된 DTO를 리스트에 담아주기
                list.add( franDto );
            } // while 종료
        }catch (Exception e){ System.out.println("[경고] 조회 결과가 없습니다.");}
        return list;
    } // func end

    // fran03 가맹점 단일조회기능 구현
    public FranDto oneFranPrint( int franNo ){
        FranDto franDto = new FranDto();
            // SQL 작성
        try { String sql = "select franNo, franName, franAddress, franCall, franOwner from Fran where franNo = ?";
            // SQL 기재
            PreparedStatement ps = conn.prepareStatement( sql );
            // SQL 매개변수 대입
            ps.setInt( 1,franNo );
            // SQL 실행
            ResultSet rs = ps.executeQuery();
            // SQL 결과에 따른 로직 확인
            while( rs.next() ){
                String franName = rs.getString("franName");
                String franAddress = rs.getString("franAddress");
                String franCall = rs.getString("franCall");
                String franOwner = rs.getString("franOwner");
                // 꺼낸 값을 객체에 넣기
                franDto = new FranDto( franNo , franName ,franAddress , franCall ,franOwner ,false, 0);
            } // while end
        }catch (Exception e){System.out.println("[경고] 조회 결과가 없습니다.");}
        return franDto;
    } // func end




    // fran04 가맹점 수정기능 구현
    public boolean franUpdate( FranDto franDto ){
        // SQL 작성
        try{String sql = "update fran set franName = ? , franAddress = ? , franCall = ? , franOwner = ? , franStatus = ? where franNo = ? ";
        // SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
        // SQL 매개변수 대입 6개
            ps.setString(1,franDto.getFranName());
            ps.setString(2,franDto.getFranAddress());
            ps.setString(3,franDto.getFranCall());
            ps.setString(4,franDto.getFranOwner());
            ps.setBoolean(5,franDto.isFranStatus());
            ps.setInt(6,franDto.getFranNo());
        // SQL 실행
            int count = ps.executeUpdate();
        // SQL 결과에 따른 로직 확인
            if( count == 1 ) return true; // 수정 결과가 1개이면 수정성공
            return false; // 수정 결과가 1이 아니면 수정 실패
        }catch (Exception e){System.out.println("[경고] 올바르지 못한 입력입니다.");}
        return false; // 예외발생시 수정실패
    }

    // fran05 가맹점 삭제기능 구현
    public boolean franDelete( FranDto frandto) {
        // SQL 작성
        try {
            String sql = "update fran set franStatus = true where franStatus = false and franNo = ? and franName = ? and franOwner = ?";
            // SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // SQL 매개변수 3개 대입
            ps.setInt(1, frandto.getFranNo());
            ps.setString(2, frandto.getFranName());
            ps.setString(3, frandto.getFranOwner());
            // SQL 실행
            int count = ps.executeUpdate();
            // SQL 결과에 따른 로직 확인
            if (count == 1) return true; // 삭제 결과가 1개이면 수정성공
            return false; // 삭제 결과가 1이 아니면 수정 실패
        } catch (Exception e) {
            System.out.println("[경고] 올바르지 못한 입력입니다.");
            return false; // 예외 발생시 삭제 실패
        } // cat end
    } // func end

    // fran06. 가맹점명 반환(번호 > 이름)
    // 기능설명 : [가맹점번호]를 매개변수로 받아, 해당하는 가맹점명을 반환한다.
    // 메소드명 : toFranNameChange()
    // 매개변수 : int franNo
    // 반환타입 : String
    public String toFranNameChange( int franNo ){
        String franName = "";       // 반환할 빈 String 생성
        try {
            // 1. SQL 작성 : 매개변수로 받은 franNo가 가맹점번호인 가맹점명을 select
            String SQL = "select franName from Fran where franNo = ?;";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, franNo );
            // 4. SQL 실행 -> Select : executeQuery() -> ResultSet으로 반환
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                franName = rs.getString("franName");
            } // while end
        } catch ( SQLException e ){
            System.out.println("[product02] SQL 기재 실패");
        } // try-catch end
        // 최종적으로 반환
        return franName;
    } // func end

    // fran07. 가맹점번호 반환(번호 > 이름)
    // 기능설명 : [가맹점명]를 매개변수로 받아, 해당하는 가맹점번호를 반환한다.
    // 메소드명 : toIntNameFranChange()
    // 매개변수 : String franName
    // 반환타입 : int
    public int toIntNameFranChange( String franName ){
        int franNo = 0;         // 반환할 빈 int 생성
        try {
            // 1. SQL 작성
            String SQL = "select franNo from Fran where franName = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setString( 1, franName );
            // 4. SQL 실행 -> Select : executeQuery() -> ResultSet 반환
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                franNo = rs.getInt("franNo");
            } // while end
        } catch ( SQLException e ){
            System.out.println("[fran0X] SQL 기재 실패");
        } // try-catch end
        return franNo;
    } // func end

    // [fran08] 가맹점번호 유효성검사 / franNoCheck()
    // 매개변수 : int franNo
    // 반환 : boolean
    public boolean franNoCheck(int franNo){
        boolean result = false;
        try {
            // [8.1] SQL 작성
            String sql = "select franNo from fran";
            // [3.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // [3.3] SQL 매개변수 / 생략
            // [3.4] SQL 실행
            ResultSet rs = ps.executeQuery();
            // [3.5] SQL 실행 결과
            while (rs.next()){
                if(rs.getInt("franNo") == franNo){
                    result = true;
                    return result;
                }
            }
        } catch (Exception e) {
            System.out.println("[경고] 확인되지 않는 가맹점 번호입니다. 가맹점 번호를 다시한번 확인해주세요.");
        }
        return result;
    } // func end

} // class

