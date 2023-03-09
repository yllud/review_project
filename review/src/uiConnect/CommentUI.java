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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dbConnect.CommentDao;


public class CommentUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f=new JFrame();
		f.setSize(450,500);
		FlowLayout flow=new FlowLayout();
		f.setLayout(flow);
		f.getContentPane().setBackground(Color.green);
		
		Font font=new Font("궁서",2,30);
		Font font2=new Font("궁서",1,20);
		
		JLabel l=new JLabel("댓글");
		l.setFont(font);
		
		JTextArea t1=new JTextArea("댓글 목록",15,25);
		
		JTextField t2=new JTextField(20);
		t1.setFont(font2);
		t2.setFont(font2);
		
		JButton b1=new JButton("입력");
		b1.setFont(font2);
		
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("sign up");
				//int comment_no =
				//String writer = 
				//int post_no = 
				String content = t2.getText();
				
				if(content.equals("")) { //기본형 4가지만 == 로 값을 비교 가능! 
					JOptionPane.showMessageDialog(f, "content는 필수입력항목입니다.");
				}
				
				CommentDao dao = new CommentDao();
				//1. 가방 생성
				CommentVO bag=new CommentVO();
				//2. 가방에 값 넣어주기
				//bag.setComment_no(comment_no);
				//bag.setWriter(writer);
				//bag.setPost_no(post_no);
				bag.setContent(content);
				
				int result=dao.insert(bag);
				t1.setText(content);
				
				if(result==1) {
					JOptionPane.showMessageDialog(f, "댓글추가 성공");
				}
				else {
					JOptionPane.showMessageDialog(f, "댓글추가 실패");
				}//action
			}
		});
		
		
		
		f.add(l);
		f.add(t1);
		f.add(t2);
		f.add(b1);
		
		f.setVisible(true);
		
	}

}
