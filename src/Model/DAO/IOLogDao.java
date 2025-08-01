package Model.DAO;

public class IOLogDao {
    //싱글톤
    private IOLogDao(){}
    private static final IOLogDao instance = new IOLogDao();
    public static IOLogDao getInstance(){
        return instance;
    }
}
