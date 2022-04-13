package domain;

public class CommentVO {
	
	// CommentVO에서 comment의 detail은 없습니다
	// list에서 모든 정보를 보이게 할 예정
	
	private long cno;
	private long pno;
	private String writer;
	private String content;
	private String regAt;
	private String modAt;
	
	public CommentVO() {}
	
	// 댓글 등록용 생성자
	public CommentVO(long pno, String writer, String content) {
		this.pno = pno;
		this.writer = writer;
		this.content = content;
	}

	// 댓글 수정용 생성자
	public CommentVO(long cno, String content) {
		this.cno = cno;
		this.content = content;
	}

	// 댓글 리스트용 생성자
	public CommentVO(long cno, long pno, String writer, String content, String modAt) {
		this.cno = cno;
		this.pno = pno;
		this.writer = writer;
		this.content = content;
		this.modAt = modAt;
	}

	public long getCno() {
		return cno;
	}

	public void setCno(long cno) {
		this.cno = cno;
	}

	public long getPno() {
		return pno;
	}

	public void setPno(long pno) {
		this.pno = pno;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegAt() {
		return regAt;
	}

	public void setRegAt(String regAt) {
		this.regAt = regAt;
	}

	public String getModAt() {
		return modAt;
	}

	public void setModAt(String modAt) {
		this.modAt = modAt;
	}

	@Override
	public String toString() {
		return "CommentVO [cno=" + cno + ", pno=" + pno + ", writer=" + writer + ", content=" + content + ", regAt="
				+ regAt + ", modAt=" + modAt + "]";
	}
}
