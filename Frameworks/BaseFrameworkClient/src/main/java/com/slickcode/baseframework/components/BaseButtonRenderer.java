package com.slickcode.baseframework.components;

import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * 
 * It extends {@link BaseButton} and implements {@link TableCellRenderer}.
 * 
 * @author Sourabh
 * 
 */
public class BaseButtonRenderer extends BaseButton implements TableCellRenderer {

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
	 */
	public BaseButtonRenderer(String text, ActionListener actionListener,
			String toolTip) {
		super(text, actionListener, toolTip);
		setOpaque(false);
	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param icon
	 * @param {@link ActionListener}
	 * @param toolTip
	 */
	public BaseButtonRenderer(Icon icon, ActionListener actionListener,
			String toolTip) {
		super(icon, actionListener, toolTip);
		setOpaque(false);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		return this;
	}

}
