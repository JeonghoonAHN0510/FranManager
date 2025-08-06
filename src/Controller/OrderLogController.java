package Controller;

import Model.DAO.OrderLogDao;
import Model.DTO.OrderLogDto;

import java.util.ArrayList;

public class OrderLogController {
    // 싱글톤
    private OrderLogController(){}
    private static final OrderLogController instance = new OrderLogController();
    public static OrderLogController getInstance(){
        return instance;
    }

    // * OrderLogDao 싱글톤 가져오기
    private OrderLogDao orderLogDao = OrderLogDao.getInstance();

    // order01 판매현황 조회기능 구현
    public ArrayList<OrderLogDto> saleStatsPrint(){
        // DAO 에게 전달후 결과를 받는다.
        ArrayList<OrderLogDto> result = OrderLogDao.getInstance().saleStatsPrint();
        // 결과를 view에게 리턴
        return result;
    } // func end

    // order02. 판매현황 페이지네이션
    // 기능설명 : DB에 저장된 판매 현황을 페이지네이션하여 [페이지 번호]를 입력받아 해당 페이지를 조회하여 출력한다.
    // 메소드명 : orderLogPage()
    // 매개변수 : int page
    // 반환타입 : ArrayList<OrderLogDto>
    public ArrayList<OrderLogDto> orderLogPage( int page ){
        // 1. dao에게 전달 후 결과 받기
        ArrayList<OrderLogDto> result = orderLogDao.orderLogPage( page );
        // 2. view에게 결과 전달하기
        return result;
    } // func end

    // order03. 판매수 반환
    // 기능설명 : DB에 저장된 판매수를 반한환다.
    // 메소드명 : orderNumber()
    // 매개변수 : X
    // 반환타입 : int
    public int orderNumber(){
        // 1. dao에게 전달 후 결과 받기
        int result = orderLogDao.orderNumber();
        // 2. view에게 결과 전달하기
        return result;
    } // func end
} // class end
