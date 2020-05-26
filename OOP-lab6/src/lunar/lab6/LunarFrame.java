package lunar.lab6;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LunarFrame extends JFrame implements ActionListener {

	private JPanel selectPhase;
	private JPanel displayPhase;
	private ImageIcon imageHolder;
	private JLabel newMoon;
	private JLabel waxCrescentMoon;
	private JLabel fQuarterMoon;
	private JLabel waxGibbousMoon;
	private JLabel fullMoon;
	private JLabel wanGibbousMoon;
	private JLabel tQuarterMoon;
	private JLabel wanCrescentMoon;
	private JLabel displayedMoon;
	private JLabel[] moonLabels;
	private String[] mPhases;

	public LunarFrame() {
		super("Lunar Phases");

		// SELECT PHASE INITIALIZING //

		selectPhase = new JPanel(new BorderLayout());

		String[] moonPhases = { "New", "Waxing Cresent", "First Quarter", "Waxing Gibbous", "Full", "Waning Gibbous",
				"Third Quarter", "Waning Crescent" };
		mPhases = moonPhases;

		JComboBox phasesBox = new JComboBox(moonPhases);
		phasesBox.addActionListener(this);
		phasesBox.setSelectedIndex(0);

		Dimension dim = phasesBox.getPreferredSize();
		dim.setSize(200, 30);
		phasesBox.setPreferredSize(dim);

		selectPhase.add(phasesBox, BorderLayout.NORTH);
		selectPhase.setBorder(BorderFactory.createTitledBorder("Select Phase"));

		// DISPLAY PHASE INITIALIZING //

		displayPhase = new JPanel(new BorderLayout());
		displayPhase.setBorder(BorderFactory.createTitledBorder("Display Phase"));

		imageHolder = new ImageIcon(getClass().getResource("new_moon.jpg"));
		newMoon = new JLabel(imageHolder);
		imageHolder = new ImageIcon(getClass().getResource("waxing_crescent.jpg"));
		waxCrescentMoon = new JLabel(imageHolder);
		imageHolder = new ImageIcon(getClass().getResource("first_quarter.jpg"));
		fQuarterMoon = new JLabel(imageHolder);
		imageHolder = new ImageIcon(getClass().getResource("waxing_gibbous.jpg"));
		waxGibbousMoon = new JLabel(imageHolder);
		imageHolder = new ImageIcon(getClass().getResource("full_moon.jpg"));
		fullMoon = new JLabel(imageHolder);
		imageHolder = new ImageIcon(getClass().getResource("waning_gibbous.jpg"));
		wanGibbousMoon = new JLabel(imageHolder);
		imageHolder = new ImageIcon(getClass().getResource("third_quarter.jpg"));
		tQuarterMoon = new JLabel(imageHolder);
		imageHolder = new ImageIcon(getClass().getResource("waning_crescent.jpg"));
		wanCrescentMoon = new JLabel(imageHolder);

		JLabel[] moons = { newMoon, waxCrescentMoon, fQuarterMoon, waxGibbousMoon, fullMoon, wanGibbousMoon,
				tQuarterMoon, wanCrescentMoon };
		moonLabels = moons;

		displayedMoon = newMoon;
		displayPhase.add(displayedMoon, BorderLayout.CENTER);

		// FRAME LAYOUT CONSTRAINTS SETTING //

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(10, 10, 10, 10);
		gc.fill = GridBagConstraints.BOTH;

		add(selectPhase, gc);

		gc.gridx = 0;
		gc.gridy = 1;

		add(displayPhase, gc);

		setSize(300, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (moonLabels != null) {
			String selectedMoon = ((JComboBox) arg0.getSource()).getSelectedItem().toString();
			int index = 0;
			for (int i = 0; i < mPhases.length; i++) {
				if (mPhases[i].equals(selectedMoon)) {
					index = i;
					break;
				}
			}
			displayedMoon.setIcon(moonLabels[index].getIcon());
		}
	}
}
