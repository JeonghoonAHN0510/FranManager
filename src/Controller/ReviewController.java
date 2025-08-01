package Controller;

public class ReviewController {
    // 싱글톤
    private ReviewController(){}
    private static final ReviewController instance = new ReviewController();
    public static ReviewController getInstance(){
        return instance;
    }
}
