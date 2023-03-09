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

	public static void main(String[] args) {
		// 지점 UI 만들기
		JFrame f = new JFrame();
		FlowLayout lay = new FlowLayout();
		f.setSize(450, 635);
		f.setTitle("지점 정보");
		f.setLayout(lay);
		
		Font font_title = new Font("돋움", 1, 40);
		Font font = new Font("돋움", 1, 30);
		
		JTextField text_name = new JTextField(8);
		text_name.setFont(font);
		
//		JLabel label_code = new JLabel();
		JLabel label_img = new JLabel();
		JLabel label_name = new JLabel("상 호 명 : ");
		JLabel label_location = new JLabel("위    치 : ");
		JLabel label_grade= new JLabel("평    점 : ");
		JLabel label_category = new JLabel("카테고리 : ");
		JLabel label_tel = new JLabel("연 락 처 : ");
//		label_code.setFont(font);
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
		
		ImageIcon icon = new ImageIcon("lotte.png");
		label_img.setIcon(icon);
		
		JButton btn1 = new JButton("게시물 찾기");
		JButton btn2 = new JButton("게시물 보기");
		btn1.setFont(font);
		btn2.setFont(font);
		btn1.setBackground(Color.LIGHT_GRAY);
		btn2.setBackground(Color.LIGHT_GRAY);

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
		f.add(btn1);
		f.add(btn2);
		
		// 게시물 찾기
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("게시물 찾기");
				String name = text_name.getText();
				
				PlaceDAO dao = new PlaceDAO();
				PlaceVO bag = dao.one(name);
				if (bag != null) {
					text_location.setText(bag.getPlace_location());
					text_category.setText(bag.getPlace_category());
					text_grade.setText(bag.getPlace_grade());
					text_tel.setText(bag.getPlace_tel());
				} else {
					JOptionPane.showMessageDialog(f, "찾으시는 항목이 없습니다.");
					text_location.setText("");
					text_category.setText("");
					text_grade.setText("");
					text_tel.setText("");
				}
			}
		});
		
		// 게시물 보기 ==> 보기 누르면 해당 게시물 나오겠지ㅏ..?
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		f.setVisible(true);
	}

}