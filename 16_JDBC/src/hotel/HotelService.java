package hotel;

import java.util.List;

public interface HotelService {
	int isNull();
	int resetHotel();
	int builtHotel(int floorCount, int roomCount);
	int roomCheckIn(RoomVO room);
	int roomCheckOut(String roomNum);
	RoomVO roomDetail(String roomNum);
	List<RoomVO> userList();
	List<RoomVO> list();
}
