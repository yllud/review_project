package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class PostDAO2 {
	
	public PostVO one(int post_no) {
		
		
		
		ResultSet rs = null; // �׸�(=�ʵ� ,�÷�,�Ӽ�)�� + ��������͸� ��� �ִ� ���̺�
		//�⺻�� ����/�Ǽ�/����/���� ��(0)���� �ʱ�ȭ
		//������ ��������(������) �ּҰ�! ������ (null) �� �ʱ�ȭ
		
		// MemberVO bag �� �����Ⱚ �ʱ�ȭ (null)
		PostVO bag = null;
		try {
			// 1.����Ŭ 11g�� ������ ��ǰ ����
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. ����Ŭ�� �ڹ� ������ ��ǰ ���� ����.");
//			Locale.setDefault(Locale.US); //�� locale�������� �е鸸!!!
			
			// 2.����Ŭ 11g�� �����غ���.(java --- oracle) 
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); //Connection
			//String data = JOptionPane.showInputDialog("�̸��Է�"); //String, �Ӿƹ��� 
			System.out.println("2. ����Ŭ ���� ����.");
			
			//ipaddress ==> InetAddress
			//String url = "http://wwww.naver.com";
			//URL u = new URL(url);
			//�ڹٴ� ��ǰ�������̿���,String�� ���� ���ڿ��� Ư���� ��ǰ���� �ν����� ����.
			//Ư���� ��ǰ���� �ν��Ϸ��� �� ��ǰ���� ������־�� �Ѵ�.
			//SQL��ǰ���� ������־�� ��.
			//PreparedStatement�� SQL��ǰ!!
			
			String sql = "select*from hr.POST where POST_NO = ? ";
			PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
			ps.setInt(1, post_no);
			//con��ǰ���� sql��Ʈ���� �ִ� �� SQL��ǰ���� ������ּ���.
			System.out.println("3. SQL�� ��ǰ(��ü)���� ������ֱ� ����.");
			
			rs = ps.executeQuery();  //insert, update, delete����!! sql�� �������� int
			System.out.println("4. SQL�� ����Ŭ�� ������ ����.");
			if(rs.next()) { // �˻� ����� �ִ� �� ���δ� rs.next() �� �Ѵ�
				// true �̸� �ִ� ��� �ǹ� , false �̸� ���� ��� �ǹ�.
				System.out.println("�˻� ��� ����.");
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
				// �˻������ �˻�ȭ�� UI �κ����� �־���Ѵ�.
				
			}else {
				System.out.println("�˻� ��� ����");
			}
			
			//System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return id, pw,name,tel �� XXXXXX!
		//return �ڿ��� �ݵ�� ���� �����͸� ��� �������־����
		//�˻������ �������� bag�� �����Ͱ� �������.
		//�˻���� ���� ���� bag�� ������ ����ֳ�?? Null!!
		return bag; // bag Ÿ���� public MemberVO ������(�˻� ���ϴ� ��������) ���� �����������!!
	}
		

public int delete(int post_no) {
	
	int result = 0;
	
	try {
		// 1.����Ŭ 11g�� ������ ��ǰ ����
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("1. ����Ŭ�� �ڹ� ������ ��ǰ ���� ����.");
//		Locale.setDefault(Locale.US); //�� locale�������� �е鸸!!!
		
		// 2.����Ŭ 11g�� �����غ���.(java --- oracle) 
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "oracle";
		Connection con = DriverManager.getConnection(url, user, password); //Connection
		//String data = JOptionPane.showInputDialog("�̸��Է�"); //String, �Ӿƹ��� 
		System.out.println("2. ����Ŭ ���� ����.");
		
		//ipaddress ==> InetAddress
		//String url = "http://wwww.naver.com";
		//URL u = new URL(url);
		//�ڹٴ� ��ǰ�������̿���,String�� ���� ���ڿ��� Ư���� ��ǰ���� �ν����� ����.
		//Ư���� ��ǰ���� �ν��Ϸ��� �� ��ǰ���� ������־�� �Ѵ�.
		//SQL��ǰ���� ������־�� ��.
		//PreparedStatement�� SQL��ǰ!!
		
		String sql = "delete from hr.POST where POST_NO = ? ";
		PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
		ps.setInt(1, post_no);
		//con��ǰ���� sql��Ʈ���� �ִ� �� SQL��ǰ���� ������ּ���.
		System.out.println("3. SQL�� ��ǰ(��ü)���� ������ֱ� ����.");
		
		result = ps.executeUpdate();  //insert, update, delete����!! sql�� �������� int
		System.out.println("4. SQL�� ����Ŭ�� ������ ����.");
		if(result == 1) {
			System.out.println("Ż�� ����.");
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
		// 1.����Ŭ 11g�� ������ ��ǰ ����
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("1. ����Ŭ�� �ڹ� ������ ��ǰ ���� ����.");
//		Locale.setDefault(Locale.US); //�� locale�������� �е鸸!!!
		
		// 2.����Ŭ 11g�� �����غ���.(java --- oracle) 
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "oracle";
		Connection con = DriverManager.getConnection(url, user, password); //Connection
		//String data = JOptionPane.showInputDialog("�̸��Է�"); //String, �Ӿƹ��� 
		System.out.println("2. ����Ŭ ���� ����.");
		
		//ipaddress ==> InetAddress
		//String url = "http://wwww.naver.com";
		//URL u = new URL(url);
		//�ڹٴ� ��ǰ�������̿���,String�� ���� ���ڿ��� Ư���� ��ǰ���� �ν����� ����.
		//Ư���� ��ǰ���� �ν��Ϸ��� �� ��ǰ���� ������־�� �Ѵ�.
		//SQL��ǰ���� ������־�� ��.
		//PreparedStatement�� SQL��ǰ!!
		
		String sql = "update hr.POST set TITLE = ? where POST_NO = ?";
		PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
		ps.setString(1, bag.getTitle());
		ps.setInt(2, bag.getPost_no());
		//con��ǰ���� sql��Ʈ���� �ִ� �� SQL��ǰ���� ������ּ���.
		System.out.println("3. SQL�� ��ǰ(��ü)���� ������ֱ� ����.");
		
		result = ps.executeUpdate();  //insert, update, delete����!! sql�� �������� int
		System.out.println("4. SQL�� ����Ŭ�� ������ ����.");
		if (result == 1) {
			System.out.println("ȸ�� ���� ����!");
		}
		//System.out.println(result);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}


public int insert(PostVO2 bag) {
	// 1. ������ �޾Ƽ� ������ �־��ּ���. MemberVO bag
	int result = 0;
	
	try {
		// 1. ����Ŭ 11g �� ������ ��ǰ ����
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("1. ����Ŭ�� �ڹ� ������ ��ǰ ���� ����.");
		
		// 2. ����Ŭ 11g �� �����غ��� (java --- oracle)
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "system";
		String password = "oracle";
		Connection con = DriverManager.getConnection(url, user, password);//Connection
		//String data = JOptionPane.showInputDialog("�̸��Է�"); //String, �Ӿƹ��� 
		System.out.println("2.����Ŭ ���� ����.");
		
		//ipaddress ==> InetAddress
		//String url = "http://wwww.naver.com";
		//URL u = new URL(url);
		//�ڹٴ� ��ǰ�������̿���,String�� ���� ���ڿ��� Ư���� ��ǰ���� �ν����� ����.
		//Ư���� ��ǰ���� �ν��Ϸ��� �� ��ǰ���� ������־�� �Ѵ�.
		//SQL��ǰ���� ������־�� ��.
		//PreparedStatement�� SQL��ǰ!!
		
		String sql = "insert into hr.POST values (?,?,?,?,?,SYSDATE,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		//con ��ǰ���� sql ��Ʈ���� �ִ°� SQL ��ǰ���� ������ּ���
		//R ����, �ε��� 0���� ����!!
		//�����ϰ� db�� �ε����� 1���� ����
		
		//2. ���濡�� ������ �ϳ��� ����������
		
		ps.setInt(1, bag.getPost_no()); // ps.setInt(1,no);
		ps.setString(2, bag.getTitle()); 
		ps.setString(3, bag.getContent()); 
		ps.setString(4, bag.getWriter());
		ps.setInt(5, bag.getScore());
		ps.setString(6, bag.getPlace_code());
		//==> insert into hr.MEMBER values ('a','a','a','a');
		
		System.out.println("3.SQL �� ��ǰ(��ü)���� ������ֱ�");
		
		result = ps.executeUpdate();
		System.out.println("4.SQL�� ����Ŭ�� ������ ����!");
		if(result == 1){
			System.out.println("�Խù��ۼ�����!!");
		}
//		System.out.println(result);
	} catch (Exception e) {
		// insert �� ����� ������ �ȵ� ���, ������ ��Ȳ�̶� �Ǵ�!
		// catch�� ����ȴ�.
		result = 0;
		e.printStackTrace();
	}
	System.out.println(result);
	return result;
	
}



}


