/**
* 
*/
package enums;

/**
 * @author pancham.gupta
 *
 */
public enum PropertyKeys {
	HOST("host"), PORT("port"), SSL("ssl"), USERNAME("user"), PASSWORD("password");
	private String key = null;

	private PropertyKeys(String key) {
		this.key = key;
	}

	public String getPropertyKey() {
		return this.key;
	}
}