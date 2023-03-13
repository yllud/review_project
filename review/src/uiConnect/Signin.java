package uiConnect;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dbConnect.MembersDAO;

public class Signin {

	public void open() {
		JFrame f = new JFrame();
		f.setTitle("나의 회원가입 화면");
		f.setSize(500, 700);
		FlowLayout flow = new FlowLayout();
		f.setLayout(flow);

		JLabel l1 = new JLabel("회원 정보 입력");
		JLabel l2 = new JLabel("----------------");

		JTextField t1 = new JTextField(10);
		JTextField t2 = new JTextField(10);
		JTextField t3 = new JTextField(10);
		JTextField t4 = new JTextField(10);
		JTextField t5 = new JTextField(10);

		RoundedButton b1 = new RoundedButton("회원가입 처리");
		RoundedButton b2 = new RoundedButton("회원탈퇴 처리");
		RoundedButton b3 = new RoundedButton("회원수정 처리");

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
		
		t3.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                t3.setText("");
            }
        });
		
		t4.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                t4.setText("");
            }
        });
		
		t5.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                t5.setText("");
            }
        });

		
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
					f.setVisible(false);
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

		Font font = new Font("돋움", Font.BOLD, 45);
		// 필요한 객체 준비 완료

		f.add(l1);
		f.add(t1);

		f.add(t2);

		f.add(t3);

		f.add(t4);

		f.add(t5);
		f.add(l2);
		f.add(b1);
		f.add(b2);
		f.add(b3);

		l1.setFont(font);
		l2.setFont(font);

		t1.setFont(font);
		t2.setFont(font);
		t3.setFont(font);
		t4.setFont(font);
		t5.setFont(font);

		b1.setFont(font);
		b2.setFont(font);
		b3.setFont(font);

		t1.setText("아이디");
		t1.setBackground(new Color(251, 206, 177));
		t1.setForeground(new Color(106, 134, 173));

		t2.setText("패스워드");
		t2.setBackground(new Color(251, 206, 177));
		t2.setForeground(new Color(106, 134, 173));

		t3.setText("이름");
		t3.setBackground(new Color(251, 206, 177));
		t3.setForeground(new Color(106, 134, 173));

		t4.setText("나이");
		t4.setBackground(new Color(251, 206, 177));
		t4.setForeground(new Color(106, 134, 173));

		t5.setText("주소");
		t5.setBackground(new Color(251, 206, 177));
		t5.setForeground(new Color(106, 134, 173));

		b1.setBackground(new Color(251, 206, 177));
		b1.setForeground(new Color(106, 134, 173));
		b2.setBackground(new Color(251, 206, 177));
		b2.setForeground(new Color(106, 134, 173));
		b3.setBackground(new Color(251, 206, 177));
		b3.setForeground(new Color(106, 134, 173));

		f.getContentPane().setBackground(new Color(250, 245, 224));

		f.setVisible(true);
	}

}
