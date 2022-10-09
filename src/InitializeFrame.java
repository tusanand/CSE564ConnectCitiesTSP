import java.awt.Color;

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
		
		btnRun = new JButton("Compute Distance");
		btnRun.setFont(Config.buttonFont);
		btnRun.setBounds(Config.padding, Config.padding, Config.btnRunWidth, Config.buttonHeight);
		btnRun.addActionListener(this);
		frame.getContentPane().add(btnRun);

		btnClear = new JButton("Clear");
		btnClear.setFont(Config.buttonFont);
		btnClear.setBounds(2 * Config.padding + Config.btnRunWidth, Config.padding, Config.btnClearWidth, Config.buttonHeight);
		btnClear.addActionListener(this);
		frame.getContentPane().add(btnClear);

		btnLoadAsymmetric = new JButton("Select Asymmetric Data");
		btnLoadAsymmetric.setFont(Config.buttonFont);
		btnLoadAsymmetric.setBounds(6 * Config.padding + Config.btnRunWidth + Config.btnClearWidth, Config.padding, Config.btnLoadAsymWidth, Config.buttonHeight);
		btnLoadAsymmetric.addActionListener(this);
		frame.getContentPane().add(btnLoadAsymmetric);

		btnLoadSymmetric = new JButton("Select Symmetric Data");
		btnLoadSymmetric.setFont(Config.buttonFont);
		btnLoadSymmetric.setBounds(7 * Config.padding + Config.btnRunWidth + Config.btnClearWidth + Config.btnLoadAsymWidth, Config.padding, Config.btnLoadSymWidth, Config.buttonHeight);
		btnLoadSymmetric.addActionListener(this);
		frame.getContentPane().add(btnLoadSymmetric);

		grid = new JPanel();
		grid.setBackground(Color.WHITE);
		grid.setBounds(
			Config.padding,
			2 * Config.padding + Config.buttonHeight,
			Config.windowWidth - 10 * Config.padding,
			Config.windowHeight - 10 * Config.padding
		);
		frame.getContentPane().add(grid);
	}

}
