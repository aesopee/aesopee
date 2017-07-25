package com.slickcode.baseframework.page;

import java.awt.Component;
import java.util.List;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.baseframework.utils.BaseDimension;
import com.slickcode.baseframework.utils.BaseUtils;
import com.slickcode.baseframework.utils.ComponentEnum;

public abstract class BaseSideMenu extends BasePanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1048781226113298299L;
	private int maxwidth = 0;
	private int rowHeight = 20;
	private int heightPadding = 10;
	private int widthPadding = 10;

	private BaseDimension baseDimension = new BaseDimension(0, 0);
	private BaseLabel headerLabel;

	private JLabel picLabel;

	private List<? extends Component> componentList;

	public abstract List<? extends Component> getSideMenu();

	public abstract String getSidePanelHeaderName();

	private void populateHeader() {
		headerLabel = new BaseLabel(getSidePanelHeaderName(), ComponentEnum.HEADER);
		maxwidth = BaseUtils.getMax(maxwidth, headerLabel.getPreferredSize()
				.getWidth());
		add(headerLabel);
	}

	private void populateImage() {
		picLabel = new JLabel(BaseUtils.populateImage("images/down_arrow.png", 20,20));
		maxwidth = BaseUtils.getMax(maxwidth, picLabel.getPreferredSize()
				.getWidth());
		add(picLabel);
	}

	private void populateSideComponents() {
		componentList = getSideMenu();

		if (null != componentList) {
			for (Component component : componentList) {
				maxwidth = BaseUtils.getMax(maxwidth, component
						.getPreferredSize().getWidth());
				add(component);
			}
		}
	}

	@Override
	public void arrangeComponents() {
		int fromLeft = baseDimension.getWidth();
		int fromTop = baseDimension.getHeight();

		/**
		 * Header
		 */
		fromLeft = fromLeft + widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(headerLabel, fromLeft, fromTop, maxwidth, rowHeight,
				headerLabel.getPreferredSize().getWidth(), 0, baseDimension,
				Alignment.CENTER);

		/**
		 * Image
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(picLabel, fromLeft, fromTop, maxwidth, rowHeight, 0,
				0, baseDimension, Alignment.CENTER);

		/**
		 * Components
		 */
		if (null != componentList) {
			for (Component component : componentList) {
				fromLeft = widthPadding;
				fromTop = baseDimension.getHeight() + heightPadding;
				BaseUtils.setBound(component, fromLeft, fromTop, maxwidth,
						rowHeight, 0, 0, baseDimension, Alignment.CENTER);

			}
		}
	}
	
	@Override
	public JPanel createPanel() {
		populateHeader();
		populateImage();
		populateSideComponents();
		loadPanelData();
		return this;
	}
	
	@Override
	public void loadPanelData() {
		arrangeComponents();
	}
}