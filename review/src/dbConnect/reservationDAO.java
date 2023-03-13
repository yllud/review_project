package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import uiConnect.PostVO;
import uiConnect.reservationVO;

public class reservationDAO { // CRUD

	public int insert(reservationVO bag) {
		// 1. 가방을 받아서 변수에 넣어주세요.
		int result = 0;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); // Connection
			System.out.println("2. 오라클 연결 성공.");

			String sql = "insert into hr.RESERVATION values (?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql); // PreparedStatement

			ps.setInt(1, bag.getR_num());
			ps.setString(2, bag.getR_id());
			ps.setString(3, bag.getR_people());
			ps.setString(4, bag.getR_place());
			ps.setString(5, bag.getR_time());

			System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");

			result = ps.executeUpdate(); // 1
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			if (result == 1) {
				System.out.println("예약 성공!");
			}
			// System.out.println(result);
		} catch (Exception e) {
			// result = 0;
			e.printStackTrace();
		}

		System.out.println(result);
		return result;
	}

	public List<String> place() {
		ResultSet rs = null;
		List<String> pList = new ArrayList<String>();
		Statement stmt;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); // Connection
			System.out.println("DB연결 성공");

			stmt = con.createStatement();
			System.out.println("Statement객체 생성 성공");

			// rs = stmt.executeQuery("select PLACE_CODE from hr.PLACE");
			// rs = stmt.executeQuery("select PLACE_NAME from hr.PLACE");
			rs = stmt.executeQuery("select PLACE_NAME, PLACE_CODE from hr.PLACE");

			while (rs.next()) {
				// int place = rs.getInt(1); // place_code로 검색할 경우
				// String place = rs.getString(1); // place_name으로 검색할 경우
				String place = rs.getString(1) + "(" + rs.getInt(2) + ")"; // place_name, place_code로 검색할 경우
				pList.add(String.valueOf(place));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pList;

	}

	public ArrayList<reservationVO> rList() {
		ResultSet rs = null; // 항목명 + 결과 데이터를 담고 있는 테이블

		// 가방들 넣어줄 큰 컨테이너 역할을 부품이 필요!
		// ArrayList<MemberVO> ==> MemberVO만 들어간 arraylist라는 의미
		ArrayList<reservationVO> list = new ArrayList<>();

		reservationVO bag = null;
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

			// ipaddress ==> InetAddress
			// String url = "http://wwww.naver.com";
			// URL u = new URL(url);
			// 자바는 부품조립식이여서,String에 넣은 문자열을 특정한 부품으로 인식하지 못함.
			// 특정한 부품으로 인식하려면 그 부품으로 만들어주어야 한다.
			// SQL부품으로 만들어주어야 함.
			// PreparedStatement가 SQL부품!!

			String sql = "select * from hr.RESERVATION";
			PreparedStatement ps = con.prepareStatement(sql); // PreparedStatement
			// 삭제!!!! ps.setString(1, id);
			System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");

			rs = ps.executeQuery(); // select문 전송시
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			while (rs.next()) { // 검색결과가 있는지 여부는 rs.next()
				// true이면 있다라는 의미, false이면 없다라는 의미
				// 1. 검색겨로가가 있으면,
				System.out.println("검색결과 있음. 성공.");
				// 2. 각 컬럼에서 값들을 꺼내오자.
				int r_num = rs.getInt(1);
				String r_id = rs.getString(2);
				String r_people = rs.getString(3);
				String r_place = rs.getString(4);
				String r_time = rs.getString(5);
				// 검색결과를 검색화면 UI부분을 주어야 함.
				// 3. 가방을 만들자.
				bag = new reservationVO();
				bag.setR_num(r_num);
				bag.setR_id(r_id);
				bag.setR_people(r_people);
				bag.setR_place(r_place);
				bag.setR_time(r_time);

				// 4. list에 bag을 추가해주자.
				list.add(bag);
			}
			ps.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public reservationVO no_max() {

		ResultSet rs = null;
		reservationVO bag = null;
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); // Connection
			System.out.println("2. 오라클 연결 성공");

			String sql = "select max(R_NUM) from hr.RESERVATION";
			PreparedStatement ps = con.prepareStatement(sql); // PreparedStatement
			// ps.setInt(1, );
			System.out.println("3. r_no_max SQL문 부품(객체)으로 만들어주기");

			rs = ps.executeQuery();

			System.out.println("4. SQL문 오라클로 보내기 성공");
			if (rs.next()) {
				int max_no = rs.getInt(1);
				System.out.println(max_no);
				bag = new reservationVO();
				bag.setMax_no(max_no);

			} else {
				System.out.println("검색 결과 없음.");
			}

			// System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bag;
	}

}
