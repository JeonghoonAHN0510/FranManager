package Controller;

import Model.DAO.SupplyLogDao;
import Model.DTO.SupplyLogDto;

import java.util.ArrayList;

public class SupplyLogController {
    // 싱글톤
    private SupplyLogController(){}
    private static final SupplyLogController instance = new SupplyLogController();
    public static SupplyLogController getInstance(){
        return instance;
    } // func end

    // * SupplyLogDao 싱글톤 가져오기
    private SupplyLogDao supplyLogDao = SupplyLogDao.getInstance();

    // supply01. 발주요청 전체조회
    // 기능설명 : DB에 저장된 요청대기 중인 발주기록울 조회한다. { 발주번호, 가맹점명, 상품명, 주문수량, 메모 }
    // 메소드명 : supplyPrintAll()
    // 매개변수 : X
    // 반환타입 : ArrayList<SupplyLogDto>
    public ArrayList<SupplyLogDto> supplyPrintAll(){
        // 1. 유효성 검사(필요 시)
        // 2. dao로부터 반환받기
        ArrayList<SupplyLogDto> supplyLogDtos = supplyLogDao.supplyPrintAll();
        // 3. view에게 전달하기
        return supplyLogDtos;
    } // func end
    // todo view 연결 필요

    // supply02. 발주요청 선택조회
    // 기능설명 : [발주번호]를 입력받아, 해당하는 발주요청을 출력한다.
    // 메소드명 : supplyPrint()
    // 매개변수 : int supNo
    // 반환타입 : SupplyLogDto
    public SupplyLogDto supplyPrint( int supNo ){
        // 1. dao에게 전달 후 결과 받기
        SupplyLogDto supplyLogDto = supplyLogDao.supplyPrint( supNo );

        // 3. view에게 전달하기
        return supplyLogDto;
    } // func end
    // todo view 연결 필요

    // supply03. 발주요청 승인
    // 기능설명 : [발주번호와 가맹점명]을 입력받아, 해당하는 발주요청의 발주상태를 1로 변경하고, 발주처리날짜를 입력한다. 재고로그에 해당 { 제품번호, 발주번호, 수량, 입출고일자 }를 추가한다.
    // 메소드명 : supplyApp()
    // 매개변수 : int supNo, String franName
    // 반환타입 : boolean -> true(성공) / false(실패)


    // supply04. 발주요청 취소
    // 기능설명 : [발주번호와 가맹점명]을 입력받아, 해당하는 발주요청의 발주상태를 2로 변경한다.
    // 메소드명 : supplyCancel()
    // 매개변수 : int supNo, String franName
    // 반환타입 : boolean -> true(성공) / false(실패)

} // class end
