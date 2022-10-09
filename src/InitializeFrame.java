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
		
		btnLoadAssymetric = createButton("Select Asymmetric Data", new int[] {555, 10, 210, 32});
		frame.getContentPane().add(btnLoadAssymetric);

		btnLoad = createButton("Select Symmetric Data", new int[] {775, 10, 200, 32});
		frame.getContentPane().add(btnLoad);

		btnClear = createButton("Clear", new int[] {446, 10, 100, 32});
		frame.getContentPane().add(btnClear);

		btnRun = createButton("Compute Distance", new int[] {20, 10, 180, 32});
		frame.getContentPane().add(btnRun);

		grid = new JPanel();
		grid.setBackground(Color.WHITE);
		grid.setBounds(10, 52, 968, 598);
		frame.getContentPane().add(grid);
	}
	
	private JButton createButton(String text, int[] bounds) {
		JButton btn = new JButton(text);
		btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
		btn.addActionListener(this);

		return btn;
	}
}
