import java.util.List;

public interface FileLoaderInterface {
	
	public <T> List<T> loadData(boolean symmetric);
	
}
