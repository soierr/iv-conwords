/**
 * 
 */
package iv.conwords.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import iv.conwords.model.WordsHolder;

/**
 * @author SOIERR
 *
 */
public class CallableLetterProcessing implements Callable<CallablerResult>{
	
	private Character targetLetter = null;
	
	private Map<Character, WordsHolder> dictionary = null;
	
	private StringBuilder sb = new StringBuilder();
	
	private List<String> conWordsList = new ArrayList<String>();
	
	private boolean isSearchCompleted = false;
	
	/*
	 *Every callable task is aimed to process a proper range of words
	 *started from the same letter 
	 */
	public CallableLetterProcessing(Character target, Map<Character, WordsHolder> dictionary) {
		
		this.targetLetter = target;
		this.dictionary = dictionary;
	}
	
	public CallablerResult call() throws Exception {
		
		Map<String, Integer> map = dictionary.get(targetLetter).getMap();
		
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
		
		int maxCount = 0, count = 0;
		
		String targetString = null, firstConWord = null, secondConWord = null;		
		
		for(Map.Entry<String, Integer> entry : entrySet){
			
			targetString = entry.getKey();
			
			count = isConcatWord(targetString, entry.getValue(), dictionary);
			
			if(count > 0){
				
				maxCount++;
				conWordsList.add(targetString);
			}
			
		}
		
		if(!conWordsList.isEmpty()){
			
			Collections.sort(conWordsList, new Comparator<String>() {
				
				public int compare(String o1, String o2) {

					return o2.length() - o1.length();
				}
				
			});
			
			firstConWord = conWordsList.get(0);
			
			for(String s : conWordsList){

				if(s.length() < firstConWord.length()){
					
					secondConWord = s;
					break;
				}
			}
			
			return new CallablerResult(firstConWord, secondConWord, maxCount);
		}
    	
		return null;
	}
	
	/*package private for tests otherwise needs to be private*/
	int isConcatWord(String str, int len, Map<Character, WordsHolder> dictionary){

    	WordsHolder wHolder = dictionary.get(str.charAt(0));
    	int minWordLength = wHolder.getMinWordCount();
    	
    	sb.setLength(0);
		sb.append(str);
		
		String strBuf = null;
		
		wHolder = dictionary.get(str.charAt(0));
    	
		for(int i =  minWordLength; i < len; i++){
						
			strBuf = sb.substring(0, i);

			if(wHolder.getMap().containsKey(strBuf)){

				isConcatRecur(i, i, len, str, dictionary);
				
				if(isSearchCompleted){
					isSearchCompleted = false;
					return len;
				}
			}
			
    	}

		return 0;
	}
	
	
	private boolean isConcatRecur(int ci, int sum, int len, String str, Map<Character, WordsHolder> dictionary){
		
		if(isSearchCompleted){
			return true;
		}
		
		if(ci >= len){
			
			return false;
		}
		
    	String strBuf = null;
    	
    	WordsHolder wHolder = dictionary.get(str.charAt(ci));
		
    	int minWordLength = wHolder.getMinWordCount();
    	
    	if(minWordLength > len - ci || wHolder.getMap().isEmpty()){
    		
    		return false;
    	}
    	
    	int i = (ci+minWordLength)-1;
    	
    	Integer strBufLen = Integer.valueOf(0);
    	
		while((i < len) && !isSearchCompleted && sum + strBufLen != len){

			strBuf = sb.substring(ci, i+1);
			
			strBufLen = (wHolder.getMap().get(strBuf) == null) ? Integer.valueOf(0) : wHolder.getMap().get(strBuf);

			if(strBufLen.intValue() > 0){
				
				if((strBufLen+sum) == len){
					
					isSearchCompleted = true;
					return isSearchCompleted;
					
				}else{
					
					if (isConcatRecur(i+1, strBufLen+sum, len, str, dictionary)){
						return (isSearchCompleted = true);
					}
				}				
			}
			
			i++;
    	}
		
		return false;
	}
}
