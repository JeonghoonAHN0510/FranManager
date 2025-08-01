package Model.DAO;

public class SupplyLogDao {
    //싱글톤
    private SupplyLogDao(){}
    private static final SupplyLogDao instance = new SupplyLogDao();
    public static SupplyLogDao getInstance(){
        return instance;
    }
}
