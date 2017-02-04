package questions;

/* Level 3 first question */
public class The_grandest_staircase_of_them_all {
	public static int answer(int n) {
		return countFutureSteps(n, 0);
	}
	
	private static int countFutureSteps(int n, int prev) {
		int sum = 0;
		for(int current = prev + 1; current < n - current; current++) {
			sum += (1 + countFutureSteps(n - current, current));
		}
		
		return sum;
	}
}
