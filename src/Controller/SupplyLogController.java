package Controller;

public class SupplyLogController {
    // 싱글톤
    private SupplyLogController(){}
    private static final SupplyLogController instance = new SupplyLogController();
    public static SupplyLogController getInstance(){
        return instance;
    }
}
