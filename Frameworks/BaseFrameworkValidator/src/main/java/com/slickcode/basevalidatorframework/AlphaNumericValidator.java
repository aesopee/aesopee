package com.slickcode.basevalidatorframework;

import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.components.IBaseComponent;
import com.slickcode.baseframework.utils.StringUtilities;

public class AlphaNumericValidator extends BaseDocumentListner {

	private String componentName;

	public AlphaNumericValidator(String componentName, IBaseComponent component,
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
		} else if (!isEmpty && !StringUtilities.containsMandatoryAlphaNumber(getFieldValue())) {
			setError(componentName + " should contain aphabates and number.");
			return false;
		}
		return true;
	}
}
