import Controller.FranController;
import Model.DTO.FranDto;
import View.View;

import java.util.ArrayList;

public class AppStart {
    public static void main(String[] args) {

        View.getInstance().mainView();

        // 0801 가맹점 추가 테스트 완료 -> 주석처리
            // boolean result = FranController.getInstance().franAdd( "테스트점" ,  "인천광역시서구어쩌구" , "010-8234-3508", "나다" );
            // System.out.println(result);

        // 0801 가맹점 전체조회 테스트 X 다시해야함  // TODO
        // ArrayList<FranDto> result2 = FranController.getInstance().franPrint();
        // System.out.println(result2);

    } // main end
} // class end
