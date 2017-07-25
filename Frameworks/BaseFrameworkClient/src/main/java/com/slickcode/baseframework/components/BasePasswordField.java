package com.slickcode.baseframework.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

import com.slickcode.baseframework.utils.ComponentEnum;

/**
 * 
 * It extends {@link JPasswordField} and implements {@link IBaseComponent}.
 * 
 * @author Sourabh
 * 
 */
public class BasePasswordField extends JPasswordField implements IBaseComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Constructor
	 * 
	 * @param text
	 * @param {@link ComponentEnum}
	 */
	public BasePasswordField(String text, ComponentEnum componentEnum) {
		super(text);
		changeComponentLayout(componentEnum);
	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param {@link ComponentEnum}
	 */
	public BasePasswordField(ComponentEnum componentEnum) {
		changeComponentLayout(componentEnum);
	}

	@Override
	public void changeComponentLayout(ComponentEnum componentEnum) {
		switch (componentEnum) {
		case ERROR:
			setForeground(Color.RED);
			setFont(new Font(getName(), Font.PLAIN, 11));
			setBorder(BorderFactory.createLineBorder(Color.red));
			break;

		case VALUE:
			setForeground(Color.BLACK);
			setFont(new Font(getName(), Font.PLAIN, 11));
			setBorder(BorderFactory.createLineBorder(Color.black));
			break;

		default:
			break;
		}
	}
}
