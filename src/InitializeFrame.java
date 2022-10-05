import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InitializeFrame extends ButtonActions implements InitFrameInterface {
	
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
		frame.setBounds(100, 100, 1000, 700);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnLoad = new JButton("Select Country Data");
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLoad.setBounds(775, 10, 200, 32);
		btnLoad.addActionListener(this);
		frame.getContentPane().add(btnLoad);

		btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBounds(650, 10, 100, 32);
		btnClear.addActionListener(this);
		frame.getContentPane().add(btnClear);

		btnRun = new JButton("Compute Distance");
		btnRun.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRun.setBounds(20, 10, 180, 32);
		btnRun.addActionListener(this);
		frame.getContentPane().add(btnRun);

		grid = new JPanel();
		grid.setBackground(Color.WHITE);
		grid.setBounds(10, 52, 968, 598);
		frame.getContentPane().add(grid);
	}
}
