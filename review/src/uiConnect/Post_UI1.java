package uiConnect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dbConnect.PostDAO;

public class Post_UI1 {

	private static int post_no;

	public static int getPost_no() {
		return post_no;
	}

	public void open() {
		// 프로그램 시작하자 마자 db에서 데이터를 가지고 와서
		// 화면을 만들어주고 싶음.
		JFrame f = new JFrame();
		f.setSize(980, 800);

		PostDAO dao = new PostDAO();
		ArrayList<PostVO> list = dao.list(); // ArrayList<MemberVO>

		String[] header = { "게시물 번호", "제목", "내용", "작성자", "별점", "날짜" };
		Object[][] all = new String[list.size()][6];

		if (list.size() == 0) {
			System.out.println("검색결과 없음. ");
		} else {
			System.out.println("검색결과는 전체 " + list.size() + "개 입니다.");
			for (int i = 0; i < all.length; i++) { // 13개의 가방,13개의 행
				all[i][0] = String.valueOf(list.get(i).getPost_no());
				all[i][1] = list.get(i).getTitle();
				all[i][2] = list.get(i).getContent();
				all[i][3] = list.get(i).getWriter();
				all[i][4] = String.valueOf(list.get(i).getScore());
				all[i][5] = list.get(i).getWrite_date();
			}
		} // else
		System.out.println(all);
		JTable table = new JTable(all, header);
		table.getColumn("게시물 번호").setPreferredWidth(500);
		table.getColumn("제목").setPreferredWidth(900);
		table.getColumn("내용").setPreferredWidth(900);
		table.getColumn("작성자").setPreferredWidth(300);
		table.getColumn("날짜").setPreferredWidth(900);
		table.getColumn("별점").setPreferredWidth(300);
		table.getRowHeight(500);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(900, 550));

		JLabel l = new JLabel("	게시물 번호		");
		JTextField t = new JTextField(10);
		JButton b = new JButton("댓글보기");
		

		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				post_no = Integer.parseInt(t.getText());

				PostDAO dao = new PostDAO();
				PostVO bag = dao.one(post_no);
				if (bag != null) {
					CommentUI cu = new CommentUI();
					cu.open();
				} else {
					JOptionPane.showMessageDialog(f, "검색 실패 게시물 번호를 확인해주세요");
				}

			}
		});

		

		f.setLayout(new FlowLayout());

		f.add(scroll);
		f.add(l);
		f.add(t);
		f.add(b);

		f.setVisible(true);
	}// main

}