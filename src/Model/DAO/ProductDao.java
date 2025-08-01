package Model.DAO;

public class ProductDao {
    //싱글톤
    private ProductDao(){}
    private static final ProductDao instance = new ProductDao();
    public static ProductDao getInstance(){
        return instance;
    }
}
