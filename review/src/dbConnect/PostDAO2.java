package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class PostDAO2 {
	
	public PostVO one(int post_no) {
		
		
		
		ResultSet rs = null; 
		
	
		PostVO bag = null;
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");

			
			 
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); //Connection
			

			
	
			String sql = "select*from hr.POST where POST_NO = ? ";
			PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
			ps.setInt(1, post_no);
			
	
			
			rs = ps.executeQuery();  
			if(rs.next()) { 
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
				
				
			}else {
				System.out.println("검색 결과 없음.");
			}
			
			//System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bag; 
	}
		

public int delete(int post_no) {
	
	int result = 0;
	
	try {

		Class.forName("oracle.jdbc.driver.OracleDriver");


		

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "oracle";
		Connection con = DriverManager.getConnection(url, user, password); 

		
		
		
		String sql = "delete from hr.POST where POST_NO = ? ";
		PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
		ps.setInt(1, post_no);

	
		
		result = ps.executeUpdate(); 
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

		Class.forName("oracle.jdbc.driver.OracleDriver");


		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "oracle";
		Connection con = DriverManager.getConnection(url, user, password);

		
		String sql = "update hr.POST set TITLE = ? where POST_NO = ?";
		PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
		ps.setString(1, bag.getTitle());
		ps.setInt(2, bag.getPost_no());

		
		result = ps.executeUpdate();  
		if (result == 1) {
			System.out.println("수정 성공!");
		}
		//System.out.println(result);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}


public int insert(PostVO2 bag) {

	int result = 0;
	
	try {
	
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "oracle";
		Connection con = DriverManager.getConnection(url, user, password);
		
		
		String sql = "insert into hr.POST values (?,?,?,?,?,SYSDATE,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		
		ps.setInt(1, bag.getPost_no()); // ps.setInt(1,no);
		ps.setString(2, bag.getTitle()); 
		ps.setString(3, bag.getContent()); 
		ps.setString(4, bag.getWriter());
		ps.setInt(5, bag.getScore());
		ps.setString(6, bag.getPlace_code());
		//==> insert into hr.MEMBER values ('a','a','a','a');
		
	
		
		result = ps.executeUpdate();

		if(result == 1){
			System.out.println("글쓰기 성공!!");
		}

	} catch (Exception e) {

		result = 0;
		e.printStackTrace();
	}
	System.out.println(result);
	return result;
	
}



}


