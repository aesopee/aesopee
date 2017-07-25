package com.slickcode.baseframework.constants;

import java.awt.GraphicsEnvironment;

import com.slickcode.baseframework.utils.ConfigLoader;

/**
 * 
 * It defines the boundaries of the page.
 * 
 * @author Sourabh
 * 
 */
public final class PageDimension {

	private static PageDimension PAGE_DIMENSION;

	private int headerMenuHeight;
	private int sideMenuWidth;

	private static final int MAIN_PAGE_HEIGHT = getScreenWorkingHeight();
	private static final int MAIN_PAGE_WIDTH = getScreenWorkingWidth();

	/**
	 * Constructor
	 */
	private PageDimension() {
		this.headerMenuHeight = Integer.parseInt(ConfigLoader
				.getPropertyValue("headerMenuHeight"));
		this.sideMenuWidth = Integer.parseInt(ConfigLoader
				.getPropertyValue("sideMenuWidth"));
	}

	/**
	 * It gives current instance of {@link PageDimension}.
	 * 
	 * @return {@link PageDimension}
	 */
	public static final PageDimension getInstance() {
		if (null == PAGE_DIMENSION) {
			PAGE_DIMENSION = new PageDimension();
		}
		return PAGE_DIMENSION;
	}

	/**
	 * 
	 * It gives width of actual area of working.
	 * 
	 * @return int
	 */
	private static int getScreenWorkingWidth() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds().width;
	}

	/**
	 * 
	 * It gives height of actual area of working.
	 * 
	 * @return int
	 */
	private static int getScreenWorkingHeight() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getMaximumWindowBounds().height;
	}

	/**
	 * 
	 * It gives height of page.
	 * 
	 * @return int
	 */

	public int getMainPageHeight() {
		return MAIN_PAGE_HEIGHT;
	}

	/**
	 * 
	 * It gives width of page.
	 * 
	 * @return int
	 */
	public int getMainPageWidth() {
		return MAIN_PAGE_WIDTH;
	}

	/**
	 * 
	 * It gives height of header.
	 * 
	 * @return int
	 */
	public int getHeaderHeight() {
		return headerMenuHeight;
	}

	/**
	 * 
	 * It gives width of header.
	 * 
	 * @return int
	 */
	public int getHeaderWidth() {
		return MAIN_PAGE_WIDTH;
	}

	/**
	 * 
	 * It gives width of Side Menu.
	 * 
	 * @return int
	 */
	public int getSideMenuWidth() {
		return sideMenuWidth;
	}

	/**
	 * 
	 * It gives height of Side Menu.
	 * 
	 * @return int
	 */
	public int getSideMenuHeight() {
		return MAIN_PAGE_HEIGHT - headerMenuHeight;
	}

	/**
	 * 
	 * It gives height of working panel.
	 * 
	 * @return int
	 */
	public int getMainPanelHeight() {
		return MAIN_PAGE_HEIGHT - headerMenuHeight;
	}

	/**
	 * 
	 * It gives width of working panel.
	 * 
	 * @return int
	 */
	public int getMainPanelWidth() {
		return MAIN_PAGE_WIDTH - sideMenuWidth;
	}
}
