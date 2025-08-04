package Controller;


import Model.DAO.ProductDao;

public class ProductController {
    // 싱글톤
    private ProductController(){}
    private static final ProductController instance = new ProductController();
    public static ProductController getInstance(){
        return instance;
    }

    // ProductDao 싱글톤 가져오기
    private ProductDao productDao = ProductDao.getInstance();

    // product02. 제품명 반환(번호 > 이름)
    // 기능설명 : [제품번호]를 매개변수로 받아, 해당하는 제품명을 반환한다.
    // 메소드명 : toProNameChange()
    // 매개변수 : int proNo
    // 반환타입 : String
    public String toProNameChange( int proNo ){
        // 1. (필요 시) 유효성 검사
        // 2. dao에게 전달 후 결과 받기
        String proName = productDao.toProNameChange( proNo );
        // 3. view에게 결과 전달하기
        return proName;
    } // func end
} // func end
