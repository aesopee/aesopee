package com.slickcode.baseframework.components;

import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

/**
 * Customized version of {@link JButton}.
 * 
 * @author Sourabh
 */
public class BaseButton extends JButton implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Constructor
	 * 
	 * @param text
	 * @param {@link ActionListener}
	 * @param toolTip
	 * 
	 */
	public BaseButton(String text, ActionListener actionListener, String toolTip) {
		setAllValues(text, actionListener, toolTip);
	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param icon
	 * @param {@link ActionListener}
	 * @param toolTip
	 */
	public BaseButton(Icon icon, ActionListener actionListener, String toolTip) {
		setAllValues(icon, actionListener, toolTip);
	}

	/**
	 * It sets the value.
	 * 
	 * @param text
	 * @param {@link ActionListener}
	 * @param toolTip
	 */
	public void setAllValues(String text, ActionListener actionListener,
			String toolTip) {
		setText(text);
		setToolTipText(toolTip);
		addActionListener(actionListener);
	}

	/**
	 * 
	 * It sets value.
	 * 
	 * @param icon
	 * @param {@link ActionListener}
	 * @param toolTip
	 */
	public void setAllValues(Icon icon, ActionListener actionListener,
			String toolTip) {
		setBorderPainted(false);
		setBorder(null);
		setMargin(new Insets(0, 0, 0, 0));
		setContentAreaFilled(false);
		setIcon(icon);
		setToolTipText(toolTip);
		addActionListener(actionListener);
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
}
