package Model.DTO;

public class ReviewPrintDto {
    // 1. 멤버변수
    // 1) private
    private int reviewNo;       // 리뷰번호
    private int orderNo;        // 판매번호
    private String proName;     // 상품명
    private String franName;    // 가맹점명
    private String review;      // 리뷰

    // 2. 생성자
    // 1) 기본생성자
    public ReviewPrintDto() {
    }
    // 2) 전체생성자
    public ReviewPrintDto(int reviewNo, int orderNo, String proName, String franName, String review) {
        this.reviewNo = reviewNo;
        this.orderNo = orderNo;
        this.proName = proName;
        this.franName = franName;
        this.review = review;
    }

    // 3. 메소드
    // 1) setter, getter
    public int getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(int reviewNo) {
        this.reviewNo = reviewNo;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getFranName() {
        return franName;
    }

    public void setFranName(String franName) {
        this.franName = franName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
    // 2) toString

    @Override
    public String toString() {
        return "ReviewPrintDto{" +
                "reviewNo=" + reviewNo +
                ", orderNo=" + orderNo +
                ", proName='" + proName + '\'' +
                ", franName='" + franName + '\'' +
                ", review='" + review + '\'' +
                '}';
    }
} // class end
