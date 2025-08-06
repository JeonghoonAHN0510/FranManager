package model.dto;

public class FranDto {
    // 1. 멤버변수
    // 1) private
        private int franNo;         // 가맹점번호 PK
        private String franName;    // 가맹점명
        private String franAddress; // 상세주소
        private String franCall;    // 전화번호
        private String franOwner;   // 가맹주명
        private boolean franStatus; // 폐점여부
        private int P;              // 매출( orderQty * orderPrice )

    //2. 생성자
   //1) 기본생성자

    public FranDto() {
    }
    //2) 전체생성자

    public FranDto(int franNo, String franName, String franAddress, String franCall, String franOwner, boolean franStatus) {
        this.franNo = franNo;
        this.franName = franName;
        this.franAddress = franAddress;
        this.franCall = franCall;
        this.franOwner = franOwner;
        this.franStatus = franStatus;
    }

    public FranDto(int franNo, String franName, String franAddress, String franCall, String franOwner, boolean franStatus, int p) {
        this.franNo = franNo;
        this.franName = franName;
        this.franAddress = franAddress;
        this.franCall = franCall;
        this.franOwner = franOwner;
        this.franStatus = franStatus;
        P = p;
    }
    //3. 메소드
        //1)setter, getter

    public int getFranNo() {
        return franNo;
    }

    public void setFranNo(int franNo) {
        this.franNo = franNo;
    }

    public String getFranName() {
        return franName;
    }

    public void setFranName(String franName) {
        this.franName = franName;
    }

    public String getFranAddress() {
        return franAddress;
    }

    public void setFranAddress(String franAddress) {
        this.franAddress = franAddress;
    }

    public String getFranCall() {
        return franCall;
    }

    public void setFranCall(String franCall) {
        this.franCall = franCall;
    }

    public String getFranOwner() {
        return franOwner;
    }

    public void setFranOwner(String franOwner) {
        this.franOwner = franOwner;
    }

    public boolean isFranStatus() {
        return franStatus;
    }

    public void setFranStatus(boolean franStatus) {
        this.franStatus = franStatus;
    }

    public int getP() {
        return P;
    }

    public void setP(int p) {
        P = p;
    }

    //2) toString


    @Override
    public String toString() {
        return "FranDto{" +
                "franNo=" + franNo +
                ", franName='" + franName + '\'' +
                ", franAddress='" + franAddress + '\'' +
                ", franCall='" + franCall + '\'' +
                ", franOwner='" + franOwner + '\'' +
                ", franStatus=" + franStatus +
                ", P=" + P +
                '}';
    }
}
