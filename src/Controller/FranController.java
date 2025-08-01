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
        // 데이터 객체로 만들기
        FranDto franDto = new FranDto( 0, franName , franAddress , franCall , franOwner , false );
        // 객체화 된 dto를 dao 에게 전달 후 결과를 받는다.
        boolean result = franDao.franAdd(franDto);
        // 결과를 view에게 리턴
        return result;

    } // func end

    // fran02 가맹점 전체조회기능 구현
    public ArrayList<FranDto> franPrint(){
        // DAO 에게 전달후 결과를 받는다.
        ArrayList<FranDto> result = franDao.franPrint();
        // 결과를 view에게 리턴
        return result;
    } // func end

} // class end
