import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class EfficientMarkov extends BaseMarkov {
	/*
	 * Initializes a Hashmap to use to store the characters based
	 * on the markov order.
	 */
	private HashMap <String, ArrayList<String>> myMap;
	/*
	 * Makes a parent class and initilizes the hashmap myMap.
	 */
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<String, ArrayList<String>>();
	}
	/*
	 * Sets the Markov order to 3
	 */
	public EfficientMarkov() {
		this(3);
	}
	/*
	 * adds keys and values to the hashmap. 
	 * keys are a number of characters based on the order number
	 * and the loop adds a number of characters to the key then
	 * adds the next three letters as the proceeding key. (abc to
	 * bcd). The values are the characters within each key split. 
	 */
	@Override
	public void setTraining(String text) {
		myText = text;
		myMap.clear();
		for(int i=0; i < myText.length() - myOrder + 1; i++) {
			String wkey = myText.substring(i, i + myOrder);
			if(! myMap.containsKey(wkey)) {
				myMap.put(wkey, new ArrayList<String>());
			}
			if(i + myOrder == text.length()) {
				myMap.get(wkey).add(PSEUDO_EOS);
			}
			else {
				String wNext = myText.substring(i + myOrder, i + myOrder + 1);
				myMap.get(wkey).add(wNext);
			}
		}
	}
	/*
	 * Returns the value of the key if the key is in the hashmap
	 */
	@Override
	public ArrayList<String> getFollows(String key) {
		if(! myMap.containsKey(key)) {
			throw new NoSuchElementException(key+" not in map");
		}

		return myMap.get(key);


	}

}
