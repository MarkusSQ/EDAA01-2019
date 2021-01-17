package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {
	

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<TextProcessor> tP = new ArrayList<TextProcessor>();
		TextProcessor p = new SingleWordCounter("nils");
		TextProcessor q = new SingleWordCounter("norge");
		
		TextProcessor mw = new MultiWordCounter(REGIONS);
		
		
		
		tP.add(p);
		tP.add(q);
		
		Scanner d = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while (d.hasNext()) {
			stopwords.add(d.next());
		}
		
		TextProcessor gw = new GeneralWordCounter(stopwords);

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			
			for(int i =0; i<tP.size(); i++) {
				tP.get(i).process(word);
			}
			mw.process(word);
			gw.process(word);
			
		}

		s.close();
		d.close();
		
		for(int i=0; i<tP.size(); i++) {
			tP.get(i).report();
			
		}
		long t0 = System.nanoTime();
		mw.report();
		gw.report();
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1-t0) / 1000000.0 + "ms");
	}
	
}