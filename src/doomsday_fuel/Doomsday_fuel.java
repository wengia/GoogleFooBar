package doomsday_fuel;

import java.util.*;

/* Level 3 second question */
/* Passed test 1,2,7. */
public class Doomsday_fuel {
	public static int[] answer(int[][] m) {
		List<Ore> oreList = new ArrayList<>();
		Map<Integer, Ore> s = new HashMap<>();
		int commonDenominator = 1;

		// Initiate all state
		for(int i = 0; i < m.length; i++) {
			Ore current = s.get(i);
			if (current == null) {
				current = new Ore(i);
				s.put(i, current);
			}
			for(int j = 0; j < m[i].length; j++) {
				if (m[i][j] != 0) {
					current.originalDenominator += m[i][j];
					Ore nextState = s.get(j);
					if (nextState == null) {
						nextState = new Ore(j);
						s.put(j, nextState);
					}
					if (i != j) // Do not count itself
						current.originalNumerators.put(j, m[i][j]);
				}
			}
			
			// Add terminator to oreList
			if (current.originalDenominator == 0)
				oreList.add(current);
			else // Calculate common minimal denominator
				commonDenominator *= current.originalDenominator;
		}
		
		// Calculate all possibility
		Queue<Ore> candidates = new LinkedList<Ore>();
		candidates.add(s.get(0));
		do {
			Ore candidate = candidates.poll();
			for(Integer index: candidate.originalNumerators.keySet()) {
				Ore current = s.get(index);
				int preNumerator = candidate.originalNumerators.get(index);
				if (candidate.denominator == 0) { // State 0
					current.numerator = preNumerator;
					current.denominator = candidate.originalDenominator;
				} else {
					current.denominator = candidate.originalDenominator * candidate.denominator;
					current.numerator = candidate.numerator * preNumerator +
							current.numerator * candidate.originalDenominator; // // Add on last probability
				}
				
				// Put non-terminator into que
				if (current.originalDenominator != 0)
					candidates.add(current);
			}
			
		} while (!candidates.isEmpty() && candidates.peek().index != 0);
		
		
		// Simplify result
		int len = oreList.size();
		int[] res = new int[len + 1];
		for(int i = 0; i < len; i++) {
			Ore current = oreList.get(i);
			if (current.denominator == 0)
				res[i] = 0;
			else
				res[i] = current.numerator * commonDenominator / current.denominator;
			res[len] += res[i];
		}
		
		// Simplify res to ensure the numbers do not have common divider
		eliminateMutualDivider(res);
		
		return res;
    }
	
	private static void eliminateMutualDivider(int[] nums) {
		int gcd = nums[0];
	    for(int i = 1; i < nums.length; i++)
	    	gcd = greatestCommonDivisor(gcd, nums[i]);
	    
	    if (gcd <= 1) return;
	    for(int i = 0; i < nums.length; i++)
	    	nums[i] /= gcd;
		
	}
	
	private static int greatestCommonDivisor(int a, int b) {
	    while (b > 0)
	    {
	        int temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}
	
	private static class Ore {
		Integer index;
		int denominator = 0;
		int numerator = 0;
		int originalDenominator = 0;
		Map<Integer, Integer> originalNumerators = new HashMap<>(); // key - index, value - next state numerator
		
		private Ore(Integer idx) {
			this.index = idx;
		}
	}
}
