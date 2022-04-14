package product_teacher;

import java.util.List;

public class ProductServiceImple implements ProductService {
	private ProductDAO dao;
	
	public ProductServiceImple() {
		dao = new ProductDAOImple();
	}

	@Override
	public int register(ProductVO pvo) {
		return dao.insert(pvo);
	}

	@Override
	public List<ProductVO> getList() {
		return dao.selectList();
	}

	@Override
	public ProductVO getDetail(int pno) {
		return dao.selectOne(pno);
	}

	@Override
	public int modify(ProductVO pvo) {
		return dao.update(pvo);
	}

	@Override
	public int remove(int pno) {
		return dao.delete(pno);
	}

}
