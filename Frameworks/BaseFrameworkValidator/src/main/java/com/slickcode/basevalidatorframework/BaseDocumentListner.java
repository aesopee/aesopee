package com.slickcode.basevalidatorframework;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.components.IBaseComponent;
import com.slickcode.baseframework.utils.ComponentEnum;

public abstract class BaseDocumentListner implements DocumentListener {

	private BaseLabel erroreLabel;
	private IBaseComponent component;
	private String error;
	private boolean isMandatory = false;

	public BaseDocumentListner(IBaseComponent component, BaseLabel erroreLabel, boolean isMandatory) {
		this.erroreLabel = erroreLabel;
		this.component = component;
		this.isMandatory = isMandatory;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		performValidation();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		performValidation();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		performValidation();
	}

	public boolean validateOnSubmit() {
		return performValidation();
	}

	private boolean performValidation() {
		boolean result = validate();
		if (result) {
			revoveErrors();
		} else {
			applyErrors();
		}
		return result;
	}

	private void applyErrors() {
		erroreLabel.addError(getError());
		component.changeComponentLayout(ComponentEnum.ERROR);
	}

	private void revoveErrors() {
		erroreLabel.addNoError();
		component.changeComponentLayout(ComponentEnum.VALUE);
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the fieldValue
	 */
	public String getFieldValue() {
		return component.getText();
	}

	/**
	 * @return the isMandatory
	 */
	public boolean isMandatory() {
		return isMandatory;
	}

	/**
	 * @param isMandatory the isMandatory to set
	 */
	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	protected abstract boolean validate();

}
