/**
* 
*/
package enums;

/**
 * @author pancham.gupta
 *
 */
public enum PropertyKeys {
	HOST("HOST"), PORT("PORT");
	private String key = null;

	private PropertyKeys(String key) {
		this.key = key;
	}

	public String getPropertyKey() {
		return this.key;
	}
}