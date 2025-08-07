package controller;

import model.dao.FranDao;
import model.dto.FranDto;

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
        if( franAddress.length() < 6)
            {System.out.println("[경고] 주소가 올바르지 않습니다.");
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
    } // func end

    // fran04 가맹점 수정기능 구현
    public boolean franUpdate(int franNo, String franName, String franAddress, String franCall, String franOwner){
        // 유효성검사
        if (franName.length() <=1){ // 가맹점명이 한글자 이하이면 오류
            System.out.println("[경고] 가맹점 이름이 올바르지 않습니다.");
            return false;}
        if( franAddress.length() < 6)
        {System.out.println("[경고] 주소가 올바르지 않습니다.");
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
        boolean result = false;
        // DTO 객체 생성
        FranDto franDto = new FranDto( franNo , franName , franAddress , franCall , franOwner , false );
        // DAO 에 전달
        result = franDao.franUpdate(franDto);
        // 결과 반환
        return result;
    }

    // fran05 가맹점 삭제기능 구현
       public boolean franDelete( int franNo , String franName , String franOwer){
        boolean result = false;
        // DTO 객체 생성
           FranDto franDto = new FranDto( franNo, franName , "", "",franOwer,true );
        // DAO 에 전달
        result = franDao.franDelete(franDto);
        // 결과 반환
        return result;

       }

    // fran06. 가맹점명 반환(번호 > 이름)
    // 기능설명 : [가맹점번호]를 매개변수로 받아, 해당하는 가맹점명을 반환한다.
    // 메소드명 : toFranNameChange()
    // 매개변수 : int franNo
    // 반환타입 : String
    public String toFranNameChange( int franNo ){
        // 1. (필요 시) 유효성 검사
        // 2. dao에게 전달 후 결과 받기
        String franName = franDao.toFranNameChange( franNo );
        // 3. view에게 결과 전달하기
        return franName;
    } // func end

    // fran07. 가맹점번호 반환(번호 > 이름)
    // 기능설명 : [가맹점명]를 매개변수로 받아, 해당하는 가맹점번호를 반환한다.
    // 메소드명 : toIntNameFranChange()
    // 매개변수 : String franName
    // 반환타입 : int
    public int toIntNameFranChange( String franName ){
        // 1. (필요 시) 유효성 검사
        // 2. dao에게 전달 후 결과 받기
        int franNo = franDao.toIntNameFranChange( franName );
        // 3. view에게 결과 전달하기
        return franNo;
    } // func end

    // [fran08] 가맹점번호 유효성검사 / franNoCheck()
    // 매개변수 : int franNo
    // 반환 : boolean
    public boolean franNoCheck(int franNo){
        // [8.1] dao 메소드 실행
        boolean result = FranDao.getInstance().franNoCheck(franNo);
        // [8.2] 결과 반환
        return result;
    } // func end

    // fran09. 가맹점명 간격조정
    // 기능설명 : [가맹점명 / 글자수]를 매개변수로 받아, 글자수를 정렬한다.
    // 메소드명 : franNameArray()
    // 매개변수 : String franName, int length
    // 반환타입 : String
    public String franNameArray( String franName, int length ){
        if ( length <= 4 ){
            franName += "\t\t";
        }
        if ( length == 5 || length == 6 ){
            franName += "\t";
        }
        return franName;
    } // func end

    // fran10. 전화번호 정렬
    // 기능설명 : [전화번호 / 글자수]를 매개변수로 받아, 전화번호를 정렬한다.
    // 메소드명 : franCallArray()
    // 매개변수 : String franCall , int length
    // 반환타입 : String
    public String franCallArray( String franCall , int length ){
        if( length == 10 ) {
            franCall += "\t\t";
        }
        if( length == 11){
            franCall += "\t";
        } return franCall;
    }
} // class end
