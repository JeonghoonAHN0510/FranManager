package View;

import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    // Scanner·Controller 등 외부 참조
    private Scanner scan = new Scanner(System.in);

    // 싱글톤
    private View() {
    }

    ;
    private static final View instance = new View();

    public static View getInstance() {
        return instance;
    }

    // [ 0 ] main view
    public void mainView() {
        //로고 출력-최초 1회
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
        }
    } // [0] main view end

    // [1] 가맹점관리
    public void franManage() {

    }

    // [2] 재고관리
    public void invenManage() {

    }

    // [3] 발주관리
    public void ioManage() {

    }

    // [4] 판매현황보기
    public void saleView() {

    }

    // [5] 통계보기
    public void statusView() {

    }

    // [6] 리뷰보기
    public void reviewView() {

    }


}  // class end
