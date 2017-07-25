package com.slickcode.baseframework.listner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * It implements {@link ActionListener}.
 * 
 * @author Sourabh
 * 
 */
public abstract class BaseActionListner implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		mask();
		boolean success = performAction(e);

		if (success) {
			onSuccess();
		} else {
			onFailure();
		}
		unmask();
	}
	
	/**
	 * 
	 * It performs action and returns boolean.
	 * 
	 * @param {@link ActionListener}
	 * @return boolean
	 */
	public abstract boolean performAction(ActionEvent e);

	/**
	 * 
	 * It will be called if performAction method returns true.
	 * 
	 */
	public abstract void onSuccess();

	/**
	 * 
	 * It will be called if performAction method returns false.
	 * 
	 */
	public abstract void onFailure();

	/**
	 * 
	 * It will be called at the start of performAction method.
	 * 
	 */
	public abstract void mask();

	/**
	 * 
	 * It will be called at the end of performAction method.
	 * 
	 */
	public abstract void unmask();

}
