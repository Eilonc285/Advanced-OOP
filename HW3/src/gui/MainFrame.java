package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
				// TODO Auto-generated method stub
			}
		});

		toolButtons.get("start").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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

}
