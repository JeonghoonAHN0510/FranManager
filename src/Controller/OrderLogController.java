package Controller;

public class OrderLogController {
    // 싱글톤
    private OrderLogController(){}
    private static final OrderLogController instance = new OrderLogController();
    public static OrderLogController getInstance(){
        return instance;
    }
}
