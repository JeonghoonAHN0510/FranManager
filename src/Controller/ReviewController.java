package Controller;

import Model.DAO.ReviewDao;

public class ReviewController {
    // 싱글톤
    private ReviewController(){}
    private static final ReviewController instance = new ReviewController();
    public static ReviewController getInstance(){
        return instance;
    }

    // dao 호출
    private ReviewDao reviewDao = ReviewDao.getInstance();

    // 메소드 =====================================================================================

    // [RV-00] /
    // 매개변수 :
    // 반환타입 :
    // 반환 :
    // [0.1]

    // [RV-01] 리뷰전체조회 / reviewPrint()
    // 매개변수 : -
    // 반환타입 : ArrayList<reviewPrintDto>
    // 반환 : ArrayList<reviewPrintDto> 전체 출력
    // [1.1]

    // [RV-02] 가맹점리뷰조회 / franReviewPrint()
    // 매개변수 : String franName
    // 반환타입 : ArrayList<reviewPrintDto>
    // 반환 : ArrayList<reviewPrintDto> 출력
    // [2.1]

    // [RV-03] 상품별리뷰 / proReviewPrint()
    // 매개변수 : String proName
    // 반환타입 : ArrayList<reviewPrintDto>
    // 반환 : ArrayList<reviewPrintDto> 출력
    // [3.1]

} // class end
