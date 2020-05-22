package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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

		HashMap<String, JMenuItem> hash = menuBar.getButtons();

		hash.get("exit").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		hash.get("blueBack").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.setBackground(Color.BLUE);
				toolBar.setBackground(Color.BLUE);
			}
		});
		hash.get("noneBack").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.setBackground(null);
				toolBar.setBackground(null);
			}
		});
		hash.get("blueVic").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.setCarColor(Color.BLUE);
				canvas.repaint();
			}
		});
		hash.get("magentaVic").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.setCarColor(Color.MAGENTA);
				canvas.repaint();
			}
		});
		hash.get("orangeVic").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.setCarColor(Color.ORANGE);
				canvas.repaint();
			}
		});
		hash.get("randomVic").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				switch (rand.nextInt(3)) {
				case 0:
					canvas.setCarColor(Color.BLUE);
					break;
				case 1:
					canvas.setCarColor(Color.MAGENTA);
					break;
				case 2:
					canvas.setCarColor(Color.ORANGE);
					break;
				default:
					canvas.setCarColor(Color.BLUE);
					break;
				}
				canvas.repaint();
			}
		});
		hash.get("help").addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runDialog();
			}
		});
	}

	private void runDialog() {
		JOptionPane.showMessageDialog(this, "Home Work 3\nGUI @ Threads");
	}

}
