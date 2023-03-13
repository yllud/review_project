package uiConnect;

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

import dbConnect.MembersDAO;

public class AdminUI {

	public void open() {
		JFrame f = new JFrame();
		f.setSize(980, 800);

		MembersDAO dao = new MembersDAO();
		ArrayList<MembersVO> list = dao.list(); // ArrayList<MemberVO>

		String[] header = { "회원 ID", "회원 PW", "회원 이름", "나이", "주소"};
		Object[][] all = new String[list.size()][5];

		if (list.size() == 0) {
			System.out.println("검색결과 없음. ");
		} else {
			System.out.println("검색결과는 전체 " + list.size() + "개 입니다.");
			for (int i = 0; i < all.length; i++) { // 13개의 가방,13개의 행
				all[i][0] = list.get(i).getId();
				all[i][1] = list.get(i).getPw();
				all[i][2] = list.get(i).getName();
				all[i][3] = String.valueOf(list.get(i).getAge());
				all[i][4] = list.get(i).getMem_addr();
			}
		} // else
		System.out.println(all);
		JTable table = new JTable(all, header);
		table.getColumn("회원 ID").setPreferredWidth(500);
		table.getColumn("회원 PW").setPreferredWidth(500);
		table.getColumn("회원 이름").setPreferredWidth(900);
		table.getColumn("나이").setPreferredWidth(300);
		table.getColumn("주소").setPreferredWidth(1000);
		table.getRowHeight(500);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(900, 550));

	

		f.setLayout(new FlowLayout());

		f.add(scroll);
		

		f.setVisible(true);
	}// main// TODO Auto-generated method stub

	

}
