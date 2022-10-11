import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileLoaderAsymmetrical implements FileLoaderInterface {

	public FileLoaderAsymmetrical() {
		// TODO Auto-generated constructor stub
	}
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
	@SuppressWarnings("unchecked")
	public <T> List<T> loadData(boolean symmetric) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		String extension = "atsp";
		String dataType = "Asymmetric";
		fileChooser.setDialogTitle("Load " + dataType + " Data");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("." + extension, extension);
		fileChooser.setFileFilter(filter);
		if (!symmetric && fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {			
			return (List<T>) this.loadFromAsymmetricFile(fileChooser.getSelectedFile().getAbsolutePath());
		}
		return null;
	}
}
