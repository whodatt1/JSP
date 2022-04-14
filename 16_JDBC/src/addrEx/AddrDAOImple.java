package addrEx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddrDAOImple implements DataAccessObject {
	private Connection conn;
	private PreparedStatement pst;
	private String query = "";

	public AddrDAOImple() {
		DataBaseConnect dbc = DataBaseConnect.getInstance();
		conn = dbc.getConnection();
	}

	@Override
	public int insert(Address addr) {
		query = "insert into addr values(?, ?, ?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, addr.getName());
			pst.setString(2, addr.getTel());
			pst.setInt(3, addr.getAge());
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
	public List<Address> selectList() {
		query = "select * from addr order by age desc";
		try {
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			List<Address> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Address(rs.getString("name"), rs.getString("tel"), rs.getInt("age")));
			}
			return list;
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
	public Address selectOne(String name) {
		query = "select * from addr where name = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new Address(rs.getString("name"), rs.getString("tel"), rs.getInt("age"));
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
	public int update(Address addr) {
		query = "update addr set tel = ?, age = ? where name = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, addr.getTel());
			pst.setInt(2, addr.getAge());
			pst.setString(3, addr.getName());
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
	public int delete(String name) {
		query = "delete from addr where name = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, name);
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
	public Address selectOneByTel(String tel) {
		query = "select * from addr where tel = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, tel);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new Address(rs.getString("name"), rs.getString("tel"), rs.getInt("age"));
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
	public List<Address> selectOneByAge(int age) {
		query = "select * from addr where age = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, age);
			ResultSet rs = pst.executeQuery();
			List<Address> list = new ArrayList<>();
			while (rs.next()) {
				list.add(new Address(rs.getString("name"), rs.getString("tel"), rs.getInt("age")));
			}
			return list;
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

}
