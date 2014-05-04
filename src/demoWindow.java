import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;


public class demoWindow {
	
	private void initUI(){
		
		String filename = "maze01.mz";
		Maze mazeOne = new Maze(filename);

		JTextArea maze = new JTextArea(mazeOne.getRow(), mazeOne.getColumn());
		maze.setFont(new Font("monospaced", Font.PLAIN, 12));
		maze.setText(mazeOne.toString());

		JFrame frame = new JFrame("This is a.....MAZE....ing :)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//JButton startButton = new JButton("Start");//The JButton name.
		//add(startButton);//Add the button to the JFrame.
		//startButton.addActionListener(this);//Reads the action.
		
		
		if (filename == "maze01.mz") {
			maze.setText(mazeOne.toString());
			maze.setFont(new Font("monospaced", Font.PLAIN, 12));
			Dimension d = new Dimension(200,200);
			frame.setPreferredSize(d);
			//JOptionPane.showMessageDialog(null, maze, "This is a.....MAZE....ing :)", JOptionPane.PLAIN_MESSAGE);
			frame.add(maze);
		} else {

			frame.add(new JScrollPane(maze));
		}
			frame.pack();
			frame.setVisible(true);
	}
	
	
	public static void main (String [] args) {
		SwingUtilities.invokeLater(new Runnable() {
		        @Override
		        public void run() {
		            new demoWindow().initUI();
		        }
			});
		/* 
		 * Maze mazeOne = new Maze("maze02.mz");
		 * JTextArea maze = new JTextArea(mazeOne.getRow(), mazeOne.getColumn());
		 * maze.setText(mazeOne.toString());
		 * maze.setFont(new Font("monospaced", Font.PLAIN, 12));
		 * JOptionPane.showMessageDialog(null, maze, "This is a.....MAZE....ing :)", JOptionPane.PLAIN_MESSAGE);
		 * */
	}
}	
		

