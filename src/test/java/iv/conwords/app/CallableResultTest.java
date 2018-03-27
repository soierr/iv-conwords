package iv.conwords.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import iv.conwords.app.CallableLetterProcessing;
import iv.conwords.model.WordsHolder;

/**
 * Unit test for simple App.
 */
public class CallableResultTest{
	
	Map<Character, WordsHolder> dictionary = new HashMap<Character, WordsHolder>();
	
	@Before
	public void prepareDictionary() throws IOException{
		
    	BufferedReader br = new BufferedReader(new FileReader(new File("words.txt")));
    	
    	String s;
    	char tc = 0;
    	int minCount = 0;
    	
    	char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    	
    	for(char nc : alphabet){
    		
    		dictionary.put(nc, new WordsHolder());
    	}
    	
    	while((s = br.readLine())!=null){
    		
    		if(!s.isEmpty()){    			
    			tc = s.charAt(0);    			
    		}else{
    			continue;
    		}
    		
    		if((minCount = s.length()) < dictionary.get(tc).getMinWordCount()){

    			dictionary.get(tc).setMinWordCount(minCount);
    		}
    		
    		dictionary.get(tc).addWord(s);
    	}
    	
    	br.close();
		
	}
	
	@Test
	public void callTest(){
		
		CallableLetterProcessing callable = new CallableLetterProcessing('e', dictionary);		
		int i = callable.isConcatWord("ethylenediaminetetraacetates", "ethylenediaminetetraacetates".length(), dictionary);
		System.out.println(i);
		
	}

}
