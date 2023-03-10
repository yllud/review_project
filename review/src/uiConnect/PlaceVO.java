package uiConnect;

public class PlaceVO {

	private String place_code;
	private String place_name;
	private String place_location;
	private String place_grade;
	private String place_category;
	private String place_tel;
	private String place_img;
	
	public String getPlace_code() {
		return place_code;
	}
	
	public void setPlace_code(String place_code) {
		this.place_code = place_code;
	}
	
	public String getPlace_name() {
		return place_name;
	}
	
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	
	public String getPlace_location() {
		return place_location;
	}
	
	public void setPlace_location(String place_location) {
		this.place_location = place_location;
	}
	
	public String getPlace_grade() {
		return place_grade;
	}
	
	public void setPlace_grade(String place_grade) {
		this.place_grade = place_grade;
	}
	
	public String getPlace_category() {
		return place_category;
	}
	
	public void setPlace_category(String place_category) {
		this.place_category = place_category;
	}

	public String getPlace_tel() {
		return place_tel;
	}

	public void setPlace_tel(String place_tel) {
		this.place_tel = place_tel;
	}

	public String getPlace_img() {
		return place_img;
	}

	public void setPlace_img(String place_img) {
		this.place_img = place_img;
	}

	@Override
	public String toString() {
		return "PlaceVO [place_code=" + place_code + ", place_name=" + place_name + ", place_location=" + place_location
				+ ", place_grade=" + place_grade + ", place_category=" + place_category + ", place_tel=" + place_tel
				+ ", place_img=" + place_img + "]";
	}
	
}