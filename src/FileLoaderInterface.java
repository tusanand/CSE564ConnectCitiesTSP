import java.util.List;

public interface FileLoaderInterface {
	
	public <T> List<T> browseAndLoadData(TspTypeInterface type);
	
}
