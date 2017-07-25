package com.slickcode.baseframework.table;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.slickcode.baseframework.components.BaseLabel;
import com.slickcode.baseframework.utils.BaseDimension;
import com.slickcode.baseframework.utils.BaseUtils;
import com.slickcode.baseframework.utils.ComponentEnum;

public class PaginatedTablePanelOld extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8411289987483543448L;

	private int labelColumnWidth = 0;
	private int rowHeight = 20;
	private int heightPadding = 10;
	private int widthPadding = 10;

	private BaseDimension baseDimension = new BaseDimension(0, 0);

	private BaseLabel noOfRowsLabel;
	private JComboBox<String> rowComboBox;
	private String[] noOfRows = {"10", "50" };
	private int lastPage = 1;

	private JButton goToFirstPageButton;
	private JButton goToBackPageButton;
	private JButton goToLastPageButton;
	private JButton goToNextPageButton;
	private BaseLabel currentPageLabel;

	private JScrollPane scrollPane;
	private JPanel panel;

	private Object[][] data;

	public PaginatedTablePanelOld(String[] columnNames, Object[][] data,
			Class<? extends Object>[] classTypes, boolean[] editableTypes) {
		this.data = data;
		setLayout(null);
		populateHeader(columnNames, classTypes, editableTypes);
		calculateLastPageNumber();
		populateNavigableButtons(columnNames, classTypes, editableTypes);
		// populateTable(columnNames, classTypes, editableTypes);
		rearrangeTable(1, columnNames, classTypes, editableTypes);
		arrangeComponents();
	}

	private void populateHeader(final String[] columnNames,
			final Class<? extends Object>[] classTypes,
			final boolean[] editableTypes) {
		noOfRowsLabel = new BaseLabel("No. Of Rows", ComponentEnum.LABEL);
		rowComboBox = new JComboBox<String>();
		rowComboBox.setModel(new DefaultComboBoxModel<String>(noOfRows));

		rowComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				calculateLastPageNumber();
				rearrangeTable(1, columnNames, classTypes, editableTypes);
			}
		});

		labelColumnWidth = BaseUtils.getMax(labelColumnWidth, noOfRowsLabel
				.getPreferredSize().getWidth()
				+ rowComboBox.getPreferredSize().getWidth() + widthPadding);

		noOfRowsLabel.setLabelFor(rowComboBox);
		add(noOfRowsLabel);
		add(rowComboBox);
	}

	private void populateTable(int destinationPageNumber, String[] columnNames,
			final Class<? extends Object>[] classTypes,
			final boolean[] editableTypes) {

		int numberOfRows = Integer.parseInt(noOfRows[rowComboBox
				.getSelectedIndex()]);
		Object[][] newData = new Object[numberOfRows][];

		for (int i = 0; i < numberOfRows; i++) {
			int maxLength = data.length;
			int currentRow = (numberOfRows * (destinationPageNumber - 1)) + i;
			if (currentRow < maxLength) {
				newData[i] = data[(numberOfRows * (destinationPageNumber - 1))
						+ i];
			} else {
				break;
			}
		}
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel(null, columnNames) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5204063646929730889L;
			Class<? extends Object>[] types = classTypes;
			boolean[] canEdit = editableTypes;

			public Class<? extends Object> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};
		table.setModel(model);
		for (Object[] object : data) {
			model.addRow(object);
		}


		scrollPane = new JScrollPane(table);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		panel = new JPanel();
		panel.add(scrollPane);

		labelColumnWidth = BaseUtils.getMax(labelColumnWidth, panel
				.getPreferredSize().getWidth());

		add(panel);
	}

	private void populateNavigableButtons(final String[] columnNames,
			final Class<? extends Object>[] classTypes,
			final boolean[] editableTypes) {
		goToBackPageButton = new JButton("<");
		goToFirstPageButton = new JButton("<<");
		goToLastPageButton = new JButton(">>");
		goToNextPageButton = new JButton(">");
		currentPageLabel = new BaseLabel("1", ComponentEnum.LABEL);

		labelColumnWidth = BaseUtils.getMax(labelColumnWidth,
				goToBackPageButton.getPreferredSize().getWidth()
						+ goToFirstPageButton.getPreferredSize().getWidth()
						+ goToLastPageButton.getPreferredSize().getWidth()
						+ goToNextPageButton.getPreferredSize().getWidth()
						+ currentPageLabel.getPreferredSize().getWidth()
						+ (widthPadding * 4));

		goToBackPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				rearrangeTable(
						Integer.parseInt(currentPageLabel.getText()) - 1,
						columnNames, classTypes, editableTypes);
			}
		});

		goToFirstPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				rearrangeTable(1, columnNames, classTypes, editableTypes);
			}
		});

		goToLastPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				rearrangeTable(lastPage, columnNames, classTypes, editableTypes);
			}
		});

		goToNextPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				rearrangeTable(
						Integer.parseInt(currentPageLabel.getText()) + 1,
						columnNames, classTypes, editableTypes);
			}
		});

		add(goToBackPageButton);
		add(goToFirstPageButton);
		add(currentPageLabel);
		add(goToLastPageButton);
		add(goToNextPageButton);
	}

	private void arrangeComponents() {
		int fromLeft = baseDimension.getWidth();
		int fromTop = baseDimension.getHeight();

		/**
		 * Header
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		List<Component> componentList = new ArrayList<Component>();
		componentList.add(noOfRowsLabel);
		componentList.add(rowComboBox);
		baseDimension = BaseUtils.arrangeComponentInRow(componentList,
				labelColumnWidth, widthPadding, 2, fromLeft, fromTop,
				rowHeight, heightPadding, baseDimension);

		/**
		 * Table
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		BaseUtils.setBound(panel, fromLeft, fromTop, labelColumnWidth, 200,
				panel.getPreferredSize().getWidth(), 0, baseDimension,
				Alignment.CENTER);
		/**
		 * Navigable Button Row
		 */
		fromLeft = widthPadding;
		fromTop = baseDimension.getHeight() + heightPadding;
		List<Component> componentList2 = new ArrayList<Component>();
		componentList2.add(goToFirstPageButton);
		componentList2.add(goToBackPageButton);
		componentList2.add(currentPageLabel);
		componentList2.add(goToNextPageButton);
		componentList2.add(goToLastPageButton);
		baseDimension = BaseUtils.arrangeComponentInRow(componentList2,
				labelColumnWidth, widthPadding, 5, fromLeft, fromTop,
				rowHeight, heightPadding, baseDimension);

		Dimension dimension = new Dimension();
		dimension.setSize(baseDimension.getWidth(), baseDimension.getHeight());

		setPreferredSize(dimension);
	}

	private void enableAllButtons() {
		goToBackPageButton.setEnabled(true);
		goToFirstPageButton.setEnabled(true);
		goToNextPageButton.setEnabled(true);
		goToLastPageButton.setEnabled(true);
	}

	private void rearrangeTable(int destinationPageNumber,
			String[] columnNames, final Class<? extends Object>[] classTypes,
			final boolean[] editableTypes) {
		enableAllButtons();
		currentPageLabel.setText(destinationPageNumber + "");
		populateTable(destinationPageNumber, columnNames, classTypes,
				editableTypes);
		defineButtonDisabilities();
	}

	private void calculateLastPageNumber() {
		int length = data.length;
		int currentNumberOfRows = Integer.parseInt(noOfRows[rowComboBox
				.getSelectedIndex()]);
		int mod = length % currentNumberOfRows;
		length = length - mod;
		int result = length / currentNumberOfRows;

		if (mod == 0) {
			lastPage = result;
		} else {
			lastPage = result + 1;
		}
	}

	private void defineButtonDisabilities() {
		enableAllButtons();
		int currentPage = Integer.parseInt(currentPageLabel.getText());
		if (currentPage == 1) {
			goToBackPageButton.setEnabled(false);
			goToFirstPageButton.setEnabled(false);
		}

		if (currentPage == lastPage) {
			goToNextPageButton.setEnabled(false);
			goToLastPageButton.setEnabled(false);
		}

	}
}
