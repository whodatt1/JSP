package product;

public class Product {
	private int pno;
	private String pname;
	private int price;
	private String madeby;
	
	public Product() {}

	public Product(String pname, int price, String madeby) {
		this.pname = pname;
		this.price = price;
		this.madeby = madeby;
	}
	
	public Product(int pno, String pname, int price) {
		this.setPno(pno);
		this.pname = pname;
		this.price = price;
	}
	
	public Product(int pno, String pname, int price, String madeby) {
		this.setPno(pno);
		this.pname = pname;
		this.price = price;
		this.madeby = madeby;
	}

	String getPname() {
		return pname;
	}

	void setPname(String pname) {
		this.pname = pname;
	}

	int getPrice() {
		return price;
	}

	void setPrice(int price) {
		this.price = price;
	}

	String getMadeby() {
		return madeby;
	}

	void setMadeby(String madeby) {
		this.madeby = madeby;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	@Override
	public String toString() {
		return "Product [pno=" + pno + ", pname=" + pname + ", price=" + price + ", madeby=" + madeby + "]";
	}
}
