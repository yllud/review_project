package uiConnect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import project.Login_UI;
import project.reservationDAO;

public class reservationUI {
	public static void main(String[] args) {
		Login_UI id = new Login_UI();
		int rnum = (int) (Math.random() * 9999);
		String rid = id.getId();

		JFrame f = new JFrame();
		f.setTitle("예약");
		f.setSize(515, 700);

		JLabel rnumLabel = new JLabel("예약번호 ");
		JLabel rnumLabel2 = new JLabel(String.valueOf(rnum));
		JLabel idLabel = new JLabel("예약자 ID ");
		JLabel idLabel2 = new JLabel(rid + "");
		JLabel peopleLabel = new JLabel("예약 인원");
		JLabel timeLable = new JLabel("예약 시간");
		JLabel placeLable = new JLabel("예약 장소");
		JLabel nLable = new JLabel("0");

		JButton minusButton = new JButton("-");
		JButton plusButton = new JButton("+");
		JButton reserveButton = new JButton("예약");

		Font font = new Font("D2Coding", Font.BOLD, 30);
		Font font2 = new Font("D2Coding", Font.BOLD, 20);

		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel p7 = new JPanel();

		p1.setBounds(0, 0, 500, 50);
		p2.setBounds(50, 30, 400, 550);
		p5.setBounds(0, 600, 500, 100);

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

		reservationDAO dao = new reservationDAO();

		List<String> placeList;
		placeList = dao.place(); 

		JComboBox<String> cb = new JComboBox<>(placeList.toArray(new String[placeList.size()]));

		// 달력에 선택된 날짜
		// String date = model.getYear() + "-" + (model.getMonth() + 1) + "-" +
		// model.getDay();

		reserveButton.setPreferredSize(new Dimension(500, 60));

		f.setLayout(null);

		p1.setLayout(new BorderLayout());
		p1.add("West", p6);
		p1.add("East", p7);
		f.add(peopleLabel);
		f.add(minusButton);
		f.add(nLable);
		f.add(plusButton);
		f.add(timeLable);
		f.add(datePicker);
		f.add(placeLable);
		f.add(cb);
		p5.add(reserveButton);
		p6.add(rnumLabel);
		p6.add(rnumLabel2);
		p7.add(idLabel);
		p7.add(idLabel2);
		p2.setBackground(Color.pink);

		peopleLabel.setBounds(70, 70, 200, 50);//200, 100, 300, 200
		minusButton.setBounds(230, 70, 55, 50);
		nLable.setBounds(310, 70, 20, 50);
		plusButton.setBounds(360, 70, 55, 50);
		
		timeLable.setBounds(70, 200, 200, 30);
		datePicker.setBounds(230, 200, 190, 30);
		
		placeLable.setBounds(70, 450, 200, 50);
		cb.setBounds(230, 460, 190, 30);
		
		reserveButton.setBorderPainted(false);
		reserveButton.setBackground(Color.pink);
		
		rnumLabel.setFont(font2);
		rnumLabel2.setFont(font2);
		rnumLabel2.setForeground(Color.pink);
		idLabel.setFont(font2);
		idLabel2.setFont(font2);
		idLabel2.setForeground(Color.pink);
		peopleLabel.setFont(font);
		peopleLabel.setFont(font);
		timeLable.setFont(font);
		placeLable.setFont(font);
		nLable.setFont(font);
		minusButton.setFont(font);
		plusButton.setFont(font);
		reserveButton.setFont(font);

		f.add(p1);
		f.add(p2);
		f.add(p5);

		minusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int nl = Integer.parseInt(nLable.getText());
				if (nl == 0) {
					JOptionPane.showMessageDialog(f, "0명 이상만 가능합니다.");
				} else if (nl > 0) {
					nl -= 1;
					nLable.setText(String.valueOf(nl));
				}
			}
		});

		plusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int nl = Integer.parseInt(nLable.getText());
				nl += 1;
				nLable.setText(String.valueOf(nl));
			}
		});

		reserveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String rnum = rnumLabel2.getText();
				String rid = idLabel2.getText();
				String rpeople = nLable.getText();
				String rplace = cb.getSelectedItem().toString();
				String str_rplace = rplace.replaceAll("[^0-9]", "");
				String rtime = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();
				String message = "아래 예약 정보가 맞습니까?\n예약 번호 : " + rnum + "\n예약자 ID : " + rid + "\n예약 인원 : " + rpeople
						+ "\n예약 장소 : " + rplace + "\n예약 날짜 : " + rtime;

				reservationDAO dao = new reservationDAO();
				reservationVO bag = new reservationVO();

				int answer = JOptionPane.showConfirmDialog(null, message, "예약 정보 확인", JOptionPane.YES_NO_OPTION);

				if (answer == JOptionPane.YES_OPTION) {
					bag.setR_num(Integer.parseInt(rnum));
					bag.setR_id(rid);
					bag.setR_people(rpeople);
					bag.setR_place(str_rplace);
					bag.setR_time(rtime);

					dao.insert(bag);
					
					reservationUI2 RUI2 = new reservationUI2();
					RUI2.open();
					f.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(f, "예약 정보를 다시 입력해주세요.");
				}

			}
		});

		f.setLocation(600, 300);
		f.setVisible(true);
	}

}
