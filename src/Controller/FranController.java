package Controller;

import Model.DAO.FranDao;
import Model.DTO.FranDto;

import java.util.ArrayList;

public class FranController {
    // 싱글톤
    private FranController(){}
    private static final FranController instance = new FranController();
    public static FranController getInstance(){
        return instance;
    }
    // 싱글톤 가져오기
    private FranDao franDao = FranDao.getInstance();

    // fran01 가맹점 추가기능 구현
    public boolean franAdd( String franName, String franAddress, String franCall, String franOwner ){
            // 유효성 검사
        if (franName.length() <=1){ // 가맹점명이 한글자 이하이면 오류
            System.out.println("[경고] 가맹점 이름이 올바르지 않습니다.");
            return false;}
        if( franAddress.length() > 6 && // 주소가 최소 7글자 이상
            franAddress.charAt(2) != '도' &&
            franAddress.charAt(3) != '도' &&
            franAddress.charAt(6) != '도' &&
            franAddress.charAt(2) != '시' &&
            franAddress.charAt(4) != '시' &&
            franAddress.charAt(6) != '시')
            // 3,4,6번째 글자에 "도" 안들어가면 false , 3,5,7번째 글자에 "시" 안들어가면 false
            {System.out.println("[경고] 주소가 올바르지 않습니다.");
            return false;}
        if(franCall.length() <= 9){
            System.out.println("[경고] 올바른 전화번호가 아닙니다.");
            return false;}
            // 데이터 객체로 만들기
            FranDto franDto = new FranDto(0, franName, franAddress, franCall, franOwner, false);
            // 객체화 된 dto를 dao 에게 전달 후 결과를 받는다.
            boolean result = franDao.franAdd(franDto);
            // 결과를 view에게 리턴
            return result;

    } // func end

    // fran02 가맹점 전체조회기능 구현 //
    public ArrayList<FranDto> franPrint(){
        // DAO 에게 전달후 결과를 받는다.
        ArrayList<FranDto> result = franDao.franPrint();
        // 결과를 view에게 리턴
        return result;
    } // func end

    // fran03 가맹점 단일조회기능 구현
    public FranDto oneFranPrint( int franNo ){
        // DAO 에게 전달후 결과를 받는다.
        FranDto franDto = franDao.oneFranPrint(franNo);
        return franDto;

    // fran04 가맹점 수정기능 구현

    // fran05 가맹점 삭제기능 구현

    } // func end



} // class end
