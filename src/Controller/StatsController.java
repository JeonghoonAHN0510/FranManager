package Controller;

public class StatsController {
    // 싱글톤
    private StatsController(){}
    private static final StatsController instance = new StatsController();
    public static StatsController getInstance(){
        return instance;
    }
}
