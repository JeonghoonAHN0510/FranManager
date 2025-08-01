package Model.DTO;

public class SupplyLogDto {
    // 1. 멤버변수
    // 1) private
    private int supNo;          // 발주번호 PK
    private int franNo;         // 가맹점번호 FK
    private int proNo;          // 제품번호 FK
    private int supQty;         // 주문수량
    private String supReqDate;  // 발주요청날짜
    private int supStatus;      // 발주상태
    private String supMemo;     // 메모

    // 2. 생성자
    // 1) 기본생성자

    public SupplyLogDto() {
    }

    // 2) 전체생성자

    public SupplyLogDto(int supNo, int franNo, int proNo, int supQty, String supReqDate, int supStatus, String supMemo) {
        this.supNo = supNo;
        this.franNo = franNo;
        this.proNo = proNo;
        this.supQty = supQty;
        this.supReqDate = supReqDate;
        this.supStatus = supStatus;
        this.supMemo = supMemo;
    }

    // 3. 메소드
    // 1) setter, getter

    public int getSupNo() {
        return supNo;
    }

    public void setSupNo(int supNo) {
        this.supNo = supNo;
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

    public int getSupQty() {
        return supQty;
    }

    public void setSupQty(int supQty) {
        this.supQty = supQty;
    }

    public String getSupReqDate() {
        return supReqDate;
    }

    public void setSupReqDate(String supReqDate) {
        this.supReqDate = supReqDate;
    }

    public int getSupStatus() {
        return supStatus;
    }

    public void setSupStatus(int supStatus) {
        this.supStatus = supStatus;
    }

    public String getSupMemo() {
        return supMemo;
    }

    public void setSupMemo(String supMemo) {
        this.supMemo = supMemo;
    }

    // 2) toString


    @Override
    public String toString() {
        return "SupplyLogDto{" +
                "supNo=" + supNo +
                ", franNo=" + franNo +
                ", proNo=" + proNo +
                ", supQty=" + supQty +
                ", supReqDate='" + supReqDate + '\'' +
                ", supStatus=" + supStatus +
                ", supMemo='" + supMemo + '\'' +
                '}';
    }
}
