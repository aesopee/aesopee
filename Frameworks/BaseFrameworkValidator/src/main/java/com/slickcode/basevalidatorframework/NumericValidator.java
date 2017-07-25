package com.slickcode.basevalidatorframework;

import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.components.IBaseComponent;
import com.slickcode.baseframework.utils.StringUtilities;

public class NumericValidator extends BaseDocumentListner {

	private String componentName;

	public NumericValidator(String componentName, IBaseComponent component,
			BaseLabel erroreLabel, boolean isMandatory) {
		super(component, erroreLabel, isMandatory);
		this.componentName = componentName;	
	}

	@Override
	protected boolean validate() {
		boolean isEmpty = StringUtilities.isEmpty(getFieldValue());
		if (isMandatory() && isEmpty) {
			setError(componentName + " cannot be empty.");
			return false;
		} else if (!isEmpty && !NumericUtilities.isInteger(getFieldValue())) {
			setError(componentName + " should contain only number.");
			return false;
		}
		return true;
	}

}
