/**
 * 
 */
package iv.conwords.app;

/**
 * @author SOIERR
 *
 */
public class CallablerResult {
	
	private String conWordFirst = null;
	
	private String conWordSecond = null;
	
	private int conWordTotal = 0;
	
	/**
	 * 
	 */
	public CallablerResult(String conWordFirst, String conWordSecond, int conWordTotal) {
		
		this.conWordFirst = conWordFirst;
		this.conWordSecond = conWordSecond;
		this.conWordTotal = conWordTotal;
	}

	/**
	 * @return the conWordFirst
	 */
	public String getConWordFirst() {
		return conWordFirst;
	}

	/**
	 * @return the conWordSecond
	 */
	public String getConWordSecond() {
		return conWordSecond;
	}

	/**
	 * @return the conWordTotal
	 */
	public int getConWordTotal() {
		return conWordTotal;
	}

}
