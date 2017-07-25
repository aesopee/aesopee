package com.slickcode.baseframework.table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicHTML;
import javax.swing.plaf.basic.BasicRadioButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.View;

import com.slickcode.baseframework.domain.ColumnDataVO;
import com.slickcode.baseframework.domain.IPanelBean;
import com.slickcode.baseframework.panel.BasePanel;
import com.slickcode.baseframework.utils.BaseDimension;
import com.slickcode.baseframework.utils.BaseUtils;

public final class PaginatedTablePanel extends BasePanel {
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
	private JScrollPane scrollPane;
	private int itemsPerPage;
	private int selectedIndexOfPage;

	private Object[][] data;
	private List<ColumnDataVO> columnDataVOList;

	public PaginatedTablePanel(List<ColumnDataVO> columnDataVOList,
			int itemsPerPage, int width, int height) {
		this.columnDataVOList = new ArrayList<ColumnDataVO>();
		this.columnDataVOList.addAll(columnDataVOList);
		this.itemsPerPage = itemsPerPage;
		baseDimension.setWidth(width);
		baseDimension.setHeight(height);
		populateModel();
	}

	@Override
	public JPanel createPanel() {
		populateNavigations();
		populateTable();
		initLinkBox(1);
		return this;
	}

	private void populateTable() {
		table = new JTable(model);
		JTableHeader header = table.getTableHeader();
		header.setDefaultRenderer(new HeaderRenderer(table));
		table.setFillsViewportHeight(true);
		table.setIntercellSpacing(new Dimension());
		table.setShowGrid(false);
		table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		table.setRowSorter(sorter);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int size = columnDataVOList.size();

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.LEADING);
		int actualWidth = 0;
		int modOfWidth = 0;
		int width = 0;
		int totalWidth = baseDimension.getWidth() - 20;
		for (int i = 0; i < size; i++) {
			actualWidth = columnDataVOList.get(i).getWidth();
			modOfWidth = (actualWidth * totalWidth) % 100;
			width = ((actualWidth * totalWidth) - modOfWidth) / 100;
			
			table.getColumnModel().getColumn(i).setPreferredWidth(width);
			table.getColumnModel()
					.getColumn(i)
					.setCellRenderer(columnDataVOList.get(i).getCellAlignment());
		}

		scrollPane = new JScrollPane(table);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);

		baseDimension.setWidth(BaseUtils.getMax(baseDimension.getWidth() - 20,
				table.getPreferredSize().getWidth()));
	}

	private void populateNavigations() {
		box.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		add(box);
	}

	@Override
	public void loadPanelData() {
		for (Object[] object : data) {
			model.addRow(object);
		}
		initLinkBox(1);
		arrangeComponents();
	}

	@Override
	public void arrangeComponents() {
		int widthPadding = 0;

		int fromLeft = widthPadding;
		int heightPadding = 20;
		int fromTop = heightPadding;
		fromLeft = fromLeft + widthPadding;

		System.out.println("Width box" + box.getPreferredSize().getWidth());
		BaseUtils.setBound(box, 0, fromTop, baseDimension.getWidth(), 20, 0, 0,
				new BaseDimension(0, 0), Alignment.CENTER);

		fromLeft = widthPadding;
		fromTop = heightPadding + heightPadding + 20;
		BaseUtils.setBound(scrollPane, 0, fromTop, baseDimension.getWidth(),
				baseDimension.getHeight(), 0, 0, baseDimension,
				Alignment.CENTER);
		System.out.println("Width scrollPane"
				+ scrollPane.getPreferredSize().getWidth());
		System.out.println("Width table"
				+ table.getPreferredSize().getWidth());
		setPreferredSize(new Dimension(baseDimension.getWidth() + 20,
				baseDimension.getHeight() + 50 + 20));
		System.out.println("panel width" + getPreferredSize().getWidth());
	}

	private void populateModel() {

		model = new DefaultTableModel(0, columnDataVOList.size()) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 5204063646929730889L;

			public Class<? extends Object> getColumnClass(int columnIndex) {
				return columnDataVOList.get(columnIndex).getClassType();
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return columnDataVOList.get(columnIndex).isEditable();
			}

			public String getColumnName(int columnIndex) {
				return columnDataVOList.get(columnIndex).getHeader();
			}
		};
		sorter = new TableRowSorter<>(model);
	}

	private void initLinkBox(final int currentPageIndex) {
		selectedIndexOfPage = currentPageIndex;
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

	private static class HeaderRenderer implements TableCellRenderer {

		DefaultTableCellRenderer renderer;

		public HeaderRenderer(JTable table) {
			renderer = (DefaultTableCellRenderer) table.getTableHeader()
					.getDefaultRenderer();
			renderer.setHorizontalAlignment(JLabel.CENTER);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int col) {
			return renderer.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, col);
		}
	}

	/**
	 * @return the data
	 */
	public Object[][] getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object[][] data) {
		this.data = data;
	}

	@Override
	public IPanelBean getPanelDataOnSubmit() {
		return null;
	}

	@Override
	public boolean validatePanelData() {
		return false;
	}

	@Override
	public void applyRights() {
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @return the selectedIndexOfPage
	 */
	public int getSelectedIndexOfPage() {
		return selectedIndexOfPage;
	}

	/**
	 * @param selectedIndexOfPage
	 *            the selectedIndexOfPage to set
	 */
	public void setSelectedIndexOfPage(int selectedIndexOfPage) {
		this.selectedIndexOfPage = selectedIndexOfPage;
	}

	/**
	 * @return the itemsPerPage
	 */
	public int getItemsPerPage() {
		return itemsPerPage;
	}

	/**
	 * @param itemsPerPage
	 *            the itemsPerPage to set
	 */
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
}

