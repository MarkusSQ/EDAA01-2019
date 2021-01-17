package map;

import java.util.Random;

public class testStuff {
	static boolean a = true;
	public static void main(String[] args) {
		SimpleHashMap map = new SimpleHashMap(10);
		for(int i=0; i<10; i++) {
			if(a == true) {
				map.put(-i, -i);
				a = false;
			}else {
				map.put(i, i);
				a = true;
			}
			
		}
		map.show();
		System.out.println(map.size());
		System.out.println(map.currentLoad());
		System.out.println(map.length());
		
		for(int i = 10; i<20; i++) {
			map.put(i, i);
		}
		System.out.println(map.length());
		map.show();
		
		
	}
	
	

}
