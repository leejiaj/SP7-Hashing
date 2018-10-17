package lxj171130;

public class CuckooHashing {
	
	int tableNum;
	int maxLocations;
	int numHashFunctions;
	int[][] table;
	int minIntValue = Integer.MIN_VALUE;
	int threshold;
	
	
	public CuckooHashing() {
		tableNum = 1;
		maxLocations = 100;
		numHashFunctions = 2;
		threshold = maxLocations;
		table = new int[tableNum][maxLocations];
		for(int i=0; i<tableNum; i++) {
			for(int j=0; j<maxLocations; j++) {
				table[i][j] = minIntValue;
			}
		}
	}
	
	private int hashCode(int i, int key) {
		switch(i) {
			case 1:
				return key % maxLocations;
			default:
				return (key / maxLocations) % maxLocations;
		}
	}
	
	public boolean add(int x) {
		for(int i=1; i<=numHashFunctions; i++ ) {
			if(table[0][hashCode(i,x)] == x) {
				return false;
			}
		}
		for(int i=1; i<=numHashFunctions; i++ ) {
			if(table[0][hashCode(i,x)] == minIntValue) {
				table[0][hashCode(i,x)] = x;
				return true;
			}
		}
		int i = 1;
		int count = 1;
		while(count++ < threshold) {
			int location = hashCode(i,x);
			if(table[0][location] == minIntValue) {
				table[0][location] = x;
				return true;
			}
			else {
				int temp = x;
				x = table[0][location];
				table[0][location] = temp;
			}
			i = i == numHashFunctions ? 1 : (i+1);
			count++;
		}
		System.out.println("Rebuild hash table with new hash functions");
		return false;
	}
	
	public boolean contains(int x) {
		for(int i=1; i<=numHashFunctions; i++ ) {
			if(table[0][hashCode(i,x)] == x) {
				return true;
			}
		}
		return false;
	}
	
	public Object remove(int x) {
		if(contains(x)) {
			for(int i=1; i<=numHashFunctions; i++ ) {
				if(table[0][hashCode(i,x)] == x) {
					table[0][hashCode(i,x)] = minIntValue;
					return x;
				}
			}
		}
		else {
			return null;
		}
		return null;
	}

	public static void main(String[] args) {
		CuckooHashing ch = new CuckooHashing();
		System.out.println(ch.add(5));
		System.out.println(ch.add(8));
		System.out.println(ch.add(2));
		System.out.println(ch.contains(5));
		System.out.println(ch.add(5));
		System.out.println(ch.remove(5));
		System.out.println(ch.add(9));
		System.out.println(ch.contains(9));
		System.out.println(ch.contains(0));	
		
	}

}
