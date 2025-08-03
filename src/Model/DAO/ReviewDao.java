package Model.DAO;

import Model.DTO.ReviewDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class ReviewDao {
    // 싱글톤
    private ReviewDao(){connect();}
    private static final ReviewDao instance = new ReviewDao();
    public static ReviewDao getInstance(){
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

    // ArrayList 선언
    private ArrayList<ReviewDto> reviewDtoList = new ArrayList<>();

    // 메소드 =====================================================================================

    // [review00] /
    // 매개변수 :
    // 반환타입 :
    // 반환 :
    // [0.1]

    // [review01] 리뷰전체조회 / reviewPrint()
    // 매개변수 : -
    // 반환타입 : ArrayList<reviewPrintDto>
    // 반환 : ArrayList<reviewPrintDto> 전체 출력
    // [1.1]

    // [review02] 가맹점리뷰조회 / franReviewPrint()
    // 매개변수 : String franName
    // 반환타입 : ArrayList<reviewPrintDto>
    // 반환 : ArrayList<reviewPrintDto> 출력
    // [2.1]

    // [review03] 상품별리뷰 / proReviewPrint()
    // 매개변수 : String proName
    // 반환타입 : ArrayList<reviewPrintDto>
    // 반환 : ArrayList<reviewPrintDto> 출력
    // [3.1]


} // class end

