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
		frame.setBounds(Config.windowStartX, Config.windowStartY, Config.windowWidth, Config.windowHeight);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnLoadAsymmetric = new JButton("Select Asymmetric Data");
		btnLoadAsymmetric.setFont(Config.buttonFont);
		btnLoadAsymmetric.setBounds(555, 10, 210, 32);
		btnLoadAsymmetric.addActionListener(this);
		frame.getContentPane().add(btnLoadAsymmetric);

		btnLoadSymmetric = new JButton("Select Symmetric Data");
		btnLoadSymmetric.setFont(Config.buttonFont);
		btnLoadSymmetric.setBounds(775, 10, 200, 32);
		btnLoadSymmetric.addActionListener(this);
		frame.getContentPane().add(btnLoadSymmetric);

		btnClear = new JButton("Clear");
		btnClear.setFont(Config.buttonFont);
		btnClear.setBounds(446, 10, 100, 32);
		btnClear.addActionListener(this);
		frame.getContentPane().add(btnClear);

		btnRun = new JButton("Compute Distance");
		btnRun.setFont(Config.buttonFont);
		btnRun.setBounds(20, 10, 180, 32);
		btnRun.addActionListener(this);
		frame.getContentPane().add(btnRun);

		grid = new JPanel();
		grid.setBackground(Color.WHITE);
		grid.setBounds(10, 52, 968, 598);
		frame.getContentPane().add(grid);
	}

}
