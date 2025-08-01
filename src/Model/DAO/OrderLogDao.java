package Model.DAO;

public class OrderLogDao {
    //싱글톤
    private OrderLogDao(){}
    private static final OrderLogDao instance = new OrderLogDao();
    public static OrderLogDao getInstance(){
        return instance;
    }
}
