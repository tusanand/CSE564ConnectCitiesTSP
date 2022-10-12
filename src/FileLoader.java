import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Tushar Anand
 * This class helps load file data into the system
 */
public class FileLoader implements FileLoaderInterface {
	
	public FileLoader() {
	}
	
	
	/**
	 * This method opens a file chooser to load a file with symmetric/asymmetric data
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> loadData(TspType type) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("."));
		String extension = type.getExtension();
		String dataType = type.getDataType();
		
		fileChooser.setDialogTitle("Load " + dataType + " Data");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("." + extension, extension);
		fileChooser.setFileFilter(filter);
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {			
			return (List<T>) type.loadFile(fileChooser.getSelectedFile().getAbsolutePath());
		}
		
		return null;
	}

}
