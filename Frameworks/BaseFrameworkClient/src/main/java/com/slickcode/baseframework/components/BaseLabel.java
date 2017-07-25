package com.slickcode.baseframework.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

import com.slickcode.baseframework.utils.BaseUtils;
import com.slickcode.baseframework.utils.ComponentEnum;

/**
 * 
 * It extends {@link JLabel} and inplements {@link TableCellRenderer}
 * 
 * @author Sourabh
 * 
 */
public class BaseLabel extends JLabel implements TableCellRenderer {

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
	public BaseLabel(String text, ComponentEnum componentEnum) {
		super(text);
		changeComponentLayout(componentEnum);
	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param {@link ComponentEnum}
	 */
	public BaseLabel(ComponentEnum componentEnum) {
		super();
		changeComponentLayout(componentEnum);
	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param {@link Icon}
	 * @param {@link ComponentEnum}
	 */
	public BaseLabel(Icon icon, ComponentEnum componentEnum) {
		super(icon);
		changeComponentLayout(componentEnum);
	}

	/**
	 * 
	 * It sets error message to tooltip and changes layout to Error Image.
	 * 
	 * @param errorMsg
	 */
	public void addError(String errorMsg) {
		setToolTipText(errorMsg);
		changeComponentLayout(ComponentEnum.ERROR_IMAGE);
	}

	/**
	 * It removes error message from tooltip and changes layout to OK.
	 */
	public void addNoError() {
		setToolTipText("");
		changeComponentLayout(ComponentEnum.OK_IMAGE);
	}

	/**
	 * It removes error message from tooltip and changes layout to No Error
	 * Image.
	 */
	public void removeError() {
		setToolTipText("");
		changeComponentLayout(ComponentEnum.NO_ERROR_IMAGE);
	}

	/**
	 * 
	 * It changes layout as per {@link ComponentEnum}.
	 * 
	 * @param {@link ComponentEnum}
	 */
	public void changeComponentLayout(ComponentEnum componentEnum) {
		switch (componentEnum) {
		case HEADER:
			setForeground(Color.BLACK);
			setFont(new Font(getName(), Font.BOLD, 20));
			break;

		case ERROR:
			setForeground(Color.RED);
			setFont(new Font(getName(), Font.PLAIN, 11));
			break;

		case LABEL:
			setForeground(Color.BLACK);
			setFont(new Font(getName(), Font.BOLD, 11));
			break;

		case ERROR_IMAGE:
			setIcon(BaseUtils.populateImage("images/error.png", 11, 11));
			setPreferredSize(new Dimension(11, 11));
			setText("");
			break;

		case NO_ERROR_IMAGE:
			setPreferredSize(new Dimension(11, 11));
			break;

		case VALUE:
			setForeground(Color.BLACK);
			setFont(new Font(getName(), Font.PLAIN, 11));
			break;

		case OK_IMAGE:
			setIcon(BaseUtils.populateImage("images/ok.png", 11, 11));
			setPreferredSize(new Dimension(11, 11));
			setText("");
			break;

		default:
			break;
		}

	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("Button.background"));
		}
		setText((value == null) ? "" : value.toString());
		return this;
	}

	/**
	 * 
	 * It sets {@link Integer} parameter.
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
	 * It sets {@link Float} parameter.
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
}
