package com.slickcode.basevalidatorframework;

import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.components.IBaseComponent;
import com.slickcode.baseframework.utils.StringUtilities;

public class NonEmtryValidator extends BaseDocumentListner {
	private String componentName;
	
	public NonEmtryValidator(String componentName, IBaseComponent component, BaseLabel erroreLabel, boolean isMandatory) {
		super(component, erroreLabel, isMandatory);
		this.componentName = componentName;
	}

	@Override
	protected boolean validate() {
		if (isMandatory() && StringUtilities.isEmpty(getFieldValue())) {
			setError(componentName + " cannot be empty.");
			return false;
		}
		return true;
	}
}
