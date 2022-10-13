import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TspSymmetric implements TspTypeInterface {
  String extension = "tsp";
  String dataType = "Symmetric";

	public String getExtension() {
		return extension;
	}

	public String getDataType() {
		return dataType;
	}

	/**
	 * This method loads the configuration from the selected symmetric file
	 * 
	 * @param filePath
	 */
	public List<Coordinates> loadFile(String filePath) {
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
						if (nextValue.trim().equals("EOF")) {
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
}
