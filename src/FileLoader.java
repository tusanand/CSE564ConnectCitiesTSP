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
	 * This method loads the configuration from the selected file
	 * 
	 * @param filePath
	 */
	private List<Coordinates> loadFromFile(String filePath) {
		List<Coordinates> dotLocations = new ArrayList<Coordinates>();
		try {
			File file = new File(filePath);
	        Scanner sc = new Scanner(file);
	        String nextValue = null;
	        while (sc.hasNextLine()) {
	            String line = sc.nextLine();
	            if("NODE_COORD_SECTION".equals(line)) {
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
	 * This method opens a file chooser to load a file
	 * 
	 */
	public List<Coordinates> loadFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setDialogTitle("Load Data");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".tsp", "tsp");
		fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {			
			return this.loadFromFile(fileChooser.getSelectedFile().getAbsolutePath());
		}
		return null;
	}

}
