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
	private JButton build = new JButton("Build a map");
	private JButton clone = new JButton("Clone a car");
	private JButton reports = new JButton("Reports");
	private HashMap<String, JButton> buttons = new HashMap<String, JButton>();

	public ToolBar() {

		buttons.put("create", create);
		buttons.put("start", start);
		buttons.put("stop", stop);
		buttons.put("resume", resume);
		buttons.put("info", info);
		buttons.put("build", build);
		buttons.put("clone", clone);
		buttons.put("reports", reports);

		Dimension dim = getPreferredSize();
		dim.setSize(800, 60);
		setPreferredSize(dim);

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridwidth = 3;

		gc.insets = new Insets(0, 3, 0, 1);

		gc.fill = GridBagConstraints.HORIZONTAL;

		gc.gridx = 0;
		gc.gridy = 0;

		add(create, gc);

		gc.insets = new Insets(0, 1, 0, 1);

		gc.gridx = 3;

		add(start, gc);

		gc.gridx = 6;

		add(stop, gc);

		gc.gridx = 9;

		add(resume, gc);

		gc.gridx = 12;

		gc.insets = new Insets(0, 1, 0, 3);

		add(info, gc);

		gc.gridwidth = 5;
		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 0;

		gc.insets = new Insets(0, 3, 0, 1);

		add(build, gc);

		gc.gridx = 5;
		gc.insets = new Insets(0, 1, 0, 1);

		add(clone, gc);

		gc.gridx = 10;
		gc.insets = new Insets(0, 1, 0, 3);

		add(reports, gc);
	}

	public HashMap<String, JButton> getButtons() {
		return buttons;
	}

}
