import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileLoaderSymmetric  implements FileLoaderInterface {

	public FileLoaderSymmetric() {
		// TODO Auto-generated constructor stub
	}
	private List<Coordinates> loadFromSymmetricFile(String filePath) {
		List<Coordinates> dotLocations = new ArrayList<Coordinates>();
		try {
			File file = new File(filePath);
			Scanner sc = new Scanner(file);
			String nextValue = null;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if ("NODE_COORD_SECTION".equals(line.trim())) {
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

	@SuppressWarnings("unchecked")
	public <T> List<T> loadData(boolean symmetric) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		String extension = "tsp";
		String dataType = "Symmetric";
		fileChooser.setDialogTitle("Load " + dataType + " Data");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("." + extension, extension);
		fileChooser.setFileFilter(filter);
		if (symmetric && fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {			
			return (List<T>) this.loadFromSymmetricFile(fileChooser.getSelectedFile().getAbsolutePath());
		}
		return null;
	}
}
