package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author Nir Barel, Eilon Cohen
 *
 */
public class ToolBar extends JPanel {

	private JButton create = new JButton("Create road system");
	private JButton start = new JButton("Start");
	private JButton stop = new JButton("Stop");
	private JButton resume = new JButton("Resume");
	private JButton info = new JButton("Info");
	private HashMap<String, JButton> buttons = new HashMap<String, JButton>();

	public ToolBar() {

		buttons.put("create", create);
		buttons.put("start", start);
		buttons.put("stop", stop);
		buttons.put("resume", resume);
		buttons.put("info", info);

		Dimension dim = getPreferredSize();
		dim.setSize(800, 30);
		setPreferredSize(dim);

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 0.5;

		gc.insets = new Insets(0, 3, 0, 1);

		gc.fill = GridBagConstraints.HORIZONTAL;

		gc.gridx = 0;
		gc.gridy = 0;

		add(create, gc);

		gc.insets = new Insets(0, 1, 0, 1);

		gc.gridx = 1;

		add(start, gc);

		gc.gridx = 2;

		add(stop, gc);

		gc.gridx = 3;

		add(resume, gc);

		gc.gridx = 4;

		gc.insets = new Insets(0, 1, 0, 3);

		add(info, gc);

	}

	public HashMap<String, JButton> getButtons() {
		return buttons;
	}

}
