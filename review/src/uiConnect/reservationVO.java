package uiConnect;

public class reservationVO {
	private int r_num;
	private String r_id;
	private String r_people;
	private String r_place;
	private String r_time;
	private int max_no;
	
	public int getMax_no() {
		return max_no;
	}
	public void setMax_no(int max_no) {
		this.max_no = max_no;
	}
	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	public String getR_id() {
		return r_id;
	}
	public void setR_id(String r_id) {
		this.r_id = r_id;
	}
	public String getR_people() {
		return r_people;
	}
	public void setR_people(String r_people) {
		this.r_people = r_people;
	}
	public String getR_place() {
		return r_place;
	}
	public void setR_place(String rplace) {
		this.r_place = rplace;
	}
	public String getR_time() {
		return r_time;
	}
	public void setR_time(String r_time) {
		this.r_time = r_time;
	}
	
	@Override
	public String toString() {
		return "reservationVO [r_num=" + r_num + ", r_id=" + r_id + ", r_people=" + r_people + ", r_place=" + r_place
				+ ", r_time=" + r_time + "]";
	}
}
