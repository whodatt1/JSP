package addrEx;

import java.util.List;

public interface DataAccessObject {
	int insert(Address addr);
	List<Address> selectList();
	Address selectOne(String name);
	int update(Address addr);
	int delete(String name);
	Address selectOneByTel(String tel);
	List<Address> selectOneByAge(int age);
}
