import java.util.List;

public interface DrawDotsInterface {
	
	public List<Coordinates> loopAndDraw(List<Coordinates> data);
	
	public void markDotsVisited(Coordinates coordinate);

}
