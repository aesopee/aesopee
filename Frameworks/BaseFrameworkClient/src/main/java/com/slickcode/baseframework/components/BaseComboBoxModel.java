package com.slickcode.baseframework.components;

import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.slickcode.baseframework.domain.SelectItem;

/**
 * 
 * It extends {@link ComboBoxModel} with {@link SelectItem} as parameter.
 * 
 * @author Sourabh
 *
 */
public class BaseComboBoxModel implements ComboBoxModel<SelectItem> {

	private SelectItem selectedItem;
	private List<SelectItem> selectItemList;
	protected EventListenerList listenerList = new EventListenerList();

	/**
	 * 
	 * Constructor
	 * 
	 * @param List of {@link SelectItem}
	 */
	public BaseComboBoxModel(List<SelectItem> selectItemList) {
		this.selectItemList = selectItemList;
	}

	@Override
	public int getSize() {
		return selectItemList.size();
	}

	@Override
	public SelectItem getElementAt(int index) {
		return selectItemList.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		listenerList.add(ListDataListener.class, l);
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		listenerList.remove(ListDataListener.class, l);
	}

	@Override
	public void setSelectedItem(Object newValue) {
		if (newValue instanceof SelectItem) {
			SelectItem selectItem = (SelectItem) newValue;
			for (SelectItem innerSelectItem : selectItemList) {
				if (selectItem.getValue().equals(innerSelectItem.getValue())) {
					selectedItem = innerSelectItem;
					fireContentsChanged(this, -1, -1);
					break;
				}
			}

		}
	}

	@Override
	public SelectItem getSelectedItem() {
		return selectedItem;
	}

	/**
	 * 
	 * It gets fired when selected item is found.
	 * 
	 * @param source
	 * @param index0
	 * @param index1
	 */
	protected void fireContentsChanged(Object source, int index0, int index1) {
        Object[] listeners = listenerList.getListenerList();
        ListDataEvent e = null;

        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ListDataListener.class) {
                if (null == e) {
                    e = new ListDataEvent(source, ListDataEvent.CONTENTS_CHANGED, index0, index1);
                }
                ((ListDataListener)listeners[i+1]).contentsChanged(e);
            }
        }
    }

}
