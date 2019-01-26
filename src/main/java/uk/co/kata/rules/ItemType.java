package uk.co.kata.rules;
/**
 * 
 * 
 * @author Dave Potts
 * 
 * Definition of a name type.  Currently its a single digit string, but if this changes
 * this class will name to be updated.
 *
 */
public class ItemType {
	String itemName;

	public ItemType(final String itemName) {
		this.itemName = itemName;

	}

	/**
	 * @return the itemName
	 */
	public final String getItemName() {
		return itemName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ItemType [itemName=" + itemName + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemType other = (ItemType) obj;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		return true;
	}

}
