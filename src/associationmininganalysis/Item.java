package associationmininganalysis;

/**
 *
 * @author VISHAL
 */
class Item
{
	int count;
	String name;
	Item next;
	
	Item(String name)
        {
		count=1;
		next=null;
		this.name=name; 
	}
	public String toString()
        {
		return name;
	}
}
