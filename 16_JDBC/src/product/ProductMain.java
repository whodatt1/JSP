package product;

import java.util.List;
import java.util.Scanner;

public class ProductMain {

	public static void main(String[] args) {
		// 상품 등록, 리스트 조회, 개별 조회, 수정, 삭제를 데이터베이스를 연동하여 구현하세요
		// 서비스 인터페이스, DAO 인터페이스를 통해 반드시 구현체로 객체를 만드세요
		// 내일 수업 시작과 동시에 풀이 합니다. => 풀이 시간의 속도가 빠를 수 있다라는 의미...
		Scanner sc = new Scanner(System.in);
		Service si = new ServiceImple();
		int menu = 0;
		int pno = 0;
		boolean flag = true;
		
		while (flag) {
			System.out.println("1. 상품등록  2. 리스트 조회  3. 개별 조회  4. 수정  5. 삭제  0. 종료");
			menu = sc.nextInt();
			
			switch (menu) {
			case 1:
				boolean isRegi = si.register(regist(sc));
				System.out.println("등록 " + (isRegi ? "성공" : "실패"));
				break;
			case 2:
				List<Product> list = si.getList();
				for (Product product : list) {
					System.out.println("Product [pno=" + product.getPno() + ", pname=" + product.getPname() + ", price=" + product.getPrice() + "]");
				}
				break;
			case 3:
				Product prod = si.getDetail(choosePno(sc));
				System.out.println(prod);
				break;  
			case 4:
				boolean isUp = si.modify(modify(sc));
				System.out.println("수정 " + (isUp ? "성공" : "실패"));
				break;
			case 5:
				boolean isDel = si.remove(remove(sc));
				System.out.println("삭제 " + (isDel ? "성공" : "실패"));
				break;
				
			default:
				flag = false;
				System.out.println("시스템을 종료합니다.");
				break;
			}
		}
	}

	private static int remove(Scanner sc) {
		System.out.println("삭제하실 상품 번호를 입력하세요");
		int pno = sc.nextInt();
		return pno;
	}

	private static Product modify(Scanner sc) {
		System.out.println("변경하실 상품 번호를 입력하세요 > ");
		int pno = sc.nextInt();
		System.out.println("상품명을 입력하세요 > ");
		String pname = sc.next();
		System.out.println("가격을 입력하세요 > ");
		int price = sc.nextInt();
		System.out.println("제작자를 입력하세요 > ");
		String madeby = sc.next();
		return new Product(pno, pname, price, madeby);
	}

	private static int choosePno(Scanner sc) {
		System.out.println("조회하실 상품 번호를 입력하세요 > ");
		int pno = sc.nextInt();
		return pno;
	}

	private static Product regist(Scanner sc) {
		System.out.println("상품 명을 입력하세요 > ");
		String pname = sc.next();
		System.out.println("상품 가격을 입력하세요 > ");
		int price = sc.nextInt();
		System.out.println("제작자를 입력하세요 > ");
		String madeby = sc.next();
		
		return new Product(pname, price, madeby);
	}


}
