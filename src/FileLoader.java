import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileLoader implements FileLoaderInterface {
	
	
	public FileLoader() {
	}
	
	/**
	 * This method loads the configuration from the selected symmetric file
	 * 
	 * @param filePath
	 */
	private List<Coordinates> loadFromSymmetricFile(String filePath) {
		List<Coordinates> dotLocations = new ArrayList<Coordinates>();
		try {
			File file = new File(filePath);
	        Scanner sc = new Scanner(file);
	        String nextValue = null;
	        while (sc.hasNextLine()) {
	            String line = sc.nextLine();
	            if("NODE_COORD_SECTION".equals(line.trim())) {
	                while (sc.hasNextLine()) {
	                    nextValue = sc.nextLine();
	                    if(nextValue.trim().equals("EOF")) {
	                    	break;
	                    }
	                    String[] s = nextValue.trim().split(" ");
	                    Coordinates coordinate = new Coordinates(Integer.parseInt(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s[2]));
	                    dotLocations.add(coordinate);
	                }
	            }
	        }
	        sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dotLocations;
	}
	
	/**
	 * This method loads the configuration from the selected asymmetric file
	 * 
	 * @param filePath
	 */
	private List<Integer> loadFromAsymmetricFile(String filePath) {
		List<Integer> matrix = new ArrayList<Integer>();
		try {
			File file = new File(filePath);
	        Scanner sc = new Scanner(file);
	        String nextValue = null;
	        int dimension = 0;
	        while (sc.hasNextLine()) {
	            String line = sc.nextLine();
	            if(line.split(":")[0].trim().equals("DIMENSION")) {
	            	dimension = Integer.parseInt(line.split(":")[1].trim());
	            }
	            if("EDGE_WEIGHT_SECTION".equals(line.trim())) {
	                while (sc.hasNextLine()) {
	                    nextValue = sc.nextLine();
	                    if(nextValue.trim().equals("EOF")) {
	                    	break;
	                    }
	                    String[] atspData = nextValue.trim().split(" ");
	                    for(String data: atspData) {
	                    	if (data.equals("")) {
	                    		continue;
	                    	}
	                    	matrix.add(Integer.parseInt(data));
	                    }
	                }
	                matrix.add(dimension);
	            }
	        }
	        sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matrix;
	}

	/**
	 * This method opens a file chooser to load a file with symmetric data
	 * 
	 */
	public List<Coordinates> loadSymmetricData() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setDialogTitle("Load Symmetric Data");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".tsp", "tsp");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {			
			return this.loadFromSymmetricFile(fileChooser.getSelectedFile().getAbsolutePath());
		}
		return null;
	}
	
	/**
	 * This method opens a file chooser to load a file with asymmetric data
	 * 
	 */
	public List<Integer> loadASymmetricData() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setDialogTitle("Load Asymmetric Data");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".atsp", "atsp");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {			
			return this.loadFromAsymmetricFile(fileChooser.getSelectedFile().getAbsolutePath());
		}
		return null;
	}

}
