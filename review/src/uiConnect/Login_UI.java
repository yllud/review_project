package uiConnect;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dbConnect.MembersDAO;




public class Login_UI {
	
	private static String id;
	
	public static String getId() {
		return id;
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(500,700);
		f.setTitle("로그인 화면");
		FlowLayout flow = new FlowLayout();
		f.setLayout(flow);
		
		Font font1 = new Font("D2Coding",Font.BOLD,40);
		Font font2 = new Font("D2Coding",Font.BOLD,80);
	
		
		JLabel l1 = new JLabel("아이디");
		JLabel l2 = new JLabel("패스워드");
		
		JTextField t1 = new JTextField(10);
		JTextField t2 = new JTextField(10);
		
		JButton b1 = new JButton("로그인");
		JButton b2 = new JButton("회원 가입 및 탈퇴");
		JButton b3 = new JButton("비밀번호 찾기");
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				id = t1.getText();
				String pw = t2.getText();
				
				MembersDAO dao= new MembersDAO();
				MembersVO bag = new MembersVO();
				
				bag.setId(id);
				bag.setPw(pw);
				int result= dao.login(bag);
				if(result==0) {
					JOptionPane.showMessageDialog(null, "로그인 실패");
				}else {
					JOptionPane.showMessageDialog(null, "로그인 성공");
				}
				
			}
		});
		
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Signin sin = new Signin();
				sin.signin();
				
			}
		});
		
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("비밀번호 찾기");
				String id = t1.getText();

				MembersDAO dao = new MembersDAO();
				MembersVO bag = dao.one(id);
				
				if (bag != null) {
					t2.setText(bag.getPw());
					t2.setBackground(Color.pink);
					
				} else {
					t2.setText("");
					JOptionPane.showMessageDialog(f, "검색결과없음");
				}
			} 
		});
		
	
		
		l1.setFont(font1);
		l2.setFont(font1);
		t1.setFont(font1);
		t2.setFont(font1);
		b1.setFont(font2);
		b2.setFont(font1);
		b3.setFont(font1);
		

		f.add(l1);
		f.add(t1);
		f.add(l2);
		f.add(t2);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		
		
		
		f.setVisible(true);
	}

}
