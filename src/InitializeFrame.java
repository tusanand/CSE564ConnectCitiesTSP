import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class initializes the frame and all of its components
 *
 */
public class InitializeFrame extends ButtonActions implements InitializeFrameInterface {
	
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
		
		btnLoadAssymetric = new JButton("Select Asymmetric Data");
		btnLoadAssymetric.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLoadAssymetric.setBounds(555, 10, 210, 32);
		btnLoadAssymetric.addActionListener(this);
		frame.getContentPane().add(btnLoadAssymetric);

		btnLoad = new JButton("Select Symmetric Data");
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLoad.setBounds(775, 10, 200, 32);
		btnLoad.addActionListener(this);
		frame.getContentPane().add(btnLoad);

		btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBounds(446, 10, 100, 32);
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
