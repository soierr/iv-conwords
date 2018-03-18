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
	
	/*Empirically obtained value. We know for sure
	 * that there is definitely a word with less length in every range
	 * of dictionary for "a" is "aa" that is 2, so that we rewrite this value for sure
	 * during dictionary processing
	 * */
	private final int MIN_WORD_COUNT = 32; 
	
	private int minWordCount = MIN_WORD_COUNT;
	
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
