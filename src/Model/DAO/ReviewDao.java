package Model.DAO;

public class ReviewDao {
    //싱글톤
    private ReviewDao(){}
    private static final ReviewDao instance = new ReviewDao();
    public static ReviewDao getInstance(){
        return instance;
    }
}
