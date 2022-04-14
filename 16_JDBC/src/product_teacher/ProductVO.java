package product_teacher;

public class ProductVO {
	private int pno;
	private String pname;
	private int price;
	private String regDate;
	private String madeBy;
	
	public ProductVO() {}
	
	// insert용 생성자
	public ProductVO(String pname, int price, String madeBy) {
		this.pname = pname;
		this.price = price;
		this.madeBy = madeBy;
	}

	// list용 생성자
	public ProductVO(int pno, String pname, int price) {
		this.pno = pno;
		this.pname = pname;
		this.price = price;
	}

	// update용 생성자
	public ProductVO(int pno, String pname, int price, String madeBy) {
		this(pno, pname, price);
		this.madeBy = madeBy;
	}

	public ProductVO(int pno, String pname, int price, String regDate, String madeBy) {
		this(pno, pname, price, madeBy);
		this.regDate = regDate;
	}

	int getPno() {
		return pno;
	}

	void setPno(int pno) {
		this.pno = pno;
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

	String getRegDate() {
		return regDate;
	}

	void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	String getMadeBy() {
		return madeBy;
	}

	void setMadeBy(String madeBy) {
		this.madeBy = madeBy;
	}

	@Override
	public String toString() {
		return "ProductVO [pno=" + pno + ", pname=" + pname + ", price=" + price + ", regDate=" + regDate + ", madeBy="
				+ madeBy + "]";
	}
}
