package com.slickcode.basevalidatorframework;

import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.components.IBaseComponent;
import com.slickcode.baseframework.utils.StringUtilities;

public class AmountValidator extends BaseDocumentListner {

	private String componentName;

	public AmountValidator(String componentName, IBaseComponent component,
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
		} else if (!isEmpty && !NumericUtilities.isAmount(getFieldValue())) {
			setError(componentName + " should contain proper amount.");
			return false;
		}
		return true;
	}

}
