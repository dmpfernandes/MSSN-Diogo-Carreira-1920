package game;

public class Cell {
	private int positionX;
	private int positionY;
	private int state;
	
	public Cell(int positionX, int positionY, int alive) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.setState(alive);
	}
	public int getPositionX() {
		return positionX;
	}
	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}
	public int getPositionY() {
		return positionY;
	}
	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}

}
