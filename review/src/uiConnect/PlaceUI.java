package uiConnect;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dbConnect.PlaceDAO;



public class PlaceUI {
	
	private static String place_code;
	

	public static String getPlace_code() {
		return place_code;
	}


	public void open() {
		// 지점 UI 만들기
		JFrame f = new JFrame();
		f.setSize(480, 715);
		f.setTitle("지점 정보");
		f.setLayout(null);
		f.getContentPane().setBackground(new Color(250,245,224));
		
		Panel p = new Panel();
		p.setBounds(0, 0, 480, 715);
		p.setBackground(new Color(250,245,224));
		
		// Font font_title = new Font("돋움", 1, 40);
		Font font = new Font("돋움", 1, 30);

		JTextField text_name = new JTextField(8);
		text_name.setFont(font);


		JLabel label_img = new JLabel();
		JLabel label_name = new JLabel("상 호 명 : ");
		JLabel label_location = new JLabel("위    치 : ");
		JLabel label_grade = new JLabel("평    점 : ");
		JLabel label_category = new JLabel("카테고리 : ");
		JLabel label_tel = new JLabel("연 락 처 : ");

		label_name.setFont(font);
		label_location.setFont(font);
		label_grade.setFont(font);
		label_category.setFont(font);
		label_tel.setFont(font);

		JTextField text_code = new JTextField(8);
		JTextField text_location = new JTextField(8);
		JTextField text_grade = new JTextField(8);
		JTextField text_category = new JTextField(8);
		JTextField text_tel = new JTextField(8);
		text_code.setFont(font);
		text_name.setFont(font);
		text_location.setFont(font);
		text_grade.setFont(font);
		text_category.setFont(font);
		text_tel.setFont(font);

		ImageIcon icon = new ImageIcon("first.jpg");
		label_img.setIcon(icon);

		RoundedButton btn_find = new RoundedButton("상호명 검색");
		RoundedButton btn_pass = new RoundedButton("게시물 보기");
		RoundedButton btn_update = new RoundedButton("상호명 수정");
		RoundedButton btn_write = new RoundedButton("게시물 작성");
		RoundedButton btn_reservation = new RoundedButton("예약하기");
		btn_find.setFont(font);
		btn_pass.setFont(font);
		btn_update.setFont(font);
		btn_write.setFont(font);
		btn_reservation.setFont(font);
		btn_find.setForeground(new Color(247, 99, 12));
		btn_pass.setForeground(new Color(247, 99, 12));
		btn_update.setForeground(new Color(247, 99, 12));
		btn_write.setForeground(new Color(247, 99, 12));
		btn_reservation.setForeground(new Color(247, 99, 12));
		btn_find.setBackground(new Color(251, 206, 177));
		btn_pass.setBackground(new Color(251, 206, 177));
		btn_update.setBackground(new Color(251, 206, 177));
		btn_write.setBackground(new Color(251, 206, 177));
		btn_reservation.setBackground(new Color(251, 206, 177));

		f.add(p);
		p.add(label_name);
		p.add(text_name);
		p.add(label_img);
		p.add(label_location);
		p.add(text_location);
		p.add(label_category);
		p.add(text_category);
		p.add(label_grade);
		p.add(text_grade);
		p.add(label_tel);
		p.add(text_tel);
		p.add(btn_find);
		p.add(btn_pass);
		p.add(btn_update);
		p.add(btn_write);
		p.add(btn_reservation);

		// 게시물 찾기
		btn_find.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("게시물 찾기");
				String name = text_name.getText();
				
				// 이미지 넣기....
				PlaceDAO dao = new PlaceDAO();
				PlaceVO bag = dao.one(name);
				
				// post에서 불러옴..........
				PlaceVO bag2 = dao.avg_grade(bag.getPlace_code());
				if (bag != null) {
					text_location.setText(bag.getPlace_location());
					text_category.setText(bag.getPlace_category());
					text_grade.setText(bag2.getAvg_grade());
					text_tel.setText(bag.getPlace_tel());
					System.out.println("지점 코드 : " + bag.getPlace_code());
					
					if (bag.getPlace_img() != null) {
						ImageIcon icon = new ImageIcon(bag.getPlace_img());
						label_img.setIcon(icon);
					} else {
						ImageIcon icon = new ImageIcon("first.jpg");
						label_img.setIcon(icon);
					}
				} else {
					JOptionPane.showMessageDialog(f, "찾으시는 항목이 없습니다.");
					text_location.setText("");
					text_category.setText("");
					text_grade.setText("");
					text_tel.setText("");
					ImageIcon icon = new ImageIcon("first.jpg");
					label_img.setIcon(icon);
				}
			}
		});

		// 게시물 보기 ==> 보기 누르면 place_name에 해당하는 게시물 화면으로 이동
		btn_pass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("해당 게시물 보기");
				String name = text_name.getText();
				
				
				PlaceDAO dao = new PlaceDAO();
				PlaceVO bag = dao.one(name);
				if (bag != null) {
					place_code=bag.getPlace_code();
					Post_UI1 post = new Post_UI1();
					post.open();
					// 화면 닫는거
					// f.setVisible(false);
					
				} else {
					JOptionPane.showMessageDialog(f, "찾으시는 상호명이 없습니다.");
					text_location.setText("");
					text_category.setText("");
					text_grade.setText("");
					text_tel.setText("");
					ImageIcon icon = new ImageIcon("first.jpg");
					label_img.setIcon(icon);
				}
				
				

			}
		});

		btn_update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = text_name.getText();
				String location = text_location.getText();
				String grade = text_grade.getText();
				String category = text_category.getText();
				String tel = text_tel.getText();
			
				System.out.println("정보 수정 처리");
				PlaceDAO dao = new PlaceDAO();

				PlaceVO bag = new PlaceVO();
				bag.setPlace_name(name);
				bag.setPlace_location(location);
				bag.setPlace_grade(grade);
				bag.setPlace_category(category);
				bag.setPlace_tel(tel);

				int result = dao.update(bag);
				if (result >= 1) {
					JOptionPane.showMessageDialog(f, "정보수정 성공");
				} else {
					JOptionPane.showMessageDialog(f, "정보 수정 실패, 재입력해주세요");
				}
			}
		});
		
		btn_write.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("해당 게시물 작성");
				String name = text_name.getText();
				
				
				PlaceDAO dao = new PlaceDAO();
				PlaceVO bag = dao.one(name);
				if (bag != null) {
					place_code=bag.getPlace_code();
					Post_UI2 post = new Post_UI2();
					post.open();
					// 화면 닫는거
					// f.setVisible(false);
					
				} else {
					JOptionPane.showMessageDialog(f, "찾으시는 상호명이 없습니다.");
					text_location.setText("");
					text_category.setText("");
					text_grade.setText("");
					text_tel.setText("");
					ImageIcon icon = new ImageIcon("first.jpg");
					label_img.setIcon(icon);
				}
				
				

			}
		});

		
		
		// 예약 창으로 넘기기
		btn_reservation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reservationUI reservation = new reservationUI();
				reservation.open();
				// 화면 닫는거
				// f.setVisible(false);
			}
		});
		
		// insert 고려해본다

		f.setVisible(true);
	}

}