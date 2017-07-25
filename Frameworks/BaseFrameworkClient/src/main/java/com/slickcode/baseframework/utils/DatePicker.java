package com.slickcode.baseframework.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DatePicker {
	int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
	int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
	JLabel monthLabel = new JLabel("", JLabel.CENTER);
	
	JLabel yearLabel = new JLabel("", JLabel.CENTER);
	String day = "";
	JDialog d;
	JButton[] button = new JButton[49];

	public DatePicker(Component parent, int x, int y) {
		d = new JDialog();
		d.setModal(true);
		String[] header = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};
		JPanel p1 = new JPanel(new GridLayout(7, 7));
		p1.setPreferredSize(new Dimension(430, 120));

		for (int i = 0; i < button.length; i++) {
			final int selection = i;
			button[i] = new JButton();
			button[i].setFocusPainted(false);
			button[i].setBackground(Color.white);
			if (i > 6)
				button[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						day = button[selection].getActionCommand();
						d.dispose();
					}
				});
			if (i < 7) {
				button[i].setText(header[i]);
				button[i].setForeground(Color.red);
			}
			p1.add(button[i]);
		}
		JPanel p2 = new JPanel(new GridLayout(1, 3));
		JButton previous = new JButton("<< Previous");
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				month--;
				displayDate();
			}
		});
		p2.add(previous);
		p2.add(monthLabel);
		JButton next = new JButton("Next >>");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				month++;
				displayDate();
			}
		});
		p2.add(next);
		
		
		//start :my code
		JPanel p3 = new JPanel(new GridLayout(1,3));
		JButton prevYear = new JButton("<< Previous");
		prevYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				year--;
				displayDate();
			}
		});
		p3.add(prevYear);
		p3.add(yearLabel);
		JButton nextYear = new JButton("Next >>");
		nextYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				year++;
				displayDate();
			}
		});
		p3.add(nextYear);
		//end: my code
		
		d.add(p1, BorderLayout.NORTH);
		d.add(p2, BorderLayout.CENTER);
		d.add(p3, BorderLayout.SOUTH);
		d.pack();
//		d.setLocationRelativeTo(parent);
		d.setLocation(x, y+5);
		displayDate();
		d.setVisible(true);
	}

	public void displayDate() {
		for (int x = 7; x < button.length; x++)
			button[x].setText("");
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"MMMM yyyy");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(year, month, 1);
		int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
		int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
		for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
			button[x].setText("" + day);
		String date = sdf.format(cal.getTime());
		String dateSplitter[] = date.split(" ");
		monthLabel.setText(dateSplitter[0]);
		yearLabel.setText(dateSplitter[1]);
		d.setTitle("Date Picker");
	}

	public String setPickedDate() {
		if (day.equals(""))
			return day;
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"dd-MM-yyyy");
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.set(year, month, Integer.parseInt(day));
		return sdf.format(cal.getTime());
	}
}
