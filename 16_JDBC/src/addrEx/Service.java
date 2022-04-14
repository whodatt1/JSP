package addrEx;

import java.util.List;

// crud service 정의 > 기본중의 기본
public interface Service {
	boolean register(Address addr);
	List<Address> getList();
	Address getDetail(String name);
	boolean modify(Address addr);
	boolean remove(String name);
	Address getDetailByTel(String tel);
	List<Address> getDetailByAge(int age);
}
