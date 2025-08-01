package Controller;

public class IOLogController {
    //싱글톤
    private IOLogController(){}
    private static final IOLogController instance = new IOLogController();
    public static IOLogController getInstance(){
        return instance;
    }
}
