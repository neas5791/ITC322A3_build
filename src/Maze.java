import java.util.Observable;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;



public class Maze extends Observable {
	
	private ArrayBag<char[]> maze;
	private int row;
	private int col;
	private int[] start;
	
	public Maze(String filename){
		// Opens maze file and builds Maze array object
		openMazeFromFile(filename);
		// reads the maze array object and sets the first 
		// entry point as start array. 
		start = startPoint();
	}
	
	public int getRow()  { return row; }
	
	public int getColumn()  { return col;  }
	
	public Boolean isExit(int r, int c){
		return ( ((r == 0 || r == row - 1) || (c == 0 || c == col - 1)) && ((r != start[0] && c != start[1])) );
	}

	public Boolean isPath(int r, int c){
		//System.out.println(r + ", " + c);
		//System.out.print((r >= 0 || r < row));
		//System.out.println(" + " + (c >= 0 || c < col ));
		
		if ( (r >= 0 && r < row) && (c >= 0 && c < col ) ){ 
			char test = (char) (maze.getObject(r))[c];
			System.out.println(r + ", " + c);
			System.out.println("Whitespace test = " + Character.isWhitespace(test));
			System.out.println("Been here test = " + (test == '*'));
			
			
			//return (Character.isWhitespace(test) || test != '*' );
			return (Character.isWhitespace(test));
		}		
		//throw new IllegalArgumentException ("The cell to be tested doesn't exist!");
		
		return false;
	}
	
	public Boolean beenHere(int r, int c){
		/*
		if ( !(r >= 0 || r < row) && !(c >= 0 || c < col ) ) 
			throw new IllegalArgumentException ("The cell to be tested doesn't exist!");
		
		char test = (char) (maze.getObject(r))[c];

		return (test == '*');
		*/
		return ((char) (maze.getObject(r))[c] == '*');
	}
	
	public String toString(){
		String screen = "";
		char[] mazeLine;
		
		for (int i = 0; i < row; i++){
			mazeLine = maze.getObject(i);
			for(int j = 0; j <col; j++){
				if (screen.isEmpty())
					screen = ((Character) mazeLine[j]).toString();
				else
					screen = screen + ((Character) mazeLine[j]).toString();
			}
			screen = screen + "\n";
		}
				
		return screen;
	}

	private void openMazeFromFile(String filename){
		Scanner scanner;
		String line;
		
		try {
			// Opens the maze file
			scanner = new Scanner(new FileInputStream(filename));
			
			// reads the first line information which contains the maze dimensions and populates the
			// private object variables row and col.
			this.row = (scanner.nextInt() * 2) + 1;
			this.col = (scanner.nextInt() * 2) + 1;
			
			// creates an Arraybag to manage the maze
			this.maze = new ArrayBag<char[]>(row);
			
			// moves the scanner to the next line of the maze file
			scanner.nextLine();
			
			// begins to read line by line the maze file
			// converting each line to character array which is added to the maze Arraybag
			while (scanner.hasNextLine()){
				line = scanner.nextLine();
				
				// checks for string lines that are not the correct length
				// ie these could be openings
				while(line.length() != col){
					line = line + " ";
				}
				// adds the whole line from the file as string array
				this.maze.add(line.toCharArray());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public int[] startPoint(){
		int [] mazeEntry;
		
		// check top row
		for (int j = 0; j < col; j++){
			if (isPath(0,j)){
				mazeEntry = new int[]{0,j};
				return mazeEntry;
			}
		}
		// check left and right side
		for (int j = 0; j < row; j++){
			if (isPath(j,0)){
				mazeEntry = new int[]{j,0};
				return mazeEntry;
			}
			if (isPath(j,col-1)){
				mazeEntry = new int[]{j,col-1};
				return mazeEntry;
			}			
		}
		//check bottom row
		for (int j = 0; j < col; j++){
			if (isPath(row,j)){
				mazeEntry = new int[]{row,j};
				return mazeEntry;
			}
		}
		
		return null;
	}

	public Boolean traverseMaze(int r, int c){
		// Checks if this is an exit point of the maze
		if(isExit(r,c))
			return true;
		
		// check cell to the right
		if (isPath(r , c + 1)) {
			(maze.getObject(r))[c+1] = '*';
			if ( traverseMaze(r , c + 1)){ return true; }
			(maze.getObject(r))[c+1] = ' ';
		}

		// check cell to the left
			if (isPath(r , c - 1)) {
				(maze.getObject(r))[c - 1] = '*';
				if ( traverseMaze(r , c - 1)){ return true; }
				(maze.getObject(r))[c - 1] = ' ';
			}
		
		// check cell to above
			if (isPath(r - 1 , c)) {
				(maze.getObject(r - 1))[c] = '*';
				if ( traverseMaze(r - 1 , c)){ return true; }
				(maze.getObject(r - 1))[c] = ' ';
			}
		
		// check cell to below
			if (isPath(r + 1 , c)) {
				(maze.getObject(r + 1))[c] = '*';				
				if ( traverseMaze(r + 1 , c)) {return true;}
				(maze.getObject(r + 1))[c] = ' ';
			}
		
//		System.out.print(this.toString());
//		System.out.println("*****************************************************");
		return false;
	}
	
	/*
	public boolean escape(int x, int y) {
	    if(beenHere(x,y)) return false; // make isVisited method
	    if(isExit(x,y)) return true;
	    
	    if(x < 0 || y < 0 || x > row || y > col) return false;
	    if(!isPath(x,y)) return false; // make isWall method


	    if(escape(x+1,y) || escape(x-1,y) || escape(x,y+1) || escape(x,y-1)) {
	        System.out.println(x + ", " + y);
	        return true;
	    }
	    return false;
	}
	*/
}
