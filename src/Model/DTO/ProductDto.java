package Model.DTO;

public class ProductDto {
    //1. 멤버변수
    //1) private
    private int proNo;
    private String proName;
    private int proSupPrice;
    private int proPrice;
    private boolean proStatus;

    //2. 생성자
    //1) 기본생성자

    public ProductDto() {
    }

    //2) 전체생성자

    public ProductDto(int proNo, String proName, int proSupPrice, int proPrice, boolean proStatus) {
        this.proNo = proNo;
        this.proName = proName;
        this.proSupPrice = proSupPrice;
        this.proPrice = proPrice;
        this.proStatus = proStatus;
    }

    //3. 메소드
    //1)setter, getter

    public int getProNo() {
        return proNo;
    }

    public void setProNo(int proNo) {
        this.proNo = proNo;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public int getProSupPrice() {
        return proSupPrice;
    }

    public void setProSupPrice(int proSupPrice) {
        this.proSupPrice = proSupPrice;
    }

    public int getProPrice() {
        return proPrice;
    }

    public void setProPrice(int proPrice) {
        this.proPrice = proPrice;
    }

    public boolean isProStatus() {
        return proStatus;
    }

    public void setProStatus(boolean proStatus) {
        this.proStatus = proStatus;
    }

    //2) toString


    @Override
    public String toString() {
        return "ProductDto{" +
                "proNo=" + proNo +
                ", proName='" + proName + '\'' +
                ", proSupPrice=" + proSupPrice +
                ", proPrice=" + proPrice +
                ", proStatus=" + proStatus +
                '}';
    }
}
