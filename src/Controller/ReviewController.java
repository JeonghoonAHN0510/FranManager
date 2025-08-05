package Controller;

import Model.DAO.ReviewDao;
import Model.DTO.ReviewPrintDto;

import java.util.ArrayList;

public class ReviewController {
    // 싱글톤
    private ReviewController() {
    }

    private static final ReviewController instance = new ReviewController();

    public static ReviewController getInstance() {
        return instance;
    }

    // dao 호출
    private ReviewDao reviewDao = ReviewDao.getInstance();

    // 메소드 =====================================================================================

    // [review01] 리뷰전체조회 / reviewPrint()
    // 매개변수 : -
    // 반환타입 : ArrayList<reviewPrintDto>
    // 반환 : ArrayList<reviewPrintDto> 전체 출력
    public ArrayList<ReviewPrintDto> reviewPrint() {
        // [1.1] dao func 실행
        ArrayList<ReviewPrintDto> reviewPrintList = reviewDao.reviewPrint();
        // [1.2] 결과 반환
        return reviewPrintList;
    }

    // [review02] 가맹점리뷰조회 / franReviewPrint()
    // 매개변수 : int franNo
    // 반환타입 : ArrayList<reviewPrintDto>
    // 반환 : ArrayList<reviewPrintDto> 출력
    public ArrayList<ReviewPrintDto> franReviewPrint(int franNo) {
        // [2.1] dao func 실행
        ArrayList<ReviewPrintDto> reviewPrintList = reviewDao.franReviewPrint(franNo);
        // [2.2] 결과 반환
        return reviewPrintList;
    }

    // [review03] 상품별리뷰 / proReviewPrint()
    // 매개변수 : String proName
    // 반환타입 : ArrayList<reviewPrintDto>
    // 반환 : ArrayList<reviewPrintDto> 출력
    public ArrayList<ReviewPrintDto> proReviewPrint(String proName) {
        // [3.1] dao func 실행
        ArrayList<ReviewPrintDto> reviewPrintList = reviewDao.proReviewPrint(proName);
        // [3.2] 결과 반환
        return reviewPrintList;
    }
} // class end
