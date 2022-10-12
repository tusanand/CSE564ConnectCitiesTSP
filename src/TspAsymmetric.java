import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TspAsymmetric implements TspType {
  String extension = "atsp";
  String dataType = "Asymmetric";

	public String getExtension() {
		return extension;
	}

	public String getDataType() {
		return dataType;
	}

	/**
	 * This method loads the configuration from the selected asymmetric file
	 * 
	 * @param filePath
	 */
	public List<Integer> loadFile(String filePath) {
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
						if (nextValue.trim().equals("EOF")) {
							break;
						}
						String[] atspData = nextValue.trim().split(" ");
						for (String data : atspData) {
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
}
