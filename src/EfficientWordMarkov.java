import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov {
	private HashMap <WordGram, ArrayList<String>> myMap;

	public EfficientWordMarkov(int order) {
		super(order);
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}
	public EfficientWordMarkov() {
		this(3);
	}
	@Override
	public void setTraining(String text) {
		myMap.clear();
		myWords = text.split("\\s+");
		for(int i=0; i < myWords.length - myOrder + 1; i++) {
			WordGram wkey = new WordGram(myWords, i, i + myOrder);
			if(! myMap.containsKey(wkey)) {
				myMap.put(wkey, new ArrayList<String>());
			}
			if(i + myOrder == myWords.length) {
				myMap.get(wkey).add(PSEUDO_EOS);
			}
			else {
				String wNext = myWords[myOrder];
				myMap.get(wkey).add(wNext);
			}
		}
	}
	@Override
	public ArrayList<String> getFollows(WordGram key) {


		if(!myMap.containsKey(key)) {
			throw new NoSuchElementException(key+" not in map");
		}

		return myMap.get(key);


	}

}

