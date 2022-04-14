package product;

import java.util.List;

public interface Service {
	boolean register(Product prod);
	List<Product> getList();
	Product getDetail(int pno);
	boolean modify(Product prod);
	boolean remove(int pno);
}
