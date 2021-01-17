package map;

public class SimpleHashMap<K, V> implements Map<K, V> { // Se til att byta lista med vektor o vice versa
	Entry<K, V>[] table;
	double loadFactor;
	double currentLoad;
	SimpleHashMap<K, V> map;
	int entrys;

	public static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			return this.value = value;
		}

		@Override
		public String toString() {
//			StringBuilder s = new StringBuilder();
//			s.append(this.getKey());
//			s.append(" = ");
//			s.append(this.getValue());
//			return s.toString();
			return key + " = " + value;
		}

	}

	/** Returnerar index som ska användas för nyckeln key. */
	private int index(K key) {
		int index =key.hashCode() % table.length;
		
		return Math.abs(index);
	}

	/**
	 * Returnerar det Entry-par som har nyckeln key i listan som finns på position
	 * index i tabellen. Om det inte finns returneras null
	 */
	private Entry<K, V> find(int index, K key) {
		if (table[index] == null) {
			return null;
		}
		if (table[index].getKey().equals(key)) {
			return table[index];
		}
		Entry<K,V> temp = table[index];
		while (temp.next != null) {
			temp = temp.next;
			if (temp.getKey().equals(key)) {
				return temp;
			}
		}
		return null;
	}

	/** Dubblar listans kapacitet */
	private void rehash() {
		Entry<K, V>[] info = table;
		table=(Entry<K, V>[]) new Entry[table.length * 2];
		entrys = 0;
		double tempLoad = currentLoad;
		currentLoad = 0;
		for (int i = 0; i < info.length; i++) {
			Entry<K, V> var = info[i];
			if(var !=null) {
				put(var.key,var.value);

					//table[index(var.key)] = var;
				
				while(var.next != null) {
					put(var.next.key,var.next.value);
					//table[index(var.next.key)] = var.next;
					var = var.next;
				}
				
			}
		
		}
		currentLoad = tempLoad/2;
	}

	/**
	 * Constructs an empty hashmap with the default initial capacity (16) and the
	 * default load factor (0.75).
	 */
	public SimpleHashMap() {
		loadFactor = 0.75;
		table = (Entry<K, V>[]) new Entry[16];
		entrys = 0;
		currentLoad = 0;
	}

	/**
	 * Constructs an empty hashmap with the specified initial capacity and the
	 * default load factor (0.75).
	 */
	public SimpleHashMap(int capacity) {
		// map = new SimpleHashMap(capacity);
		loadFactor = 0.75;
		table = (Entry<K,V>[]) new Entry[capacity];
		entrys = 0;
		currentLoad = 0;
	}

	/** Skriver en sträng med innehållet i table, en rad per position. */
	public String show() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) {
				return null;
			}
			Entry<K, V> temp = table[i];
			System.out.print(i + "    " + temp.toString());
			
			while (temp.next != null) {
				temp = temp.next;
				System.out.print("     " + temp.toString());
			}
			
			System.out.println("");
		}
		return null;
	}

	@Override
	public V get(Object obj) { 
		K key = (K) obj;
		Entry<K, V> temp = find(index(key), key);
		
		if (temp != null) {
			if (temp.key.equals(key)) {
				return temp.value;
			}
			while (temp.next != null) {
				if (temp.next.key.equals(key)) {
					return temp.next.value;
				}
				temp = temp.next;
			}
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i <= size(); i++) {
			if (table[i] != null) {
				return false;
			}
		}
		return true;
	}
	
	public double currentLoad() { //till för test
		return currentLoad;
	}
	
	public int length() { //också till för test
		return table.length;
	}

	private boolean hasNext(Entry<K, V> e) {
		if (e.next != null) {
			return true;
		}
		return false;
	}

	@Override
	public V put(K key, V value) {
		Entry<K,V> old = find(index(key),key);
		
		if( old != null) {
			V v = old.value;
			old.key = key;
			old.value = value;
			return v;
		}
		
		
		
		Entry<K, V> sameIndex = table[index(key)]; 
		table[index(key)] = new Entry<K, V>(key, value);
		entrys++;
		
		if (sameIndex != null) {
			table[index(key)].next = sameIndex;
			return sameIndex.value;
		}
		
		currentLoad = currentLoad + (double) 1/table.length;
		
		if (currentLoad >= 0.75) {
			rehash();
		}
		return null;
	}

	@Override
	public V remove(Object arg0) {
		K key = (K) arg0;
		
		
		Entry<K,V> temp = find(index(key),key);
		Entry<K,V> atIndex = table[index(key)];
		
		if(temp == null) {
			return null;
		}
		entrys--;
		V value = temp.value;
		if(atIndex.equals(temp)) {
			if(temp.next == null) {
				table[index(key)] = null;
				
				return value;
			}else {
				table[index(key)] = temp.next;
				
				return value;
			}	
		}
		while(atIndex.next != temp) {
			atIndex = atIndex.next;
		}
		if(temp.next == null) {
			atIndex.next = null;
		
			return value;
		}
		if(temp.next != null) {
			atIndex.next = temp.next;
			temp.next = null;	
			return value;
		}
		table[index(key)] = null;
		return temp.value;
		
	}

	@Override
	public int size() {
		return entrys;
	}

}
