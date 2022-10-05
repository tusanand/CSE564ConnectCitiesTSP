import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is used to run the algorithm for symmetric data
 *
 */
public class TSPAlgorithm implements TSPAlgorithmInterface {
	private List<Coordinates> tempCoordinates;
	private double totalDistanceTravelled;
	private List<String> visitedCities = new ArrayList<>();
	private boolean isRunning = false;
	private MessageDialogInterface msgDialog;
	private DrawDotsInterface drawDots;

	public TSPAlgorithm(MessageDialogInterface msgDialog, DrawDotsInterface drawDots) {
		this.msgDialog = msgDialog;
		this.drawDots = drawDots;
	}
	
	/**
	 * This method randomly selects a coordinate from a list of coordinates
	 * 
	 * @param coordinatesList
	 */
	private Coordinates randomSelectDot(List<Coordinates> coordinatesList) {
		return coordinatesList.get(new Random().nextInt(coordinatesList.size()));
	}
	
	/**
	 * This method calculates the Manhattan distance between two points
	 * 
	 * @param selectedPoint
	 * @param traversedPoint
	 */
	private double calculateManhattanDistance(Coordinates selectedPoint, Coordinates traversedPoint) {
		return Math.sqrt(Math.pow(selectedPoint.getX() - traversedPoint.getX(), 2)
				+ Math.pow(selectedPoint.getY() - traversedPoint.getY(), 2));
	}
	
	/**
	 * This method executes the algorithm
	 */
	private void executeAlgorithm(List<Coordinates> coordinates, boolean hasBeenRun) {
		this.isRunning = true;
		this.tempCoordinates = new ArrayList<Coordinates>(coordinates);
		final Coordinates initialDestination = this.randomSelectDot(this.tempCoordinates);
		this.visitedCities.add("City"+initialDestination.getIndex());
		if(!hasBeenRun) {
			this.drawDots.markDotsVisited(initialDestination);
		}
		this.tempCoordinates.removeIf(c -> c.getX() == initialDestination.getX() && c.getY() == initialDestination.getY());

		while (this.tempCoordinates.size() > 0) {
			Coordinates finalDestination = this.randomSelectDot(this.tempCoordinates);
			if(!hasBeenRun) {
				this.drawDots.markDotsVisited(finalDestination);
			}
			this.totalDistanceTravelled += this.calculateManhattanDistance(initialDestination, finalDestination);
			initialDestination.setIndex(finalDestination.getIndex());
			initialDestination.setX(finalDestination.getX());
			initialDestination.setY(finalDestination.getY());
			this.visitedCities.add("City"+initialDestination.getIndex());
			this.tempCoordinates.removeIf(c -> c.getX() == initialDestination.getX() && c.getY() == initialDestination.getY());
		}
		this.msgDialog.showMessage(new String[]{
				"Total distance travelled: " + this.totalDistanceTravelled, 
				"List of cities visited:", 
				String.valueOf(this.visitedCities)});
	}
	
	
	/**
	 * This method starts the algorithm
	 */
	public boolean startAlgorithm(List<Coordinates> coordinates, boolean hasBeenRun) {
		this.totalDistanceTravelled = 0;
		this.visitedCities.clear();
		if (coordinates.isEmpty()) {
			this.msgDialog.showMessage(new String[]{"Please load data to run."});
			return false;
		} else if (this.isRunning) {
			this.msgDialog.showMessage(new String[]{"The system is already executing the algorithm."});
			return false;
		}
		this.executeAlgorithm(coordinates, hasBeenRun);
		this.isRunning = false;
		return true;
	}

}
