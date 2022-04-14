package hotel_teacher;

import java.util.List;

public interface HotelDAO2 {
	public int insert(List<Room> roomList);

	public List<Room> selectList();

	public Room selectOne(String roomNum);

	public int updateCheckIn(Room room);

	public int updateCheckOut(Room room);
	
	public void truncateHotel();
	
}
