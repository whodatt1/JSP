package addrEx;

import java.util.List;

public class AddrServiceImple implements Service {
	
	private DataAccessObject dao;
	public AddrServiceImple() {
		dao = new AddrDAOImple();
	}
	
	@Override
	public boolean register(Address addr) {
		return dao.insert(addr) > 0 ? true : false;
	}

	@Override
	public List<Address> getList() {
		return dao.selectList();
	}

	@Override
	public Address getDetail(String name) {
		return dao.selectOne(name);
	}

	@Override
	public boolean modify(Address addr) {
		return dao.update(addr) > 0 ? true : false;
	}

	@Override
	public boolean remove(String name) {
		return dao.delete(name) > 0 ? true : false;
	}

	@Override
	public Address getDetailByTel(String tel) {
		return dao.selectOneByTel(tel);
	}

	@Override
	public List<Address> getDetailByAge(int age) {
		return dao.selectOneByAge(age);
	}

}
