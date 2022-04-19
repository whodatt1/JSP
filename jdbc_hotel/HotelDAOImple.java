package hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDAOImple implements HotelDAO{
	private Connection conn;
	private PreparedStatement pst;
	private String query = "";
	
	public HotelDAOImple() {
		MySQLConnect dbc = MySQLConnect.getInstance();
		conn = dbc.getConnection();
	}

	@Override
	public int insert(List<Room> roomList) {
		int result = 1;
		for (Room room : roomList) {
			query = "insert into room (room_num) values(?)";
			try {
				pst = conn.prepareStatement(query);
				pst.setString(1, room.getRoomNum());
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
		return result;
	}

	@Override
	public List<Room> selectList() {
		query = "select * from room";
		List<Room> list = new ArrayList<>();
		try {			
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new Room(rs.getString("room_num"),
						new User(rs.getString("user_name"),
								rs.getInt("user_age"),
								rs.getString("user_gen")),
						rs.getBoolean("is_empty"),
						rs.getString("check_in_time"),
						rs.getString("check_out_time")));
			}
			return list;
		} catch (Exception e) {
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
	public Room selectOne(String roomNum) {
		query = "select * from room where room_num = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, roomNum);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new Room(rs.getString("room_num"),
						new User(rs.getString("user_name"),
								rs.getInt("user_age"),
								rs.getString("user_gen")),
						rs.getBoolean("is_empty"),
						rs.getString("check_in_time"),
						rs.getString("check_out_time"));
			}
		} catch (Exception e) {
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
	public int updateCheckIn(Room room) {
		query = "update room set user_name = ?, user_age = ?, user_gen = ?, is_empty = 0,"
				+ "check_in_time = now(), check_out_time = null where room_num = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, room.getUser().getName());
			pst.setInt(2, room.getUser().getAge());
			pst.setString(3, room.getUser().getGen());
			pst.setString(4, room.getRoomNum());
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int updateCheckOut(Room room) {
		query = "update room set user_name = null, user_age = 0, user_gen = null, is_empty = 1,"
				+ "check_in_time = null, check_out_time = now() where room_num = ?";
		try {
			pst = conn.prepareStatement(query);			
			pst.setString(1, room.getRoomNum());
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public void truncateHotel() {
		query = "truncate table room";
		try {
			pst = conn.prepareStatement(query);
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
