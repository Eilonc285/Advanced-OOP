package gui;

import java.util.HashMap;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	private JMenu file = new JMenu("File");
	private JMenu background = new JMenu("Background");
	private JMenu vehiclesColor = new JMenu("Vehicles color");
	private JMenu help = new JMenu("Help");
	private JMenuItem exit = new JMenuItem("Exit");
	private JMenuItem blue = new JMenuItem("Blue");
	private JMenuItem none = new JMenuItem("None");
	private JMenuItem vBlue = new JMenuItem("Blue");
	private JMenuItem vMagenta = new JMenuItem("Magenta");
	private JMenuItem vOrange = new JMenuItem("Orange");
	private JMenuItem vRandom = new JMenuItem("Random");
	private JMenuItem iHelp = new JMenuItem("Help");
	private HashMap<String, JMenuItem> buttons = new HashMap();

	public MenuBar() {

		buttons.put("exit", exit);
		buttons.put("blueBack", blue);
		buttons.put("noneBack", none);
		buttons.put("blueVic", vBlue);
		buttons.put("magentaVic", vMagenta);
		buttons.put("orangeVic", vOrange);
		buttons.put("randomVic", vRandom);
		buttons.put("help", iHelp);

		file.add(exit);
		background.add(blue);
		background.add(none);
		vehiclesColor.add(vBlue);
		vehiclesColor.add(vMagenta);
		vehiclesColor.add(vOrange);
		vehiclesColor.add(vRandom);
		help.add(iHelp);

		add(file);
		add(background);
		add(vehiclesColor);
		add(help);

	}

	public HashMap<String, JMenuItem> getButtons() {
		return buttons;
	}

}
