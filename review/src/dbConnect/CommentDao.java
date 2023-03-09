package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import uiConnect.CommentVO;


public class CommentDao {
	public int insert(CommentVO bag) {
		// TODO Auto-generated method stub
		int result=0;
		try {
			// 1.오라클 11g와 연결한 부품 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. success oracle setting");
			
			// 2. 오라클 11g 연결
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			String user="system";
			String password="oracle";
			Connection con =  DriverManager.getConnection(url, user, password);
			DriverManager.getConnection(url,user,password);
			System.out.println("2. success connecting oracle");
			
			//(String id, String pw, String name,String tel)
			String sql = "insert into hr.COMMENT values (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			//con 부품으로 sql 스트링에 있는 거 sql 부품으로 생성
			//유일하게 db는 인덱스가 1부터 시작!
			//2. 가방에서 값들을 하나씩 꺼내 쓰세요.
			
			ps.setInt(1, bag.getComment_no());
			ps.setString(2, bag.getWriter());
			ps.setString(3, bag.getContent());
			ps.setInt(4, bag.getPost_no());
			System.out.println("3. create sql object");
			
			result=ps.executeUpdate();
			System.out.println("4. success sending to sql oracle");
			if(result==1) {
				System.out.println("success insert");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
}
