import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author Tushar Anand
 * This class helps mark pointers on the screen based on the coordinates
 *
 */
public class DrawDots implements DrawDotsInterface {
	private int circleDiameter = 4;
	private List<Coordinates> coordinates = new ArrayList<Coordinates>();
	private double[] minXY;
	private double[] maxXY;
	private Graphics graphics;
	protected JPanel grid;
	
	public DrawDots(JPanel grid) {
		this.maxXY = new double[] {Double.MIN_VALUE, Double.MIN_VALUE};
		this.minXY = new double[] {Double.MAX_VALUE, Double.MAX_VALUE};
		this.grid = grid;
	}
	
	/**
	 * This method draws dots on the panel
	 * 
	 * @param index
	 * @param x
	 * @param y
	 */
	private void drawDots(int index, double x, double y) {
		double scaledDownX = 948*((x - this.minXY[0])/Math.abs(this.maxXY[0]-this.minXY[0]));
		double scaledDownY = 578*((y - this.minXY[1])/Math.abs(this.maxXY[1]-this.minXY[1]));
		if (!this.coordinates.stream().anyMatch(coordinate -> coordinate.getX() == scaledDownX && coordinate.getY() == scaledDownY)) {
			this.coordinates.add(new Coordinates(index, scaledDownX, scaledDownY));
			this.graphics = this.grid.getGraphics();
			Graphics2D g2d = (Graphics2D)this.graphics;
			Shape circle = new Arc2D.Double(scaledDownX, scaledDownY, this.circleDiameter, this.circleDiameter, 0, 360, Arc2D.CHORD);
			g2d.fill(circle);
		}
	}
	
	/**
	 * This method finds the minimum and maximum value of the original X and Y coordinates for symmetric data
	 * @param dotLocations
	 */
	private void findMinMaxActualCoordinate(List<Coordinates> originalCoordinates) {
		for(Coordinates coordinate:  originalCoordinates) {
			this.minXY[0] = Math.min(this.minXY[0], coordinate.getX());
            this.minXY[1] = Math.min(this.minXY[1], coordinate.getY());
            this.maxXY[0] = Math.max(this.maxXY[0], coordinate.getX());
            this.maxXY[1] = Math.max(this.maxXY[1], coordinate.getY());
		}
	}

	/**
	 * This method loops through list of coordinates and draw each dot
	 * 
	 * @param data - list of coordinates
	 */
	public List<Coordinates> loopAndDraw(List<Coordinates> originalCoordinates) {
		this.findMinMaxActualCoordinate(originalCoordinates);
		for (Coordinates coordinate : originalCoordinates) {
			this.drawDots(coordinate.getIndex(), coordinate.getX(), coordinate.getY());
		}
		return this.coordinates;
	}
	
	/**
	 * This method draws a red oval around the selected dots
	 * 
	 * @param coordinate
	 */
	public void markDotsVisited(Coordinates coordinate) {
		Graphics2D g2d = (Graphics2D)this.graphics;
		Shape circle = new Arc2D.Double(coordinate.getX() - 2, coordinate.getY() - 2, this.circleDiameter + 4, this.circleDiameter + 4, 0, 360, Arc2D.CHORD);
		g2d.setColor(Color.RED);
		g2d.draw(circle);
	}

}
