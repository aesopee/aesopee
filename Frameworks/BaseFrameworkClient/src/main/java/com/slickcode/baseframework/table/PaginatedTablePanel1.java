package com.slickcode.baseframework.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.baseframework.panel.BasePanel;

public final class PaginatedTablePanel1 extends BasePanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 165180098597537431L;
	private static final LinkViewRadioButtonUI LINKVIEW_RADIOBUTTON_UI = new LinkViewRadioButtonUI();
	private static final int LR_PAGE_SIZE = 5;
	private final Box box = Box.createHorizontalBox();
	private DefaultTableModel model;
	private transient TableRowSorter<? extends TableModel> sorter;
	private JTable table;
	private int itemsPerPage;

	private String[] columnNames; 
	private Object[][] data;
	private Class<? extends Object>[] classTypes;
	private boolean[] editableTypes;
	
	public PaginatedTablePanel1(String[] columnNames,
			Class<? extends Object>[] classTypes, boolean[] editableTypes,
			int itemsPerPage, int width, int height) {
		super(new BorderLayout());
		this.columnNames = columnNames;
		this.classTypes = classTypes;
		this.editableTypes = editableTypes;
		this.itemsPerPage = itemsPerPage;
//		setPreferredSize(new Dimension(width, height));
		baseDimension.setWidth(width);
		baseDimension.setHeight(height);
	}

	private void populateModel(final String[] columnNames,
			final Class<? extends Object>[] classTypes,
			final boolean[] editableTypes) {

		model = new DefaultTableModel(null, columnNames) {
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
		sorter = new TableRowSorter<>(model);

	}

	private void initLinkBox(final int currentPageIndex) {
		sorter.setRowFilter(new RowFilter<TableModel, Integer>() {
			@Override
			public boolean include(
					Entry<? extends TableModel, ? extends Integer> entry) {
				int ti = currentPageIndex - 1;
				int ei = entry.getIdentifier();
				return ti * itemsPerPage <= ei
						&& ei < ti * itemsPerPage + itemsPerPage;
			}
		});

		int startPageIndex = currentPageIndex - LR_PAGE_SIZE;
		if (startPageIndex <= 0) {
			startPageIndex = 1;
		}

		int rowCount = model.getRowCount();
		int v = rowCount % itemsPerPage == 0 ? 0 : 1;
		int maxPageIndex = rowCount / itemsPerPage + v;
		int endPageIndex = currentPageIndex + LR_PAGE_SIZE - 1;
		if (endPageIndex > maxPageIndex) {
			endPageIndex = maxPageIndex;
		}

		box.removeAll();
		if (startPageIndex >= endPageIndex) {
			// if I only have one page, Y don't want to see pagination buttons
			// suggested by erServi
			return;
		}

		ButtonGroup bg = new ButtonGroup();
		JRadioButton f = makePrevNextRadioButton(itemsPerPage, 1, "|<",
				currentPageIndex > 1);
		box.add(f);
		bg.add(f);
		JRadioButton p = makePrevNextRadioButton(itemsPerPage,
				currentPageIndex - 1, "<", currentPageIndex > 1);
		box.add(p);
		bg.add(p);
		box.add(Box.createHorizontalGlue());
		for (int i = startPageIndex; i <= endPageIndex; i++) {
			JRadioButton c = makeRadioButton(itemsPerPage, currentPageIndex, i);
			box.add(c);
			bg.add(c);
		}
		box.add(Box.createHorizontalGlue());
		JRadioButton n = makePrevNextRadioButton(itemsPerPage,
				currentPageIndex + 1, ">", currentPageIndex < maxPageIndex);
		box.add(n);
		bg.add(n);
		JRadioButton l = makePrevNextRadioButton(itemsPerPage, maxPageIndex,
				">|", currentPageIndex < maxPageIndex);
		box.add(l);
		bg.add(l);
		box.revalidate();
		box.repaint();
	}

	private JRadioButton makeRadioButton(final int itemsPerPage, int current,
			final int target) {
		JRadioButton radio = new JRadioButton(String.valueOf(target)) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7207591839572751830L;

			@Override
			protected void fireStateChanged() {
				ButtonModel model = getModel();
				if (model.isEnabled()) {
					if (model.isPressed() && model.isArmed()) {
						setForeground(Color.GREEN);
					} else if (model.isSelected()) {
						setForeground(Color.RED);
						// } else if (isRolloverEnabled() && model.isRollover())
						// {
						// setForeground(Color.BLUE);
					}
				} else {
					setForeground(Color.GRAY);
				}
				super.fireStateChanged();
			}
		};
		radio.setForeground(Color.BLUE);
		radio.setUI(LINKVIEW_RADIOBUTTON_UI);
		if (target == current) {
			radio.setSelected(true);
		}
		radio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initLinkBox(target);
			}
		});
		return radio;
	}

	private JRadioButton makePrevNextRadioButton(final int itemsPerPage,
			final int target, String title, boolean flag) {
		JRadioButton radio = new JRadioButton(title);
		radio.setForeground(Color.BLUE);
		radio.setUI(LINKVIEW_RADIOBUTTON_UI);
		radio.setEnabled(flag);
		radio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				initLinkBox(target);
			}
		});
		return radio;
	}

	public static void main(String... args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}

	public static void createAndShowGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		JFrame frame = new JFrame("TablePagination");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// frame.getContentPane().add(new MainPanel());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public JPanel createPanel() {
		return this;
	}

	@Override
	public void loadPanelData() {

		populateModel(columnNames, classTypes, editableTypes);
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		table.setIntercellSpacing(new Dimension());
		table.setShowGrid(false);
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		table.setRowSorter(sorter);
		
		for (Object[] object : data) {
			model.addRow(object);
		}

		initLinkBox(1);
		box.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		add(box, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		add(scrollPane);
	}

	@Override
	public void arrangeComponents() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return the data
	 */
	public Object[][] getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object[][] data) {
		this.data = data;
	}

	@Override
	public IPanelBean getPanelDataOnSubmit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validatePanelData() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void applyRights() {
		// TODO Auto-generated method stub
		
	}
	
	
}
