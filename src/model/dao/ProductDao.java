package model.dao;

import model.dto.ProductDto;

import java.sql.*;
import java.util.ArrayList;

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
            System.out.println("[경고] 입력하신 제품명으로 조회되는 제품이 없습니다.");
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

    // [product03] 제품번호 유효성 검사 / proNoCheck()
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
            System.out.println("[경고] 확인되지 않는 제품번호입니다. 제품번호를 다시 확인해주세요.");
        }
        return result;
    }//func end

    // product04. 제품 전체조회
    // 기능설명 : DB에 저장된 모든 제품을 조회하여 출력한다.
    // 메소드명 : productAllPrint()
    // 매개변수 : X
    // 반환타입 : ArrayList<ProductDto>
    public ArrayList<ProductDto> productAllPrint(){
        ArrayList<ProductDto> productDtos = new ArrayList<>();  // 반환할 빈 배열 생성
        try {
            // 1. SQL 작성
            String SQL = "select * from Product";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                // SQL 결과에서 값 꺼내기
                int proNo = rs.getInt("proNo");
                String proName = rs.getString("proName");
                int proSupPrice = rs.getInt("proSupPrice");
                int proPrice = rs.getInt("proPrice");
                boolean proStatus = rs.getBoolean("proStatus");
                // 객체 생성하고 값 넣기
                ProductDto productDto = new ProductDto( proNo, proName, proSupPrice, proPrice, proStatus );
                // 생성한 객체를 배열에 넣기
                productDtos.add( productDto );
            } // while end
        } catch ( SQLException e ){
            System.out.println("[product04] SQL 기재 실패");
        } // try-catch end
        // 최종적으로 반환
        return productDtos;
    } // func end

    // product05. 제품 선택조회
    // 기능설명 : [제품번호]를 입력받아, 해당하는 제품정보를 출력한다.
    // 메소드명 : productPrint()
    // 매개변수 : int proNo
    // 반환타입 : ProductDto
    public ProductDto productPrint( int proNo ){
        ProductDto productDto = new ProductDto();   // 반환할 빈 객체 생성
        try {
            // 1. SQL 작성
            String SQL = "select * from Product where proNo = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, proNo );
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                // SQL 결과에서 값 꺼내기
                String proName = rs.getString("proName");
                int proSupPrice = rs.getInt("proSupPrice");
                int proPrice = rs.getInt("proPrice");
                boolean proStatus = rs.getBoolean("proStatus");
                // 객체에 값 넣기
                productDto = new ProductDto( proNo, proName, proSupPrice, proPrice, proStatus );
            } // while end
        } catch ( SQLException e ){
            System.out.println("[product05] SQL 기재 실패");
        } // try-catch end
        return productDto;
    } // func end

    // product06. 제품 등록
    // 기능설명 : [제품명, 공급가액, 소비자판매가]를 입력받아, 제품을 추가한다.
    // 메소드명 : productAdd()
    // 매개변수 : ProductDto -> String proName, int proSupPrice, int proPrice
    // 반환타입 : int -> 0 : 등록 성공, 1 : 등록 실패, 2 : 제품명 오류, 3 : 가격 오류
    public int productAdd( ProductDto productDto ){
        try {
            // 1. SQL 작성
            String SQL = "insert into Product ( proName, proSupPrice, proPrice ) values ( ?, ?, ? )";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setString( 1, productDto.getProName() );
            ps.setInt( 2, productDto.getProSupPrice() );
            ps.setInt( 3, productDto.getProPrice() );
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과 반환
            if ( count == 1 ){
                return 0;
            } // if end
        } catch ( SQLException e ){
            System.out.println("[product06] SQL 기재 실패");
        } // try-catch end
        return 1;
    } // func end

    // product07. 제품 수정
    // 기능설명 : [제품번호, 제품명, 공급가액, 소비자판매가]를 입력받아, 해당하는 제품정보를 수정한다.
    // 메소드명 : productUpdate()
    // 매개변수 : ProductDto -> int proNo, String proName, int proSupPrice, int proPrice
    // 반환타입 : boolean -> true : 수정 성공 / false : 수정 실패
    public boolean productUpdate( ProductDto productDto ){
        try {
            // 1. SQL 작성
            String SQL = "update Product set proName = ?, proSupPrice = ?, proPrice = ? where proNo = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setString( 1, productDto.getProName() );
            ps.setInt( 2, productDto.getProSupPrice() );
            ps.setInt( 3, productDto.getProPrice() );
            ps.setInt( 4, productDto.getProNo() );
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과 반환
            if ( count == 1 ){
                return true;
            } // if end
        } catch ( SQLException e ){
            System.out.println("[product07] SQL 기재 실패");
        } // try-catch end
        return false;
    } // func end

    // product08. 제품 상태변경
    // 기능설명 : [제품번호, 제품명]을 입력받아, 해당하는 제품의 상태를 변경한다.
    // 메소드명 : productStatusChange()
    // 매개변수 : ProductDto -> int proNo, String proName
    // 반환타입 : boolean -> true : 삭제 성공 / false : 삭제 실패
    public boolean productStatusChange( ProductDto productDto ){
        try {
            // 1. SQL 작성 : proStatus가 true면 false로 / false면 true로 변경하도록 작성
            String SQL = "update Product set proStatus = (case proStatus when true then false else true end) where proNo = ? and proName = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setInt( 1, productDto.getProNo() );
            ps.setString( 2, productDto.getProName() );
            // 4. SQL 실행
            int count = ps.executeUpdate();
            // 5. SQL 결과 반환
            if ( count == 1 ){
                return true;
            } // if end
        } catch ( SQLException e ){
            System.out.println("[product08] SQL 기재 실패");
        } // try-catch end
        return false;
    } // func end

    // product09. 판매여부 변환
    // 기능설명 : [판매여부(boolean)]을 받아, [판매여부(String)]으로 반환한다.
    // 메소드명 : toproStatusChange()
    // 매개변수 : boolean proStatus
    // 반환타입 : String -> "판매중" / "판매 종료"
    public String toproStatusChange( boolean proStatus ){
        if ( proStatus ){
            return "판매중";
        } else {
            return "판매종료";
        } // if end
    } // func end

    // product10. 제품명 유효성 검사
    // 기능설명 : [제품명]를 매개변수로 받아, 해당하는 제품의 존재 여부를 확인한다.
    // 메소드명 : proNameCheck()
    // 매개변수 : String proName
    // 반환타입 : boolean -> true : 제품 존재 / false : 제품 없음
    public boolean proNameCheck( String proName ){
        try {
            // 1. SQL 작성
            String SQL = "select count(*) count from Product where proName = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement( SQL );
            // 3. SQL 매개변수 대입
            ps.setString( 1, proName );
            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과 반환
            while( rs.next() ){
                if ( rs.getInt("count") != 0 ){         // 제품이 존재하면
                    return true;                        // true 반환
                } // if end
            } // while end
        } catch ( SQLException e ){
            System.out.println("[product10] SQL 기재 실패");
        } // try-catch end
        return false;
    } // func end
} // class end
