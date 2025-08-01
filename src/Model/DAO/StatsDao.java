package Model.DAO;

public class StatsDao {
    //싱글톤
    private StatsDao(){}
    private static final StatsDao instance = new StatsDao();
    public static StatsDao getInstance(){
        return instance;
    }
}
