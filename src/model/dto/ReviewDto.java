package model.dto;

public class ReviewDto {
    // 1. 멤버변수
    // 1) private
    private int reviewNo;       // 리뷰번호 PK
    private String review;      // 리뷰
    private int orderNo;        // 판매번호 FK

    // 2. 생성자
    // 1) 기본생성자
    public ReviewDto() {
    }

    // 2) 전체생성자
    public ReviewDto(int reviewNo, String review, int orderNo) {
        this.reviewNo = reviewNo;
        this.review = review;
        this.orderNo = orderNo;
    }

    // 3. 메소드
    // 1) setter, getter
    public int getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(int reviewNo) {
        this.reviewNo = reviewNo;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    // 2) toString
    @Override
    public String toString() {
        return "ReviewDto{" +
                "reviewNo=" + reviewNo +
                ", review='" + review + '\'' +
                ", orderNo=" + orderNo +
                '}';
    }
} // class end
