package uiConnect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dbConnect.MembersDAO;

public class Main {

private static String id;
	
	public static String getId() {
		return id;
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(500,700);
		f.setTitle("로그인 화면");
		f.getContentPane().setBackground(new Color(250,245,224));
		f.setLayout(null);
		
		JPanel p1 = new JPanel();
		p1.setBounds(0,0,500,700);
		p1.setBackground(new Color(250,245,224));
		
		Font font1 = new Font("돋움",Font.BOLD,40);
		
		JTextField t1 = new JTextField(10);
		t1.setText("아이디");
		t1.setFont(font1);
		t1.setBackground(new Color(251, 206, 177));
		t1.setForeground(new Color(106, 134, 173));
		
		JTextField t2 = new JTextField(10);
		t2.setText("패스워드");
		t2.setFont(font1);
		t2.setBackground(new Color(251, 206, 177));
		t2.setForeground(new Color(106, 134, 173));
		
		t1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                t1.setText("");
            }
        });
		
		t2.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                t2.setText("");
            }
        });
		
		JLabel l1 = new JLabel("로그인");
		l1.setFont(font1);
		
		p1.add(l1);
		p1.add(t1);
		p1.add(t2);
		
		JLabel l2 = new JLabel("----------------");
		l2.setFont(font1);
		p1.add(l2);
		
		
		RoundedButton b1 = new RoundedButton(" 로 그 인  ");
		RoundedButton b2 = new RoundedButton("회원 가입 및 탈퇴");
		RoundedButton b3 = new RoundedButton("비밀번호 찾기");
		RoundedButton b4 = new RoundedButton("회원 목록");
		
		
		b1.setPreferredSize(new Dimension(375,80));
		b2.setPreferredSize(new Dimension(375,80));
		b3.setPreferredSize(new Dimension(375,80));
		b4.setPreferredSize(new Dimension(375,80));
		
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
					
					//PlaceUI 연결
					PlaceUI place = new PlaceUI();
					place.open();
					f.setVisible(false);
				}
				
			}
		});
		
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Signin singin = new Signin();
				singin.open();
				
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
		
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("회원 목록");
				AdminUI admin = new AdminUI();
				admin.open();
			} 
		});
		
		b1.setFont(font1);
		b2.setFont(font1);
		b3.setFont(font1);
		b4.setFont(font1);

		f.add(p1);
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p1.add(b4);
		
		f.setVisible(true);
	}

}