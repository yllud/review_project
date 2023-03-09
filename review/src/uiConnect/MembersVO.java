package uiConnect;

public class MembersVO {
	//MemberVO 가방에 넣은 데이터는 Member 테이블에 들어갈 예정
	//각 컬럼과 일치시켜 줌.
	private String id;
	private String pw;
	private String name;
	private int age;
	private String mem_addr;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	
	
}
