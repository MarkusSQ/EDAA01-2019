package textproc;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.*;

public class GeneralWordCounter implements TextProcessor {
	private Map<String, Integer> m;
	private Set<String> stopwords;
	
	public GeneralWordCounter(Set<String> stopwords) {
		this.stopwords = stopwords;
		m = new TreeMap<String, Integer>();
		
	}

	@Override
	public void process(String w) {
		if(!stopwords.contains(w)) { //! framför inverterar
			if(m.containsKey(w)) {
				int x = m.get(w)+1;
				m.replace(w, m.get(w), x);
			} else {
				m.put(w, 1);
			}
			
		}

	}

	@Override
	public void report() {
		/*for(Map.Entry<String, Integer> entry : m.entrySet()) {
			if(entry.getValue()>200) {
				System.out.println(entry.getKey()+ " "+ entry.getValue());
			}
		}*/
		Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		//System.out.println(wordList);
		wordList.sort(new WordCountComparator()); //använder samma objekt hela tiden. Integer har comparator redan och behöver inte detta, däremot inte Map.Entry<String, Integer>
		for(int i =0; i<4; i++) {
			System.out.println(wordList.get(i));
		}

	}
	
	//lab 3
	public Set<Map.Entry<String, Integer>> getWords() {
		return m.entrySet();
	}

}
