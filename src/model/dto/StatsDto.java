package model.dto;

public class StatsDto {
    // 1. 멤버변수
    // 1) private
    private int number;
    private String region;
    private int totalPrice;

    // 2. 생성자
    // 1) 기본생성자
    public StatsDto() { }
    // 2) 전체생성자
    public StatsDto(int number, String region, int totalPrice) {
        this.number = number;
        this.region = region;
        this.totalPrice = totalPrice;
    } // func end
    // 3) 부분생성자
    public StatsDto(int number, int totalPrice) {
        this.number = number;
        this.totalPrice = totalPrice;
    } // func end
    public StatsDto(String region, int totalPrice) {
        this.region = region;
        this.totalPrice = totalPrice;
    } // func end
    // 3. 메소드
    // 1) setter, getter
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }
    // 2) toString
    @Override
    public String toString() {
        return "StatsDto{" +
                "number=" + number +
                ", region='" + region + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    } // func end
} // class end
