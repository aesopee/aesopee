package com.slickcode.basevalidatorframework;

import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.components.IBaseComponent;
import com.slickcode.baseframework.utils.StringUtilities;

public class DateValidator extends BaseDocumentListner {

	private String componentName;
	private String dateFormat;
	
	public DateValidator(String componentName, IBaseComponent component,
			BaseLabel erroreLabel, boolean isMandatory, String dateFormat) {
		super(component, erroreLabel, isMandatory);
		this.componentName = componentName;
		this.dateFormat = dateFormat;
	}

	@Override
	protected boolean validate() {
		boolean isEmpty = StringUtilities.isEmpty(getFieldValue());
		if (isMandatory() && isEmpty) {
			setError(componentName + " cannot be empty.");
			return false;
		} else if (!isEmpty && !DateUtilities.isDate(getFieldValue(), dateFormat)) {
			setError(componentName + " should contain only Date in " + dateFormat + " format.");
			return false;
		}
		return true;
	}

}
