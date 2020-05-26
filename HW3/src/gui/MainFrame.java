package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import utilities.GameDriver;

/**
 * 
 * @author Nir Barel, Eilon Cohen
 *
 */
public class MainFrame extends JFrame {

	private MenuBar menuBar = new MenuBar();
	private ToolBar toolBar = new ToolBar();
	private MyCanvas canvas = new MyCanvas();
	private int numOfVehicles = 25;
	private int numOfJunctions = 11;
	private ActionListener startListener;

	public MainFrame() {
		super("Road system");
		setSize(800, 700);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(menuBar, BorderLayout.NORTH);
		add(canvas, BorderLayout.CENTER);
		add(toolBar, BorderLayout.SOUTH);

		pack();

		setVisible(true);

		HashMap<String, JMenuItem> menuButtons = menuBar.getButtons();
		HashMap<String, JButton> toolButtons = toolBar.getButtons();

		menuButtons.get("exit").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuButtons.get("blueBack").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.setBackground(Color.BLUE);
				toolBar.setBackground(Color.BLUE);
			}
		});
		menuButtons.get("noneBack").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.setBackground(null);
				toolBar.setBackground(null);
			}
		});
		menuButtons.get("blueVic").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.setRandomCarColor(false);
				canvas.setCarColor(Color.BLUE);
				canvas.repaint();
			}
		});
		menuButtons.get("magentaVic").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.setRandomCarColor(false);
				canvas.setCarColor(Color.MAGENTA);
				canvas.repaint();
			}
		});
		menuButtons.get("orangeVic").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.setRandomCarColor(false);
				canvas.setCarColor(Color.ORANGE);
				canvas.repaint();
			}
		});
		menuButtons.get("randomVic").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.randomizeCarColors();
				canvas.setRandomCarColor(true);
				canvas.repaint();
			}
		});
		menuButtons.get("help").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runDialog();
			}
		});

		toolButtons.get("create").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				makeDialog();
			}
		});

		toolButtons.get("stop").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameDriver.setPause(true);
			}
		});

		toolButtons.get("resume").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameDriver.setPause(false);
				synchronized (GameDriver.class) {
					GameDriver.class.notifyAll();
				}
			}
		});

		toolButtons.get("info").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void runDialog() {
		JOptionPane.showMessageDialog(this, "Home Work 3\nGUI @ Threads");
	}

	public void refresh() {
		canvas.repaint();
		repaint();
	}

	private void makeDialog() {

		ChangeListener vicListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider theSlider = (JSlider) e.getSource();
				numOfVehicles = new Integer(theSlider.getValue());
			}
		};

		ChangeListener juncListener = new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider theSlider = (JSlider) e.getSource();
				numOfJunctions = new Integer(theSlider.getValue());
			}
		};

		JOptionPane optionPane = new JOptionPane();
		JSlider vicSlider = getSlider(optionPane, 0, 50, 5, vicListener);
		JSlider juncSlider = getSlider(optionPane, 3, 20, 1, juncListener);
		optionPane.add(vicSlider);
		optionPane.add(juncSlider);
		optionPane.setMessage(new Object[] { "Number of junctions", juncSlider, "Number of vehicles", vicSlider });
		optionPane.setOptionType(JOptionPane.OK_CANCEL_OPTION);
		Dimension dim = optionPane.getSize();
		dim.setSize(600, 300);
		optionPane.setSize(dim);
		optionPane.setPreferredSize(dim);
		JDialog dialog = optionPane.createDialog(this, "My Slider");
		dialog.setVisible(true);
	}

	private JSlider getSlider(final JOptionPane optionPane, int min, int max, int tick, ChangeListener cl) {
		JSlider slider = new JSlider(min, max);
		slider.setMajorTickSpacing(tick);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
//		ChangeListener changeListener = new ChangeListener() {
//			public void stateChanged(ChangeEvent changeEvent) {
//				JSlider theSlider = (JSlider) changeEvent.getSource();
//				if (!theSlider.getValueIsAdjusting()) {
//					optionPane.setInputValue(new Integer(theSlider.getValue()));
//				}
//			}
//		};
		slider.addChangeListener(cl);
		return slider;
	}

	public int getNumOfVehicles() {
		return numOfVehicles;
	}

	public int getNumOfJunctions() {
		return numOfJunctions;
	}

	public void setStartListener(ActionListener listener) {
		startListener = listener;
		toolBar.getButtons().get("start").addActionListener(startListener);
	}

}
