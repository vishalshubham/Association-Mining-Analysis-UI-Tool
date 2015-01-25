package associationmininganalysis;

/**
 *
 * @author VISHAL
 */
public class FPTree
{
	ItemTb itemTb;
	Tree tree;
	FPTree()
        {
		tree=new Tree();
	}
	public void ConsItemTb(int num)
        {
		itemTb=new ItemTb(num);
	}
	
    class ItemTb
    {
    	private int[] count;
    	private String[] item;
    	private Node[] link;
    	private int len;
    	ItemTb(int num)
        {
    		count=new int[num];
    		item=new String[num];
    		link=new Node[num];
    		len=num;
    	}
    	void Insert(String item,int num,int count,Node node)
        {
    		if (len>num)
                {
    			this.item[num]=item;
    			this.count[num]=count;
    			this.link[num]=node;
    		}	
    	}
    	int ReadCount(int num)
        {
    		return count[num];
    	}
    	String ReadItem(int n)
        {
    		String name=item[n];
    		return name;
    	}
    	Node ReadNode(int num)
        {
    		return link[num];
    	}
    	void SetNode(Node node,int num)
        {
    		link[num]=node;
    	}
    	int Length()
        {
    		return len;
    	}
    	void CopyItemArray(String[] dest)
        {
    		System.arraycopy(item,0,dest,0,len);
    	}
    	void CopyCountArray(int[] dest)
        {
    		System.arraycopy(count,0,dest,0,len);
    	}
    	Item CopyItemArray()
        {
    		Item head=new Item("head");
    		Item item=head;
    		String name;
    		int count;
    		for(int i=0;i<len;i++){
    			name=ReadItem(i);
    			count=ReadCount(i);
    			item.next=new Item(name);
    			item.next.count=count;
    			item=item.next;
    		}
    		return head.next;
    	}
    	 public Node FindNode(String name)
         {
         	int i=0;
         	while(i<len)
                {
         	    if (name.equals(item[i]))
         	    	return link[i];
         	    i++;
         	}
         	return null;
         }
    }

    class Tree
    {
    	Node root;
    	Tree()
        {
    		root=new Node();
    	}  	
    }
    class Node
    {
    	String item;
    	int count;
    	Node bnode;
    	Node parent;
    	Cnode child;
    	Node()
        {
    		count=0;
    		item="root";
    		bnode=null;
    		parent=null;
    		child=null;//new Cnode();
    	}
    	Node(String name)
        {
    		count=1;
    		item=name;
    		bnode=null;
    		parent=null;
    		child=null;//new Cnode();
    	}
    	void IncCount(int num)
        {
    	count+=num;	
        }
    	
    	Node InsertNode(String item,int count)
        {
    		Cnode c;
    		Cnode temp=new Cnode();
    		Node d=new Node(item);
    		d.IncCount(count-1);
    		temp.link=d;
    		d.parent=this;
    		c=this.child;
    		if (c==null)
                {
    			this.child=temp;
    			return d;
    		}	
    		while(c.next!=null)
                {
    			c=c.next;
    		}	
    		c.next=temp;
    		return d;
    	}
    	
        public Node FindChild(Node d,String item)
        {
            String it;
        	Cnode c;
        	Node t;
        	c=d.child;
        	while(c!=null)
                {
        		t=c.link;
        		it=c.link.item;
        	    if (it.equals(item))
        		    return c.link;
        	    c=c.next;
        	   
        	}
        	return null;
        }
    	class Cnode{
    		Cnode next;
    		Node link;
    		Cnode(){
    			next=null;
    			link=null;
    		}
    	}
    }
}
