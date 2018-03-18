/**
 * 
 */
package iv.conwords.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SOIERR
 *
 */
public class WordsHolder {
	
	private int minWordCount = 16;
	
	private Map<String, Integer> map = new HashMap<>();

	/**
	 * @return the minWordCount
	 */
	public int getMinWordCount() {
		return minWordCount;
	}

	/**
	 * @param minWordCount the minWordCount to set
	 */
	public void setMinWordCount(int minWordCount) {
		this.minWordCount = minWordCount;
	}

	/**
	 * @return the set
	 */
	public Map<String, Integer> getMap() {
		return map;
	}
	
	public void addWord(String word){
		map.put(word, word.length());
	}
	
}
