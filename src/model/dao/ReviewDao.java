package model.dao;

import model.dto.ReviewPrintDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReviewDao {
    // 싱글톤
    private ReviewDao() {
        connect();
    }

    private static final ReviewDao instance = new ReviewDao();

    public static ReviewDao getInstance() {
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

    // 메소드 =====================================================================================

    // [review01] 리뷰전체조회 / reviewPrint()
    // 매개변수 : -
    // 반환타입 : ArrayList<reviewPrintDto>
    // 반환 : ArrayList<reviewPrintDto> 전체 출력
    public ArrayList<ReviewPrintDto> reviewPrint() {
        ArrayList<ReviewPrintDto> reviewPrintList = new ArrayList<>();
        try {
            // [1.1] SQL 작성
            String sql = "select review.reviewNo, review.orderNo, product.proName, orderlog.franNo, review.review from review join orderlog on (review.orderNo = orderlog.orderNo) inner join product on (product.proNo = orderlog.proNo) order by reviewNo desc;";
            // [1.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // [1.3] SQL 매개변수 대입 / 생략X
            // [1.4] SQL 실행
            ResultSet rs = ps.executeQuery();
            // [1.5] SQL 실행 결과 확인
            // [1.5.1] 반복문
            while (rs.next()) {
                // [1.5.2] dto 선언
                ReviewPrintDto reviewPrintDto = new ReviewPrintDto(
                        // [1.5.3] 생성자 삽입
                        rs.getInt("reviewNo"),
                        rs.getInt("orderNo"),
                        rs.getString("proName"),
                        rs.getInt("franNo"),
                        rs.getString("review")
                );
                // [1.5.4] list에 add
                reviewPrintList.add(reviewPrintDto);
            }
        } catch (Exception e) {
            System.out.println("[경고] 리뷰 조회가 불가합니다.");
        }
        // [1.6] 결과 반환
        return reviewPrintList;
    } // func end

    // [review02] 가맹점리뷰조회 / franReviewPrint()
    // 매개변수 : int franNo
    // 반환타입 : ArrayList<reviewPrintDto>
    // 반환 : ArrayList<reviewPrintDto> 출력
    public ArrayList<ReviewPrintDto> franReviewPrint(int franNo) {
        ArrayList<ReviewPrintDto> reviewPrintList = new ArrayList<>();
        try {
            // [2.1] SQL 작성
            String sql = "select review.reviewNo, review.orderNo, product.proName, orderlog.franNo, review.review from review join orderlog on (review.orderNo = orderlog.orderNo) inner join product on (product.proNo = orderlog.proNo) where franNo = ? order by reviewNo desc;";
            // [2.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // [2.3] SQL 매개변수 대입
            ps.setInt(1, franNo);
            // [2.4] SQL 실행
            ResultSet rs = ps.executeQuery();
            // [2.5] SQL 실행 결과 확인
            // [2.5.1] 반복문
            while (rs.next()) {
                // [2.5.2] dto 선언
                ReviewPrintDto reviewPrintDto = new ReviewPrintDto(
                        // [2.5.3] 생성자 삽입
                        rs.getInt("reviewNo"),
                        rs.getInt("orderNo"),
                        rs.getString("proName"),
                        rs.getInt("franNo"),
                        rs.getString("review")
                );
                // [2.5.4] list에 add
                reviewPrintList.add(reviewPrintDto);
            }
        } catch (Exception e) {
            System.out.println("[경고] 리뷰 조회가 불가합니다.");
        }
        // [2.6] 결과 반환
        return reviewPrintList;
    } // func end

    // [review03] 상품별리뷰 / proReviewPrint()
    // 매개변수 : String proName
    // 반환타입 : ArrayList<reviewPrintDto>
    // 반환 : ArrayList<reviewPrintDto> 출력
    public ArrayList<ReviewPrintDto> proReviewPrint(String proName) {
        ArrayList<ReviewPrintDto> reviewPrintList = new ArrayList<>();
        try {
            // [3.1] SQL 작성
            String sql = "select review.reviewNo, review.orderNo, product.proName, orderlog.franNo, review.review from review join orderlog on (review.orderNo = orderlog.orderNo) inner join product on (product.proNo = orderlog.proNo) where proName = ? order by reviewNo desc;";
            // [3.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // [3.3] SQL 매개변수 대입
            ps.setString(1, proName);
            // [3.4] SQL 실행
            ResultSet rs = ps.executeQuery();
            // [3.5] SQL 실행 결과 확인
            // [3.5.1] 반복문
            while (rs.next()) {
                // [3.5.2] dto 선언
                ReviewPrintDto reviewPrintDto = new ReviewPrintDto(
                        // [3.5.3] 생성자 삽입
                        rs.getInt("reviewNo"),
                        rs.getInt("orderNo"),
                        rs.getString("proName"),
                        rs.getInt("franNo"),
                        rs.getString("review")
                );
                // [3.5.4] list에 add
                reviewPrintList.add(reviewPrintDto);
            }
        } catch (Exception e) {
            System.out.println("[경고] 리뷰 조회가 불가합니다.");
        }
        // [3.6] 결과 반환
        return reviewPrintList;
    } // func end

} // class end

