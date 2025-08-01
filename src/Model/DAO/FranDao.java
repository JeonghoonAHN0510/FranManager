package Model.DAO;

public class FranDao {
    //싱글톤
    private FranDao(){}
    private static final FranDao instance = new FranDao();
    public static FranDao getInstance(){
        return instance;
    }
}
