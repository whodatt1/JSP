package addrEx;

public class Address {
	private String name;
	private String tel;
	private int age;
	
	public Address() {}
	public Address(String name, String tel, int age) {
		this.name = name;
		this.tel = tel;
		this.age = age;
	}
	String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name;
	}
	String getTel() {
		return tel;
	}
	void setTel(String tel) {
		this.tel = tel;
	}
	int getAge() {
		return age;
	}
	void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Address [name=" + name + ", tel=" + tel + ", age=" + age + "]";
	}
}
