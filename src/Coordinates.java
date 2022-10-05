import java.io.Serializable;

/**
 * @author Tushar Anand
 * This class helps store the coordinate value
 */
public class Coordinates implements Serializable {
	private int index;
	private double x;
	private double y;

	public Coordinates(int index, double x, double y) {
		this.index = index;
		this.x = x;
		this.y = y;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Coordinates [index=" + index + ", x=" + x + ", y=" + y + "]";
	}
	
}
