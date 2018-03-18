package iv.conwords.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import iv.conwords.model.WordsHolder;

public class App{
	
	private static final String FILE_NAME = "words.txt";
	
	private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
    public static void main(String[] args ) throws Exception{
    	
    	Map<Character, WordsHolder> dictionary = loadWords();	
    	
    	ExecutorService es = Executors.newFixedThreadPool(ALPHABET.length);
    	
    	Future<CallablerResult> future = null;
    	
    	List<Future<CallablerResult>> listFuture = new ArrayList<>();
    	
    	for(char aLetter : ALPHABET){
    		
    		future = es.submit(new CallableLetterProcessing(aLetter, dictionary));
    		listFuture.add(future);
    	}
    	
    	es.shutdown();
    	
    	while(!es.isShutdown()){}
    	
    	List<String> list = new ArrayList<>();
    	
    	int conWordsCount = 0;
    	
    	for(Future<CallablerResult> f : listFuture){

    		list.add(f.get().getConWordFirst());
    		list.add(f.get().getConWordSecond());
    		conWordsCount = conWordsCount + f.get().getConWordTotal();
    	}
    	
    	Collections.sort(list, new Comparator<String>() {
    		

    		@Override
    		public int compare(String o1, String o2) {
    			return o2.length() - o1.length();
    		}
		});
    	
    	System.out.printf("\nFirst largest concatenated word:  \"%s\"", list.get(0));
    	System.out.printf(" Length: %s\n\n", list.get(0).length());
    	
    	System.out.printf("Second largest concatenated word: \"%s\"", list.get(1));
    	System.out.printf("   Length: %s\n\n", list.get(1).length());
    	
    	System.out.printf("Concatenated words found: %s\n", conWordsCount);
    }
    
    private static Map<Character, WordsHolder> loadWords() throws IOException{
    	
    	BufferedReader br = new BufferedReader(new FileReader(new File(FILE_NAME)));
    	
    	String stringLine;
    	char dictionaryLetter = 0;
    	int minCount = 0;
    	
    	Map<Character, WordsHolder> dictionary = new HashMap<Character, WordsHolder>();
    	
    	WordsHolder wordsHolder = null;
    	
    	for(char aLetter : ALPHABET){
    		
    		dictionary.put(aLetter, new WordsHolder());
    	}
    	
    	while((stringLine = br.readLine())!=null){
    		
    		if(!stringLine.isEmpty()){    			
    			dictionaryLetter = stringLine.charAt(0);    			
    		}else{
    			continue;
    		}
    		
    		wordsHolder = dictionary.get(dictionaryLetter);
    		
    		if((minCount = stringLine.length()) < wordsHolder.getMinWordCount()){

    			wordsHolder.setMinWordCount(minCount);
    		}
    		
    		wordsHolder.addWord(stringLine);
    	}
    	
    	br.close();
    	
    	return dictionary;
    	
    }

}
