package com.slickcode.baseframework.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.baseframework.utils.BaseDimension;


public abstract class BasePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected BaseDimension baseDimension = new BaseDimension(0, 20);
	
	public BasePanel() {
		setLayout(null);
	}

	public BasePanel(LayoutManager layoutManager) {
		super(layoutManager);
	}
	
	public abstract JPanel createPanel();
	
	public abstract void loadPanelData();
	
	public abstract void arrangeComponents();
	
	public abstract IPanelBean getPanelDataOnSubmit(); 
	
	public abstract boolean validatePanelData();

	public abstract void applyRights();
	
	public void setTitle(String title) {
		Border redLine = BorderFactory.createLineBorder(Color.red);
		Border raisedBevel = BorderFactory.createRaisedBevelBorder();
		Border loweredBevel = BorderFactory.createLoweredBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(raisedBevel,
				loweredBevel);
		compound = BorderFactory.createCompoundBorder(redLine, compound);

		compound = BorderFactory.createCompoundBorder(compound, new EmptyBorder(20, 20,
				20, 20));
		compound = BorderFactory.createTitledBorder(compound, title,
				TitledBorder.LEFT, TitledBorder.TOP, new Font(getName(),
						Font.BOLD | Font.ITALIC, 20), Color.BLACK);
		setBorder(compound);
	}
	
	@Override
	public Dimension getPreferredSize() {
		setPreferredSize(new Dimension(baseDimension.getWidth(), baseDimension.getHeight()));
		return super.getPreferredSize();
	}
}
