package hotel_teacher;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class HotelServiceImple implements HotelService2 {
	private Scanner sc = new Scanner(System.in);
	private HotelDAO2 dao;
	
	public HotelServiceImple() {
		this.dao = new HotelDAOImple();
	}

	@Override
	public boolean choiceMenu(int menu) {
		
		String roomNum = "";
		switch (menu) {
		case 1:
			getRoomsStatus(); // 객실현황
			break;
		case 2:
			System.out.println("입실할 방번호를 입력 > ");
			roomNum = sc.next();
			roomCheckIn(roomNum);
			break;
		case 3:
			System.out.println("퇴실할 방번호를 입력 > ");
			roomNum = sc.next();
			roomCheckOut(roomNum);
			break;
		case 4:
			System.out.println("조회할 방번호를 입력 > ");
			roomNum = sc.next();
			getRoomDetail(roomNum);
			break;
		case 5:
			System.out.println("-------- 투숙객 리스트 ---------");
			getUserList();
			break;
		default:
			System.out.println("저희 호텔을 이용해 주셔서 감사합니다");
			return false;
		}
		return true;
	
	}

	@Override
	public void getRoomsStatus() {
		List<Room> roomList = dao.selectList();
		for (Room room : roomList) {
			System.out.print(room.getRoomNum() + "호");
			System.out.print("["+ (room.isEmpty() ? "입실가능":"사용중") + "]\t");
			System.out.println();
		}
	}

	@Override
	public void roomCheckIn(String roomNum) {
		Room room = dao.selectOne(roomNum);
		if(room.getRoomNum().equals(roomNum)) {
			if(room.isEmpty()) {
				room.setEmpty(false);
				room.setUser(getUserInfo());
				LocalDateTime todayNow = LocalDateTime.now();
				room.setCheckInTime(
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(todayNow));
				room.setCheckOutTime(null);
				int isUp = dao.updateCheckIn(room);
				System.out.println(roomNum + (isUp > 0 ? "호 입실완료":"호 입실오류"));
			}else {
				System.out.println(roomNum + "호는 현재 사용중!");
			}
		}
	}

	private User2 getUserInfo() {
		System.out.println("투숙객의 정보를 입력해야 합니다!");
		System.out.println("이름 > ");
		String name = sc.next();
		System.out.println("나이 > ");
		int age = sc.nextInt();
		System.out.println("성별 > ");
		String gen = sc.next();

		return new User2(name, age, gen.substring(0, 1));
	}

	@Override
	public void roomCheckOut(String roomNum) {
		Room room = dao.selectOne(roomNum);
		if(room.getRoomNum().equals(roomNum)) {
			if(!room.isEmpty()) {
				room.setEmpty(true);
				room.setUser(null);
				LocalDateTime todayNow = LocalDateTime.now();
				room.setCheckOutTime(
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(todayNow));
				room.setCheckInTime(null);
				int isUp = dao.updateCheckOut(room);
				System.out.println(roomNum + (isUp > 0 ? "호 퇴실완료":"호 퇴실오류"));
			}else {
				System.out.println(roomNum + "호는 현재 빈방!");
			}
		}
	}

	@Override
	public void getRoomDetail(String roomNum) {
		Room room = dao.selectOne(roomNum);
		System.out.println(room);
	}

	@Override
	public void getUserList() {
		List<Room> roomList = dao.selectList();
		for (Room room : roomList) {
			if (room.getUser().getName() != null) {
				System.out.print(room.getRoomNum() + " > ");
				System.out.println(room.getUser());				
			}
		}
	}

}
