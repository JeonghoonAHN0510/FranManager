package Controller;

public class FranController {
    //싱글톤
    private FranController(){}
    private static final FranController instance = new FranController();
    public static FranController getInstance(){
        return instance;
    }
}
