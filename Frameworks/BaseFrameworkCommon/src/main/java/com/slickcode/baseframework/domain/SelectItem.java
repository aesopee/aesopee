package com.slickcode.baseframework.domain;

public class SelectItem {
	
	private String label;
	private String value;
	
	public SelectItem() {
	}
	
	public SelectItem(String value, String label) {
		this.value = value;
		this.label = label;
	}
	
	public SelectItem(Integer intValue, String label) {
		String value = "";
		if(null != intValue){
			value = String.valueOf(intValue);
		}
		this.value = value;
		this.label = label;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return label;
	}
}