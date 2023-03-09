package uiConnect;

public class CommentVO {
	private int comment_no;
	private String writer;
	private String content;
	private int post_no;
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
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
	public int getPost_no() {
		return post_no;
	}
	public void setPost_no(int post_no) {
		this.post_no = post_no;
	}
	@Override
	public String toString() {
		return "CommentVO [comment_no=" + comment_no + ", writer=" + writer + ", content=" + content + ", post_no="
				+ post_no + "]";
	}
	
	
	
	
}
