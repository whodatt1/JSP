package product_teacher;

import java.util.List;
import java.util.Scanner;

public class productMain {

	public static void main(String[] args) {
		ProductService psv = new ProductServiceImple();
		List<ProductVO> list = null;
		ProductVO pvo = null;
		
		Scanner sc = new Scanner(System.in);
		
		boolean flag = true;
		int price, menu, pno, isOk = 0;
		String name, madeBy = "";

		while (flag) {
			System.out.println("====================상품관리 시스템====================");
			System.out.println("1. 등록  2. 목록  3. 찾기  4. 수정  5. 삭제  0. 종료");
			System.out.println(">>>>>>>>>>>>>>>>>>>원하는 메뉴 선택>>>>>>>>>>>>>>>>>>>");

			menu = sc.nextInt();

			switch (menu) {
			case 1:
				System.out.println("상품명 > ");
				name = sc.next();
				System.out.println("상품가격 > ");
				price = sc.nextInt();
				System.out.println("제조사 > ");
				madeBy = sc.next();
				isOk = psv.register(new ProductVO(name, price, madeBy));
				System.out.println("상품 등록 " + (isOk > 0 ? "성공" : "실패"));
				break;
			case 2:
				list = psv.getList();
				for (ProductVO prdVo : list) {
					System.out.println(prdVo);
				}
				break;
			case 3:
				System.out.println("상품번호 > ");
				pno = sc.nextInt();
				pvo = psv.getDetail(pno);
				System.out.println(pvo);
				break;
			case 4:
				System.out.println("상품번호 > ");
				pno = sc.nextInt();
				System.out.println("상품명 > ");
				name = sc.next();
				System.out.println("상품가격 > ");
				price = sc.nextInt();
				System.out.println("제조사 > ");
				madeBy = sc.next();
				isOk = psv.modify(new ProductVO(pno, name, price, madeBy));
				System.out.println("상품 수정 " + (isOk > 0 ? "성공" : "실패"));
				break;
			case 5:
				System.out.println("상품번호 > ");
				pno = sc.nextInt();
				isOk = psv.remove(pno);
				System.out.println("상품 삭제 " + (isOk > 0 ? "성공" : "실패"));
				break;

			default:
				System.out.println("이용해주셔서 감사합니다");
				flag = false;
				break;
			}
		}
	}

}
