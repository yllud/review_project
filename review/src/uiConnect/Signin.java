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




public class Signin {

	public static void signin() {
		JFrame f = new JFrame();
		f.setTitle("나의 회원가입 화면");
		f.setSize(500, 1000);
		FlowLayout flow = new FlowLayout();
		f.setLayout(flow);

		
		JLabel l2 = new JLabel("아이디");
		JLabel l3 = new JLabel("패스워드");
		JLabel l4 = new JLabel("이름");
		JLabel l5 = new JLabel("나이");
		JLabel l6 = new JLabel("주소");
		
		JTextField t1 = new JTextField(10);
		JTextField t2 = new JTextField(10);
		JTextField t3 = new JTextField(10);
		JTextField t4 = new JTextField(10);
		JTextField t5 = new JTextField(10);

		JButton b1 = new JButton("회원가입 처리");
		JButton b2 = new JButton("회원탈퇴 처리");
		JButton b3 = new JButton("회원수정 처리");
		JButton b4 = new JButton("회원검색 처리");

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("회원가입처리");
				String id = t1.getText();
				String pw = t2.getText();
				String name = t3.getText();
				int age = Integer.parseInt(t4.getText());
				String mem_addr = t5.getText();
				
				if (id.equals("")) {
					JOptionPane.showMessageDialog(f, "id는 필수 입력항목입니다.");
				}

				MembersDAO dao = new MembersDAO();
				MembersVO bag = new MembersVO();
				bag.setId(id);
				bag.setPw(pw);
				bag.setName(name);
				bag.setAge(age);
				bag.setMem_addr(mem_addr);

				int result = dao.insert(bag);
				if (result == 1) {
					JOptionPane.showMessageDialog(f, "회원가입 성공");
				}

			}
		});

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("회원탈퇴처리");
				String id = t1.getText();

				MembersDAO dao = new MembersDAO();
				MembersVO bag = new MembersVO();
				bag.setId(id);
				int result = dao.delete(bag);
				if (result >= 1) {
					JOptionPane.showMessageDialog(f, "회원탈퇴 성공");
				}
			}
		});

		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("회원수정처리");
				String id = t1.getText();
				String mem_addr = t5.getText();

				MembersDAO dao = new MembersDAO();
				MembersVO bag = new MembersVO();

				bag.setId(id);
				bag.setMem_addr(mem_addr);
				int result = dao.update(bag);
				if (result >= 1) {
					JOptionPane.showMessageDialog(f, "회원정보 수정 성공");
				}
			}
		});

		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("회원검색처리");
				String id = t1.getText();

				MembersDAO dao = new MembersDAO();
				MembersVO bag = dao.one(id);

				if (bag != null) {
					t2.setText(bag.getPw());
					t3.setText(bag.getName());
					t4.setText(Integer.toString(bag.getAge()));
					t5.setText(bag.getMem_addr());
					t2.setBackground(Color.pink);
					t3.setBackground(Color.pink);
					t4.setBackground(Color.pink);
					t5.setBackground(Color.pink);
				} else {
					t2.setText("");
					t3.setText("");
					t4.setText("");
					t5.setText("");
					JOptionPane.showMessageDialog(f, "검색결과없음");
				}
			}
		});

		Font font = new Font("D2Coding", Font.BOLD, 45);
		// 필요한 객체 준비 완료

		
		f.add(l2);
		f.add(t1);
		f.add(l3);
		f.add(t2);
		f.add(l4);
		f.add(t3);
		f.add(l5);
		f.add(t4);
		f.add(l6);
		f.add(t5);
		
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);

		
		l2.setFont(font);
		l3.setFont(font);
		l4.setFont(font);
		l5.setFont(font);
		l6.setFont(font);
		
		t1.setFont(font);
		t2.setFont(font);
		t3.setFont(font);
		t4.setFont(font);
		t5.setFont(font);
		
		b1.setFont(font);
		b2.setFont(font);
		b3.setFont(font);
		b4.setFont(font);

		t1.setBackground(Color.yellow);
		t1.setForeground(Color.blue);
		t2.setBackground(Color.yellow);
		t2.setForeground(Color.blue);
		t3.setBackground(Color.yellow);
		t3.setForeground(Color.blue);
		t4.setBackground(Color.yellow);
		t4.setForeground(Color.blue);
		t5.setBackground(Color.yellow);
		t5.setForeground(Color.blue);

		b1.setBackground(Color.pink);
		b1.setForeground(Color.red);
		b2.setBackground(Color.pink);
		b2.setForeground(Color.red);
		b3.setBackground(Color.pink);
		b3.setForeground(Color.red);
		b4.setBackground(Color.pink);
		b4.setForeground(Color.red);

		f.getContentPane().setBackground(Color.green);

		f.setVisible(true);
	}

}
