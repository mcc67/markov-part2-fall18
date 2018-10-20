import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov {
	/*
	 * Initializes a Hashmap to use to store the characters based
	 * on the markov order.
	 */
	private HashMap <WordGram, ArrayList<String>> myMap;
	/*
	 * Makes a parent class and initilizes the hashmap myMap.
	*/
	public EfficientWordMarkov(int order) {
		super(order);
		myMap = new HashMap<WordGram, ArrayList<String>>();
	}
	/*
	 * Sets the Markov order to 2
	 */
	public EfficientWordMarkov() {
		this(2);
	}
	/*
	 * adds keys and values to the hashmap. 
	 * Functions the same way as setTraining for EfficientMarkov
	 * but has a wordgram as the key instead of a string
	 */
	@Override
	public void setTraining(String text) {
		myMap.clear();
		myWords = text.split("\\s+");
		for(int i=0; i < myWords.length - myOrder + 1; i++) {
			WordGram wkey = new WordGram(myWords, i, myOrder);
			if(!myMap.containsKey(wkey)) {
				myMap.put(wkey, new ArrayList<String>());
			}
			if(i + myOrder == myWords.length) {
				myMap.get(wkey).add(PSEUDO_EOS);
			}
			else {
				String wNext = myWords[i+myOrder];
				myMap.get(wkey).add(wNext);
			}
		}
	}
	/*
	 * Returns the value of the key if the key is in the hashmap
	 */
	@Override
	public ArrayList<String> getFollows(WordGram key) {


		if(!myMap.containsKey(key)) {
			throw new NoSuchElementException(key+" not in map");
		}

		return myMap.get(key);


	}

}

