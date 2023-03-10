package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import uiConnect.CommentVO;
import uiConnect.PostVO;
import uiConnect.Post_UI1;

public class CommentDao {
	public int insert(CommentVO bag) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			// 1.오라클 11g와 연결한 부품 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. success oracle setting");

			// 2. 오라클 11g 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password);
			DriverManager.getConnection(url, user, password);
			System.out.println("2. success connecting oracle");

			// (String id, String pw, String name,String tel)
			String sql = "insert into hr.COM values (?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			// con 부품으로 sql 스트링에 있는 거 sql 부품으로 생성
			// 유일하게 db는 인덱스가 1부터 시작!
			// 2. 가방에서 값들을 하나씩 꺼내 쓰세요.

			ps.setInt(1, bag.getComment_no());
			ps.setString(2, bag.getWriter());
			ps.setInt(3, bag.getPost_no());
			ps.setString(4, bag.getContent());

			System.out.println(bag.getComment_no());
			System.out.println(bag.getWriter());
			System.out.println(bag.getPost_no());
			System.out.println(bag.getContent());

			System.out.println("3. create sql object");

			result = ps.executeUpdate();
			System.out.println("4. success sending to sql oracle");
			if (result == 1) {
				System.out.println("success insert");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}

	public ArrayList<CommentVO> list() {
		ResultSet rs = null; // 항목명+결과 데이터를 담고 있는 테이블

		ArrayList<CommentVO> list = new ArrayList<>();
		CommentVO bag = null;
		try {
			// 1.오라클 11g와 연결한 부품 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");

			// 2.오라클 11g에 연결해보자.(java --- oracle)
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); // Connection
			// String data = JOptionPane.showInputDialog("이름입력"); //String, 임아무개
			System.out.println("2. 오라클 연결 성공.");

			Post_UI1 pu = new Post_UI1();
			String post_no = String.valueOf(pu.getPost_no());
			String sql = "select * from hr.COM where post_no=" + post_no + " order by comment_no";
			PreparedStatement ps = con.prepareStatement(sql); // PreparedStatement
			// ps.setString(1, id);
			System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");

			rs = ps.executeQuery();
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			while (rs.next()) {
				System.out.println("검색 결과 있음 성공.");

				int comment_no = rs.getInt(1);
				String writer = rs.getString(2);
				int post_no2 = rs.getInt(3);
				String content = rs.getString(4);

				System.out.println(comment_no + " " + writer + " " + post_no2 + " " + content);

				bag = new CommentVO();
				bag.setComment_no(comment_no);
				bag.setWriter(writer);
				bag.setPost_no(post_no2);
				bag.setContent(content);

				list.add(bag);

			}
			// System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}
	
	public ArrayList<CommentVO> listOrder() {
		ResultSet rs = null; // 항목명+결과 데이터를 담고 있는 테이블

		ArrayList<CommentVO> list = new ArrayList<>();
		CommentVO bag = null;
		try {
			// 1.오라클 11g와 연결한 부품 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");

			// 2.오라클 11g에 연결해보자.(java --- oracle)
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); // Connection
			// String data = JOptionPane.showInputDialog("이름입력"); //String, 임아무개
			System.out.println("2. 오라클 연결 성공.");

			Post_UI1 pu = new Post_UI1();
			
			String sql = "select * from hr.COM order by comment_no desc";
			PreparedStatement ps = con.prepareStatement(sql); // PreparedStatement
			// ps.setString(1, id);
			System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");

			rs = ps.executeQuery();
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			while (rs.next()) {
				System.out.println("검색 결과 있음 성공.");

				int comment_no = rs.getInt(1);
				String writer = rs.getString(2);
				int post_no2 = rs.getInt(3);
				String content = rs.getString(4);

				System.out.println(comment_no + " " + writer + " " + post_no2 + " " + content);

				bag = new CommentVO();
				bag.setComment_no(comment_no);
				bag.setWriter(writer);
				bag.setPost_no(post_no2);
				bag.setContent(content);

				list.add(bag);

			}
			// System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	
}
