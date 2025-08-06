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
        // 1. (필요 시) 유효성 검사
        // 2. dao로부터 반환받기
        ArrayList<SupplyLogDto> supplyLogDtos = supplyLogDao.supplyPrintAll();
        // 3. view에게 전달하기
        return supplyLogDtos;
    } // func end

    // supply02. 발주요청 선택조회
    // 기능설명 : [발주번호]를 입력받아, 해당하는 발주요청을 출력한다.
    // 메소드명 : supplyPrint()
    // 매개변수 : int supNo
    // 반환타입 : SupplyLogDto
    public SupplyLogDto supplyPrint( int supNo ){
        // 반환할 객체 생성
        SupplyLogDto supplyLogDto = new SupplyLogDto();
        // 1. (필요 시) 유효성 검사
        boolean check = supNoCheck( supNo );
        if ( !check ){  // 유효성 검사에 걸린다면
            supplyLogDto.setSupNo(0);
            return supplyLogDto;
        } // if end
        // 2. dao에게 전달 후 결과 받기
        supplyLogDto = supplyLogDao.supplyPrint( supNo );
        // 3. view에게 전달하기
        return supplyLogDto;
    } // func end

    // supply03. 발주요청 승인
    // 기능설명 : [발주번호와 가맹점명]을 입력받아, view에서 [가맹점명 -> 가맹점번호] 변환 후, 해당하는 발주요청의 발주상태를 1로 변경한다. 재고로그에 해당 { 제품번호, 발주번호, 수량, 입출고일자 }를 추가한다.
    // 메소드명 : supplyApp()
    // 매개변수 : int supNo, int franNo
    // 반환타입 : int -> 0 : 출고 성공, 1 : 가맹점명 일치 X, 2 : 재고 부족
    public int supplyApp( int supNo, int franNo ){
        // 1. (필요 시) 유효성 검사
        // 2. dao에게 전달 후 결과 받기
        int result = supplyLogDao.supplyApp( supNo, franNo );
        // 3. view에게 결과 전달하기
        return result;
    } // func end

    // supply04. 발주요청 취소
    // 기능설명 : [발주번호와 가맹점명]을 입력받아, view에서 [가맹점명 -> 가맹점번호] 변환 후, 해당하는 발주요청의 발주상태를 2로 변경한다.
    // 메소드명 : supplyCancel()
    // 매개변수 : int supNo, int franNo
    // 반환타입 : boolean -> true(성공) / false(실패)
    public boolean supplyCancel( int supNo, int franNo ){
        // 1. (필요 시) 유효성 검사
        // 2. dao에게 전달 후 결과 받기
        boolean result = supplyLogDao.supplyCancel( supNo, franNo );
        // 3. view에게 결과 전달하기
        return result;
    } // func end

    // supply05. 발주번호 유효성 검사
    // 기능설명 : [발주번호]를 받아 발주리스트에 해당 발주이력이 있는지를 확인한다.
    // 메소드명 : supNoCheck()
    // 매개변수 : int supNo
    // 반환타입 : boolean -> true : 발주 존재 / false : 발주 없음
    public boolean supNoCheck( int supNo ){
        // 1. dao에게 전달 후 결과받기
        boolean result = supplyLogDao.supNoCheck( supNo );
        // 2. 결과 전달하기
        return result;
    } // func end

    // supply06. 총재고 반환(발주번호 > 총재고)
    // 기능설명 : [발주번호]를 받아, 해당하는 제품의 총 재고량을 반환한다.
    // 메소드명 : toTotalQtyChange()
    // 매개변수 : int supNo
    // 반환타입 : int
    public int toTotalQtyChange( int supNo ){
        // 1. dao에게 전달 후 결과받기
        int totalQty = supplyLogDao.toTotalQtyChange( supNo );
        // 2. view에게 결과 전달하기
        return totalQty;
    } // func end
} // class end
