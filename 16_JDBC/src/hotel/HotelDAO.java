package hotel;

import java.util.List;

public interface HotelDAO {
	int itIsNull();
	int daoResetHotel();
	int	daoBuiltHotel(int floorCount, int roomCount);
	int daoRoomCheckIn(RoomVO room);
	int daoRoomCheckOut(String roomNum);
	RoomVO daoRoomDetail(String roomNum);
	List<RoomVO> daoUserList();
	List<RoomVO> getList();
}
