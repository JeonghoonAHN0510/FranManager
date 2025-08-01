package Model.DTO;

public class OrderLogDto {
    // 1. 멤버변수
    // 1) private
    private int orderNo;        // 판매번호 PK
    private int franNo;         // 가맹점번호 FK
    private int proNo;          // 제품번호 FK
    private int orderQty;       // 판매수량
    private int orderPrice;     // 당시판매가겨
    private String orderDate;   // 판매날짜

    //2. 생성자
    //1) 기본생성자

    public OrderLogDto() {
    }

    //2) 전체생성자

    public OrderLogDto(int orderNo, int franNo, int proNo, int orderQty, int orderPrice, String orderDate) {
        this.orderNo = orderNo;
        this.franNo = franNo;
        this.proNo = proNo;
        this.orderQty = orderQty;
        this.orderPrice = orderPrice;
        this.orderDate = orderDate;
    }


    //3. 메소드
    //1)setter, getter

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getFranNo() {
        return franNo;
    }

    public void setFranNo(int franNo) {
        this.franNo = franNo;
    }

    public int getProNo() {
        return proNo;
    }

    public void setProNo(int proNo) {
        this.proNo = proNo;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    //2) toString


    @Override
    public String toString() {
        return "OrderLogDto{" +
                "orderNo=" + orderNo +
                ", franNo=" + franNo +
                ", proNo=" + proNo +
                ", orderQty=" + orderQty +
                ", orderPrice=" + orderPrice +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
