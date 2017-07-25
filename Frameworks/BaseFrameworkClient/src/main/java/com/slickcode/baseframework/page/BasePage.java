package com.slickcode.baseframework.page;

import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.slickcode.baseframework.constants.PageDimension;
import com.slickcode.baseframework.panel.BasePanel;

/**
 * 
 * It extends {@link BasePanel}.
 * @author Sourabh
 *
 */
public abstract class BasePage extends BasePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4940837915383746578L;

	public BasePage() {
		arrangePage();
	}

	private void arrangePage() {
		setLayout(null);
		Insets insets = getInsets();
		BaseHeaderPanel headerPanel = getHeaderPanel();
		// JPanel sideMenuPanel = populateSideMenu();
		JPanel mainPanel = createPanel();
		JScrollPane jScrollPane = new JScrollPane(mainPanel);
		jScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.add(jScrollPane);

		add(headerPanel);

		PageDimension pageDimension = PageDimension.getInstance();
		headerPanel
				.setBounds(insets.left, insets.top,
						pageDimension.getHeaderWidth(),
						pageDimension.getHeaderHeight());

		if (null != panel) {
			add(panel);
			panel.setBounds(insets.left + pageDimension.getSideMenuWidth(),
					insets.top + pageDimension.getHeaderHeight(),
					pageDimension.getMainPanelWidth() - 10,
					pageDimension.getMainPanelHeight() - 50);
		}
	}

	public abstract JPanel populateSideMenu();

	public abstract BaseHeaderPanel getHeaderPanel();

}
