import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is used to run the algorithm for asymmetric data
 *
 */
public class AtspAlgorithm implements AtspAlgorithmInterface {
	private MessageDialogInterface msgDialog;
	private boolean isRunning = false;
	private int dimension;
	private double totalDistanceTravelled;
	private List<String> visitedCities = new ArrayList<>();
	List<Integer> tempData;
	
	public AtspAlgorithm(MessageDialogInterface msgDialog) {
		this.msgDialog = msgDialog;
	}
	
	/**
	 * This method executes the algorithm
	 */
	private void executeAlgorithm(List<List<Integer>> asymmetricDistance) {
		this.isRunning = true;
		this.tempData = new ArrayList<Integer>();
		for (int i = 0; i < this.dimension; ++i) {
			this.tempData.add(i);
		}
		int initialDestination = this.randomSelectIndex(this.tempData);
		this.tempData = this.removeData(this.tempData, initialDestination);
		this.visitedCities.add("City" + (initialDestination + 1));
		
		while (this.tempData.size() > 0) {
			int finalDestination = this.randomSelectIndex(this.tempData);
			this.totalDistanceTravelled += asymmetricDistance.get(initialDestination).get(finalDestination);
			initialDestination = finalDestination;
			this.tempData = this.removeData(this.tempData, initialDestination);
			this.visitedCities.add("City" + (initialDestination + 1));
		}
		
		this.msgDialog.showMessages(new String[] {
			"Total distance travelled: " + this.totalDistanceTravelled, 
			"List of cities visited:", 
			String.valueOf(this.visitedCities) }
		);
	}

	/**
	 * This method removes elements from a list
	 * 
	 * @param data
	 * @param key
	 */
	private List<Integer> removeData(List<Integer> data, int key) {
		for (int i = 0; i < data.size(); ++i) {
			if (data.get(i) == key) {
				data.remove(i);
			}
		}
		return data;
	}
	
	/**
	 * This method randomly selects a index from the dimension
	 * 
	 * @param data
	 */
	private int randomSelectIndex(List<Integer> data) {
		return data.get(new Random().nextInt(data.size()));
	}
	
	/**
	 * This method processes the data and starts the algorithm
	 * @param matrix
	 */
	public void startAlgorithm(List<Integer> matrix) {
		List<Integer> copyMatrix = new ArrayList<Integer>(matrix);
		this.totalDistanceTravelled = 0;
		this.visitedCities.clear();
		if (copyMatrix.isEmpty()) {
			this.msgDialog.showMessages(new String[]{"Please load data to run."});
			return;
		} else if (this.isRunning) {
			this.msgDialog.showMessages(new String[]{"The system is already executing the algorithm."});
			return;
		}
		List<List<Integer>> asymmetricDistance = new ArrayList<List<Integer>>();
		this.dimension = copyMatrix.remove(copyMatrix.size()-1);
		int index = 0;
		while (index < copyMatrix.size()) {
			asymmetricDistance.add(copyMatrix.subList(index, index + this.dimension));
			index = index + this.dimension;
		}
		this.executeAlgorithm(asymmetricDistance);
		this.isRunning = false;
	}
}
