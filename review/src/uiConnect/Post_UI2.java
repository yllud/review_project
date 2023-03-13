package uiConnect;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dbConnect.PostDAO;


public class Post_UI2 {
	public void open() {
		JFrame f = new JFrame();
		f.setTitle("게시판 화면");
		f.setSize(1200, 1200);
		
		ImageIcon star = new ImageIcon("star0.png");
		ImageIcon star1 = new ImageIcon("star1.png");
		ImageIcon star2 = new ImageIcon("star2.png");
		ImageIcon star3 = new ImageIcon("star3.png");
		ImageIcon star4 = new ImageIcon("star4.png");
		ImageIcon star5 = new ImageIcon("star5.png");

		JLabel l2 = new JLabel("게시판 번호");
		JLabel l3 = new JLabel("게시판 제목");
		JLabel l4 = new JLabel("게시판 내용");
		JLabel l5 = new JLabel("게시판 작성자");
		JLabel l6 = new JLabel("별점");
		JLabel l7 = new JLabel();

		JTextField t1 = new JTextField(5);
		JTextField t2 = new JTextField(10);
		JTextArea t3 = new JTextArea("", 10, 20);
		JTextField t4 = new JTextField(10);
		JTextField t5 = new JTextField(10);

		RoundedButton b1 = new RoundedButton("게시판 글쓰기 처리");
		RoundedButton b2 = new RoundedButton("게시판 지우기 처리");
		RoundedButton b3 = new RoundedButton("게시판 수정 처리");



		FlowLayout flow = new FlowLayout();

	
		Font font = new Font("돋움", Font.BOLD, 40);
		
		Font font2 = new Font("휴먼편지체", Font.BOLD, 40);

	

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String no = t1.getText();
				String title = t2.getText();
				String content = t3.getText();
				String writer = t4.getText();
				String score = t5.getText();
				int no2 = Integer.parseInt(no);
				int score2 = Integer.parseInt(score);
				
				if (title.equals("")) { 
					JOptionPane.showMessageDialog(f, "게시판 제목은 필수 입력 항목입니다.");
				}

				PostDAO dao = new PostDAO();
				PostVO bag = new PostVO();
				bag.setPost_no(no2);
				bag.setTitle(title);
				bag.setContent(content);
				bag.setWriter(writer);
				bag.setScore(score2);
				bag.setPlace_code("1111");
				int result = dao.insert(bag);
				if (result == 1) {
					JOptionPane.showMessageDialog(f, "글쓰기 성공!!");
				} else {
					JOptionPane.showMessageDialog(f, "글쓰기 실패!! 재입력해주세요");
					t1.setText("");
					t2.setText("");
					t3.setText("");
					t4.setText("");
				}
				if (score.equals("0")) {
					l7.setIcon(star);
				}else if (score.equals("1")) {
					l7.setIcon(star1);
				}
				if (score.equals("2")) {
					l7.setIcon(star2);
				}else if (score.equals("3")) {
					l7.setIcon(star3);
				}
				if (score.equals("4")) {
					l7.setIcon(star4);
				}else if (score.equals("5")) {
					l7.setIcon(star5);
				}
			}// action
		});// b1

		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String id = t1.getText();
				
				int id2 = Integer.parseInt(id);
				PostDAO dao = new PostDAO();
				int result = dao.delete(id2);
				if (result == 1) {
					JOptionPane.showMessageDialog(f, "게시판 삭제 성공");
					t1.setText("");
				} else {
					JOptionPane.showMessageDialog(f, "게시판 삭제 실패!!, 게시물 번호를 확인해주세요");
					t1.setText("");
				}
			}// action
		});// b2

	
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String no = t1.getText();
				String title = t2.getText(); // ""
				
				int no2 = Integer.parseInt(no);
				PostDAO dao = new PostDAO();
				PostVO bag = new PostVO();
				bag.setPost_no(no2);
				bag.setTitle(title);
				int result = dao.update(bag);
				;
				if (result == 1) {
					JOptionPane.showMessageDialog(f, "게시판수정 완료");
				} else {
					JOptionPane.showMessageDialog(f, "게시판 수정 실패!!, 게시물 번호를 확인해주세요");
				}
			}// action
		});// b3




		
		f.setLayout(flow);


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



		l2.setFont(font2);
		l3.setFont(font2);
		l4.setFont(font2);
		l5.setFont(font2);
		l6.setFont(font2);
		t1.setFont(font);
		t2.setFont(font);
		t3.setFont(font);
		t4.setFont(font);
		t5.setFont(font);
		b1.setFont(font2);
		b2.setFont(font2);
		b3.setFont(font2);

		t1.setBorder(null);
		t2.setBorder(null);
		t4.setBorder(null);
		t5.setBorder(null);
		
		t1.setBackground(Color.white);
		t1.setForeground(Color.black);
		t2.setBackground(Color.white);
		t2.setForeground(Color.black);
		t3.setBackground(Color.white);
		t3.setForeground(Color.black);
		t4.setBackground(Color.white);
		t4.setForeground(Color.black);
		t5.setBackground(Color.white);
		t5.setForeground(Color.black);



		f.getContentPane().setBackground(new Color(250, 245,224));

		f.setVisible(true);

	}
}