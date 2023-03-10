package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import uiConnect.PlaceVO;

public class PlaceDAO {
	
	public PlaceVO one(String name) {
		ResultSet rs = null;

		PlaceVO bag = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 사용할 드라이버
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); // Connection
			System.out.println("2. 오라클 연결 성공");

			String sql = "select * from hr.PLACE where PLACE_NAME = ?";
			PreparedStatement ps = con.prepareStatement(sql); // PreparedStatement
			ps.setString(1, name);
			System.out.println("3. SQL문 부품(객체)으로 만들어주기");

			rs = ps.executeQuery(); // select문 전송시
			System.out.println("4. SQL문 오라클로 보내기 성공");
			if (rs.next()) { // 검색결과가 있는지 여부는 res.next()
				// true이면 있다라는 의미, false이면 없다라는 의미
				System.out.println("검색결과 있음. 성공");
				String place_code=rs.getString(1);
				String place_name2 = rs.getString(2);
				String place_location = rs.getString(3);
				String place_grade = rs.getString(4);
				String place_category = rs.getString(5);
				String place_tel = rs.getString(6);
				String place_img = rs.getString(7);
				System.out.println(place_name2 + " " + place_location + " " + place_grade + " " + place_category + " "
						+ place_tel + " " + place_img);
				// 검색결과를 검색화면 UI부분을 주어야함
				bag = new PlaceVO();
				bag.setPlace_code(place_code);
				bag.setPlace_name(place_name2);
				bag.setPlace_location(place_location);
				bag.setPlace_grade(place_grade);
				bag.setPlace_category(place_category);
				bag.setPlace_tel(place_tel);
				bag.setPlace_img(place_img);
			} else {
				System.out.println("검색결과 없음. 성공.");
			}

		} catch (Exception e) { // 모든 예외처리 해달라
			e.printStackTrace();
		}
		// return 뒤에는 반드시 여러 데이터를 묶어서 리턴해주어야함
		return bag;
	}

	public int update(PlaceVO bag) {
		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 사용할 드라이버
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); // Connection
			System.out.println("2. 오라클 연결 성공");

			String sql = "update hr.PLACE set PLACE_LOCATION = ?, PLACE_GRADE = ?, PLACE_CATEGORY = ?, PLACE_TEL = ? where PLACE_NAME = ?";
			PreparedStatement ps = con.prepareStatement(sql); // PreparedStatement
			
			ps.setString(1, bag.getPlace_location());
			ps.setString(2, bag.getPlace_grade());
			ps.setString(3, bag.getPlace_category());
			ps.setString(4, bag.getPlace_tel());
			ps.setString(5, bag.getPlace_name());

			System.out.println("3. SQL문 부품(객체)으로 만들어주기");

			result = ps.executeUpdate(); // select문 전송시
			System.out.println("4. SQL문 오라클로 보내기 성공");
			if (result >= 1) {
				System.out.println("수정할 항목 있음. 성공");
				
			} else {
				System.out.println("수정할 항목 없음. 성공.");
			}

		} catch (Exception e) { // 모든 예외처리 해달라
			result = 0;
			e.printStackTrace();
		}
		// return 뒤에는 반드시 여러 데이터를 묶어서 리턴해주어야함
		return result;
	}

	public int del(String name) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 사용할 드라이버
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); // Connection
			System.out.println("2. 오라클 연결 성공");

			String sql = "delete from hr.PLACE where PLACE_NAME = ?";
			PreparedStatement ps = con.prepareStatement(sql); // PreparedStatement
			ps.setString(1, name);
			System.out.println("3. SQL문 부품(객체)으로 만들어주기");

			result = ps.executeUpdate(); // select문 전송시
			System.out.println("4. SQL문 오라클로 보내기 성공");
			if (result >= 1) { // 검색결과가 있는지 여부는 res.next()
				System.out.println("회원탈퇴 성공.");
			} else {
				System.out.println("탈퇴할 회원정보 없음. 성공.");
			}

		} catch (Exception e) { // 모든 예외처리 해달라
			e.printStackTrace();
		}
		// return 뒤에는 반드시 여러 데이터를 묶어서 리턴해주어야함
		return result;
	}

}