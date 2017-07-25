package com.slickcode.baseframework.utils;

import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuUtils {

	public static JMenu populateJMenu(String title, Integer mnemonic) {
		JMenu menu = new JMenu();
		menu.setFont(FontUtils.MENU_BAR_FONT);
		menu.setText(title);
		if (null != mnemonic) {
			menu.setMnemonic(mnemonic);
		}
		return menu;
	}

	public static JMenuItem populateJMenuItem(String title, Icon icon,
			Integer keyStroke, Integer mnemonic) {
		JMenuItem menuItem = new JMenuItem();
		menuItem.setFont(FontUtils.MENU_BAR_FONT);
		menuItem.setText(title);
		menuItem.setIcon(icon);
		if (null != keyStroke) {
			KeyStroke ctrl = KeyStroke.getKeyStroke(keyStroke, Toolkit
					.getDefaultToolkit().getMenuShortcutKeyMask());
			menuItem.setAccelerator(ctrl);
		}

		if (null != mnemonic) {
			menuItem.setMnemonic(mnemonic);
		}

		return menuItem;
	}
}
