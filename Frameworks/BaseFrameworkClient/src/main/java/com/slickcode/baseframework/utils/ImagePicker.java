package com.slickcode.baseframework.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ImagePicker {

	private JDialog dialog;
	
	public ImagePicker(Component parent, int x, int y) {
		dialog = new JDialog();
		dialog.setModal(true);
		dialog.setLayout(null);
		dialog.setPreferredSize(new Dimension(510,600));
		final JPanel mainPanel=populateData(parent);
		JScrollPane jScrollPane = new JScrollPane(mainPanel);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 

		
		final JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(new JButton("Hiii"));
		panel.add(jScrollPane);
		panel.setPreferredSize(new Dimension(500,500));
		
		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(100,100));
		final JButton button = new JButton("Open");
		panel2.add(button);
		final Component parent1 = dialog;
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
//				Point l = button.getLocationOnScreen();
//				ImagePicker imagePicker = new ImagePicker(parent1, l.x, l.y);
				try {
					openFile(parent1, mainPanel);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				dateOFExpiryField.setText(new DatePicker(parent, l.x, l.y+dateOFExpiryField.getHeight()).setPickedDate());
			}
		});
		
		dialog.add(panel);	
		dialog.add(panel2);

		panel.setBounds(0, 0,
				510, 510);
	
		panel2.setBounds(0, 0,
				510, 50);
	
		
		dialog.pack();
//		d.setLocationRelativeTo(parent);
		dialog.setLocation(x, y+5);
//		displayDate();
		dialog.setVisible(true);

	}

	private JPanel populateData(Component parent){
		JPanel panel = new JPanel();
//		openFile(parent, panel);
		return panel;
	}
	
	
    static JLabel label;
    static BufferedImage icon;
    static File oldfile;
    JFileChooser fc;
    ImageFilter1 fJavaFilter = new ImageFilter1();
    static File fFile;

	boolean openFile(Component parent, JPanel panel) throws IOException {
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Open File");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.setCurrentDirectory(new File("."));
        fc.setFileFilter(fJavaFilter);
        int result = fc.showOpenDialog(parent);
        if (result == JFileChooser.CANCEL_OPTION) {
            return true;
        } else if (result == JFileChooser.APPROVE_OPTION) {

            fFile = fc.getSelectedFile();
            oldfile = fFile;
            FileInputStream fileInputStream = new FileInputStream(fFile);
            icon = ImageIO.read(fileInputStream);
            label = new JLabel(new ImageIcon(icon));
            label.setBounds(500, 0, 1000, 500);
            label.setVisible(true);
            panel.add(label);
            // Set the position of its text, relative to its icon:
            label.setVerticalTextPosition(JLabel.BOTTOM);
            label.setHorizontalTextPosition(JLabel.CENTER);
        } else {
            return false;
        }
        return true;
    }
}
class ImageFilter1 extends javax.swing.filechooser.FileFilter {
    public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith(".png")
                || f.getName().toLowerCase().endsWith(".jpg")
                || f.getName().toLowerCase().endsWith(".jif")
                || f.isDirectory();
    }

    public String getDescription() {
        return "Image files (*.png)";
    }

}

