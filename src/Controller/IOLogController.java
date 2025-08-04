package Controller;

import Model.DAO.IOLogDao;
import Model.DTO.IOLogDto;

import java.util.ArrayList;
import java.util.Map;

public class IOLogController {
    // 싱글톤
    private IOLogController() {
    }

    private static final IOLogController instance = new IOLogController();

    public static IOLogController getInstance() {
        return instance;
    }

    // dao 싱글톤 호출
    private IOLogDao ioLogDao = IOLogDao.getInstance();

    // 메소드 =====================================================================================

    // [IOLog00]
    // 매개변수 :
    // 반환타입 :
    // 반환 :

    // [IOLog01] 재고로그등록·재고등록 /  ioLogAdd()
    // 매개변수 : int proNo, int ioQty
    // 반환타입 : boolean
    // 반환 : true 성공 / false 실패
    public boolean ioLogAdd(int proNo, int ioQty, String ioMemo) {
        // [1.1] 매개변수를 활용하여 IOLogDto 생성
        IOLogDto iologDto = new IOLogDto(0, proNo, 0, ioQty, "", ioMemo);
        // [1.2] IOlogDto를 dao로 전달
        boolean result = IOLogDao.getInstance().ioLogAdd(iologDto);
        // [1.3] 결과 반환
        return result;
    } // func end

    // [IOLog02] 재고로그조회 / IOLogPrint()
    // 매개변수 : -
    // 반환타입 : ArrayList<IOLogDto>
    // 반환 : ArrayList<IOLogDto> 출력
    public ArrayList<IOLogDto> IOLogPrint() {
        // [2.1] dao 실행
        ArrayList<IOLogDto> ioLogDtoList = ioLogDao.IOLogPrint();

        // [2.2] 결과 반환
        return ioLogDtoList;
    } //func end

    // [IOLog03] 단일재고로그조회 / oneIOLogPrint()
    // 매개변수 : int ioNo
    // 반환타입 : IOLogDto
    // 반환 : IOLogDto 출력
    public IOLogDto oneIOLogPrint(int ioNo) {
        // [3.1] dao의 oneIOLogPrint 실행
        IOLogDto ioLogDto = ioLogDao.oneIOLogPrint(ioNo);
        // [3.2] 결과 반환
        return ioLogDto;
    } //func end

    // [IOLog04] 재고조회 / IOPrint()
    // 매개변수 : -
    // 반환타입 : Map<Integer, Integer>
    // 반환 : Map<Integer, Integer> 출력, 단, 상품번호별 합산 후 출력
    public Map<Integer, Integer> IOPrint() {
        // [4.1] doo의 메소드 실행
        Map<Integer, Integer> map = ioLogDao.IOPrint();
        // [4.2] 결과 반환
        return map;
    }

    // [IOLog05] 재고로그수정 / ioUpdate()
    // 매개변수 : int ioNo, int proNo, String IO, int ioQty, String ioMemo
    // 반환타입 : boolean
    // 반환 : true 성공 / false 실패
    public boolean ioUpdate(int ioNo, int proNo, int IO, int ioQty, String ioMemo) {
        boolean result = false;

        // [5.1] dto 객체 생성
        IOLogDto ioLogDto = new IOLogDto(ioNo, proNo, IO, ioQty, "", ioMemo);
        // [5.2] dao에 전달
        result = ioLogDao.ioUpdate(ioLogDto);
        // [5.3] 결과 반환
        return result;

    } //func end

    // [IOLog06] 입출고번호 유효성 검사 / ioNoCheck()
    // 매개변수 : int invenNo
    // 반환타입 : boolean
    // 반환 : boolean

    public boolean ioNoCheck(int invenNo) {
        // [6.1] dao에 전달
        boolean result = ioLogDao.ioNoCheck(invenNo);
        // [6.2] 결과 반환
        return result;
    } // func end

} // class end
