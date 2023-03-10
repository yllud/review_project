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
import javax.swing.JTextField;

import dbConnect.PlaceDAO;



public class PlaceUI {

	public void open() {
		// 지점 UI 만들기
		JFrame f = new JFrame();
		FlowLayout lay = new FlowLayout();
		f.setSize(480, 715);
		f.setTitle("지점 정보");
		f.setLayout(lay);
		
		Font font_title = new Font("돋움", 1, 40);
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

		JButton btn_find = new JButton("게시물 검색");
		JButton btn_pass = new JButton("게시물 보기");
		JButton btn_update = new JButton("게시물 수정");
		JButton btn_del = new JButton("게시물 삭제");
		JButton btn_reservation = new JButton("예약하기");
		btn_find.setFont(font);
		btn_pass.setFont(font);
		btn_update.setFont(font);
		btn_del.setFont(font);
		btn_reservation.setFont(font);
		btn_find.setBackground(Color.LIGHT_GRAY);
		btn_pass.setBackground(Color.LIGHT_GRAY);
		btn_update.setBackground(Color.LIGHT_GRAY);
		btn_del.setBackground(Color.LIGHT_GRAY);
		btn_reservation.setForeground(Color.yellow);
		btn_reservation.setBackground(Color.blue);

		f.add(label_name);
		f.add(text_name);
		f.add(label_img);
		f.add(label_location);
		f.add(text_location);
		f.add(label_category);
		f.add(text_category);
		f.add(label_grade);
		f.add(text_grade);
		f.add(label_tel);
		f.add(text_tel);
		f.add(btn_find);
		f.add(btn_pass);
		f.add(btn_update);
		f.add(btn_del);
		f.add(btn_reservation);

		// 게시물 찾기
		btn_find.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("게시물 찾기");
				String name = text_name.getText();
				
				// 이미지 넣기....
				PlaceDAO dao = new PlaceDAO();
				PlaceVO bag = dao.one(name);
				if (bag != null) {
					text_location.setText(bag.getPlace_location());
					text_category.setText(bag.getPlace_category());
					text_grade.setText(bag.getPlace_grade());
					text_tel.setText(bag.getPlace_tel());
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
				Post_UI post = new Post_UI();
				post.open();
				// 화면 닫는거
				// f.setVisible(false);
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

		btn_del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = text_name.getText();

				if (name.equals("")) {
					System.out.println("상호명은 필수입력항목입니다.");
				}

				System.out.println("지점 삭체 처리");
				PlaceDAO dao = new PlaceDAO();
				int result = dao.del(name);
				if (result >= 1) {
					JOptionPane.showMessageDialog(f, "상호명 삭제 성공");
					text_name.setText("");
					text_location.setText("");
					text_category.setText("");
					text_grade.setText("");
					text_tel.setText("");
				} else {
					JOptionPane.showMessageDialog(f, "존재하지 않는 상호명. 재입력해주세요");
					text_name.setText("");
					text_location.setText("");
					text_category.setText("");
					text_grade.setText("");
					text_tel.setText("");
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