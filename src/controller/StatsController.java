package controller;

import model.dao.StatsDao;
import model.dto.StatsDto;
import util.CustomList;

public class StatsController {
    // 싱글톤
    private StatsController(){}
    private static final StatsController instance = new StatsController();
    public static StatsController getInstance(){
        return instance;
    }

    // StatsDao 싱글톤 가져오기
    private StatsDao statsDao = StatsDao.getInstance();

    // stats01. 상품별 통계 조회
    // 기능설명 : DB에 저장된 상품별 최근30일 판매금액의 상위 10개를 판매금액 기준 내림차순으로 출력한다.
    // 메소드명 : proStatsPrint()
    // 매개변수 : X
    // 반환타입 : CustomList<StatsDto>
    public CustomList<StatsDto> proStatsPrint(){
        // 1. (필요 시) 유효성 검사
        // 2. dao에게 전달 후 결과받기
        CustomList<StatsDto> statsDtos = statsDao.proStatsPrint();
        // 3. view에게 결과 전달하기
        return statsDtos;
    } // func end

    // stats02. 지역별 통계 조회
    // 기능설명 : DB에 저장된 지역별(서울/인천:구, 경기:시) 최근 30일 매출액의 상위 10개를 매출액 기준 내림차순으로 출력한다."
    // 메소드명 : regionStatsPrint()
    // 매개변수 : X
    // 반환타입 : CustomList<StatsDto>
    public CustomList<StatsDto> regionStatsPrint(){
        // 1. (필요 시) 유효성 검사
        // 2. dao에게 전달 후 결과 받기
        CustomList<StatsDto> statsDtos = statsDao.regionStatsPrint();
        // 3. view에게 결과 전달하기
        return statsDtos;
    } // func end

    // stats03. 시간대별 통계 조회
    // 기능설명 : DB에 저장된 매출액을 시간대별로 출력한다.
    // 메소드명 : hourStatsPrint()
    // 매개변수 : X
    // 반환타입 : CustomList<StatsDto>
    public CustomList<StatsDto> hourStatsPrint(){
        // 1. (필요 시) 유효성 검사
        // 2. dao에게 전달 후 결과 받기
        CustomList<StatsDto> statsDtos = statsDao.hourStatsPrint();
        // 3. view에게 결과 전달하기
        return statsDtos;
    } // func end
} // class end
