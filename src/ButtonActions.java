import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ButtonActions implements ActionListener {
	protected JFrame frame;
	protected JButton btnSave;
	protected JButton btnLoad;
	protected JPanel grid;
	protected JButton btnClear;
	protected JButton btnRun;
	private Graphics graphics;
	private List<Coordinates> coordinates = new ArrayList<Coordinates>();
	private List<Coordinates> tempCoordinates;
	private double totalDistanceTravelled;
	private List<String> visitedCities = new ArrayList<>();
	private boolean isRunning = false;
	private int circleDiameter = 10;
	
	/**
	 * This method implements button triggers
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnClear) {
			this.clearDots();
		} else if (e.getSource() == btnLoad) {
			this.loadFile();
		} else if (e.getSource() == btnRun) {
			this.startAlgorithm();
		}
	}
	
	/**
	 * This method draws input field to take threshold distance measure from user
	 */
	public void startAlgorithm() {
		this.totalDistanceTravelled = 0;
		this.visitedCities.clear();
		if (this.coordinates.isEmpty()) {
			this.showMessage(new String[]{"Please load data before clicking on Run."});
			return;
		} else if (this.isRunning) {
			this.showMessage(new String[]{"The system is already executing the algorithm."});
			return;
		}
		this.executeAlgorithm();
	}
	

	/**
	 * This method executes the algorithm
	 */
	public void executeAlgorithm() {
		this.isRunning = true;
		this.tempCoordinates = new ArrayList<Coordinates>(this.coordinates);
		final Coordinates initialDestination = this.randomSelectDot(this.tempCoordinates);
		this.markDotsVisited(initialDestination);
		this.tempCoordinates.removeIf(c -> c.getX() == initialDestination.getX() && c.getY() == initialDestination.getY());
		System.out.println("City"+initialDestination.getIndex());

		while (this.tempCoordinates.size() > 0) {
			Coordinates finalDestination = this.randomSelectDot(this.tempCoordinates);
			this.markDotsVisited(finalDestination);
			this.totalDistanceTravelled += this.calculateManhattanDistance(initialDestination, finalDestination);
			initialDestination.setIndex(finalDestination.getIndex());
			initialDestination.setX(finalDestination.getX());
			initialDestination.setY(finalDestination.getY());
			visitedCities.add("City"+initialDestination.getIndex());
			System.out.println("City"+initialDestination.getIndex());
			this.tempCoordinates.removeIf(c -> c.getX() == initialDestination.getX() && c.getY() == initialDestination.getY());
		}
		this.showMessage(new String[]{
				"Total distance travelled: " + this.totalDistanceTravelled, 
				"List of Cities visited in Sequence:", 
				String.valueOf(this.visitedCities)}, false);
		this.isRunning = false;
	}

	/**
	 * This method calculates the Manhattan distance between two points
	 * 
	 * @param selectedPoint
	 * @param traversedPoint
	 */
	public double calculateManhattanDistance(Coordinates selectedPoint, Coordinates traversedPoint) {
		return Math.sqrt(Math.pow(selectedPoint.getX() - traversedPoint.getX(), 2)
				+ Math.pow(selectedPoint.getY() - traversedPoint.getY(), 2));
	}

	/**
	 * This method draws a red oval around the selected dots
	 * 
	 * @param coordinate
	 */
	public void markDotsVisited(Coordinates coordinate) {
		Graphics2D g2d = (Graphics2D)graphics;
		Shape circle = new Arc2D.Double(coordinate.getX() - 2, coordinate.getY() - 2, circleDiameter + 4, circleDiameter + 4, 0, 360, Arc2D.CHORD);
		g2d.draw(circle);
		//graphics.drawOval((int)coordinate.getX() - 2, (int)coordinate.getY() - 2, (int)circleDiameter + 4, (int)circleDiameter + 4);
	}

	/**
	 * This method randomly selects a coordinate from a list of coordinates
	 * 
	 * @param coordinatesList
	 */
	public Coordinates randomSelectDot(List<Coordinates> coordinatesList) {
		return coordinatesList.get(new Random().nextInt(coordinatesList.size()));
	}

	/**
	 * This method draws dots on the panel
	 * 
	 * @param x
	 * @param y
	 */
	public void drawDots(int index, double x, double y) {
		if (!this.coordinates.stream().anyMatch(coordinate -> coordinate.getX() == x && coordinate.getY() == y)) {
			this.coordinates.add(new Coordinates(index, x, y));
		}
		graphics = grid.getGraphics();
		Graphics2D g2d = (Graphics2D)graphics;
		Shape circle = new Arc2D.Double(x, y, circleDiameter, circleDiameter, 0, 360, Arc2D.CHORD);
		g2d.fill(circle);
		//graphics = grid.getGraphics();
		//graphics.fillOval((int)x, (int)y, circleDiameter, circleDiameter);
	}

	/**
	 * This method loops through list of coordinates and draw each dot
	 * 
	 * @param data - list of coordinates
	 */
	public void loopAndDraw(List<Coordinates> data) {
		for (Coordinates coordinate : data) {
			this.drawDots(coordinate.getIndex(), coordinate.getX(), coordinate.getY());
		}
	}

	/**
	 * This method clears dots from the screen
	 */
	public void clearDots() {
		grid.repaint();
		this.totalDistanceTravelled = 0;
		this.visitedCities.clear();
		this.coordinates.clear();
		this.isRunning = false;
	}

	/**
	 * This method loads the configuration from the selected file
	 * 
	 * @param filePath
	 */
	public void loadFromFile(String filePath) {
		try {
			List<Coordinates> dotLocations = new ArrayList<Coordinates>();
			File file = new File(filePath);
			if (!file.exists()) {
				this.showMessage(new String[]{"No data found."});
				return;
			}
	        Scanner sc = new Scanner(file);
	        String nextValue = null;
	        while (sc.hasNextLine()) {
	            String line = sc.nextLine();
	            if("NODE_COORD_SECTION".equals(line)){
	                while (sc.hasNextLine()) {
	                    nextValue = sc.nextLine();
	                    if(nextValue.trim().equals("EOF")) {
	                    	break;
	                    }
	                    String[] s = nextValue.trim().split(" ");
	                    Coordinates coordinate = new Coordinates(Integer.parseInt(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s[2]));
	                    dotLocations.add(coordinate);
	                }
	                loopAndDraw(dotLocations);
	                showMessage(new String[]{"The file is successfully loaded."});
	            }
	        }
	        sc.close();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method opens a file chooser to load a file
	 * 
	 */
	public void loadFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setDialogTitle("Load Data");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".tsp", "tsp");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			this.clearDots();
			Timer timer = new java.util.Timer();
			timer.schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					loadFromFile(fileChooser.getSelectedFile().getAbsolutePath());
					timer.cancel();
				}
			}, 100);

		}
	}

	/**
	 * This method shows a pop-up with messages
	 * 
	 * @param message
	 */
	public void showMessage(String[] messages, boolean... runTimer) {
		JDialog messageDialog = new JDialog(frame, "Message");
		if (messageDialog != null) {
			messageDialog.dispose();
		}
		messageDialog.setSize(350, 200);
		messageDialog.setResizable(false);
		messageDialog.setVisible(true);
		messageDialog.setLocationRelativeTo(frame);
		
		int y = 20;
		for(String message: messages) {
			JLabel messageLabel = new JLabel("<html>"+ message +"</html>", SwingConstants.CENTER);
			messageLabel.setBounds(20, y, 150, 50);
			y+=20;
			messageDialog.add(messageLabel);
		}
		if(runTimer.length == 0) {
			this.startTimer(3000, messageDialog);
		}
	}

	public void startTimer(int millis, JDialog messageDialog) {
		Timer timer = new java.util.Timer();
		timer.schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				messageDialog.dispose();
				timer.cancel();
			}
		}, millis);
	}
}
