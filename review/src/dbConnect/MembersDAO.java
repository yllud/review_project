package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import uiConnect.MembersVO;
import uiConnect.PlaceUI;
import uiConnect.PostVO;

public class MembersDAO {
	
	public int insert(MembersVO bag) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.오라클과 자바 연결할 부품 설정 성공");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("2.오라클 연결 성공.");

			String sql = "insert into hr.MEMBERS values (?, ?, ?, ?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			
			//가방에서 값들을 하나씩 꺼내 쓰세요.
			ps.setString(1, bag.getId());
			ps.setString(2, bag.getPw());
			ps.setString(3, bag.getName());
			ps.setInt(4, bag.getAge());
			ps.setString(5, bag.getMem_addr());

			System.out.println("3.SQL문 객체로 만들어주기");

			result =ps.executeUpdate();
			System.out.println("4.SQL문 전송 성공");
			if(result ==1) {
				System.out.println("회원가입 성공!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int delete(MembersVO bag) {
		int result = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.오라클과 자바 연결할 부품 설정 성공");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("2.오라클 연결 성공.");

			String sql = "delete from hr.MEMBERS where id =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,bag.getId());
			System.out.println("3.SQL문 객체로 만들어주기");

			result=ps.executeUpdate();
			System.out.println("4.SQL문 전송 성공");
			if(result >=1) {
				System.out.println("회원탈퇴 성공!");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int update(MembersVO bag) {
		int result=0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.오라클과 자바 연결할 부품 설정 성공");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("2.오라클 연결 성공.");

			String sql = "update hr.MEMBERS set mem_addr =? where id =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bag.getMem_addr());
			ps.setString(2, bag.getId());
			System.out.println("3.SQL문 객체로 만들어주기");
			result = ps.executeUpdate();
			System.out.println("4.SQL문 전송 성공");
			if(result >=1) {
				System.out.println("회원정보 수정 성공!");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public MembersVO one(String id) {
		ResultSet rs = null; //항목명+결과 데이터를 담고 있는 테이블
		MembersVO bag = null;
		try {
			// 1.오라클 11g와 연결한 부품 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");
			
			
			// 2.오라클 11g에 연결해보자.(java --- oracle) 
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); //Connection
			//String data = JOptionPane.showInputDialog("이름입력"); //String, 임아무개 
			System.out.println("2. 오라클 연결 성공.");
			
			
			String sql = "select * from hr.MEMBERS where id = ? ";
			PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
			ps.setString(1, id);
			System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");
			
			rs = ps.executeQuery(); 
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			if(rs.next()) {
				System.out.println("검색 결과 있음 성공.");
			
				String id2= rs.getString(1);
				String pw= rs.getString(2);
				String name= rs.getString(3);
				int age = Integer.parseInt(rs.getString(4));
				String mem_addr = rs.getString(5);
				
				
				System.out.println(id2+" "+pw);
				bag = new MembersVO();
				bag.setId(id2);
				bag.setPw(pw);
				bag.setName(name);;
				bag.setAge(age);
				bag.setMem_addr(mem_addr);
				
				
				
			}else {
				System.out.println("검색 결과 없음.");
			}
			//System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bag;
		
	}
	
	public int login(MembersVO bag) {
		int result=0;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.오라클과 자바 연결할 부품 설정 성공");

			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password);
			System.out.println("2.오라클 연결 성공.");

			String sql = "select * from hr.members where id =? and pw =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bag.getId());
			ps.setString(2, bag.getPw());
			System.out.println("3.SQL문 객체로 만들어주기");
			rs = ps.executeQuery();
			System.out.println("4.SQL문 전송 성공");
			if(rs.next()) {
				System.out.println("로그인 성공");
				result=1;
				
			}else {
				System.out.println("로그인 실패");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	public ArrayList<MembersVO> list() {
		ResultSet rs = null; //항목명+결과 데이터를 담고 있는 테이블
		
		ArrayList<MembersVO> list=new ArrayList<>();
		MembersVO bag = null;
		try {
			// 1.오라클 11g와 연결한 부품 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");
			
			
			// 2.오라클 11g에 연결해보자.(java --- oracle) 
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); //Connection
			//String data = JOptionPane.showInputDialog("이름입력"); //String, 임아무개 
			System.out.println("2. 오라클 연결 성공.");
			
			
			String sql = "select * from hr.members";
			PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
			//ps.setString(1, id);
			System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");
			
			rs = ps.executeQuery(); 
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			while(rs.next()) {
				System.out.println("검색 결과 있음 성공.");
				
				String id =rs.getString(1);
				String pw=rs.getString(2);
				String name=rs.getString(3);
				int age= rs.getInt(4);
				String mem_addr= rs.getString(5);
		
				bag = new MembersVO();
				bag.setId(id);
				bag.setPw(pw);
				bag.setName(name);
				bag.setAge(age);
				bag.setMem_addr(mem_addr);
				
				list.add(bag);
				
			}
			//System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	}
}
