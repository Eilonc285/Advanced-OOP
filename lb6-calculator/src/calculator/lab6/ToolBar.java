package calculator.lab6;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class ToolBar extends JPanel {
	public ToolBar() {
		Dimension dim = getPreferredSize();
		dim.setSize(550, 30);
		setPreferredSize(dim);

		setLayout(new FlowLayout());

	}

}
