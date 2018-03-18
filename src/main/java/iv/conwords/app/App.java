package iv.conwords.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    	
    	for(char c : ALPHABET){
    		
    		future = es.submit(new CallableLetterProcessing(c, dictionary));
    		listFuture.add(future);
    	}
    	
    	es.shutdown();
    	
    	while(!es.isShutdown()){
    		
    	}
    	
    	String maxString = "";
    	
    	for(Future<CallablerResult> f : listFuture){

    		if(maxString.length() < f.get().getConWordFirst().length()){
    			maxString = f.get().getConWordFirst();
    		}
    	}
    	
    	System.out.println(maxString);
    	
    }
    
    private static Map<Character, WordsHolder> loadWords() throws IOException{
    	
    	BufferedReader br = new BufferedReader(new FileReader(new File(FILE_NAME)));
    	
    	String s;
    	char tc = 0;
    	int minCount = 0;
    	
    	Map<Character, WordsHolder> dictionary = new HashMap<Character, WordsHolder>();
    	
    	for(char nc : ALPHABET){
    		
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
    	
    	return dictionary;
    	
    }

}
