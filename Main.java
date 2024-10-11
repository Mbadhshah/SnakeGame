import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
	
		JFrame frame = new JFrame("Snake Game");
		GameBoard gameBoard = new GameBoard();
		
		
		
		
		frame.add(gameBoard);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	
	}

}
