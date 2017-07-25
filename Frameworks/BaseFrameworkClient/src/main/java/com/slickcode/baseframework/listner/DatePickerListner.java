package com.slickcode.baseframework.listner;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.slickcode.baseframework.utils.DatePicker;

/**
 * 
 * It extends {@link BaseActionListner}. It gives facility for Date.
 * 
 * @author Sourabh
 * 
 */
public class DatePickerListner extends BaseActionListner {

	private JTextField targetField;
	private JFrame baseMainPage;

	/**
	 * @param {@link JFrame}
	 * @param {@link JTextField}
	 */
	public DatePickerListner(JFrame baseMainPage, JTextField targetField) {
		this.baseMainPage = baseMainPage;
		this.targetField = targetField;
	}

	@Override
	public boolean performAction(ActionEvent e) {
		Point l = targetField.getLocationOnScreen();
		targetField.setText(new DatePicker(baseMainPage, l.x, l.y
				+ targetField.getHeight()).setPickedDate());
		return true;
	}

	@Override
	public void onSuccess() {
	}

	@Override
	public void onFailure() {
	}

	@Override
	public void mask() {
		baseMainPage.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
	}

	@Override
	public void unmask() {
		baseMainPage.setCursor(Cursor.getDefaultCursor());
	}
}
