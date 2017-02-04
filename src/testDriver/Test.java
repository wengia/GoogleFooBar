package testDriver;

import doomsday_fuel.Doomsday_fuel;

public class Test {
	public static void main(String args[]) {
		int[][] m = new int[][]{
			{1, 1, 0, 0, 0, 1}, 
			{4, 1, 0, 3, 2, 0}, 
			{0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0}, 
			{0, 0, 0, 0, 0, 0}};
			
		
		int[] res = Doomsday_fuel.answer(m);
		for(int r : res)
			System.out.print(r + " ");
	}
}
