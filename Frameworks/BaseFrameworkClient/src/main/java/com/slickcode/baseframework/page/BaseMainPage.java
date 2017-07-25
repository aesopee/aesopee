package com.slickcode.baseframework.page;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import com.slickcode.baseframework.constants.PageDimension;
import com.slickcode.baseframework.utils.ConfigLoader;

/**
 * 
 * It extends {@link JFrame}.
 * 
 * @author Sourabh
 * 
 */
public abstract class BaseMainPage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CardLayout cardLayout;
	private JPanel cardPanel;

	/**
	 * 
	 * Constructor
	 * 
	 * @param {@link BasePage}
	 */
	public BaseMainPage(BasePage startingPage) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		setToolTipProperties();
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(cardPanel);
		setResizable(false);
		setBoundaries();
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle();
		setJMenuBar(populateMenuBar());
		showPanel(startingPage);
	}

	/**
	 * It sets tool tip properties.
	 */
	private void setToolTipProperties() {
		UIManager.put("ToolTip.background", Color.gray);
		UIManager.put("ToolTip.border",
				BorderFactory.createLineBorder(Color.red));
		ToolTipManager.sharedInstance().setDismissDelay(15000);
	}

	/**
	 * It sets title.
	 */
	public void setTitle() {
		setTitle(ConfigLoader.getPropertyValue("title"));
	}

	/**
	 * It returns cardPanel.
	 * 
	 * @return
	 */
	public JPanel getCardPanel() {
		return cardPanel;
	}

	/**
	 * It sets boundaries to the Page.
	 */
	private void setBoundaries() {
		PageDimension pageDimension = PageDimension.getInstance();
		setSize(pageDimension.getMainPageWidth(),
				pageDimension.getMainPageHeight());
	}

	/**
	 * 
	 * It shows the given Page from cards.
	 * 
	 * @param {@link BasePage}
	 */
	public void showPanel(BasePage basePage) {
		cardPanel.removeAll();
		cardPanel.add(new JScrollPane(basePage));
		cardPanel.revalidate();
		cardPanel.repaint();
	}

	/**
	 * It populates the menu bar.
	 * 
	 * @return {@link JMenuBar}
	 */
	public abstract JMenuBar populateMenuBar();
}