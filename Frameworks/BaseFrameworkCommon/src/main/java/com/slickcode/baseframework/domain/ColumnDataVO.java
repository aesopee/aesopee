package com.slickcode.baseframework.domain;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.slickcode.baseframework.utils.AlignmentEnum;

public class ColumnDataVO {
	private String header;
	private int width;
	private Class<? extends Object> classType;
	private boolean editable;
	private AlignmentEnum alignmentEnum;
	private static DefaultTableCellRenderer rightRenderer;
	private static DefaultTableCellRenderer leftRenderer;
	private static DefaultTableCellRenderer centerRenderer;

	static {
		rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);

		leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

		centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public ColumnDataVO(String header, int width,
			Class<? extends Object> classType, boolean editable) {
		super();
		this.header = header;
		this.width = width;
		this.classType = classType;
		this.editable = editable;
		this.alignmentEnum = AlignmentEnum.CENTER;
	}

	public ColumnDataVO(String header, int width,
			Class<? extends Object> classType, boolean editable,
			AlignmentEnum alignmentEnum) {
		super();
		this.header = header;
		this.width = width;
		this.classType = classType;
		this.editable = editable;
		this.alignmentEnum = alignmentEnum;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header
	 *            the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the classType
	 */
	public Class<? extends Object> getClassType() {
		return classType;
	}

	/**
	 * @param classType
	 *            the classType to set
	 */
	public void setClassType(Class<? extends Object> classType) {
		this.classType = classType;
	}

	/**
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * @param editable
	 *            the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * @return the alignmentEnum
	 */
	public AlignmentEnum getAlignmentEnum() {
		return alignmentEnum;
	}

	/**
	 * @param alignmentEnum
	 *            the alignmentEnum to set
	 */
	public void setAlignmentEnum(AlignmentEnum alignmentEnum) {
		this.alignmentEnum = alignmentEnum;
	}

	/**
	 * @return the alignmentEnum
	 */
	public DefaultTableCellRenderer getCellAlignment() {
		switch (getAlignmentEnum()) {
		case LEFT:
			return leftRenderer;
		case RIGHT:
			return rightRenderer;
		default:
			return centerRenderer;
		}
	}

}
