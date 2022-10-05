import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonActions implements ActionListener {
	protected JFrame frame;
	protected JButton btnLoad;
	protected JButton btnLoadAssymetric;
	protected JPanel grid;
	protected JButton btnClear;
	protected JButton btnRun;
	private List<Coordinates> coordinates;
	private List<Coordinates> originalCoordinates;
	private List<Integer> asymmetricData;
	private boolean hasBeenRun;
	private double[] minXY;
	private double[] maxXY;
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
		this.maxXY = new double[] {Double.MIN_VALUE, Double.MIN_VALUE};
		this.minXY = new double[] {Double.MAX_VALUE, Double.MAX_VALUE};
		this.msgDialog = new MessageDialog(frame);
		this.tspAlgorithm = new TSPAlgorithm(this.msgDialog, this.drawDots);
		this.atspAlgorithm = new AtspAlgorithm(this.msgDialog);
		this.fileLoader = new FileLoader();
	}
	
	private void findMinMaxActualCoordinate(List<Coordinates> dotLocations) {
		for(Coordinates coordinate:  dotLocations) {
			this.minXY[0] = Math.min(this.minXY[0], coordinate.getX());
            this.minXY[1] = Math.min(this.minXY[1], coordinate.getY());
            this.maxXY[0] = Math.max(this.maxXY[0], coordinate.getX());
            this.maxXY[1] = Math.max(this.maxXY[1], coordinate.getY());
		}
	}

	/**
	 * This method clears dots from the screen
	 */
	private void clearScreen() {
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
			this.clearScreen();
		} else if (e.getSource() == this.btnLoad) {
			this.clearScreen();
			this.originalCoordinates = this.fileLoader.loadSymmetricData();
			if(this.originalCoordinates != null) {
				this.hasBeenRun = false;
				this.symmetric = true;
				this.drawDots = new DrawDots(this.minXY, this.maxXY, this.grid);
				this.findMinMaxActualCoordinate(this.originalCoordinates);
				this.coordinates = this.drawDots.loopAndDraw(this.originalCoordinates);
				this.msgDialog.showMessage(new String[]{"The file is successfully loaded."});
				this.tspAlgorithm = new TSPAlgorithm(this.msgDialog, this.drawDots);
			}
		} else if (e.getSource() == this.btnLoadAssymetric) {
			this.clearScreen();
			List<Integer> data = this.fileLoader.loadASymmetricData();
			if(data != null) {
				this.asymmetricData = data;
				this.symmetric = false;
				this.msgDialog.showMessage(new String[] {"The file is successfully loaded."});
			}
		} else if (e.getSource() == this.btnRun) {
			if(this.symmetric) {
				boolean success = this.tspAlgorithm.startAlgorithm(this.coordinates, this.hasBeenRun);
				this.hasBeenRun = success;
			} else {
				this.atspAlgorithm.startAlgorithm(this.asymmetricData);
			}
		}
	}
}
