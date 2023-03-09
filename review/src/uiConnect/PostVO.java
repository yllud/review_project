package uiConnect;

public class PostVO {
	private int post_no;
	private String title;
	private String content;
	private String writer;
	private int score;
	private String place_code;

	public int getPost_no() {
		return post_no;
	}

	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getPlace_code() {
		return place_code;
	}

	public void setPlace_code(String place_code) {
		this.place_code = place_code;
	}

	@Override
	public String toString() {
		return "PostVO [post_no=" + post_no + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", score=" + score + ", place_code=" + place_code + "]";
	}

}
