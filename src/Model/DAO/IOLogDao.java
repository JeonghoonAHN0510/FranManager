package Model.DAO;

import Model.DTO.IOLogDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class IOLogDao {
    // 싱글톤
    private IOLogDao(){connect();}
    private static final IOLogDao instance = new IOLogDao();
    public static IOLogDao getInstance(){
        return instance;
    }
    
    // (*) DB 연동
    private String db_url = "jdbc:mysql://localList:3306/FranManager";
    private String db_user ="root";
    private String db_password = "1234";
    private Connection conn;
    
    // DB 연동 메소드
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection( db_url , db_user , db_password );
        }catch (Exception e ){ System.out.println(e);   }
    }

    // ArrayList 선언
    ArrayList<IOLogDto> ioLogDtoList = new ArrayList<>();


    // 메소드 =====================================================================================

    // [IOLog00]
    // 매개변수 :
    // 반환타입 :
    // 반환 :

    // [IOLog01] 재고로그등록·재고등록 /  ioLogAdd()
    // 매개변수 : String proName, int ioQty
    // 반환타입 : boolean
    // 반환 : true 성공 / false 실패
    // [1.1]
    // [1.2]

    // [IOLog02] 재고로그조회 / IOLogPrint()
    // 매개변수 : -
    // 반환타입 : ArrayList<IOLogDto>
    // 반환 : ArrayList<IOLogDto> 출력
    // [2.1]

    // [IOLog03] 단일재고로그조회 / oneIOLogPrint()
    // 매개변수 : int ioNo
    // 반환타입 : IOLogDto
    // 반환 : IOLogDto 출력
    // [3.1]

    // [IOLog04] 재고조회 / IOPrint()
    // 매개변수 : -
    // 반환타입 : ArrayList<IOLogDto>
    // 반환 : ArrayList<IOLogDto> 출력, 단, 상품번호별 합산 후 출력
    // [4.1]

    // [IOLog05] 재고로그수정 / ioUpdate()
    // 매개변수 : int ioNo, int proNo, String IO, int ioQty, String ioMemo
    // 반환타입 : boolean
    // 반환 : true 성공 / false 실패
    // [5.1]


} // class end
