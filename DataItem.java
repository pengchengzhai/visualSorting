import java.awt.Color;

public class DataItem implements Comparable<DataItem>{
	private int number;
	private Color color;

	public DataItem(int number) {
		this.number = number;
		this.color = Color.GRAY;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	public int compareTo(DataItem o) {
		return Integer.compare(number, o.number);
	}
	

}
