
public class demo {
	
	
	public static void main (String [] args){
		Maze mazeOne = new Maze("maze03.mz");
	
		System.out.print(mazeOne);
		
		//System.out.println(mazeOne.isPath(3,0));
		
		for (int i = 0; i < mazeOne.startPoint().length; i++)
			System.out.println( (mazeOne.startPoint())[i]);
		
		if ( mazeOne.traverseMaze( (mazeOne.startPoint())[0], (mazeOne.startPoint())[1])){
			System.out.print(mazeOne);
		}
	}	
}
