package uiConnect;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dbConnect.reservationDAO;

public class reservationUI2 {

	public void open() {

		JFrame f = new JFrame();
		f.setTitle("예약 목록");
		f.setSize(515, 500);

		reservationDAO dao = new reservationDAO();

		ArrayList<reservationVO> reserveList = dao.rList();

		String[] header = { "예약번호", "예약자 ID", "인원", "예약 장소", "예약 시간" };
		Object[][] all = new String[reserveList.size()][5];

		if (reserveList.size() == 0) {
			System.out.println("검색결과 없음. ");
		} else {
			System.out.println("검색결과는 전체 " + reserveList.size() + "개 입니다.");
			for (int i = 0; i < all.length; i++) { // 13개의 가방,13개의 행
				all[i][0] = String.valueOf(reserveList.get(i).getR_num());
				all[i][1] = reserveList.get(i).getR_id();
				all[i][2] = reserveList.get(i).getR_people();
				all[i][3] = reserveList.get(i).getR_place();
				all[i][4] = reserveList.get(i).getR_time();
			}
		} // else

		JTable table = new JTable(all, header);
		table.setBackground(Color.pink);
		table.setGridColor(Color.white);
		table.setSelectionBackground(Color.white);
		JScrollPane scroll = new JScrollPane(table);

		f.setLayout(new FlowLayout());
		f.getContentPane().setBackground(Color.white);

		f.add(scroll);

		f.setLocation(600, 300);
		f.setVisible(true);
	}

}
