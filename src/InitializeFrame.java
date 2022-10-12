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
		frame.setBounds(Config.WINDOW_START_X, Config.WINDOW_START_Y, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnRun = new JButton("Compute Distance");
		btnRun.setFont(Config.BUTTON_FONT);
		btnRun.setBounds(Config.PADDING, Config.PADDING, Config.BTN_RUN_WIDTH, Config.BUTTON_HEIGHT);
		btnRun.addActionListener(this);
		frame.getContentPane().add(btnRun);

		btnClear = new JButton("Clear");
		btnClear.setFont(Config.BUTTON_FONT);
		btnClear.setBounds(2 * Config.PADDING + Config.BTN_RUN_WIDTH, Config.PADDING, Config.BTN_CLEAR_WIDTH, Config.BUTTON_HEIGHT);
		btnClear.addActionListener(this);
		frame.getContentPane().add(btnClear);

		btnLoadAsymmetric = new JButton("Select Asymmetric Data");
		btnLoadAsymmetric.setFont(Config.BUTTON_FONT);
		btnLoadAsymmetric.setBounds(6 * Config.PADDING + Config.BTN_RUN_WIDTH + Config.BTN_CLEAR_WIDTH, Config.PADDING, Config.BTN_LOAD_ASYM_WIDTH, Config.BUTTON_HEIGHT);
		btnLoadAsymmetric.addActionListener(this);
		frame.getContentPane().add(btnLoadAsymmetric);

		btnLoadSymmetric = new JButton("Select Symmetric Data");
		btnLoadSymmetric.setFont(Config.BUTTON_FONT);
		btnLoadSymmetric.setBounds(7 * Config.PADDING + Config.BTN_RUN_WIDTH + Config.BTN_CLEAR_WIDTH + Config.BTN_LOAD_ASYM_WIDTH, Config.PADDING, Config.BTN_LOAD_SYM_WIDTH, Config.BUTTON_HEIGHT);
		btnLoadSymmetric.addActionListener(this);
		frame.getContentPane().add(btnLoadSymmetric);

		grid = new JPanel();
		grid.setBackground(Color.WHITE);
		grid.setBounds(
			Config.PADDING,
			2 * Config.PADDING + Config.BUTTON_HEIGHT,
			Config.WINDOW_WIDTH - 10 * Config.PADDING,
			Config.WINDOW_HEIGHT - 10 * Config.PADDING
		);
		frame.getContentPane().add(grid);
	}

}
