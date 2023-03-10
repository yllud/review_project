package uiConnect;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Post_UI_in {

	public static void Post_ui() {
		JFrame f = new JFrame();
		f.setSize(600,500);
		f.setTitle("게시글 작성");
		FlowLayout flow = new FlowLayout();
		f.setLayout(flow);
		Font font1 = new Font("D2Coding",Font.BOLD,40);
		Font font2 = new Font("D2Coding",Font.BOLD,20);
		
		JLabel l1 = new JLabel("게시글 번호");
		JLabel l2 = new JLabel("제목");
		JLabel l3 = new JLabel("작성자");
		JLabel l4= new JLabel("내용");
		JLabel l5= new JLabel("작성날짜");
		JLabel l6= new JLabel("별점");
		
		JTextField t1 = new JTextField(2);
		JTextField t2 = new JTextField(10);
		JTextField t3 = new JTextField(5);
		JTextField t4 = new JTextField(10);
		JTextField t5 = new JTextField(5);
		JTextArea a1 = new JTextArea(20,50);
		
		JButton b1 = new JButton("글 쓰기");
		
		l1.setFont(font2);
		l2.setFont(font2);
		l3.setFont(font2);
		l4.setFont(font2);
		l5.setFont(font2);
		l6.setFont(font2);
		
		t1.setFont(font2);
		t2.setFont(font2);
		t3.setFont(font2);
		t4.setFont(font2);
		t5.setFont(font2);
		
		
		
		
		
		
		f.add(l1);
		f.add(t1);
		f.add(l2);
		f.add(t2);
		f.add(l3);
		f.add(t3);
		f.add(l4);
		f.add(a1);
		f.add(l5);
		f.add(t4);
		f.add(l6);
		f.add(t5);
		f.add(b1);
		
		
		
		
		
		
		
		
		
		
		
		f.setVisible(true);
	}

}
