package View;

import Controller.FranController;
import Controller.SupplyLogController;
import Model.DTO.FranDto;
import Model.DTO.SupplyLogDto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    // Scanner·Controller 등 외부 참조
    private Scanner scan = new Scanner(System.in);
    private SupplyLogController supplyLogController = SupplyLogController.getInstance();
    private FranController franController = FranController.getInstance();

    // 싱글톤
    private View() { }
    private static final View instance = new View();
    public static View getInstance() {
        return instance;
    }

    // [ 0 ] main view
    public void mainView() {
        //로고 조회-최초 1회
        System.out.println("\n" +
                "      ::::::::::  :::::::::       :::      ::::    :::         :::   :::        :::      ::::    :::     :::       ::::::::    ::::::::::  ::::::::: \n" +
                "     :+:         :+:    :+:    :+: :+:    :+:+:   :+:        :+:+: :+:+:     :+: :+:    :+:+:   :+:    :+: :+:    :+:    :+:  :+:         :+:    :+: \n" +
                "    +:+         +:+    +:+   +:+   +:+   :+:+:+  +:+       +:+ +:+:+ +:+   +:+   +:+   :+:+:+  +:+   +:+   +:+   +:+         +:+         +:+    +:+  \n" +
                "   :#::+::#    +#++:++#:   +#++:++#++:  +#+ +:+ +#+       +#+  +:+  +#+  +#++:++#++:  +#+ +:+ +#+  +#++:++#++:  :#:         +#++:++#    +#++:++#:    \n" +
                "  +#+         +#+    +#+  +#+     +#+  +#+  +#+#+#       +#+       +#+  +#+     +#+  +#+  +#+#+#  +#+     +#+  +#+  #+#+#  +#+         +#+    +#+    \n" +
                " #+#         #+#    #+#  #+#     #+#  #+#   #+#+#       #+#       #+#  #+#     #+#  #+#   #+#+#  #+#     #+#  #+#    #+#  #+#         #+#    #+#     \n" +
                "###         ###    ###  ###     ###  ###    ####       ###       ###  ###     ###  ###    ####  ###     ###   ########   ##########  ###    ###      \n");
        // 메뉴 무한반복
        for (; ; ) {
            System.out.println(
                    "╔══════════════════════════════════════════════════════════════════════════════════╗\n" +
                            "║                                \uD83C\uDF1F FranManager \uD83C\uDF1F                                ║\n" +
                            "╠══════════════════════════════════════════════════════════════════════════════════╣\n" +
                            "║             1. \uD83C\uDFEA 가맹점 관리     ▌  2. \uD83D\uDCE6 재고 관리  ▌ 3. \uD83D\uDCCB 발주 관리             ║\n" +
                            "║             4. \uD83D\uDCB0 판매 현황 보기  ▌  5. \uD83D\uDCCA 통계 보기  ▌ 6. \uD83D\uDCDD 리뷰 보기             ║\n" +
                            "╚══════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("\uD83D\uDC49 메뉴 선택 : ");
            int choice = scan.nextInt();
            try {
                if (choice == 1) {
                    franManage();
                } else if (choice == 2) {
                    invenManage();
                } else if (choice == 3) {
                    ioManage();
                } else if (choice == 4) {
                    saleView();
                } else if (choice == 5) {
                    statusView();
                } else if (choice == 6) {
                    reviewView();
                } else {
                    System.out.println("[경고] 올바르지 못한 메뉴입니다.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[경고] 입력 타입이 올바르지 못합니다.");
            } catch (Exception e) {
                System.out.println("[경고] 관리자에게 문의하세요.");
            }
        } // 무한루프 end
    } // [0] main view end

    // [1] 가맹점관리
    public void franManage() {
        for (; ; ) {
            System.out.println(
                    "╔═══════════════════════════════════╣ 가맹점 관리 ╠══════════════════════════════════╗\n" +
                            "║           1. 가맹점 리스트 보기  ▌  2. 가맹점 추가                                   ║\n" +
                            "║           3. 가맹점 정보 수정    ▌  4. 가맹점 삭제  ▌  5. 메인으로 돌아가기            ║\n" +
                            "╚═══════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("\uD83D\uDC49 메뉴 선택 : ");
            int choice = scan.nextInt();
            try {
                if (choice == 1) {
                    System.out.println("═════════════════════════════════════════════════════════════════════════");
                    System.out.println("가맹점번호 \t 가맹점명 \t 전화번호 \t 가맹주명 \t 매출액");
                    ArrayList<FranDto> result = franController.franPrint();
                    System.out.println("─────────────────────────────────────────────────────────────────────────");
                    for (FranDto dto : result ){
                        System.out.printf(dto.getFranNo()+"\t"+dto.getFranName()+"\t"+dto.getFranCall()+"\t"+dto.getFranOwner()+"\t"+dto.getP()+"\n");}
                    System.out.println("─────────────────────────────────────────────────────────────────────────");

                } else if (choice == 2) {
                    scan.nextLine();
                    System.out.print("가맹점명 : ");
                    String franName = scan.nextLine();
                    System.out.print("상세주소 : ");
                    String franAddress = scan.nextLine();
                    System.out.print("전화번호 : ");
                    String franCall = scan.nextLine();
                    System.out.print("가맹점주 : ");
                    String franOwner = scan.nextLine();

                    boolean result = franController.franAdd( franName , franAddress , franCall ,franOwner );
                    if(result){System.out.println("[안내] 가맹점이 정상적으로 추가되었습니다.");}
                    else{System.out.println("[경고] 가맹점 추가가 실패했습니다.");}

                } else if (choice == 3) {
                    System.out.print("가맹점 번호 : ");
                    int franNo = scan.nextInt();

                    System.out.println("──┤ 선택 가맹점 정보 ├─────────────────────────────────────────────────────");
                    System.out.println("가맹점 번호 \t 가맹점명 \t 전화번호 \t 가맹주명 \t 상세주소");
                    System.out.println("─────────────────────────────────────────────────────────────────────────");
                    // TODO 단일 가맹점정보 조회 func 연결

                    System.out.println("──┤  수정 정보 입력  ├─────────────────────────────────────────────────────");
                    scan.nextLine();
                    System.out.print("가맹점명 : ");
                    String franName = scan.nextLine();
                    System.out.print("상세주소 : ");
                    String franAddress = scan.nextLine();
                    System.out.print("전화번호 : ");
                    String franCall = scan.nextLine();
                    System.out.print("가맹점주 : ");
                    String franOwner = scan.nextLine();

                    // TODO 1.3. 가맹점 정보 수정 func 연결

                } else if (choice == 4) {
                    System.out.print("가맹점 번호 : ");
                    int franNo = scan.nextInt();

                    System.out.println("──┤ 선택 가맹점 정보 ├─────────────────────────────────────────────────────");
                    System.out.println("가맹점 번호 \t 가맹점명 \t 전화번호 \t 가맹주명 \t 상세주소");
                    System.out.println("─────────────────────────────────────────────────────────────────────────");
                    // TODO 단일 가맹점정보 조회 func 연결
                    System.out.println("─────────────────────────────────────────────────────────────────────────");

                    // TODO 1.4. 가맹점 삭제 func 연결

                } else if (choice == 5) {
                    break; 
                } else {
                    System.out.println("[경고] 올바르지 못한 메뉴입니다.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[경고] 입력 타입이 올바르지 못합니다.");
            } catch (Exception e) {
                System.out.println("[경고] 관리자에게 문의하세요.");
            }
        } // 무한루프 end
    } // franManage end

    // [2] 재고관리
    public void invenManage() {
        for (; ; ) {
            System.out.println(
                    "╔════════════════════════════════════╣ 재고 관리 ╠═══════════════════════════════════╗\n" +
                    "║               1. 재고 현황 보기  ▌  2. 재고 등록                                     ║\n" +
                    "║               3. 재고 로그      ▌  4. 재고 수정  ▌  5. 메인으로 돌아가기              ║\n" +
                    "╚═══════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("\uD83D\uDC49 메뉴 선택 : ");
            int choice = scan.nextInt();
            try {
                if (choice == 1) {
                    System.out.println("═══════════════════════════════════════════════════════════════════════");
                    System.out.println("제품번호 \t 제품명 \t 재고수량 \t 비고");
                    System.out.println("───────────────────────────────────────────────────────────────────────");
                    // TODO 2.1. 재고 현황 보기 func 연결
                    System.out.println("───────────────────────────────────────────────────────────────────────");
                    
                } else if (choice == 2) {
                    scan.nextLine();
                    System.out.print("제품명 : ");
                    String franName = scan.nextLine();
                    System.out.print("입고 수량 : ");
                    int ioQty = scan.nextInt();
                    
                    // TODO 2.2. 재고 등록 func 연결

                } else if (choice == 3) {
                    System.out.println("═════════════════════════════════════════════════════════════════════════");
                    System.out.println("재고번호 \t 제품번호 \t 제품명 \t 입고·출고 \t 수량 \t 입고일자 \t 메모");
                    System.out.println("─────────────────────────────────────────────────────────────────────────");
                    // TODO 2.3. 재고 로그 func 연결
                    System.out.println("─────────────────────────────────────────────────────────────────────────");

                } else if (choice == 4) {
                    System.out.print("재고번호 : ");
                    int ioNo = scan.nextInt();

                    System.out.println("──┤ 선택 가맹점 정보 ├───────────────────────────────────────────────────────");
                    System.out.println("재고번호 \t 제품번호 \t 제품명 \t 입고·출고 \t 수량 \t 입고일자 \t 메모");
                    System.out.println("───────────────────────────────────────────────────────────────────────────");
                    // TODO 단일 재고 이력 조회 func 연결


                    System.out.println("──┤  수정 정보 입력  ├───────────────────────────────────────────────────────");
                    System.out.print("제품번호 : ");
                    int proNo = scan.nextInt();
                    System.out.print("입·출고 : ");
                    String IO = scan.next();
                    System.out.print("수량 : ");
                    int ioQty = scan.nextInt();
                    scan.nextLine();
                    System.out.print("메모 : ");
                    String ioMemo = scan.nextLine();

                    // TODO 2.4. 재고 수정 func 연결

                } else if (choice == 5) {
                    break;
                } else {
                    System.out.println("[경고] 올바르지 못한 메뉴입니다.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[경고] 입력 타입이 올바르지 못합니다.");
            } catch (Exception e) {
                System.out.println("[경고] 관리자에게 문의하세요.");
            }
        } // 무한루프 종료
    } // invenManage end

    // [3] 발주관리
    public void ioManage() {
        for ( ; ; ) {
            System.out.println(
                    "╔════════════════════════════════════╣ 발주 관리 ╠═══════════════════════════════════╗\n" +
                    "║                   1. 가맹점 발주 요청 보기  ▌  2. 출고 처리                          ║\n" +
                    "║                   3. 발주 요청 취소 처리    ▌  4. 메인으로 돌아가기                   ║\n" +
                    "╚═══════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("\uD83D\uDC49 메뉴 선택 : ");
            int choice = scan.nextInt();
            try {
                if (choice == 1) {

                    System.out.println("═════════════════════════════════════════════════════════════════════════");
                    System.out.println("발주번호 \t 가맹점명 \t 제품 \t 주문수량 \t 메모");
                    System.out.println("─────────────────────────────────────────────────────────────────────────");
                    // TODO 3.1. 가맹점 발주 요청 보기 func 연결
                    // controller에게 결과값 받기
                    ArrayList<SupplyLogDto> supplyLogDtos = supplyLogController.supplyPrintAll();
                    for ( SupplyLogDto supplyLogDto : supplyLogDtos ){      // 리스트를 하나씩 순회하면서
                        // 원하는 값 꺼내오기
                        int supNo = supplyLogDto.getSupNo();
                        int franNo = supplyLogDto.getFranNo();
                        int proNo = supplyLogDto.getProNo();
                        int supQty = supplyLogDto.getSupQty();
                        String supMemo = supplyLogDto.getSupMemo();
                        // todo 가맹점번호, 제품번호를 가맹점명, 제품명으로 변환



                    } // for end

                } else if (choice == 2) {
                    System.out.print("발주번호 : ");
                    int supNo = scan.nextInt();

                    System.out.println("──┤ 발주번호 정보 조회 ├─────────────────────────────────────────────────────");
                    System.out.println("발주번호 \t 가맹점명 \t 제품 \t 주문수량 \t 메모");
                    System.out.println("───────────────────────────────────────────────────────────────────────────");
                    // TODO 단일 발주정보 출력 func 연결

                    // TODO 3.2. 출고 처리 func 연결
                } else if (choice == 3) {
                    System.out.print("발주번호 : ");
                    int supNo = scan.nextInt();

                    System.out.println("──┤ 발주 요청 취소 ├────────────────────────────────────────────────────────");
                    System.out.println("발주번호 \t 가맹점명 \t 제품 \t 주문수량 \t 메모");
                    System.out.println("───────────────────────────────────────────────────────────────────────────");
                    // TODO 단일 발주정보 출력 func 연결
                    System.out.println("───────────────────────────────────────────────────────────────────────────");

                    // TODO 3.3. 발주 요청 취소 처리 func 연결

                } else if (choice == 4) {
                    break;
                } else {
                    System.out.println("[경고] 올바르지 못한 메뉴입니다.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[경고] 입력 타입이 올바르지 못합니다.");
            } catch (Exception e) {
                System.out.println("[경고] 관리자에게 문의하세요.");
            }
        } // 무한루프 종료
    } // ioManage end

    // [4] 판매현황보기
    public void saleView() {
        System.out.println("═════════════════════════════════════════════════════════════════════════");
        System.out.println("판매번호 \t 가맹점명 \t 제품명 \t 판매수량 \t 날짜·시간");
        System.out.println("─────────────────────────────────────────────────────────────────────────");

        // TODO 4. 판매현황 보기 func 연결
    } // saleView end

    // [5] 통계보기
    public void statusView() {
        for (; ; ) {
            System.out.println(
                    "╔════════════════════════════════════╣ 통계 보기 ╠═══════════════════════════════════╗\n" +
                    "║                       1. 제품별 통계      ▌  2. 지역별 통계                          ║\n" +
                    "║                       3. 시간대별 통계    ▌  4. 메인으로 돌아가기                     ║\n" +
                    "╚═══════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("\uD83D\uDC49 메뉴 선택 : ");
            int choice = scan.nextInt();
            try {
                if (choice == 1) {
                    System.out.println("※ 통계는 판매 금액 기준 상위 10건만 조회가능합니다. " +
                            "   통계 집계기간은 최근 30일입니다.");
                    System.out.println("═════════════════════════════════════════════════════════════════════════");
                    System.out.println("통계번호 \t 제품명 \t 판매금액 ");
                    System.out.println("─────────────────────────────────────────────────────────────────────────");
                    // TODO 5.1. 제품별 통계 func 연결
                    System.out.println("─────────────────────────────────────────────────────────────────────────");
                } else if (choice == 2) {

                    System.out.println("※ 통계는 판매 금액 기준 상위 10건만 조회가능합니다. " +
                            "   통계 집계기간은 최근 30일입니다.");
                    System.out.println("═════════════════════════════════════════════════════════════════════════");
                    System.out.println("통계번호 \t 지역 \t 매출액 ");
                    System.out.println("─────────────────────────────────────────────────────────────────────────");
                    // TODO 5.2. 지역별 통계 func 연결
                    System.out.println("─────────────────────────────────────────────────────────────────────────");

                } else if (choice == 3) {

                    System.out.println("※ 통계 집계기간은 최근 30일입니다.");
                    System.out.println("═════════════════════════════════════════════════════════════════════════");
                    System.out.println("시간대 \t 매출액 ");
                    System.out.println("─────────────────────────────────────────────────────────────────────────");
                    // TODO 5.3. 시간대별 통계 func 연결
                    System.out.println("─────────────────────────────────────────────────────────────────────────");
                    
                } else if (choice == 4) {
                    break;
                } else {
                    System.out.println("[경고] 올바르지 못한 메뉴입니다.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[경고] 입력 타입이 올바르지 못합니다.");
            } catch (Exception e) {
                System.out.println("[경고] 관리자에게 문의하세요.");
            }
        } // 무한루프 종료
    } // statusView end

    // [6] 리뷰보기
    public void reviewView() {
        for (; ; ) {
            System.out.println(
                    "╔════════════════════════════════════╣ 발주 관리 ╠═══════════════════════════════════╗\n" +
                    "║                       1. 리뷰 전체 조회    ▌  2. 가맹점별 리뷰 조회                   ║\n" +
                    "║                       3. 제품별 리뷰 조회  ▌  4. 메인으로 돌아가기                    ║\n" +
                    "╚═══════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("\uD83D\uDC49 메뉴 선택 : ");
            int choice = scan.nextInt();
            try {
                if (choice == 1) {
                    System.out.println("═════════════════════════════════════════════════════════════════════════");
                    System.out.println("리뷰번호 \t 판매번호 \t 제품명 \t 가맹점명 \t 리뷰");
                    System.out.println("─────────────────────────────────────────────────────────────────────────");
                    // TODO 6.1. 리뷰 전체 조회 func 연결
                    System.out.println("─────────────────────────────────────────────────────────────────────────");

                } else if (choice == 2) {
                    System.out.print("가맹점명 : ");
                    String franName = scan.next();

                    System.out.println("──┤ 선택 가맹점 리뷰 ├───────────────────────────────────────────────────────");
                    System.out.println("리뷰번호 \t 판매번호 \t 제품명 \t 가맹점명 \t 리뷰");
                    System.out.println("───────────────────────────────────────────────────────────────────────────");
                    // TODO 6.2. 가맹점별 리뷰 조회 func 연결
                    System.out.println("───────────────────────────────────────────────────────────────────────────");

                } else if (choice == 3) {
                    System.out.print("제품명 : ");
                    String proName = scan.next();

                    System.out.println("──┤ 선택 제품 리뷰 ├─────────────────────────────────────────────────────────");
                    System.out.println("리뷰번호 \t 판매번호 \t 제품명 \t 가맹점명 \t 리뷰");
                    System.out.println("───────────────────────────────────────────────────────────────────────────");
                    // TODO 6.3. 제품별 리뷰 조회 func 연결
                    System.out.println("───────────────────────────────────────────────────────────────────────────");

                } else if (choice == 4) {
                    break;
                } else {
                    System.out.println("[경고] 올바르지 못한 메뉴입니다.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[경고] 입력 타입이 올바르지 못합니다.");
            } catch (Exception e) {
                System.out.println("[경고] 관리자에게 문의하세요.");
            }
        } // 무한루프 종료
    } // reviewView end

}  // class end
