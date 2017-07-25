package com.slickcode.baseframework.components;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 * 
 * It extends {@link DefaultCellEditor}. Giving more customization.
 * 
 * @author Sourabh
 *
 */
public class BaseButtonEditor extends DefaultCellEditor {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected BaseButton button;

    /**
     * 
     * Constructor
     * 
     * @param {@link JCheckBox}
     * @param {@link BaseButton}
     */
    public BaseButtonEditor(JCheckBox checkBox, BaseButton button) {
        super(checkBox);
        this.button = button;
        this.button.setOpaque(false);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        return button;
    }
}