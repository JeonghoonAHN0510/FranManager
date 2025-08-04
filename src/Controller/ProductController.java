package Controller;

import Model.DAO.ProductDao;

public class ProductController {
    // 싱글톤
    private ProductController(){}
    private static final ProductController instance = new ProductController();
    public static ProductController getInstance(){
        return instance;
    }

    // dao 호출
    private ProductDao productDao = ProductDao.getInstance();

    // 메소드 ==================

    // [product01] 제품번호 반환 / toIntproNoChange()
    // 매개변수 : String proName
    // 반환타입 : int
    // 반환 : proNo
    public int toIntproNoChange(String proName) {
    // [1.1] dao의 함수 실행
        int result = productDao.toIntproNoChange(proName);
    // [1.2] 결과를 view로 반환
        return result;
    } // func end

} // class end
