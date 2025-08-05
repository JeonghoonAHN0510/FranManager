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

    // order01 판매현황 조회기능 구현
    public ArrayList<OrderLogDto> saleStatsPrint(){
        // DAO 에게 전달후 결과를 받는다.
        ArrayList<OrderLogDto> result = OrderLogDao.getInstance().saleStatsPrint();
        // 결과를 view에게 리턴
        return result;
    } // func end
}
