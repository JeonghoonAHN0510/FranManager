package Model.DTO;

public class IOLogDto {
    // 1. 멤버변수
    // 1) private
        private int ioNo;       // 입출고번호 PK
        private int proNo;      // 제품번호 FK
        private int IO;         // 입고·출고 // 입고 = 0 // 출고 = supplyNo 참조
        private int ioQty;      // 입출고수량
        private String ioDate;  // 입출고일자
        private String ioMemo;  // 메모

    //2. 생성자
    //1) 기본생성자
    public IOLogDto() {
    }
    //2) 전체생성자
    public IOLogDto(int ioNo, int proNo, int IO, int ioQty, String ioDate, String ioMemo) {
        this.ioNo = ioNo;
        this.proNo = proNo;
        this.IO = IO;
        this.ioQty = ioQty;
        this.ioDate = ioDate;
        this.ioMemo = ioMemo;
    }

    //3. 메소드
    //1)setter, getter
    public int getIoNo() {
        return ioNo;
    }

    public void setIoNo(int ioNo) {
        this.ioNo = ioNo;
    }

    public int getProNo() {
        return proNo;
    }

    public void setProNo(int proNo) {
        this.proNo = proNo;
    }

    public int getIO() {
        return IO;
    }

    public void setIO(int IO) {
        this.IO = IO;
    }

    public int getIoQty() {
        return ioQty;
    }

    public void setIoQty(int ioQty) {
        this.ioQty = ioQty;
    }

    public String getIoDate() {
        return ioDate;
    }

    public void setIoDate(String ioDate) {
        this.ioDate = ioDate;
    }

    public String getIoMemo() {
        return ioMemo;
    }

    public void setIoMemo(String ioMemo) {
        this.ioMemo = ioMemo;
    }

    //2) toString
    @Override
    public String toString() {
        return "IOLogDto{" +
                "ioNo=" + ioNo +
                ", proNo=" + proNo +
                ", IO=" + IO +
                ", ioQty=" + ioQty +
                ", ioDate='" + ioDate + '\'' +
                ", ioMemo='" + ioMemo + '\'' +
                '}';
    }
} // class end
