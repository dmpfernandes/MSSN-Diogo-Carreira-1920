package game;
import processing.IProcessingApp;
import processing.core.PApplet;

public class GameOfLife implements IProcessingApp {

	private static final int BOARD_X = 8;
	private static final int BOARD_Y = 8;

	private Board board;
	

	@Override
	public void setup(PApplet p) {
		board = new Board(BOARD_X, BOARD_Y, p.width/BOARD_X, p.height/BOARD_Y);
		board.fillInitialBoard(p);
	}

	@Override
	public void draw(PApplet p, float dt) {
		board.generate();

	}
	
	@Override
	public void mousePressed(PApplet p) {
		
	}

	@Override
	public void keyPressed(PApplet p) {
		
	}

	@Override
	public void mouseReleased(PApplet p) {
		// TODO Auto-generated method stub
		
	}

}
