package hotel_teacher;

public interface HotelService2 {
	boolean choiceMenu(int menu);
	void getRoomsStatus();
	void roomCheckIn(String roomNum);
	void roomCheckOut(String roomNum);
	void getRoomDetail(String roomNum);
	void getUserList();
}
