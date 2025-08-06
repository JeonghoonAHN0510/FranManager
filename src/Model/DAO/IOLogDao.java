package Model.DAO;

import Model.DTO.IOLogDto;
import com.sun.source.tree.Tree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class IOLogDao {
    // 싱글톤
    private IOLogDao() {
        connect();
    }

    private static final IOLogDao instance = new IOLogDao();

    public static IOLogDao getInstance() {
        return instance;
    }

    // (*) DB 연동
    private String db_url = "jdbc:mysql://localhost:3306/FranManager";
    private String db_user = "root";
    private String db_password = "1234";

    private Connection conn;

    // DB 연동 메소드
    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_user, db_password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // 메소드 =====================================================================================

    // [IOLog01] 재고로그등록·재고등록 /  ioLogAdd()
    // 매개변수 : String proName, int ioQty
    // 반환타입 : boolean
    // 반환 : true 성공 / false 실패
    public boolean ioLogAdd(IOLogDto ioLogDto) {
//        System.out.println(ioLogDto.toString());
        boolean result = false;
        try {
            // [1.1] SQL 작성
            String sql = "insert into ioLog(proNo, IO, ioQty, ioMemo) values (?,?,?,?)";
//            System.out.println(ioLogDto.getProNo() + "  " + 0 + "  " + ioLogDto.getIoQty() + "  " + ioLogDto.getIoMemo());
            // [1.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // [1.3] SQL 매개변수 대입
            ps.setInt(1, ioLogDto.getProNo());
            ps.setInt(2, 0); // 본 func으로 등록된 레코드는 모드 입고이므로, 0
            ps.setInt(3, ioLogDto.getIoQty());
            ps.setString(4, ioLogDto.getIoMemo());
            // [1.4] SQL 실행
            int count = ps.executeUpdate();
            // [1.5] SQL 실행 결과 확인
            if (count == 1) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println("[경고] 재고등록을 실패하였습니다.");
        }
        return result;
    } // func end

    // [IOLog02] 재고로그조회 / IOLogPrint()
    // 매개변수 : -
    // 반환타입 : ArrayList<IOLogDto>
    // 반환 : ArrayList<IOLogDto> 출력
    public ArrayList<IOLogDto> IOLogPrint() {
        // [2.1] ArrayList 선언
        ArrayList<IOLogDto> ioLogDtoList = new ArrayList<>();
        try {
            // [2.2] SQL 작업
            // [2.2.1] SQL 작성
            String sql = "select * from ioLog order by ioNO desc";
            // [2.2.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // [2.2.3] SQL 매개변수 대입 / 생략
            // [2.2.4] SQL 실행
            ResultSet rs = ps.executeQuery();
            // [2.2.5] SQL 실행 결과 확인
            // [2.2.5.1] 반복문
            while (rs.next()) {
                // [2.2.5.2] 저장한 Dto 선언
                IOLogDto ioLogDto = new IOLogDto(
                        // [2.2.5.3] rs를 dto에 삽입
                        rs.getInt("ioNo"),
                        rs.getInt("proNo"),
                        rs.getInt("IO"),
                        rs.getInt("ioQty"),
                        rs.getString("ioDate"),
                        rs.getString("ioMemo")
                );
//                System.out.println(ioLogDto.toString());

                // [2.2.5.4] list에 dto 삽입
                ioLogDtoList.add(ioLogDto);
            }
        } catch (Exception e) {
            System.out.println("[경고] 재고로그조회를 실패하였습니다.");
        }
        // [2.3] 결과 반환
        return ioLogDtoList;
    } // func end

    // [IOLog03] 단일재고로그조회 / oneIOLogPrint()
    // 매개변수 : int ioNo
    // 반환타입 : IOLogDto
    // 반환 : IOLogDto 출력
    public IOLogDto oneIOLogPrint(int ioNo) {
        // [3.5.1] dto 객체 생성
        IOLogDto ioLogDto = new IOLogDto();

        try {
            // [3.1] SQL 작성
            String sql = "select * from ioLog where ioNo = ?";
            // [3.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // [3.3] SQL 매개변수 대입
            ps.setInt(1, ioNo);
            // [3.4] SQL 실행
            ResultSet rs = ps.executeQuery();
            // [3.5] SQL 실행 결과 확인
            rs.next();
            // [3.5.2] dto 객체에 멤버변수 대입
            ioLogDto.setIoNo(rs.getInt("ioNo"));
            ioLogDto.setProNo(rs.getInt("proNo"));
            ioLogDto.setIO(rs.getInt("IO"));
            ioLogDto.setIoQty(rs.getInt("ioQty"));
            ioLogDto.setIoDate(rs.getString("ioDate"));
            ioLogDto.setIoMemo(rs.getString("ioMemo"));
        } catch (Exception e) {
            System.out.println("[경고] 올바르지 못한 재고번호입니다. 재고번호를 다시한번 확인하여주세요.");
        }
        // [3.6] dto 반환
        return ioLogDto;
    } //func end

    // [IOLog04] 재고조회 / IOPrint()
    // 매개변수 : -
    // 반환타입 : Map<int, int>
    // 반환 : Map<int, int> 출력, 단, 상품번호별 합산 후 출력
    public Map<Integer, Integer> IOPrint() {
        Map<Integer, Integer> map = new TreeMap<>();
        try {
            // [4.1] SQL 작성
            String sql = "SELECT proNo, SUM(CASE WHEN io = 0 THEN ioQty ELSE -ioQty END) AS totalQty FROM ioLog GROUP BY proNo order by proNo asc";
            // io 구분이 0 (입고) 이면 qty를 sum하고, io가 0이 아니면 qty를 - sum 함

            // [4.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);

            // [4.3] SQL 매개변수 대입 / 생략
            // [4.4] SQL 실행
            ResultSet rs = ps.executeQuery();

            // [4.5] SQL 실행 결과 확인
            while (rs.next()) {
                map.put(rs.getInt("proNo"), rs.getInt("totalQty"));
//                System.out.println(map.toString());
            }

        } catch (Exception e) {
            System.out.println("[경고] 재고조회에 실패하였습니다.");
        }
        return map;
    } // func end


    // [IOLog05] 재고로그수정 / ioUpdate()
    // 매개변수 : IOLogDto
    // 반환타입 : boolean
    // 반환 : true 성공 / false 실패
    public boolean ioUpdate(IOLogDto ioLogDto) {
        boolean result = false;
        try {
            // [5.1] SQL 작성
            String sql = "update ioLog set proNO = ?, IO = ?, ioQty = ?, ioMemo = ? where ioNo=?";
            // [5.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // [5.3] SQL 매개변수 대입
            ps.setInt(1, ioLogDto.getProNo());
            ps.setInt(2, ioLogDto.getIO());
            ps.setInt(3, ioLogDto.getIoQty());
            ps.setString(4, ioLogDto.getIoMemo());
            ps.setInt(5, ioLogDto.getIoNo());
            // [5.4] SQL 실행
            int count = ps.executeUpdate();
            // [5.5] SQL 실행 결과 확인
            if (count == 1) {
                result = true;
            } else {
                result = false;
            }
        } catch (Exception e) {
        }
        // [5.6] 결과 반환
        return result;
    }

    // [IOLog06] 입출고번호 유효성 검사 / ioNoCheck()
    // 매개변수 : int ioNo
    // 반환타입 : boolean
    // 반환 : boolean

    public boolean ioNoCheck(int ioNo) {
        boolean result = false;
        try {
            // [6.1] SQL 작성
            String sql = "select ioNo from iolog";
            // [6.2] SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // [6.3] SQL 매개변수 대입 / 생량
            // [6.4] SQL 실행
            ResultSet rs = ps.executeQuery();
            // [6.5] SQL 실행 결과 확인
            while (rs.next()){
                if(rs.getInt("ioNo") == ioNo){
                    result = true;
                    return result;
                }
            }
        } catch (Exception e) {
            System.out.println("[경고] 확인되지 않는 재고번호입니다. 재고번호를 다시한번 확인해주세요.");
        }
        return result;
    } // func end
} // class end
