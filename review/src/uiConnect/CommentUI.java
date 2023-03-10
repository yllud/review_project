package uiConnect;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dbConnect.CommentDao;


public class CommentUI {
	
	

	public void open() {
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
		
		JTextArea t1=new JTextArea(15,25);
		
		JTextField t2=new JTextField(20);
		t1.setFont(font2);
		t2.setFont(font2);
		
		JButton b1=new JButton("입력");
		b1.setFont(font2);
		
		//원래 있던 댓글 불러오기
		CommentDao dao =new CommentDao();
		ArrayList<CommentVO> list0 = dao.list(); // ArrayList<MemberVO>
		for (int i = 0; i < list0.size(); i++) { // 13개의 가방,13개의 행
			
			
			t1.append("\n"+list0.get(i).getWriter()+": "+list0.get(i).getContent());
			
		}
		
		
		//댓글 추가
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("sign up");
				Main main = new Main();
				//댓글 목록에서 가장 큰 번호를 불러와 1을 더해서 자동 번호 생성
				CommentDao dao =new CommentDao();
				ArrayList<CommentVO> list = dao.listOrder(); // ArrayList<MemberVO>
				
				int last_no=list.get(0).getComment_no();
				System.out.println(last_no);
				
				int comment_no =last_no+1;
				System.out.println(comment_no);
				
				String writer = main.getId();
				Post_UI1 pu=new Post_UI1();
				int post_no =pu.getPost_no();
				String content = t2.getText();
				
				
				if(content.equals("")) { //기본형 4가지만 == 로 값을 비교 가능! 
					JOptionPane.showMessageDialog(f, "content는 필수입력항목입니다.");
				}
				
				
				//1. 가방 생성
				CommentVO bag=new CommentVO();
				//2. 가방에 값 넣어주기
				bag.setComment_no(comment_no);
				bag.setWriter(writer);
				bag.setPost_no(post_no);
				bag.setContent(content);
				
				int result=dao.insert(bag);
				
				if(result==1) {
					JOptionPane.showMessageDialog(f, "댓글추가 성공");
					
					t1.append("\n"+writer+": "+content);
					t2.setText("");
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
