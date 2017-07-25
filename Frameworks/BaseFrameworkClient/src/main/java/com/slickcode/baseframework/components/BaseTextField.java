package com.slickcode.baseframework.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import com.slickcode.baseframework.utils.ComponentEnum;
import com.slickcode.baseframework.utils.StringUtilities;

/**
 * 
 * It extends {@link JTextField} and implements {@link IBaseComponent}
 * 
 * @author Sourabh
 * 
 */
public class BaseTextField extends JTextField implements IBaseComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Constructor
	 * 
	 * @param text
	 * @param {@link DocumentListener}
	 * @param {@link ComponentEnum}
	 */
	public BaseTextField(String text, DocumentListener documentListener,
			ComponentEnum componentEnum) {
		super(text);
		changeComponentLayout(componentEnum);
		getDocument().addDocumentListener(documentListener);
	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param text
	 * @param {@link ComponentEnum}
	 */
	public BaseTextField(String text, ComponentEnum componentEnum) {
		super(text);
		changeComponentLayout(componentEnum);
	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param {@link ComponentEnum}
	 */
	public BaseTextField(ComponentEnum componentEnum) {
		changeComponentLayout(componentEnum);
	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param {@link DocumentListener}
	 * @param {@link ComponentEnum}
	 */
	public BaseTextField(DocumentListener documentListener,
			ComponentEnum componentEnum) {
		changeComponentLayout(componentEnum);
		getDocument().addDocumentListener(documentListener);
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

	/**
	 * 
	 * It sets {@link Integer} as value.
	 * 
	 * @param value
	 */
	public void setText(Integer value) {
		String text = "";
		if (null != value) {
			text = String.valueOf(value);
		}
		super.setText(text);
	}

	/**
	 * 
	 * It sets {@link Float} as value.
	 * 
	 * @param value
	 */
	public void setText(Float value) {
		String text = "";
		if (null != value) {
			text = String.valueOf(value);
		}
		super.setText(text);
	}

	@Override
	public String getText() {
		String text = super.getText();

		if (StringUtilities.isEmpty(text)) {
			return null;
		} else {
			return text;
		}
	}
}
