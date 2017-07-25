package com.slickcode.basevalidatorframework;

import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.components.IBaseComponent;
import com.slickcode.baseframework.utils.StringUtilities;

public class AlphabatesValidator extends BaseDocumentListner {

	private String componentName;

	public AlphabatesValidator(String componentName, IBaseComponent component,
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
		} else if (!isEmpty && !StringUtilities.containsMandatoryAlphabates(getFieldValue())) {
			setError(componentName + " should only contain aphabates.");
			return false;
		}
		return true;
	}

}