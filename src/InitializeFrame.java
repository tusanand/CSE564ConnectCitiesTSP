import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InitializeFrame extends ButtonActions implements InitFrame {
	
	public InitializeFrame() {
		this.initialize();
		this.frame.setVisible(true);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setTitle("Connect the Dots");
		frame.setBounds(100, 100, 679, 494);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnLoad = new JButton("Select Country Data");
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLoad.setBounds(450, 10, 200, 32);
		btnLoad.addActionListener(this);
		frame.getContentPane().add(btnLoad);

		btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBounds(325, 10, 100, 32);
		btnClear.addActionListener(this);
		frame.getContentPane().add(btnClear);

		btnRun = new JButton("Compute Distance");
		btnRun.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRun.setBounds(20, 10, 180, 32);
		btnRun.addActionListener(this);
		frame.getContentPane().add(btnRun);

		grid = new JPanel();
		grid.setBackground(Color.WHITE);
		grid.setBounds(10, 52, 645, 395);
		frame.getContentPane().add(grid);

		errorLabel = new JLabel();
		errorLabel.setBounds(20, 60, 200, 30);
		errorLabel.setForeground(Color.RED);
	}
}
