import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ButtonActions implements ActionListener {
	protected JFrame frame;
	protected JButton btnSave;
	protected JButton btnLoad;
	protected JPanel grid;
	protected JButton btnClear;
	protected JButton btnRun;
	private List<Coordinates> coordinates;
	private List<Coordinates> originalCoordinates;
	private boolean hasBeenRun;
	private double[] minXY;
	private double[] maxXY;
	private MessageDialogInterface msgDialog;
	private FileLoaderInterface fileLoader;
	private DrawDotsInterface drawDots;
	private TSPAlgorithmInterface tspAlgorithm;
	
	public ButtonActions() {
		this.coordinates = new ArrayList<Coordinates>();
		this.hasBeenRun = false;
		this.maxXY = new double[] {Double.MIN_VALUE, Double.MIN_VALUE};
		this.minXY = new double[] {Double.MAX_VALUE, Double.MAX_VALUE};
		this.msgDialog = new MessageDialog(frame);
		this.tspAlgorithm = new TSPAlgorithm(this.msgDialog, this.drawDots);
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
	}
	
	/**
	 * This method implements button triggers
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClear) {
			this.clearScreen();
		} else if (e.getSource() == btnLoad) {
			this.clearScreen();
			this.fileLoader = new FileLoader();
			this.originalCoordinates = this.fileLoader.loadFile();
			if(this.originalCoordinates != null) {
				this.hasBeenRun = false;
				this.drawDots = new DrawDots(this.minXY, this.maxXY, this.grid);
				this.findMinMaxActualCoordinate(this.originalCoordinates);
				this.coordinates = this.drawDots.loopAndDraw(this.originalCoordinates);
				this.msgDialog.showMessage(new String[]{"The file is successfully loaded."});
				this.tspAlgorithm = new TSPAlgorithm(this.msgDialog, this.drawDots);
			}
		} else if (e.getSource() == btnRun) {
			boolean success = this.tspAlgorithm.startAlgorithm(this.coordinates, this.hasBeenRun);
			this.hasBeenRun = success;
		}
	}
}
