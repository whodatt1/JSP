package hotel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Hotel {
	private Scanner sc = new Scanner(System.in);
	private HotelService hs;
	private int floorCount, roomCount, menu, roomNum = 0;
	private boolean flag = true;

	public Hotel() {
		hs = new HotelServiceImple();
		if (hs.isNull() == 1) {
			System.out.println("1. 기존 호텔에 접속하기  2. 새로운 호텔 짓기");
			int choose = sc.nextInt();
			switch (choose) {
			case 1:
				frontDesk();
				break;
			case 2:
				hs.resetHotel();
				builtHotelNow();
				frontDesk();
				break;

			default:
				break;
			}
		} else {
			builtHotelNow();
			frontDesk();
		}
	}

	private void frontDesk() {
		while (flag) {
			System.out.println("=======================호텔 관리 메뉴 =======================");
			System.out.println("1:객실현황  2:입실  3:퇴실  4:객실상세조회 5:투숙객조회 Etc:종료");
			System.out.println(">>>>>>>>>>>>>>>>>>>>메뉴를 선택하세요>>>>>>>>>>>>>>>>>>>>");
			menu = sc.nextInt();

			flag = choiceMenu(menu);
		}
	}

	private boolean choiceMenu(int menu) {
		String roomNum = "";
		switch (menu) {
		case 1:
			List<RoomVO> roomInfo = hs.list();
			for (RoomVO room : roomInfo) {
				System.out.println(room.getRoomNum() + " " + (room.isEmpty() ? "입실가능" : "사용중"));
			}
			break;
		case 2:
			System.out.println("입실할 방번호를 입력하세요");
			roomNum = sc.next();
			checkInMeth(roomNum);
			break;
		case 3:
			System.out.println("퇴실하실 방 번호를 입력하세요");
			roomNum = sc.next();
			checkOutMeth(roomNum);
			break;
		case 4:
			System.out.println("조회하실 방 번호를 입력하세요");
			roomNum = sc.next();
			RoomVO rd = hs.roomDetail(roomNum);
			System.out.println(rd);
			break;
		case 5:
			List<RoomVO> userList = hs.userList();
			for (RoomVO user : userList) {
				if (user.getUser().getName() != null) {
					System.out.println(user.getRoomNum() + " : " + user.getUser());					
				}
			}
			break;

		default:
			System.out.println("이용해 주셔서 감사합니다.");
			return false;
		}
		return true;
	}

	private void checkOutMeth(String roomNum) {
		RoomVO room = hs.roomDetail(roomNum);
		if (room.getRoomNum().equals(roomNum)) {
			if (room.isEmpty()) {
				room.setEmpty(false);
				room.setUser(null);
				LocalDateTime todayNow = LocalDateTime.now();
				room.setCheckInTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(todayNow));
				room.setCheckOutTime(null);
				int isOut = hs.roomCheckOut(roomNum);
				System.out.println("퇴실 " + (isOut > 0 ? "성공" : "실패"));
			} else {
				System.out.println(roomNum + "호는 현재 빈방!");
			}
		}
	}

	private void checkInMeth(String roomNum) {
		RoomVO room = hs.roomDetail(roomNum);
		if (room.getRoomNum().equals(roomNum)) {
			if (room.isEmpty()) {
				room.setEmpty(false);
				room.setUser(getUserInfo());
				LocalDateTime todayNow = LocalDateTime.now();
				room.setCheckInTime(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(todayNow));
				room.setCheckOutTime(null);
				int isIn = hs.roomCheckIn(room);
				System.out.println("입실 " + (isIn > 0 ? "성공" : "실패"));
			} else {
				System.out.println(roomNum + "호는 현재 사용중!");
			}
		}
	}

	private User getUserInfo() {
		System.out.println("사용자분 성함을 입력하세요");
		String user_name = sc.next();
		System.out.println("사용자분 나이를 입력하세요");
		int user_age = sc.nextInt();
		System.out.println("사용자분 성별을 입력하세요");
		String user_gen = sc.next();

		return new User(user_name, user_age, user_gen.substring(0, 1));
	}

	private void builtHotelNow() {
		System.out.println("-------호텔을 건설합니다-------");
		System.out.println("호텔 층수를 입력하세요 > ");
		floorCount = sc.nextInt();
		System.out.println("층 당 방 수를 입력하세요 >");
		roomCount = sc.nextInt();
		int isBuil = hs.builtHotel(floorCount, roomCount);
		System.out.println("호텔 건설 " + (isBuil > 0 ? "성공" : "실패"));
	}
}
