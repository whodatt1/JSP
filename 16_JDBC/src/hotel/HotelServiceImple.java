package hotel;

import java.util.List;

public class HotelServiceImple implements HotelService {
	
	private HotelDAO hdao;
	
	public HotelServiceImple() {
		hdao = new HotelDAOImple();
	}
	
	@Override
	public int isNull() {
		return hdao.itIsNull();
	}
	
	@Override
	public int resetHotel() {
		return hdao.daoResetHotel();
	}
	
	@Override
	public int builtHotel(int floorCount, int roomCount) {
		return hdao.daoBuiltHotel(floorCount, roomCount);
	}

	@Override
	public List<RoomVO> list() {
		return hdao.getList();
	}

	@Override
	public int roomCheckIn(RoomVO room) {
		return hdao.daoRoomCheckIn(room);
	}

	@Override
	public int roomCheckOut(String roomNum) {
		return hdao.daoRoomCheckOut(roomNum);
	}

	@Override
	public RoomVO roomDetail(String roomNum) {
		return hdao.daoRoomDetail(roomNum);
	}

	@Override
	public List<RoomVO> userList() {
		return hdao.daoUserList();
	}
}
