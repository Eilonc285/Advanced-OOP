package calculator.lab6;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Calculator extends JFrame implements CalcListener, ActionListener {

//	private ToolBar toolBar;
	private JMenuBar menuBar;
	private JMenu file;
	private JMenu help;
	private JMenuItem exit;
	private JMenuItem manual;
	private JTextField outputHistory;
	private JTextField output;
	private Keys keys;
	private String lastOperator;
	private double previousNumber;
	private boolean nextNumber;
	private boolean reDoEntry = false;

	public Calculator() {
		super("Calculator");

		setLayout(new GridBagLayout());

		menuBar = new JMenuBar();
		file = new JMenu("Flie");
		help = new JMenu("Help");
		exit = new JMenuItem("Exit");
		manual = new JMenuItem("Instruction manual");

		exit.addActionListener(this);
		exit.setName("Exit");
		manual.addActionListener(this);
		manual.setName("Instruction manual");

		file.add(exit);
		help.add(manual);

		menuBar.add(file);
		menuBar.add(help);

//		toolBar = new ToolBar();
		output = new JTextField(10);
		previousNumber = 0;
		lastOperator = "+";
		nextNumber = true;

		keys = new Keys();

		output.setEditable(false);
		output.setFont(output.getFont().deriveFont(20.0f));
		output.setText(Integer.toString((int) previousNumber));

		keys.setCalcListener(this);

		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 0.5;
		gc.weighty = 0.2;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.PAGE_START;
		gc.fill = GridBagConstraints.BOTH;

		add(menuBar, gc);

		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;

		add(output, gc);

		gc.gridy = 2;
		gc.weighty = 1;

		add(keys, gc);

		setSize(550, 360);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

	@Override
	public void submitKey(JButton btn) {
		String txt = btn.getText();
		if (keys.isNumber(txt)) {
			if (nextNumber) {
				if (!reDoEntry) {
					previousNumber = Double.parseDouble(output.getText());
				}
				output.setText(txt);
				nextNumber = false;
				reDoEntry = false;
			} else {
				output.setText(output.getText() + txt);
			}
		} else {
			nextNumber = true;
			// Update textField by special buttons //
			if (txt.equals("%")) {
				if (lastOperator.equals("*") || lastOperator.equals("/")) {
					output.setText(Double.toString(Double.parseDouble(output.getText()) / 100));
				} else if (lastOperator.equals("+") || lastOperator.equals("-")) {
					output.setText(Double.toString(previousNumber * Double.parseDouble(output.getText()) / 100));
				}
			} else if (txt.equals("+/-")) {
				output.setText(Double.toString(-1 * Double.parseDouble(output.getText())));
			} else if (txt.equals("sqrt")) {
				output.setText(Double.toString(Math.sqrt(Double.parseDouble(output.getText()))));
			} else if (txt.equals("1/x")) {
				output.setText(Double.toString(1 / Double.parseDouble(output.getText())));
			} else if (txt.equals(".")) {
				output.setText(output.getText() + ".");
				nextNumber = false;
			} else if (txt.equals("C")) {
				previousNumber = 0;
				output.setText("0");
				lastOperator = "+";
			} else if (txt.equals("Backspace")) {
				if (output.getText().length() > 1) {
					output.setText(output.getText().substring(0, output.getText().length() - 1));
					nextNumber = false;
				} else {
					output.setText("0");
					nextNumber = true;
					reDoEntry = true;
				}
			} else if (txt.equals("CE")) {
				output.setText("0");
				reDoEntry = true;
			} else {
				output.setText(Double.toString(calculateHistory()));
				previousNumber = 0;
				lastOperator = txt;
			}

		}
	}

	private double calculateHistory() {
		switch (lastOperator) {
		case "+":
			return previousNumber + Double.parseDouble(output.getText());
		case "-":
			return previousNumber - Double.parseDouble(output.getText());
		case "*":
			return previousNumber * Double.parseDouble(output.getText());
		case "/":
			return previousNumber / Double.parseDouble(output.getText());
		case "=":
			return Double.parseDouble(output.getText());
		default:
			return previousNumber;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (((JMenuItem) e.getSource()).getName()) {
		case "Exit":
			System.exit(0);
			break;
		case "Instruction manual":
			instructionsFrame();
		default:
			break;
		}

	}

	private void instructionsFrame() {
		class instructionsFrame extends JFrame {

			private JTextArea instructions;

			public instructionsFrame() {
				super("Instructions");

				instructions = new JTextArea();
				instructions.setText("Instructions of how to use a basic calculator.");
				instructions.setFont(instructions.getFont().deriveFont(20.0f));
				instructions.setEditable(false);
				setLayout(new BorderLayout());
				setSize(500, 500);
				setVisible(true);
				add(new JScrollPane(instructions), BorderLayout.CENTER);
			}
		}
		new instructionsFrame();
	}

}
