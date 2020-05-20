package calculator.lab6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Keys extends JPanel implements ActionListener {

	private CalcListener listener;

	private JButton backspace = new JButton("Backspace"), clearEntry = new JButton("CE"), clear = new JButton("C"),
			divide = new JButton("/"), squareRoot = new JButton("sqrt"), multiply = new JButton("*"),
			oneOver = new JButton("1/x"), minus = new JButton("-"), percentage = new JButton("%"),
			plus = new JButton("+"), equals = new JButton("="), point = new JButton("."),
			plusMinus = new JButton("+/-");
	private JButton[] nonNumbers;
	private JButton[] numbers;

	public Keys() {

		Dimension dim = getPreferredSize();
		dim.setSize(550, 300);
		setPreferredSize(dim);

		numbers = new JButton[10];
		for (int i = 0; i < 10; i++) {
			numbers[i] = new JButton(String.format("%s", Integer.toString(i)));
			numbers[i].setFont(numbers[i].getFont().deriveFont(20.0f));
			numbers[i].setForeground(Color.blue);
		}

		JButton[] temp = { backspace, clearEntry, divide, squareRoot, multiply, clear, oneOver, minus, percentage, plus,
				equals, point, plusMinus };
		nonNumbers = temp;
		for (int i = 0; i < nonNumbers.length; i++) {
			nonNumbers[i].setFont(nonNumbers[i].getFont().deriveFont(20.0f));
			nonNumbers[i].setForeground(Color.red);
		}

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 0.3;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 3;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 180);

		add(backspace, gc);

		gc.weightx = 0.2;
		gc.weighty = 0.5;
		gc.gridx = 3;
		gc.gridy = 0;
		gc.gridwidth = 1;
		gc.insets = new Insets(2, 0, 0, 2);

		add(clearEntry, gc);

		gc.weightx = 0.2;
		gc.weighty = 0.5;
		gc.gridx = 4;
		gc.gridy = 0;

		add(clear, gc);

		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 1;

		add(numbers[7], gc);

		gc.gridx = 1;
		gc.gridy = 1;

		add(numbers[8], gc);

		gc.gridx = 2;
		gc.gridy = 1;

		add(numbers[9], gc);

		gc.gridx = 3;
		gc.gridy = 1;

		add(divide, gc);

		gc.gridx = 4;
		gc.gridy = 1;

		add(squareRoot, gc);

		gc.gridx = 0;
		gc.gridy = 2;

		add(numbers[4], gc);

		gc.gridx = 1;
		gc.gridy = 2;

		add(numbers[5], gc);

		gc.gridx = 2;
		gc.gridy = 2;

		add(numbers[6], gc);

		gc.gridx = 3;
		gc.gridy = 2;

		add(multiply, gc);

		gc.gridx = 4;
		gc.gridy = 2;

		add(oneOver, gc);

		gc.gridx = 0;
		gc.gridy = 3;

		add(numbers[1], gc);

		gc.gridx = 1;
		gc.gridy = 3;

		add(numbers[2], gc);

		gc.gridx = 2;
		gc.gridy = 3;

		add(numbers[3], gc);

		gc.gridx = 3;
		gc.gridy = 3;

		add(minus, gc);

		gc.gridx = 4;
		gc.gridy = 3;

		add(percentage, gc);

		gc.gridx = 0;
		gc.gridy = 4;

		add(numbers[0], gc);

		gc.gridx = 1;
		gc.gridy = 4;

		add(plusMinus, gc);

		gc.gridx = 2;
		gc.gridy = 4;

		add(point, gc);

		gc.gridx = 3;
		gc.gridy = 4;

		add(plus, gc);

		gc.gridx = 4;
		gc.gridy = 4;

		add(equals, gc);

		for (int i = 0; i < numbers.length; i++) {
			numbers[i].addActionListener(this);
		}
		for (int i = 0; i < nonNumbers.length; i++) {
			nonNumbers[i].addActionListener(this);
		}

	}

	@Override
	public void actionPerformed(ActionEvent keyPress) {
		if (listener != null) {
			listener.submitKey((JButton) keyPress.getSource());
		}
	}

	public void setCalcListener(CalcListener listener) {
		this.listener = listener;
	}

	public boolean isNumber(String name) {
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i].getText().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
