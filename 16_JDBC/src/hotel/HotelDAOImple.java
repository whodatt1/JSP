package hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDAOImple implements HotelDAO {

	private Connection conn;
	private PreparedStatement pst;
	private String query = "";

	public HotelDAOImple() {
		MySQLConnect dbc = MySQLConnect.getInstanceof();
		conn = dbc.getConnection();
	}

	@Override
	public int itIsNull() {
		query = "select * from room";
		try {
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	@Override
	public int daoResetHotel() {
		query = "truncate room";
		try {
			pst = conn.prepareStatement(query);
			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	@Override
	public int daoBuiltHotel(int floorCount, int roomCount) {
		int result = 1;
		for (int i = 0; i < floorCount; i++) {
			for (int j = 0; j < roomCount; j++) {
				query = "insert into room (room_num) values (" + ((i + 1) + (j < 10 ? "0" : "") + (j + 1)) + ")";
				try {
					pst = conn.prepareStatement(query);
					result *= pst.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						pst.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	@Override
	public List<RoomVO> getList() {
		query = "select * from room";
		List<RoomVO> roomList = new ArrayList<>(); 
		try {
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				roomList.add(new RoomVO(rs.getString("room_num"),
						new User(rs.getString("user_name"),
								rs.getInt("user_age"),
								rs.getString("user_gen")),
						rs.getBoolean("is_empty"),
						rs.getString("check_in_time"),
						rs.getString("check_out_time")));
			}
			return roomList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int daoRoomCheckIn(RoomVO room) {
		query = "update room set user_name = ?, user_age = ?, user_gen = ?, is_empty = 0,"
				+ "check_in_time = now(), check_out_time = null where room_num = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, room.getUser().getName());
			pst.setInt(2, room.getUser().getAge());
			pst.setString(3, room.getUser().getGen());
			pst.setString(4, room.getRoomNum());
			System.out.println(pst.executeUpdate());
			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int daoRoomCheckOut(String roomNum) {
		query = "update room set user = null, is_empty = ?, check_in_time = null, check_out_time = now() where room_num = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setBoolean(1, true);
			pst.setString(2, roomNum);
			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public RoomVO daoRoomDetail(String roomNum) {
		query = "select * from room where room_num = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, roomNum);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new RoomVO(rs.getString("room_num"),
						new User(rs.getString("user_name"),
								rs.getInt("user_age"),
								rs.getString("user_gen")),
						rs.getBoolean("is_empty"),
						rs.getString("check_in_time"),
						rs.getString("check_out_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<RoomVO> daoUserList() {
		query = "select room_num, user_name, user_age, user_gen from room";
		try {
			pst = conn.prepareStatement(query);
			List<RoomVO> userList = new ArrayList<>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				userList.add(new RoomVO(rs.getString("room_num"), 
							 new User(rs.getString("user_name"), rs.getInt("user_age"),
									 rs.getString("user_gen"))));
			}
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
