import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class invokes all the button actions
 *
 */
public class ButtonActions implements ActionListener {
	protected JFrame frame;
	protected JButton btnLoadSymmetric;
	protected JButton btnLoadAsymmetric;
	protected JPanel grid;
	protected JButton btnClear;
	protected JButton btnRun;
	private List<Coordinates> coordinates;
	private List<Coordinates> originalCoordinates;
	private List<Integer> asymmetricData;
	private boolean hasBeenRun;
	private MessageDialogInterface msgDialog;
	private FileLoaderInterface fileLoader;
	private DrawDotsInterface drawDots;
	private TSPAlgorithmInterface tspAlgorithm;
	private AtspAlgorithmInterface atspAlgorithm;
	private boolean symmetric;
	
	public ButtonActions() {
		this.coordinates = new ArrayList<Coordinates>();
		this.asymmetricData = new ArrayList<Integer>();
		this.hasBeenRun = false;
		this.symmetric = true;
		this.msgDialog = new MessageDialog(frame);
		this.tspAlgorithm = new TSPAlgorithm(this.msgDialog, this.drawDots);
		this.atspAlgorithm = new AtspAlgorithm(this.msgDialog);
		this.fileLoader = new FileLoader();
	}

	/**
	 * This method clears the data
	 */
	private void clearData() {
		grid.repaint();
		this.coordinates.clear();
		this.asymmetricData.clear();
		this.symmetric = true;
		this.hasBeenRun = false;
	}
	
	/**
	 * This method implements button triggers
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnClear) {
			this.clearData();
		} else if (e.getSource() == this.btnLoadSymmetric) {
			this.clearData();
			this.originalCoordinates = this.fileLoader.loadData(new TspSymmetric());
			if (this.originalCoordinates != null) {
				this.hasBeenRun = false;
				this.symmetric = true;
				this.drawDots = new DrawDots(this.grid);
				this.coordinates = this.drawDots.loopAndDraw(this.originalCoordinates);
				this.msgDialog.showMessages(new String[]{"The file is successfully loaded."});
				this.tspAlgorithm = new TSPAlgorithm(this.msgDialog, this.drawDots);
			}
		} else if (e.getSource() == this.btnLoadAsymmetric) {
			this.clearData();
			List<Integer> data = this.fileLoader.loadData(new TspAsymmetric());
			if (data != null) {
				this.asymmetricData = data;
				this.symmetric = false;
				this.msgDialog.showMessages(new String[] {"The file is successfully loaded."});
			}
		} else if (e.getSource() == this.btnRun) {
			if (this.symmetric) {
				boolean success = this.tspAlgorithm.startAlgorithm(this.coordinates, this.hasBeenRun);
				this.hasBeenRun = success;
			} else {
				this.atspAlgorithm.startAlgorithm(this.asymmetricData);
			}
		}
	}
}
