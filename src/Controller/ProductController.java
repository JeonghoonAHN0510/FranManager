package Controller;


import Model.DAO.ProductDao;
import Model.DTO.ProductDto;

import java.util.ArrayList;

public class ProductController {
    // 싱글톤
    private ProductController(){}
    private static final ProductController instance = new ProductController();
    public static ProductController getInstance(){
        return instance;
    }

    // ProductDao 싱글톤 가져오기
    private ProductDao productDao = ProductDao.getInstance();

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

    // [product03] 상품번호 유효성 검사 / proNoCheck()
    // 매개변수 : int proNo
    // 반환타입 : boolean
    public boolean proNoCheck(int proNo){
        // [3.1] dao에 전달
        boolean result = productDao.proNoCheck(proNo);
        // [3.2] 결과 반환
        return result;
    }// func end

    // product04. 제품 전체조회
    // 기능설명 : DB에 저장된 모든 제품을 조회하여 출력한다.
    // 메소드명 : productAllPrint()
    // 매개변수 : X
    // 반환타입 : ArrayList<ProductDto>
    public ArrayList<ProductDto> productAllPrint(){
        // 1. dao에게 결과 받기
        ArrayList<ProductDto> productDtos = productDao.productAllPrint();
        // 2. view에게 결과 전달하기
        return productDtos;
    } // func end

    // product05. 제품 선택조회
    // 기능설명 : [제품번호]를 입력받아, 해당하는 제품정보를 출력한다.
    // 메소드명 : productPrint()
    // 매개변수 : int proNo
    // 반환타입 : ProductDto
    public ProductDto productPrint( int proNo ){
        // 1. dao에게 값 전달 후 결과 받기
        ProductDto productDto = productDao.productPrint( proNo );
        // 2. view에게 결과 전달하기
        return productDto;
    } // func end

    // product06. 제품 등록
    // 기능설명 : [제품명, 공급가액, 소비자판매가]를 입력받아, 제품을 추가한다.
    // 메소드명 : productAdd()
    // 매개변수 : String proName, int proSupPrice, int proPrice
    // 반환타입 : boolean -> true : 등록 성공 / false : 등록 실패
    public boolean productAdd( String proName, int proSupPrice, int proPrice ){
        // 1. dao에게 전달할 객체 생성하기
        ProductDto productDto = new ProductDto( 0, proName, proSupPrice, proPrice, true );
        // 2. dao에게 전달 후 결과 받기
        boolean result = productDao.productAdd( productDto );
        // 3. view에게 결과 전달하기
        return result;
    } // func end

    // product07. 제품 수정
    // 기능설명 : [제품번호, 제품명, 공급가액, 소비자판매가]를 입력받아, 해당하는 제품정보를 수정한다.
    // 메소드명 : productUpdate()
    // 매개변수 : int proNo, String proName, int proSupPrice, int proPrice
    // 반환타입 : boolean -> true : 수정 성공 / false : 등록 실패
    public boolean productUpdate( int proNo, String proName, int proSupPrice, int proPrice ){
        // 1. dao에게 전달할 객체 생성하기
        ProductDto productDto = new ProductDto( proNo, proName, proSupPrice, proPrice, true );
        // 2. dao에게 전달 후 결과 받기
        boolean result = productDao.productUpdate( productDto );
        // 3. view에게 결과 전달하기
        return result;
    } // func end

    // product08. 제품 삭제
    // 기능설명 : [제품번호, 제품명]을 입력받아, 해당하는 제품을 삭제처리한다.
    // 메소드명 : productDelete()
    // 매개변수 : int proNo, String proName
    // 반환타입 : boolean -> true : 삭제 성공 / false : 삭제 실패
    public boolean productDelete( int proNo, String proName ){
        // 1. dao에게 전달할 객체 생성
        ProductDto productDto = new ProductDto( proNo, proName, 0, 0, true );
        // 2. dao에게 객체 전달 후 결과받기
        boolean result = productDao.productDelete( productDto );
        // 3. view에게 결과 전달하기
        return result;
    } // func end

    // product09. 판매여부 변환
    // 기능설명 : [판매여부(boolean)]을 받아, [판매여부(String)]으로 반환한다.
    // 메소드명 : toproStatusChange()
    // 매개변수 : boolean proStatus
    // 반환타입 : String -> "판매중" / "판매 종료"
    public String toproStatusChange( boolean proStatus ){
        // 1. dao에게 전달 후 결과받기
        String status = productDao.toproStatusChange( proStatus );
        // 2. view에게 결과 전달하기
        return status;
    } // func end
} // class end
