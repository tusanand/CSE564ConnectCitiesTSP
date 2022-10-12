import java.util.List;

public interface TspTypeInterface {
  public String getExtension();
  public String getDataType();
  public <T> List<T> loadFile(String filePath);
}