class LinkViewRadioButtonUI extends BasicRadioButtonUI {
	private static Dimension size = new Dimension();
	private static Rectangle viewRect = new Rectangle();
	private static Rectangle iconRect = new Rectangle();
	private static Rectangle textRect = new Rectangle();

	@Override
	public Icon getDefaultIcon() {
		return null;
	}

	@Override
	public synchronized void paint(Graphics g, JComponent c) {
		// AbstractButton b = (AbstractButton) c;
		Font f = c.getFont();
		g.setFont(f);
		FontMetrics fm = c.getFontMetrics(f);

		Insets i = c.getInsets();
		c.getSize(size);
		viewRect.x = i.left;
		viewRect.y = i.top;
		viewRect.width = size.width - i.right - viewRect.x;
		viewRect.height = size.height - i.bottom - viewRect.y;
		iconRect.setBounds(0, 0, 0, 0); // .x = iconRect.y = iconRect.width =
										// iconRect.height = 0;
		textRect.setBounds(0, 0, 0, 0); // .x = textRect.y = textRect.width =
										// textRect.height = 0;

		if (c.isOpaque()) {
			g.setColor(c.getBackground());
			g.fillRect(0, 0, size.width, size.height);
		}

		String text;
		AbstractButton b;
		if (c instanceof AbstractButton) {
			b = (AbstractButton) c;
			text = SwingUtilities.layoutCompoundLabel(b, fm,
					b.getText(),
					null, // altIcon != null ? altIcon : getDefaultIcon(),
					b.getVerticalAlignment(), b.getHorizontalAlignment(),
					b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
					viewRect, iconRect, textRect, 0); // b.getText() == null ? 0
														// :
														// b.getIconTextGap());
		} else {
			return;
		}

		ButtonModel model = b.getModel();
		g.setColor(c.getForeground());
		if (!model.isSelected() && !model.isPressed() && !model.isArmed()
				&& b.isRolloverEnabled() && model.isRollover()) {
			g.drawLine(viewRect.x, viewRect.y + viewRect.height, viewRect.x
					+ viewRect.width, viewRect.y + viewRect.height);
		}
		View v = (View) c.getClientProperty(BasicHTML.propertyKey);
		if (v == null) {
			paintText(g, c, textRect, text);
		} else {
			v.paint(g, textRect);
		}
	}

}
