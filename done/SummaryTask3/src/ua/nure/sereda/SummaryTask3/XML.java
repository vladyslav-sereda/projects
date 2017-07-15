package ua.nure.sereda.SummaryTask3;

/**
 * Holds entities declared in XSD document.
 */
public enum XML {
	CARDS("Cards"),
	OLDCARD("OldCard"),
	THEMA("Thema"),
	TYPE("Type"),
		SEND("Send"), TYPENAME("TypeName"),
	COUNTRY("Country"),
	YEAR("Year"),
	AUTHOR("Author"),
	VALUABLE("Valuable");

	private String value;

	XML(String value) {
		this.value = value;
	}
	
	/**
	 * Determines if a name is equal to the string value wrapped by this enum element.<br/>
	 * If a SAX/StAX parser make all names of elements and attributes interned you can use
	 * <pre>return value == name;</pre> instead <pre>return value.equals(name);</pre>
	 * @param name string to compare with value. 
	 * @return value.equals(name)
	 */
	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}
}
