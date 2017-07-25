package com.slickcode.baseframework.components;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import com.slickcode.baseframework.domain.SelectItem;
import com.slickcode.baseframework.utils.ComponentEnum;

/**
 * 
 * It extends {@link JComboBox}
 * 
 * @author Sourabh
 * 
 * @param <E>
 */
public class BaseComboBox<E> extends JComboBox<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BaseComboBoxModel model;

	/**
	 * 
	 * Constructor
	 * 
	 * @param List
	 *            of {@link SelectItem}
	 * @param {@link ComponentEnum}
	 */
	@SuppressWarnings("unchecked")
	public BaseComboBox(List<SelectItem> selectItemList,
			ComponentEnum componentEnum) {
		super();
		setEditable(true);
		model = new BaseComboBoxModel(selectItemList);
		setModel((ComboBoxModel<E>) model);
		changeComponentLayout(componentEnum);
	}

	/**
	 * 
	 * Constructor
	 * 
	 * @param {@link ComponentEnum}
	 */
	public BaseComboBox(ComponentEnum componentEnum) {
		super();
		setEditable(true);
		changeComponentLayout(componentEnum);
	}

	/**
	 * 
	 * Setting list of {@link SelectItem} to {@link BaseComboBoxModel}.
	 * 
	 * @param List
	 *            of {@link SelectItem}
	 */
	@SuppressWarnings("unchecked")
	public void setSelectItemList(List<SelectItem> selectItemList) {
		model = new BaseComboBoxModel(selectItemList);
		setModel((ComboBoxModel<E>) model);
	}

	/**
	 * 
	 * It changes layout as per the {@link ComponentEnum}.
	 * 
	 * @param {@link ComponentEnum}
	 */
	public void changeComponentLayout(ComponentEnum componentEnum) {
		switch (componentEnum) {

		case ERROR:
			setBorder(BorderFactory.createLineBorder(Color.red));
			setForeground(Color.RED);
			setFont(new Font(getName(), Font.PLAIN, 11));
			break;

		case VALUE:
			setBorder(BorderFactory.createLineBorder(Color.black));
			setForeground(Color.BLACK);
			setFont(new Font(getName(), Font.PLAIN, 11));
			break;

		default:
			break;
		}
	}

	/**
	 * 
	 * It fetches the selected element value and returns it as {@link String}.
	 * 
	 * @return {@link String}
	 */
	public String getSelectedValue() {
		if (getSelectedIndex() >= 0) {
			SelectItem item = model.getElementAt(getSelectedIndex());
			if (null == item) {
				return null;
			}
			return item.getValue();
		}
		return null;
	}

	/**
	 * 
	 * It fetches the selected element label and returns it as {@link String}.
	 * 
	 * @return {@link String}
	 */
	public String getSelectedLabel() {
		if (getSelectedIndex() >= 0) {
			SelectItem item = model.getElementAt(getSelectedIndex());
			if (null == item) {
				return null;
			}
			return item.getLabel();
		}
		return null;
	}
}
