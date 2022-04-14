package product;

import java.util.List;

public class ServiceImple implements Service {
	private DAOImple daoi;

	public ServiceImple() {
		daoi = new DAOImple();
	}

	@Override
	public boolean register(Product prod) {
		return daoi.insert(prod) < 0 ? false : true;
	}

	@Override
	public List<Product> getList() {
		return daoi.getList();
	}

	@Override
	public Product getDetail(int pno) {
		return daoi.selectOne(pno);
	}

	@Override
	public boolean modify(Product prod) {
		return daoi.update(prod) < 0 ? false : true;
	}

	@Override
	public boolean remove(int pno) {
		return daoi.delete(pno) < 0 ? false : true;
	}

}
