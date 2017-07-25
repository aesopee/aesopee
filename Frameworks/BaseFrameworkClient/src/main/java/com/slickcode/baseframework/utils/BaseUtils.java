package com.slickcode.baseframework.utils;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

public final class BaseUtils {

	public static void setBound(Component component, int fromLeft, int fromTop,
			int width, int height, double preferredWidth,
			double preferredHeight, BaseDimension baseDimension,
			Alignment alignment) {
		int widthSpacing = 0;
		if (preferredWidth != 0.0d) {
			widthSpacing = BaseUtils.calculatePerfectSpacing(preferredWidth,
					width);
		}

		int heightSpacing = 0;
		if (preferredHeight != 0.0d) {
			heightSpacing = BaseUtils.calculatePerfectSpacing(preferredHeight,
					height);
		}
		component.setBounds(fromLeft + widthSpacing, fromTop + heightSpacing,
				width - (widthSpacing * 2), height - (heightSpacing * 2));
		baseDimension.setSize(
				getMax(baseDimension.getWidth(), fromLeft + width),
				getMax(baseDimension.getHeight(), fromTop + height));
	}

	public static void setBound(List<? extends Component> componenList,
			int fromLeft, int fromTop, int width, int height,
			double preferredWidth, double preferredHeight,
			BaseDimension baseDimension, Alignment alignment, int widthPadding) {

		int size = componenList.size();
		int totalWidthPadding = widthPadding * (size - 1);
		int componentWidth = (width - totalWidthPadding) / size;
		for (Component component : componenList) {
			setBound(component, fromLeft, fromTop, componentWidth, height,
					preferredWidth, preferredHeight, baseDimension, alignment);
			fromLeft = fromLeft + componentWidth + widthPadding;
		}
	}

	public static int getMax(double value1, double value2) {
		int val1 = new Double(value1).intValue();
		int val2 = new Double(value2).intValue();
		if (val1 == val2) {
			return val1;
		} else if (val1 > val2) {
			return val1;
		} else {
			return val2;
		}
	}

	private static int calculatePerfectSpacing(double preferredWidth,
			double columnWidth) {
		double difference = (columnWidth - preferredWidth) / 2;
		return new Double(difference).intValue();
	}

	public static int calculateWidth(List<? extends Component> componentList,
			int fieldColumnWidth, int widthPadding, int numberOfCompInRow) {
		int size = componentList.size();
		int extraComponents = size % numberOfCompInRow;

		for (int i = 0; i < size - extraComponents; i = i + numberOfCompInRow) {
			int maxValue = 0;
			for (int j = 0; j < numberOfCompInRow; j++) {
				maxValue = getMax(maxValue, componentList.get(i + j)
						.getPreferredSize().getWidth());
			}
			fieldColumnWidth = BaseUtils.getMax(fieldColumnWidth,
					(maxValue * numberOfCompInRow)
							+ (widthPadding * (numberOfCompInRow - 1)));
		}
		int maxValue = 0;
		for (int i = size - extraComponents; i < size; i = size + 1) {

			maxValue = getMax(maxValue, componentList.get(i).getPreferredSize()
					.getWidth());

		}
		fieldColumnWidth = BaseUtils.getMax(fieldColumnWidth,
				(maxValue * numberOfCompInRow)
						+ (widthPadding * (numberOfCompInRow - 1)));

		return fieldColumnWidth;
	}

	public static BaseDimension arrangeComponentInRow(
			List<? extends Component> componentList, int fieldColumnWidth,
			int widthPadding, int numberOfCompInRow, int fromLeft, int fromTop,
			int rowHeight, int heightPadding, BaseDimension baseDimension) {
		int size = componentList.size();
		int extraComponents = size % numberOfCompInRow;
		int totalWidthPadding = widthPadding * (numberOfCompInRow - 1);
		int componentWidth = (fieldColumnWidth - totalWidthPadding)
				/ numberOfCompInRow;

		for (int i = 0; i < size - extraComponents; i = i + numberOfCompInRow) {
			BaseUtils.setBound(
					new ArrayList<Component>(componentList.subList(i, i
							+ numberOfCompInRow)), fromLeft, fromTop,
					fieldColumnWidth, rowHeight, 0, 0, baseDimension,
					Alignment.CENTER, widthPadding);
			fromTop = fromTop + rowHeight + heightPadding;
		}

		fieldColumnWidth = (componentWidth * extraComponents)
				+ (widthPadding * (extraComponents - 1));
		for (int i = size - extraComponents; i < size; i = size + 1) {
			BaseUtils.setBound(
					new ArrayList<Component>(componentList.subList(i, size)),
					fromLeft, fromTop, fieldColumnWidth, rowHeight, 0, 0,
					baseDimension, Alignment.CENTER, widthPadding);
			fromTop = fromTop + rowHeight + heightPadding;
		}

		return baseDimension;
	}

	public static ImageIcon populateImage(String imagePath, int width,
			int height) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream is = loader.getResourceAsStream(imagePath);

		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image dimg = myPicture.getScaledInstance(width, height,
				Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		return imageIcon;
	}

}
