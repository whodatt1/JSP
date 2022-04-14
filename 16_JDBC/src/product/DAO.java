package product;

import java.util.List;

public interface DAO {
	int insert(Product prod);
	List<Product> getList();
	Product selectOne(int pno);
	int update(Product prod);
	int delete(int pno);
}
