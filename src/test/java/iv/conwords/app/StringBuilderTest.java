/**
 * 
 */
package iv.conwords.app;

import org.junit.Test;

/**
 * @author SOIERR
 *
 */
public class StringBuilderTest {
	
	
	@Test
	public void sbTest(){
		
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		sb.append("Sergey");
		System.out.println(sb);
		sb.setLength(0);
		sb.append("Sergey2");
		System.out.println(sb);
				
	}

}
