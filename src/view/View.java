package view;

import controller.FranController;
import controller.*;
import model.dto.*;
import controller.IOLogController;
import controller.ProductController;
import controller.SupplyLogController;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class View {
    // Scanner·Controller 등 외부 참조
    private Scanner scan = new Scanner(System.in);
    private FranController franController = FranController.getInstance();
    private IOLogController ioLogController = IOLogController.getInstance();
    private OrderLogController orderLogController = OrderLogController.getInstance();
    private ProductController productController = ProductController.getInstance();
    private ReviewController reviewController = ReviewController.getInstance();
    private StatsController statsController = StatsController.getInstance();
    private SupplyLogController supplyLogController = SupplyLogController.getInstance();
    // 천 단위 콤마를 위한 NumberFormat 클래스
    private NumberFormat nf = NumberFormat.getInstance();

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
                            "║  1. \uD83C\uDFEA 가맹점 관리     ▌  2. \uD83C\uDF71 제품 관리  ▌  3. \uD83D\uDCE6 입출고 관리 ▌  4. \uD83D\uDCCB 발주 관리     ║\n" +
                            "║  5. \uD83D\uDCB0 판매 현황 보기   ▌  6. \uD83D\uDCCA 통계 보기  ▌  7. \uD83D\uDCDD 리뷰 보기                        ║ \n" +
                            "╚══════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("\uD83D\uDC49 메뉴 선택 : ");
            int choice = scan.nextInt();
            try {
                if (choice == 1) {
                    franManage();
                } else if (choice == 2) {
                    productManage();
                } else if (choice == 3) {
                    ioManage();
                } else if (choice == 4) {
                    supplyManage();
                } else if (choice == 5) {
                    saleView();
                } else if (choice == 6) {
                    statusView();
                } else if (choice == 7) {
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
                    "║           1. 가맹점 리스트 보기  ▌  2. 가맹점 추가                                    ║\n" +
                    "║           3. 가맹점 정보 수정    ▌  4. 가맹점 삭제  ▌  5. 메인으로 돌아가기             ║\n" +
                    "╚══════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("\uD83D\uDC49 메뉴 선택 : ");
            int choice = scan.nextInt();
            try {
                if (choice == 1) {
                    System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                    System.out.println("가맹점번호 \t 가맹점명 \t\t전화번호 \t\t가맹주명  \t매출액 \t 상세주소");
                    ArrayList<FranDto> result = franController.franPrint();
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                    for (FranDto dto : result) {
                        int totalPrice = dto.getP();
                        // Price들 천 단위 콤마찍기
                        String price = nf.format(totalPrice);
                        // 값 하나씩 꺼내기
                        int franNo2 = dto.getFranNo();
                        String franName = dto.getFranName();
                        String franCall = dto.getFranCall();
                        String franOwner = dto.getFranOwner();
                        String franAddress = dto.getFranAddress();
                        franName = franController.franNameArray( franName, franName.length() );
                        franCall = franController.franCallArray( franCall, franCall.length() );
                        System.out.printf("  %d   \t%s   \t%s\t%s\t%s원\t%s\n", franNo2,franName,franCall,franOwner,price,franAddress);
                    }
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");

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

                    boolean result = franController.franAdd(franName, franAddress, franCall, franOwner);
                    if (result) {
                        System.out.println("[안내] 가맹점이 정상적으로 추가되었습니다.");
                    } else {
                        System.out.println("[경고] 가맹점 추가에 실패했습니다.");
                    }

                } else if (choice == 3) {
                    System.out.print("가맹점번호 : ");
                    // 사용자로부터 수정할 가맹점 번호 입력 받기
                    int franNo = scan.nextInt();
                    // 입력한 번호에 해당하는 객체 가져오기
                    FranDto dto = franController.oneFranPrint(franNo);
                    // 값 하나씩 꺼내기
                    int franNo2 = dto.getFranNo();
                    String franName = dto.getFranName();
                    String franCall = dto.getFranCall();
                    String franOwner = dto.getFranOwner();
                    String franAddress = dto.getFranAddress();
                    if( dto.getFranNo() != 0 ){
                    System.out.println("──┤ 선택 가맹점 정보 ├────────────────────────────────────────────────────────────────");
                    System.out.println("가맹점번호 \t 가맹점명 \t 전화번호 \t 가맹주명 \t 상세주소");
                    System.out.printf("  %d\t    %s\t  %s\t  %s\t  %s\n", franNo2,franName,franCall,franOwner,franAddress);
                    System.out.println("───────────────────────────────────────────────────────────────────────────────────");
                    // 단일 출력
                    System.out.println("──┤  수정 정보 입력  ├───────────────────────────────────────────────────────────────");
                    scan.nextLine();
                    System.out.print("가맹점명 : ");
                    String franName2 = scan.nextLine();
                    System.out.print("상세주소 : ");
                    String franAddress2 = scan.nextLine();
                    System.out.print("전화번호 : ");
                    String franCall2 = scan.nextLine();
                    System.out.print("가맹점주 : ");
                    String franOwner2 = scan.nextLine();

                    // 수정 정보를 controller에 전달
                    boolean result = franController.franUpdate(franNo2, franName2, franAddress2, franCall2, franOwner2);
                    if (result) {
                        System.out.println("[안내] 가맹점 정보가 정상적으로 수정되었습니다");
                    } else {
                        System.out.println("[경고] 가맹점 수정에 실패했습니다.");
                    }}else {System.out.println("[경고] 존재하지 않는 가맹점 번호입니다.");}

                } else if (choice == 4) {
                    // 사용자로부터 수정할 가맹점 번호 입력 받기
                    System.out.print("가맹점 번호 : ");

                    int franNo = scan.nextInt();
                    // 입력한 번호에 해당하는 객체 가져오기
                    FranDto dto = franController.oneFranPrint(franNo);
                    // 값 하나씩 꺼내기
                    int franNo2 = dto.getFranNo();
                    String franName = dto.getFranName();
                    String franCall = dto.getFranCall();
                    String franOwner = dto.getFranOwner();
                    String franAddress = dto.getFranAddress();
                    if( franNo2 != 0 ){
                    System.out.println("──┤ 선택 가맹점 정보 ├────────────────────────────────────────────────────────────────");
                    System.out.println("가맹점번호 \t 가맹점명 \t 전화번호 \t 가맹주명 \t 상세주소");
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                    // 단일 조회
                    System.out.printf("  %d\t    %s\t  %s\t  %s\t  %s\n", franNo2,franName,franCall,franOwner,franAddress);
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                    System.out.println("❗❗ 삭제를 원하시면, [ 가맹점명 ]과 [ 가맹주명 ]을 입력하세요.");
                    System.out.print("가맹점명 : "); String franName2 = scan.next();
                    System.out.print("가맹주명 : "); String franOwner2 = scan.next();
                    // 수정 정보를 controller에 전달
                    boolean result = franController.franDelete(franNo2, franName2, franOwner2);
                    if(result) {
                        System.out.println("[안내] 가맹점이 정상적으로 삭제되었습니다");
                    } else {
                        System.out.println("[경고] 가맹점 삭제에 실패했습니다.");
                    }}else {System.out.println("[경고] 존재하지 않는 가맹점 번호입니다.");}

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


    // [2] 제품관리
    public void productManage() {
        for (; ; ) {
            System.out.println(
                    "╔════════════════════════════════════╣ 제품 관리 ╠═══════════════════════════════════╗\n" +
                            "║               1. 제품 전체 조회  ▌  2. 제품 등록                                     ║\n" +
                            "║               3. 제품 수정      ▌  4. 판매상태변경   ▌  5. 메인으로 돌아가기             ║\n" +
                            "╚═══════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("\uD83D\uDC49 메뉴 선택 : ");
            int choice = scan.nextInt();
            try {
                if (choice == 1) {
                    System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                    System.out.println("제품번호 \t 제품명 \t\t\t공급가액 \t   소비자판매가 \t 판매여부");
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                    // Controller로부터 결과 받기
                    ArrayList<ProductDto> productDtos = productController.productAllPrint();
                    // 배열 하나씩 순회하면서 출력하기
                    for (ProductDto productDto : productDtos) {
                        // 값 하나씩 꺼내기
                        int proNo = productDto.getProNo();
                        String proName = productDto.getProName();
                        int proSupPrice = productDto.getProSupPrice();
                        int proPrice = productDto.getProPrice();
                        boolean proStatus = productDto.isProStatus();
                        // 판매여부 변환하기
                        String status = productController.toproStatusChange(proStatus);
                        // Price들 천 단위 콤마찍기
                        String SupPrice = nf.format(proSupPrice);
                        String price = nf.format(proPrice);
                        // 글자수 정렬 함수
                        proName = productController.proNameArray( proName, proName.length() );
                        price = productController.priceArray( price, price.length() );
                        // 하나씩 출력하기
                        System.out.printf("%d\t%s\t\t%s원 \t%s원\t %s\n", proNo, proName, SupPrice, price, status);
                    } // for end
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                } else if (choice == 2) {
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                    // 사용자로부터 등록할 제품정보 받기
                    System.out.print("제품명 : ");          String proName = scan.next();
                    System.out.print("공급가액 : ");        int proSupPrice = scan.nextInt();
                    System.out.print("소비자판매가 : ");     int proPrice = scan.nextInt();
                    // 입력한 정보 등록하고 결과받기
                    int result;
                    // 제품명 유효성 검사
                    if ( productController.proNameCheck( proName ) ){  // 이미 제품이 존재하면
                        result = 2;
                    } else {
                        result = productController.productAdd(proName, proSupPrice, proPrice);
                    } // if end
                    // 결과에 따른 출력하기
                    if ( result == 0 ) {
                        System.out.println("[안내] 제품이 정상적으로 등록되었습니다.");
                    } else if ( result == 1 ) {
                        System.out.println("[경고] 제품 등록에 실패하였습니다.");
                    } else if ( result == 2 ) {
                        System.out.println("[경고] 이미 존재하는 제품명입니다.");
                    } else {
                        System.out.println("[경고] 공급가액은 소비자판매가보다 높을 수 없습니다.");
                    } // if end
                } else if (choice == 3) {
                    // 사용자로부터 수정할 제품번호 받기
                    System.out.print("제품번호 : ");
                    int proNoInput = scan.nextInt();
                    // 입력한 제품번호에 해당하는 제품 꺼내오기
                    ProductDto productDto = productController.productPrint(proNoInput);
                    if (productDto.getProNo() != 0) {          // 제품번호가 올바르다면
                        // 값 하나씩 꺼내기
                        int proNo = productDto.getProNo();
                        String proName = productDto.getProName();
                        int proSupPrice = productDto.getProSupPrice();
                        int proPrice = productDto.getProPrice();
                        boolean proStatus = productDto.isProStatus();
                        // 판매여부 변환하기
                        String status = productController.toproStatusChange(proStatus);
                        // Price들 천 단위 콤마찍기
                        String SupPrice = nf.format(proSupPrice);
                        String price = nf.format(proPrice);
                        // 글자수 정렬 함수
                        proName = productController.proNameArray( proName, proName.length() );
                        price = productController.priceArray( price, price.length() );
                        // 제품정보 출력하기
                        System.out.println("──┤ 선택 제품 정보 ├──────────────────────────────────────────────────────────────────");
                        System.out.println("제품번호 \t 제품명 \t\t\t공급가액 \t   소비자판매가 \t 판매여부");
                        System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                        System.out.printf("%d\t%s\t\t%s원 \t%s원\t %s\n", proNo, proName, SupPrice, price, status);
                        System.out.println("──┤  수정 정보 입력  ├────────────────────────────────────────────────────────────────");
                        // 사용자로부터 수정정보 입력받기
                        System.out.print("제품명 : ");
                        String proNameInput = scan.next();
                        System.out.print("공급가액 : ");
                        int proSupPriceInput = scan.nextInt();
                        System.out.print("소비자판매가 : ");
                        int proPriceInput = scan.nextInt();
                        // 입력한 값 controller에게 전달 후 결과 받기
                        boolean result = productController.productUpdate(proNoInput, proNameInput, proSupPriceInput, proPriceInput);
                        // 결과에 따른 출력하기
                        if (result) {
                            System.out.println("[안내] 제품이 정상적으로 수정되었습니다.");
                        } else {
                            System.out.println("[경고] 제품 수정에 실패하였습니다.");
                        } // if end
                    } else {
                        System.out.println("[경고] 올바르지 못한 제품번호입니다.");
                    } // if end
                } else if (choice == 4) {
                    // 사용자로부터 상태변경할 제품번호 받기
                    System.out.print("제품번호 : ");
                    int proNoInput = scan.nextInt();
                    // 입력한 제품번호에 해당하는 제품 꺼내오기
                    ProductDto productDto = productController.productPrint(proNoInput);
                    if ( productDto.getProNo() != 0 ){
                        // 값 하나씩 꺼내기
                        int proNo = productDto.getProNo();
                        String proName = productDto.getProName();
                        int proSupPrice = productDto.getProSupPrice();
                        int proPrice = productDto.getProPrice();
                        boolean proStatus = productDto.isProStatus();
                        // 판매여부 변환하기
                        String status = productController.toproStatusChange(proStatus);
                        // Price들 천 단위 콤마찍기
                        String SupPrice = nf.format(proSupPrice);
                        String price = nf.format(proPrice);
                        // 글자수 정렬 함수
                        proName = productController.proNameArray( proName, proName.length() );
                        price = productController.priceArray( price, price.length() );
                        // 제품정보 출력하기
                        System.out.println("──┤ 선택 제품 정보 ├──────────────────────────────────────────────────────────────────");
                        System.out.println("제품번호 \t 제품명 \t\t\t공급가액 \t   소비자판매가 \t 판매여부");
                        System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                        System.out.printf("%d\t%s\t\t%s원 \t%s원\t %s\n", proNo, proName, SupPrice, price, status);
                        System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                        // 사용자로부터 삭제할 제품명 받기
                        System.out.print("❗❗ 판매상태변경을 원하시면, [ 제품명 ]을 입력하세요. ");
                        String proNameInput = scan.next();
                        boolean result = productController.productStatusChange( proNo, proNameInput );
                        if ( result ){
                            System.out.println("[안내] 해당 제품의 판매상태가 정상적으로 변경되었습니다.");
                        } else {
                            System.out.println("[경고] 해당 제품의 판매상태 변경에 실패하였습니다.");
                        } // if end
                    } else {
                        System.out.println("[경고] 존재하지 않는 제품번호입니다.");
                    } // if end
                } else if (choice == 5) {
                    break;
                } else {
                    System.out.println("[경고] 올바르지 못한 메뉴입니다.");
                } // if end
            } catch (InputMismatchException e) {
                System.out.println("[경고] 입력 타입이 올바르지 못합니다.");
            } catch (Exception e) {
                System.out.println("[경고] 관리자에게 문의하세요.");
            } // try-catch end
        } // 무한루프 end
    } // func end


    // [3] 입출고관리
    public void ioManage() {
        for (; ; ) {
            System.out.println(
                    "╔═══════════════════════════════════╣ 입출고 관리 ╠══════════════════════════════════╗\n" +
                            "║             1. 제품별 재고 현황  ▌  2. 입고 등록                                     ║\n" +
                            "║             3. 입출고 로그      ▌  4. 입출고 수정  ▌  5. 메인으로 돌아가기              ║\n" +
                            "╚══════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("\uD83D\uDC49 메뉴 선택 : ");
            int choice = scan.nextInt();
            try {
                if (choice == 1) { // 2.1. 재고 현황 보기 func 연결
                    // [3.1.1] ioLogController의 ioPrint 메소드 실행
                    Map<Integer, Integer> ioMap = ioLogController.IOPrint();

                    System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                    System.out.println("제품번호 \t  제품명 \t   재고수량 \t\t\t 비고");
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");

                    // [3.1.2] ioMap에 대한 반복문
                    for (int proNo : ioMap.keySet()) {
                        // [2.1.3] memo 출력 관련
                        String memo = "";
                        if (ioMap.get(proNo) <= 10) {
                            memo = "[재고부족] 제품 주문이 필요합니다.";
                        }
                        // 글자수 맞추기 todo 가능하면 함수화
                        String proName = productController.toProNameChange(proNo);
                        proName = productController.proNameArray( proName, proName.length() );
                        // [3.1.4] 각 열마다 출력
                        System.out.printf("%d\t%s\t\t%d \t\t%s \n", proNo, proName, ioMap.get(proNo), memo);
                    }
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                } else if (choice == 2) { // 2.2. 입출고 로그 등록 func 연결
                    // [3.2.1] console에서 정보 받기
                    scan.nextLine();
                    System.out.print("제품명 : ");
                    String proName = scan.nextLine();
                    System.out.print("입고 수량 : ");
                    int ioQty = scan.nextInt();
                    scan.nextLine();
                    System.out.print("메모 : ");
                    String ioMemo = scan.nextLine();

                    // [3.2.2] proName > proNo 변환 메소드
                    int proNo = productController.toIntproNoChange(proName);
                    // [3.2.3] proNo 유효성 검사
                    boolean check = productController.proNoCheck(proNo);
                    if (check == false) {
                    } else {
                        // [3.2.3] 재고 등록 메소드 실행
                        boolean result = ioLogController.ioLogAdd(proNo, ioQty, ioMemo);

                        // [3.2.4] 결과에 따른 출력
                        if (result == true) {
                            System.out.println("[안내] 제품 재고가 정상적으로 등록되었습니다.");
                        } else {
                            System.out.println("[경고] 제품 재고 등록을 실패하였습니다.");
                        }
                    }
                } else if (choice == 3) { // 2.3. 재고 로그 func 연결
                    System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                    System.out.println("입·출고번호 \t 제품번호 \t 제품명    입고·출고    수량 \t    입·출고일자 \t\t     메모");
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");

                    // [3.3.1] IOLogController, IOLogPrint() 실행
                    ArrayList<IOLogDto> ioLogDtoList = ioLogController.IOLogPrint();

                    // [3.3.2] 반복문
                    for (IOLogDto ioLogDto : ioLogDtoList) {
                        // [3.3.3] 입고·출고 text로 변환
                        String io = "";
                        if (ioLogDto.getIO() == 0) {
                            io = "입고";
                        } else {
                            io = "출고";
                        }
                        String proName = productController.toProNameChange(ioLogDto.getProNo());
                        // 글자수 정렬 함수
                        proName = productController.proNameArray( proName, proName.length() );
                        // [3.3.4] 출력
                        System.out.printf("  %d \t %d \t\t%s \t%s \t  %d \t%s    %s \n",
                                ioLogDto.getIoNo(), ioLogDto.getProNo(), proName, io, ioLogDto.getIoQty(), ioLogDto.getIoDate(), ioLogDto.getIoMemo());
                    }
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");

                } else if (choice == 4) { //3.4. 재고 수정 func 연결
                    System.out.print("입·출고번호 : ");
                    int ioNo = scan.nextInt();

                    // [3.4.1] 입·출고번호 유효성검사
                    boolean check = ioLogController.ioNoCheck(ioNo);
                    if (check == false) {
                        System.out.println("[경고] 존재하지 않는 입출고번호 입니다.");
                    } else {
                        // [3.4.2] 단일 재고 이력 조회 func / IoController - oneIOLogPrint()
                        IOLogDto ioLogDto = ioLogController.oneIOLogPrint(ioNo);

                        System.out.println("──┤ 선택 가맹점 정보 ├─────────────────────────────────────────────────────────────────");
                        System.out.println("입·출고번호 \t 제품번호 \t 제품명    입고·출고   수량 \t    입·출고일자 \t\t     메모");
                        System.out.println("────────────────────────────────────────────────────────────────────────────────────");

                        // [3.4.3] 반복문 출력
                        // [3.4.3.1] 입고·출고 text로 변환
                        String ioString = "";
                        if (ioLogDto.getIO() == 0) {
                            ioString = "입고";
                        } else {
                            ioString = "출고";
                        }
                        String proName = productController.toProNameChange(ioLogDto.getProNo());
                        // 글자수 정렬 함수
                        proName = productController.proNameArray( proName, proName.length() );
                        System.out.printf("  %d \t %d \t\t%s \t%s \t %d \t%s    %s \n",
                                ioLogDto.getIoNo(), ioLogDto.getProNo(), proName, ioString, ioLogDto.getIoQty(), ioLogDto.getIoDate(), ioLogDto.getIoMemo());

                        // [3.4.4] 수정 정보 받기
                        System.out.println("──┤  수정 정보 입력  ├────────────────────────────────────────────────────────────────");
                        System.out.print("제품번호 : ");
                        int proNo = scan.nextInt();
                        System.out.print("입·출고 : ");
                        String IO = scan.next();
                        int io = 0;
                        if (IO.equals("출고")) {
                            System.out.print("발주번호 : ");
                            io = scan.nextInt();
                        } else if (IO.equals("입고")) {
                            io = 0;
                        }
                        System.out.print("수량 : ");
                        int ioQty = scan.nextInt();
                        scan.nextLine();
                        System.out.print("메모 : ");
                        String ioMemo = scan.nextLine();

                        // [3.4.5] 수정 정보 IoLogControll에 전달
                        boolean result = ioLogController.ioUpdate(ioNo, proNo, io, ioQty, ioMemo);

                        // [3.4.6] 결과 출력
                        if (result) {
                            System.out.println("[안내] 재고를 성공적으로 수정하였습니다.");
                        } else {
                            System.out.println("[경고] 재고 수정을 실패하였습니다.");
                        }
                    }
                } else if (choice == 5) {
                    break;
                } else {
                    System.out.println("[경고] 올바르지 못한 메뉴입니다.");
                }
            } catch (InputMismatchException e) {
                System.out.println("[경고] 입력 타입이 올바르지 못합니다.");
            } catch (Exception e) {
                System.out.println("[경고] 올바르지 못한 입력입니다. 다시 한 번 올바르게 입력을 시도하여 주세요.");
            }
        } // 무한루프 종료
    } // ioManage end

    // [4] 발주관리
    public void supplyManage() {
        for (; ; ) {
            System.out.println(
                    "╔════════════════════════════════════╣ 발주 관리 ╠═══════════════════════════════════╗\n" +
                            "║                   1. 가맹점 발주 요청 보기  ▌  2. 출고 처리                           ║\n" +
                            "║                   3. 발주 요청 취소 처리    ▌  4. 메인으로 돌아가기                     ║\n" +
                            "╚═══════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("\uD83D\uDC49 메뉴 선택 : ");
            int choice = scan.nextInt();
            try {
                if (choice == 1) {          // 가맹점 발주 요청 보기를 선택하면
                    System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                    System.out.println("발주번호 \t  가맹점명 \t\t 제품명 \t    주문수량 \t\t 메모");
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                    // controller에게 결과값 받기
                    ArrayList<SupplyLogDto> supplyLogDtos = supplyLogController.supplyPrintAll();
                    for (SupplyLogDto supplyLogDto : supplyLogDtos) {      // 리스트를 하나씩 순회하면서
                        // 원하는 값 꺼내오기
                        int supNo = supplyLogDto.getSupNo();
                        int franNo = supplyLogDto.getFranNo();
                        int proNo = supplyLogDto.getProNo();
                        int supQty = supplyLogDto.getSupQty();
                        String supMemo = supplyLogDto.getSupMemo();
                        // 가맹점번호, 제품번호를 가맹점명, 제품명으로 변환
                        String franName = franController.toFranNameChange(franNo);
                        String proName = productController.toProNameChange(proNo);
                        // 글자수 정렬 함수
                        franName = franController.franNameArray( franName, franName.length() );
                        proName = productController.proNameArray( proName, proName.length() );
                        // 값 출력하기
                        System.out.printf("%d \t %s\t%s \t  %d \t %s\n", supNo, franName, proName, supQty, supMemo);
                    } // for end
                    System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                } else if (choice == 2) {   // 출고 처리를 선택하면
                    System.out.print("발주번호 : ");
                    int supNo = scan.nextInt();
                    SupplyLogDto supplyLogDto = supplyLogController.supplyPrint(supNo);
                    if (supplyLogDto.getFranNo() != 0) {       // 발주번호에 해당하는 발주가 존재한다면
                        System.out.println("──┤ 발주번호 정보 조회 ├───────────────────────────────────────────────────────────────");
                        System.out.println("발주번호 \t  가맹점명 \t\t  제품명 \t    주문수량 \t\t 메모");
                        System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                        // 원하는 값 꺼내오기
                        int franNo = supplyLogDto.getFranNo();
                        int proNo = supplyLogDto.getProNo();
                        int supQty = supplyLogDto.getSupQty();
                        String supMemo = supplyLogDto.getSupMemo();
                        // 가맹점번호, 제품번호를 가맹점명, 제품명으로 변환
                        String franName = franController.toFranNameChange(franNo);
                        String proName = productController.toProNameChange(proNo);
                        // 글자수 정렬 함수
                        franName = franController.franNameArray( franName, franName.length() );
                        proName = productController.proNameArray( proName, proName.length() );
                        // 값 출력하기
                        System.out.printf("%d \t %s \t %s \t\t%d \t %s\n", supNo, franName, proName, supQty, supMemo);
                        System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                        // 사용자로부터 '가맹점명' 입력받기
                        System.out.print("❗❗ 출고처리를 원하시면, [ 가맹점명 ]을 입력하세요. ");
                        String franNameInput = scan.next();
                        int franNoInput = franController.toFranNoChange(franNameInput);
                        // 결과를 받을 int 생성
                        int result;
                        // 입력받은 발주번호를 통해 해당 제품의 총 재고량 반환
                        int totalQty = supplyLogController.toTotalQtyChange( supNo );
                        if ( totalQty < supQty ){       // 총 재고량보다 주문수량이 많다면
                            result = 2;
                        } else {                        // 총 재고량이 주문수량보다 많다면
                            // 입력값 controller에게 전달 후 결과 받기
                            result = supplyLogController.supplyApp(supNo, franNoInput);
                        } // if end
                        if ( result == 0 ) {        // 출고처리에 성공했다면
                            System.out.println("[안내] 정상적으로 출고처리 되었습니다.");
                        } else if ( result == 1 ){  // 출고처리에 실패했다면
                            System.out.println("[경고] 가맹점명이 일치하지 않습니다.");
                        } else if ( result == 2 ){  // 재고가 부족하다면
                            System.out.println("[경고] 본사 재고가 부족합니다.");
                        } // if end
                    } else { // 발주번호에 해당하는 발주가 존재하지 않는다면
                        System.out.println("[경고] 출고처리할 수 없는 발주번호입니다.");
                    } // if end
                } else if (choice == 3) {   // 발주 요청 취소 처리를 선택하면
                    System.out.print("발주번호 : ");
                    int supNo = scan.nextInt();
                    SupplyLogDto supplyLogDto = supplyLogController.supplyPrint(supNo);
                    if (supplyLogDto.getFranNo() != 0) {       // 발주번호에 해당하는 발주가 존재한다면
                        System.out.println("──┤ 발주번호 정보 조회 ├───────────────────────────────────────────────────────────────");
                        System.out.println("발주번호 \t  가맹점명 \t\t  제품명 \t    주문수량 \t\t 메모");
                        System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                        // 원하는 값 꺼내오기
                        int franNo = supplyLogDto.getFranNo();
                        int proNo = supplyLogDto.getProNo();
                        int supQty = supplyLogDto.getSupQty();
                        String supMemo = supplyLogDto.getSupMemo();
                        // 가맹점번호, 제품번호를 가맹점명, 제품명으로 변환
                        String franName = franController.toFranNameChange(franNo);
                        String proName = productController.toProNameChange(proNo);
                        // 글자수 정렬 함수
                        franName = franController.franNameArray( franName, franName.length() );
                        proName = productController.proNameArray( proName, proName.length() );
                        // 값 출력하기
                        System.out.printf("%d \t %s \t %s \t %d \t %s\n", supNo, franName, proName, supQty, supMemo);
                        System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                        // 사용자로부터 '가맹점명' 입력받기
                        System.out.print("❗❗ 발주취소를 원하시면, [ 가맹점명 ]을 입력하세요. ");
                        String franNameInput = scan.next();
                        int franNoInput = franController.toFranNoChange(franNameInput);
                        // 입력값 controller에게 전달 후 결과 받기
                        boolean result = supplyLogController.supplyCancel(supNo, franNoInput);
                        // 결과에 따른 출력하기
                        if (result) {  // 취소처리에 성공했다면
                            System.out.println("[안내] 정상적으로 발주취소 처리되었습니다.");
                        } else {        // 취소처리에 실패했다면
                            System.out.println("[경고] 가맹점명이 일치하지 않습니다.");
                        } // if end
                    } else { // 발주번호에 해당하는 발주가 존재하지 않는다면
                        System.out.println("[경고] 취소처리할 수 없는 발주번호입니다.");
                    } // if end
                } else if (choice == 4) {   // 돌아가기를 선택하면
                    break;
                } else {
                    System.out.println("[경고] 올바르지 못한 메뉴입니다.");
                } // if end
            } catch (InputMismatchException e) {
                System.out.println("[경고] 입력 타입이 올바르지 못합니다.");
            } catch (Exception e) {
                System.out.println("[경고] 관리자에게 문의하세요.");
            } // try-catch end
        } // 무한루프 종료
    } // ioManage end

    // [5] 판매현황보기
    public void saleView() {
        for ( ; ; ){
            int orderNumber = orderLogController.orderNumber();
            int maxPage = orderNumber / 50 + 1;
            System.out.println("※ 0. 메인으로 돌아가기");
            System.out.print("▷ 페이지 입력 [ 1 ~ " + maxPage + " ] : ");         int page = scan.nextInt();
            if ( page == 0 ) break;
            if ( page > maxPage ){
                System.out.println("[경고] 유효하지않은 페이지입니다. 다시 입력해주세요.");
                continue;
            } // if end
            System.out.println("════════════════════════════════════════════════════════════════════════════════════");
            System.out.println("판매번호 \t 가맹점명 \t 제품명 \t 판매수량 \t 날짜·시간");
            System.out.println("────────────────────────────────────────────────────────────────────────────────────");
            // controller에게 page 전달 후 결과 받기
            ArrayList<OrderLogDto> orderLogDtos = orderLogController.orderLogPage( page );
            // 페이지 유효성 검사
            // 배열 하나씩 순회하기
            for ( OrderLogDto orderLogDto : orderLogDtos ){
                // 배열에서 값 하나씩 꺼내기
                int orderNo = orderLogDto.getOrderNo();
                int franNo = orderLogDto.getFranNo();
                int proNo = orderLogDto.getProNo();
                int orderQty = orderLogDto.getOrderQty();
                String orderDate = orderLogDto.getOrderDate();
                // 가맹점번호, 제품번호 -> 가맹점명, 제품명으로 변환하기
                String franName = franController.toFranNameChange( franNo );
                String proName = productController.toProNameChange( proNo );
                // 하나씩 출력하기
                System.out.printf("%d \t %s \t %s \t %d \t %s\n", orderNo, franName, proName, orderQty, orderDate );
            } // for end
            System.out.println("────────────────────────────────────────────────────────────────────────────────────");
        } // 무한루프 end
    } // saleView end

    // [6] 통계보기
    public void statusView() {
        for (; ; ) {
            System.out.println(
                    "╔════════════════════════════════════╣ 통계 보기 ╠═══════════════════════════════════╗\n" +
                            "║                       1. 제품별 통계      ▌  2. 지역별 통계                          ║\n" +
                            "║                       3. 시간대별 통계    ▌  4. 메인으로 돌아가기                      ║\n" +
                            "╚═══════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("\uD83D\uDC49 메뉴 선택 : ");
            int choice = scan.nextInt();
            try {
                if (choice == 1) {
                    System.out.println("※ 통계는 판매 금액 기준 상위 10건만 조회가능합니다. " +
                            "   통계 집계기간은 최근 30일입니다.");
                    System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                    System.out.println("통계번호 \t 제품명 \t 판매금액 ");
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                    // controller로부터 통계자료 받기
                    ArrayList<StatsDto> statsDtos = statsController.proStatsPrint();
                    for (int i = 0; i < statsDtos.size(); i++) {   // 통계자료 하나씩 순회하기
                        StatsDto statsDto = statsDtos.get(i);
                        // 통계자료에서 하나씩 값 꺼내기
                        int proNo = statsDto.getNumber();
                        int totalPrice = statsDto.getTotalPrice();
                        // totalPrice 천 단위 콤마 찍기
                        String price = nf.format(totalPrice);
                        // 제품번호 -> 제품명으로 변환하기
                        String proName = productController.toProNameChange(proNo);
                        // 하나씩 출력하기
                        System.out.printf("  %d \t %s \t %s원\n", i + 1, proName, price);
                    } // for end
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                } else if (choice == 2) {
                    System.out.println("※ 통계는 판매 금액 기준 상위 10건만 조회가능합니다. " +
                            "   통계 집계기간은 최근 30일입니다.");
                    System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                    System.out.println("통계번호 \t 지역 \t 매출액 ");
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                    // controoler로부터 통계자료 받기
                    ArrayList<StatsDto> statsDtos = statsController.regionStatsPrint();
                    for (int i = 0; i < statsDtos.size(); i++) {   // 통계자료 하나씩 순회하기
                        StatsDto statsDto = statsDtos.get(i);
                        // 통계자료에서 하나씩 값 꺼내기
                        String region = statsDto.getRegion();
                        int totalPrice = statsDto.getTotalPrice();
                        // totalPrice 천 단위 콤마찍기
                        String price = nf.format(totalPrice);
                        // 하나씩 출력하기
                        System.out.printf("  %d \t %s \t %s원\n", i + 1, region, price);
                    } // for end
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                } else if (choice == 3) {
                    System.out.println("※ 통계 집계기간은 최근 30일입니다.");
                    System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                    System.out.println("\t   시간대 \t\t  매출액 ");
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                    // controller로부터 통계자료 받기
                    ArrayList<StatsDto> statsDtos = statsController.hourStatsPrint();
                    for (int i = 0; i < statsDtos.size(); i++) {   // 통계자료 하나씩 순회하기
                        StatsDto statsDto = statsDtos.get(i);
                        // 통계자료에서 값 꺼내기
                        int totalPrice = statsDto.getTotalPrice();
                        // totalPrice 천 단위 콤마찍기
                        String price = nf.format(totalPrice);
                        // 하나씩 출력하기 : 출력 형식을 맞추기 위한 if문
                        if (i < 9) {
                            System.out.printf("0%d : 00 ~ 0%d : 00 \t %s원\n", i, i + 1, price);
                        } else if (i == 9) {
                            System.out.printf("0%d : 00 ~ %d : 00 \t %s원\n", i, i + 1, price);
                        } else {
                            System.out.printf("%d : 00 ~ %d : 00 \t %s원\n", i, i + 1, price);
                        } // if end
                    } // for end
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                } else if (choice == 4) {
                    break;
                } else {
                    System.out.println("[경고] 올바르지 못한 메뉴입니다.");
                } // if end
            } catch (InputMismatchException e) {
                System.out.println("[경고] 입력 타입이 올바르지 못합니다.");
            } catch (Exception e) {
                System.out.println("[경고] 관리자에게 문의하세요.");
            } // try-catch end
        } // 무한루프 종료
    } // statusView end

    // [7] 리뷰보기
    public void reviewView() {
        for (; ; ) {
            System.out.println(
                    "╔════════════════════════════════════╣ 리뷰 보기 ╠═══════════════════════════════════╗\n" +
                            "║                       1. 리뷰 전체 조회    ▌  2. 가맹점별 리뷰 조회                    ║\n" +
                            "║                       3. 제품별 리뷰 조회  ▌  4. 메인으로 돌아가기                     ║\n" +
                            "╚═══════════════════════════════════════════════════════════════════════════════════╝");
            System.out.print("\uD83D\uDC49 메뉴 선택 : ");
            int choice = scan.nextInt();
            try {
                if (choice == 1) { // 7.1. 리뷰 전체 조회
                    // [7.1.1] reviewController func 실행
                    ArrayList<ReviewPrintDto> reviewPrintList = reviewController.reviewPrint();

                    System.out.println("════════════════════════════════════════════════════════════════════════════════════");
                    System.out.println("리뷰번호 \t 판매번호 \t 제품명 \t 가맹점명 \t 리뷰");
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");

                    // [7.1.2] 반복문
                    for (ReviewPrintDto reviewPrintDto : reviewPrintList) {
                        // [7.1.3] franNO > franName 변환
                        String franName = franController.toFranNameChange(reviewPrintDto.getFranNo());

                        System.out.printf("%d \t %d \t %s \t %s \t %s \n",
                                reviewPrintDto.getReviewNo(),
                                reviewPrintDto.getOrderNo(),
                                reviewPrintDto.getProName(),
                                franName,
                                reviewPrintDto.getReview());
                    }
                    System.out.println("────────────────────────────────────────────────────────────────────────────────────");

                } else if (choice == 2) { // 7.2. 가맹점별 리뷰 조회
                    System.out.print("가맹점명 : ");
                    String franName = scan.next();

                    // [7.2.1] franName 유효성 검사
                    // [7.2.1.1] franName > franNo로 변환
                    int franNo = franController.toFranNoChange(franName);
                    // [7.2.1.2] franNo 존재여부 확인
                    boolean check = franController.franNoCheck(franNo);

                    // [7.2.1.3] 유효성 검사에 따라 진행
                    if (check == false) {
                        System.out.println("[경고] 존재하지 않는 가맹점입니다.");
                    } else {
                        // [7.2.2] reviewController func 실행
                        ArrayList<ReviewPrintDto> reviewPrintList = reviewController.franReviewPrint(franNo);
                        System.out.println("──┤ 선택 가맹점 리뷰 ├────────────────────────────────────────────────────────────────");
                        System.out.println("리뷰번호 \t 판매번호 \t 제품명 \t 가맹점명 \t 리뷰");
                        System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                        // [7.1.2] 반복문
                        for (ReviewPrintDto reviewPrintDto : reviewPrintList) {
                            System.out.printf("%d \t %d \t %s \t %s \t %s \n",
                                    reviewPrintDto.getReviewNo(),
                                    reviewPrintDto.getOrderNo(),
                                    reviewPrintDto.getProName(),
                                    franName,
                                    reviewPrintDto.getReview());
                        }
                        System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                    }
                } else if (choice == 3) { // 7.3. 제품별 리뷰 조회
                    System.out.print("제품명 : ");
                    String proName = scan.next();

                    // [7.3.1] 제품명 유효성 검사
                    // [7.3.1.1] proName > proNo 변환
                    int proNo = productController.toIntproNoChange(proName);
                    // [7.3.1.2] proNO 존재여부 확인
                    boolean check = productController.proNoCheck(proNo);

                    // [7.3.1.3] 유효성 검사에 따라 진행
                    if (check == false) {
                    } else {
                        // [7.3.2] reviewController func 실행
                        ArrayList<ReviewPrintDto> reviewPrintList = reviewController.proReviewPrint(proName);
                        System.out.println("──┤ 선택 제품 리뷰 ├──────────────────────────────────────────────────────────────────");
                        System.out.println("리뷰번호 \t 판매번호 \t 제품명 \t 가맹점명 \t 리뷰");
                        System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                        // [7.3.3] 반복문
                        for (ReviewPrintDto reviewPrintDto : reviewPrintList) {
                            // [7.1.3] franNO > franName 변환
                            String franName = franController.toFranNameChange(reviewPrintDto.getFranNo());

                            System.out.printf("%d \t %d \t %s \t %s \t %s \n",
                                    reviewPrintDto.getReviewNo(),
                                    reviewPrintDto.getOrderNo(),
                                    reviewPrintDto.getProName(),
                                    franName,
                                    reviewPrintDto.getReview());
                        }
                        System.out.println("────────────────────────────────────────────────────────────────────────────────────");
                    }
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
