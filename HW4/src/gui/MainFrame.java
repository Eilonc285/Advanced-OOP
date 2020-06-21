package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import components.Junction;
import components.Moked;
import components.Road;
import components.Vehicle;
import utilities.GameDriver;

/**
 * 
 * @author Nir Barel, Eilon Cohen
 *
 */
public class MainFrame extends JFrame {

	private MenuBar menuBar = new MenuBar();
	private ToolBar toolBar = new ToolBar();
	private JLayeredPane layeredPane = new JLayeredPane();
	private MyCanvas canvas = new MyCanvas();
	private int numOfVehicles = 25;
	private int numOfJunctions = 11;
	private JTable table;

	public MainFrame() {
		super("Road system");
		setSize(800, 700);
		setLayout(new BorderLayout());
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				if (Moked.getMoked().verifyReports()) {
					System.exit(0);
				}
				String ObjButtons[] = { "Yes", "No" };
				int PromptResult = JOptionPane.showOptionDialog(null, "Some reports are not authorized. Exit anyway?",
						"Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons,
						ObjButtons[1]);
				if (PromptResult == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});

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
				if (GameDriver.getDriving() != null) {
					ArrayList<Vehicle> vehicles = GameDriver.getDriving().getVehicles();
					String[] columns = { "Vehicle #", "Type", "Location", "Time on loc", "Speed" };
					String[][] data = new String[vehicles.size()][5];

					for (int i = 0; i < vehicles.size(); i++) {
						data[i][0] = Integer.toString(vehicles.get(i).getId());
						data[i][1] = vehicles.get(i).getVehicleType().toString();
						data[i][2] = (vehicles.get(i).getCurrentRoutePart() instanceof Junction)
								? vehicles.get(i).getCurrentRoutePart().toString()
								: "From " + vehicles.get(i).getLastRoad().getStartJunction().toString() + " to "
										+ vehicles.get(i).getLastRoad().getEndJunction().toString();
						data[i][3] = Long.toString(vehicles.get(i).getTimeOnCurrentPart());
						double roadSpeed;
						if (vehicles.get(i).getCurrentRoutePart() instanceof Road) {
							roadSpeed = ((Road) vehicles.get(i).getCurrentRoutePart()).getMaxSpeed();
						} else {
							roadSpeed = 0;
						}
						double speed = Math.min(vehicles.get(i).getVehicleType().getAverageSpeed(), roadSpeed);
						data[i][4] = Double.toString(speed);
					}
					table = new JTable(data, columns);
					table.getColumnModel().getColumn(2).setMinWidth(300);
					makeInfo();
				}
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

	private void makeInfo() {
		JOptionPane optionPane = new JOptionPane();
		optionPane.setMessage(new Object[] { new JScrollPane(table) });
		optionPane.setPreferredSize(new Dimension(500, 500));
		JDialog dialog = optionPane.createDialog(this, "Vehicles info");
		dialog.setResizable(true);
		dialog.setVisible(true);
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
//		optionPane.add(vicSlider);
//		optionPane.add(juncSlider);
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
		toolBar.getButtons().get("start").addActionListener(listener);
	}

}
