package textproc;

import java.util.TreeMap;
import java.util.Map;


public class MultiWordCounter implements TextProcessor {
	private Map<String, Integer> m;
	
	public MultiWordCounter(String[] words) {
		m = new TreeMap<String, Integer>();
		for(int i=0; i<words.length; i++) {
			m.put(words[i], 0);
		}
	}

	@Override
	public void process(String w) {
		if(m.containsKey(w)==true) {
			int x=m.get(w)+1;
			m.replace(w, m.get(w), x); //räcker med key och nya värdet
		}

	}

	@Override
	public void report() {
		System.out.println(m); //m.keyset, iterera genom dom, enhanced for-loop, innebär skillnad mellan TreeMap o HashMap. TreeMap långsammare. Hashmap mer minne än treemap
			
		}

	}


