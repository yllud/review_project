package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class PostDAO2 {
	
	public PostVO one(int post_no) {
		
		
		
		ResultSet rs = null; // 항목(=필드 ,컬럼,속성)명 + 결과데이터를 담고 있는 테이블
		//기본형 정수/실수/문자/논리만 값(0)으로 초기화
		//나머지 데이터형(참조형) 주소가! 들어가있음 (null) 로 초기화
		
		// MemberVO bag 의 쓰레기값 초기화 (null)
		PostVO bag = null;
		try {
			// 1.오라클 11g와 연결한 부품 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");
//			Locale.setDefault(Locale.US); //맥 locale에러나신 분들만!!!
			
			// 2.오라클 11g에 연결해보자.(java --- oracle) 
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); //Connection
			//String data = JOptionPane.showInputDialog("이름입력"); //String, 임아무개 
			System.out.println("2. 오라클 연결 성공.");
			
			//ipaddress ==> InetAddress
			//String url = "http://wwww.naver.com";
			//URL u = new URL(url);
			//자바는 부품조립식이여서,String에 넣은 문자열을 특정한 부품으로 인식하지 못함.
			//특정한 부품으로 인식하려면 그 부품으로 만들어주어야 한다.
			//SQL부품으로 만들어주어야 함.
			//PreparedStatement가 SQL부품!!
			
			String sql = "select*from hr.POST where POST_NO = ? ";
			PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
			ps.setInt(1, post_no);
			//con부품으로 sql스트링에 있는 것 SQL부품으로 만들어주세요.
			System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");
			
			rs = ps.executeQuery();  //insert, update, delete문만!! sql문 실행결과가 int
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			if(rs.next()) { // 검색 결과가 있는 지 여부는 rs.next() 로 한다
				// true 이면 있다 라는 의미 , false 이면 없다 라는 의미.
				System.out.println("검색 결과 존재.");
				int post_no1 = rs.getInt(1); 
				String title = rs.getString(2);
				String content = rs.getString(3);
				String writer = rs.getString(4);			
				int score = rs.getInt(5);
				String write_date = rs.getString(6);
				String place_code = rs.getString(7);
				System.out.println(post_no1 + " "+ title + " "+ content + " "+ writer + " "
						+ score + " " + write_date + " " + place_code);
				bag = new PostVO();
				bag.setPost_no(post_no1);
				bag.setTitle(title);
				bag.setContent(content);
				bag.setWriter(writer);
				bag.setScore(score);
				bag.setPlace_code(place_code);
				// 검색결과를 검색화면 UI 부분으로 주어야한다.
				
			}else {
				System.out.println("검색 결과 없음");
			}
			
			//System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return id, pw,name,tel 은 XXXXXX!
		//return 뒤에는 반드시 여러 데이터를 묶어서 리턴해주어야함
		//검색결과가 있을때는 bag에 데이터가 들어있음.
		//검색결과 없을 때는 bag에 무엇이 들어있나?? Null!!
		return bag; // bag 타입을 public MemberVO 변수명(검색 원하는 변수선언) 으로 설정해줘야함!!
	}
		

public int delete(int post_no) {
	
	int result = 0;
	
	try {
		// 1.오라클 11g와 연결한 부품 설정
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");
//		Locale.setDefault(Locale.US); //맥 locale에러나신 분들만!!!
		
		// 2.오라클 11g에 연결해보자.(java --- oracle) 
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "oracle";
		Connection con = DriverManager.getConnection(url, user, password); //Connection
		//String data = JOptionPane.showInputDialog("이름입력"); //String, 임아무개 
		System.out.println("2. 오라클 연결 성공.");
		
		//ipaddress ==> InetAddress
		//String url = "http://wwww.naver.com";
		//URL u = new URL(url);
		//자바는 부품조립식이여서,String에 넣은 문자열을 특정한 부품으로 인식하지 못함.
		//특정한 부품으로 인식하려면 그 부품으로 만들어주어야 한다.
		//SQL부품으로 만들어주어야 함.
		//PreparedStatement가 SQL부품!!
		
		String sql = "delete from hr.POST where POST_NO = ? ";
		PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
		ps.setInt(1, post_no);
		//con부품으로 sql스트링에 있는 것 SQL부품으로 만들어주세요.
		System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");
		
		result = ps.executeUpdate();  //insert, update, delete문만!! sql문 실행결과가 int
		System.out.println("4. SQL문 오라클로 보내기 성공.");
		if(result == 1) {
			System.out.println("탈퇴 성공.");
		}
		
		//System.out.println(result);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}

public int update(PostVO2 bag) {
	
	int result = 0;
	
	try {
		// 1.오라클 11g와 연결한 부품 설정
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");
//		Locale.setDefault(Locale.US); //맥 locale에러나신 분들만!!!
		
		// 2.오라클 11g에 연결해보자.(java --- oracle) 
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "oracle";
		Connection con = DriverManager.getConnection(url, user, password); //Connection
		//String data = JOptionPane.showInputDialog("이름입력"); //String, 임아무개 
		System.out.println("2. 오라클 연결 성공.");
		
		//ipaddress ==> InetAddress
		//String url = "http://wwww.naver.com";
		//URL u = new URL(url);
		//자바는 부품조립식이여서,String에 넣은 문자열을 특정한 부품으로 인식하지 못함.
		//특정한 부품으로 인식하려면 그 부품으로 만들어주어야 한다.
		//SQL부품으로 만들어주어야 함.
		//PreparedStatement가 SQL부품!!
		
		String sql = "update hr.POST set TITLE = ? where POST_NO = ?";
		PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
		ps.setString(1, bag.getTitle());
		ps.setInt(2, bag.getPost_no());
		//con부품으로 sql스트링에 있는 것 SQL부품으로 만들어주세요.
		System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");
		
		result = ps.executeUpdate();  //insert, update, delete문만!! sql문 실행결과가 int
		System.out.println("4. SQL문 오라클로 보내기 성공.");
		if (result == 1) {
			System.out.println("회원 수정 성공!");
		}
		//System.out.println(result);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}


public int insert(PostVO2 bag) {
	// 1. 가방을 받아서 변수에 넣어주세요. MemberVO bag
	int result = 0;
	
	try {
		// 1. 오라클 11g 와 연결한 부품 설정
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");
		
		// 2. 오라클 11g 에 연결해보자 (java --- oracle)
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "oracle";
		Connection con = DriverManager.getConnection(url, user, password);//Connection
		//String data = JOptionPane.showInputDialog("이름입력"); //String, 임아무개 
		System.out.println("2.오라클 연결 성공.");
		
		//ipaddress ==> InetAddress
		//String url = "http://wwww.naver.com";
		//URL u = new URL(url);
		//자바는 부품조립식이여서,String에 넣은 문자열을 특정한 부품으로 인식하지 못함.
		//특정한 부품으로 인식하려면 그 부품으로 만들어주어야 한다.
		//SQL부품으로 만들어주어야 함.
		//PreparedStatement가 SQL부품!!
		
		String sql = "insert into hr.POST values (?,?,?,?,?,SYSDATE,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		//con 부품으로 sql 스트링에 있는것 SQL 부품으로 만들어주세요
		//R 빼고, 인덱스 0부터 시작!!
		//유일하게 db는 인덱스가 1부터 시작
		
		//2. 가방에서 값들을 하나씩 꺼내쓰세요
		
		ps.setInt(1, bag.getPost_no()); // ps.setInt(1,no);
		ps.setString(2, bag.getTitle()); 
		ps.setString(3, bag.getContent()); 
		ps.setString(4, bag.getWriter());
		ps.setInt(5, bag.getScore());
		ps.setString(6, bag.getPlace_code());
		//==> insert into hr.MEMBER values ('a','a','a','a');
		
		System.out.println("3.SQL 문 부품(객체)으로 만들어주기");
		
		result = ps.executeUpdate();
		System.out.println("4.SQL문 오라클로 보내기 성공!");
		if(result == 1){
			System.out.println("게시물작성성공!!");
		}
//		System.out.println(result);
	} catch (Exception e) {
		// insert 가 제대로 실행이 안된 경우, 위험한 상황이라 판단!
		// catch가 실행된다.
		result = 0;
		e.printStackTrace();
	}
	System.out.println(result);
	return result;
	
}



}


