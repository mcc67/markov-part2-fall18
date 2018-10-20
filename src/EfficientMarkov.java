import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class EfficientMarkov extends BaseMarkov {
	private HashMap <String, ArrayList<String>> myMap;

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<String, ArrayList<String>>();
	}
	public EfficientMarkov() {
		this(3);
	}
	@Override
	public void setTraining(String text) {
		myText = text;
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
	@Override
	public ArrayList<String> getFollows(String key) {
		if(!myMap.containsKey(key)) {
			throw new NoSuchElementException(key+" not in map");
		}

		return myMap.get(key);


	}

}
