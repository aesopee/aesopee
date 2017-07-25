package com.slickcode.baseframework.components;

import com.slickcode.baseframework.utils.ComponentEnum;

/**
 * 
 * It gives way for Layout design.
 * 
 * @author Sourabh
 *
 */
public interface IBaseComponent {
	
	public void changeComponentLayout(ComponentEnum componentEnum);

	public String getText();
}
