package com.slickcode.baseframework.page;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import com.slickcode.baseframework.utils.SpringUtilities;

public abstract class ButtonPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4087517062189402554L;
	
	public ButtonPanel(BaseMainPage baseMainPage, int rows, int columns) {
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		addButtonsToGrid(baseMainPage);
		SpringUtilities.makeCompactGrid(this,
                rows, columns, //rows, cols
                6, 6,        //initX, initY
                6, 6);       //xPad, yPad
 	}
	
	private void addButtonsToGrid(BaseMainPage baseMainPage) {
		List<JButton> buttonList = populateButtonList(baseMainPage);
		for(JButton button: buttonList) {
			add(button);
		}
	}

	public abstract List<JButton> populateButtonList(BaseMainPage baseMainPage);
				
	}
