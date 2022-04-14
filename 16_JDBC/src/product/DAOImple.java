package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOImple implements DAO {
	private Connection conn;
	private PreparedStatement pst;
	private String query = "";
	
	public DAOImple() {
		DataBaseConnect dbc = DataBaseConnect.getInstanceof();
		conn = dbc.getConnection();
	}
	
	@Override
	public int insert(Product prod) {
		query = "insert into product (pname, price, madeby) values (?, ?, ?)";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, prod.getPname());
			pst.setInt(2, prod.getPrice());
			pst.setString(3, prod.getMadeby());
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
	public List<Product> getList() {
		query = "select pno, pname, price from product";
		try {
			pst = conn.prepareStatement(query);
			List<Product> list = new ArrayList<>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				list.add(new Product(rs.getInt("pno"), rs.getString("pname"), rs.getInt("price")));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Product selectOne(int pno) {
		query = "select * from product where pno = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, pno);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return new Product(rs.getInt("pno"), rs.getString("pname"), rs.getInt("price"), rs.getString("madeby"));
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
	public int update(Product prod) {
		query = "update product set pname = ?, price = ?, regdate = now(), madeby = ? where pno = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, prod.getPname());
			pst.setInt(2, prod.getPrice());
			pst.setString(3, prod.getMadeby());
			pst.setInt(4, prod.getPno());
			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int pno) {
		query = "delete from product where pno = ?";
		try {
			pst = conn.prepareStatement(query);
			pst.setInt(1, pno);
			return pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
