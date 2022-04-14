package product_teacher;

import java.util.List;

public interface ProductService {
	int register(ProductVO pvo);
	List<ProductVO> getList();
	ProductVO getDetail(int pno);
	int modify(ProductVO pvo);
	int remove(int pno);
}
